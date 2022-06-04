package com.item.config;

import com.item.domain.user.Role;
import com.item.web.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import static com.item.domain.user.Role.*;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final AuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/", "/login", "/users/add").permitAll()
                .antMatchers("/items/add").hasRole(ROLE_SELLER.name())
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureHandler(failureHandler);

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
