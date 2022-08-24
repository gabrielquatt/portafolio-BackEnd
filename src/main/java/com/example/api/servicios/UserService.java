package com.example.api.servicios;

import com.example.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository ur;
    public boolean authenticate(String name, String pass) {
            if(this.ur.comparePass(name,pass)!=null){
                return true;
            }
        return false;
    }

}
