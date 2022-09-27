package com.example.api.security.dtos;

import java.util.HashSet;
import java.util.Set;

public class NewUser {


    private String userName;

    private String email;

    private String password;
    private Set<String> roles = new HashSet<>();
    public NewUser() {
    }
    public NewUser(String userName, String email, String password,
            Set<String> roles) {

        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
  
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<String> getRoles() {
        return roles;
    }
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    
}
