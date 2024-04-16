package com.imps.imps.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registration")
public class UserRegistrationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    
    public UserRegistrationEntity() {
    }

    public UserRegistrationEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(Object object) {
        this.username = (String) object;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(Object object) {
        this.password = (String) object;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(Object object) {
        this.email = (String) object;
    }
}
