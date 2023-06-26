package com.example.demosecurity.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true //(role allowed)
)
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final CustomFilter customFilter;


    //Nếu đối tượng cần khởi tạo là interface thì ko thể khởi tạo trực tiếp
    //Phải go to implementations để chọn đối tượng khởi tạo để triển khai interface

    //Tạo đối tượng encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        //Thuật toán Bcrypt là thuật toán mặc định mã hóa passwword
        //Ngoài ra còn nhiều thuật toán mã hóa
        return new BCryptPasswordEncoder();
    }

    //Tạo đối tượng AuthenticationProvider
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); //set chức năng so sánh pass
        daoAuthenticationProvider.setUserDetailsService(userDetailsService); //set chức năng tìm kiếm user
        return daoAuthenticationProvider;
    }

    //Tạo đối tượng AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] PUBLIC_ROUTE = {"/", "/api/v1/auth/**", "/login"};
        http
                .csrf(c -> c.disable()) //Disable bảo vệ trước tấn công ủy quyền
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(PUBLIC_ROUTE).permitAll()
                        .anyRequest().authenticated()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/")
                        //Xóa cookie và cho hết hạn session
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .authenticationProvider(authenticationProvider())  //cung cấp pp xác thực username, password, tìm kiểm user
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
