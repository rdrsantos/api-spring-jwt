package com.rdr.blog.configs.security;

import com.rdr.blog.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigs extends WebSecurityConfigurerAdapter {

    private final AuthService authService;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public SecurityConfigs(AuthService authService, PasswordEncoder encoder, TokenService tokenService, UserRepository userRepository) {
        this.authService = authService;
        this.encoder = encoder;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Configurations for authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(encoder);
    }

    //Configuration for authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user/**/posts").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new AuthTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
    }

    //Configuration for static resources
    @Override
    public void configure(WebSecurity web) throws  Exception {
    }
}
