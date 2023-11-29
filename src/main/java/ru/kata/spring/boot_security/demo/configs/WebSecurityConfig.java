package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.kata.spring.boot_security.demo.Service.UserService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;

    private final UserService userService;

    private final PasswordConfig passwordConfig;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserService userService, PasswordConfig passwordConfig) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
        this.passwordConfig = passwordConfig;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER","ADMIN" )
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler)
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }
    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordConfig.getPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }
}
