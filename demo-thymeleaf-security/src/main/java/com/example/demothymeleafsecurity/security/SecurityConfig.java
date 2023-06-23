package com.example.demothymeleafsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true //(role allowed)
)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] PUBLIC_ROUTE ={"/", "/login","/css/**","/js/**","/image/**"};
        http
                .authorizeHttpRequests((authz) -> authz
                        //Phân quyền bằng cấu hình tập trung
                        .requestMatchers(PUBLIC_ROUTE).permitAll()
//                        .requestMatchers("/dashboard").hasAnyRole("ADMIN", "SALE")
//                        .requestMatchers("/user-list").hasRole("ADMIN")
//                        .requestMatchers("/product-list").hasAnyAuthority("ROLE_ADMIN", "ROLE_SALE")
//                        .requestMatchers("/post-list").hasAnyRole("ADMIN", "SALE","AUTHOR)
//                        .requestMatchers("/profile").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/")
                        //Xóa cookie và cho hết hạn session
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .permitAll()
                );
        return http.build();
    }


    //Định nghĩa default user
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("123")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .roles("ADMIN","SALE")
                .build();

        UserDetails author = User.withDefaultPasswordEncoder()
                .username("author")
                .password("123")
                .roles("AUTHOR")
                .build();

        UserDetails sale = User.withDefaultPasswordEncoder()
                .username("sale")
                .password("123")
                .roles("SALE")
                .build();
        return new InMemoryUserDetailsManager(user, admin, author, sale);
    }
}
