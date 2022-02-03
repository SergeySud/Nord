package com.example.nord;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    final UserDetailsService userDetailsService;
//
//    @Bean
//    public UserDetailsService UserDetails() {
//        return super.userDetailsService();
//    }

//    @Autowired
//    public SecurityConfiguration(UserDetailsService UserDetails) {
//        this.userDetailsService = UserDetails;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(getPasswordEncoder());

        auth.inMemoryAuthentication()
                .withUser("admin").password(getPasswordEncoder().
                        encode("admin")).roles("ADMIN")
                .and()
                .withUser("user").password(getPasswordEncoder().encode("user")).roles("USER")
                .and()
                .passwordEncoder(getPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/all").authenticated()
                .mvcMatchers("/delete/**").authenticated()
                .mvcMatchers("/**").permitAll()
                .and().httpBasic().and().formLogin().and().csrf().disable().headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}