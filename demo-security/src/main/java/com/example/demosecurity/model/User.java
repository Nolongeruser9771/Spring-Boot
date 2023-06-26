package com.example.demosecurity.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //lấy ra danh sách quyền của user
        //Duyệt qua danh sách role
        //Với mỗi role name tạo đối tượng SimpleGrantedAuthority triển khai interface GrantedAuthority
        return roles.stream()
                .map(role ->  new SimpleGrantedAuthority("ROLE_"+ role.getName()))
                .toList();
    }

    @Override
    public String getUsername() {
        //thông tin sử dụng đăng nhập
        return this.email;
    }

    @Override
    public String getPassword() {
        //thông tin sử dụng đăng nhập
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        //tài khoản hết hạn?
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //tài khoản bị khóa?
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //password hết hạn?
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
