package com.example.api.security.controllers;


import com.example.api.security.dtos.JwtDto;
import com.example.api.security.dtos.LoginUser;
import com.example.api.security.dtos.NewUser;
import com.example.api.security.entities.Message;
import com.example.api.security.entities.Role;
import com.example.api.security.entities.User;
import com.example.api.security.enums.RoleList;
import com.example.api.security.jwt.JwtProvider;
import com.example.api.security.services.RoleService;
import com.example.api.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder,
            UserService userService, RoleService roleService, JwtProvider jwtProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
        this.jwtProvider = jwtProvider;
    }

    /**
     * Al registrarse se verificara los datos del usuario en la DB
     * si existe se retornara el Token de la sesion
     * */
    @CrossOrigin(origins = "https://portafolio-web-gq-31166.web.app")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginUser loginUser){
        try {
                UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword());
                Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtProvider.generateToken(authentication);
                JwtDto jwtDto = new JwtDto(jwt);
                return new ResponseEntity<>(jwtDto, HttpStatus.OK);
        } catch (Exception e) {
                return new ResponseEntity<>(new Message("Error"), HttpStatus.BAD_REQUEST);
        }
    }

//    //Solo un Admin puede Crear Un Usuario para esta APP
//    @CrossOrigin(origins = "https://portafolio-web-gq-31166.web.app")
//    @PostMapping("/register")
//    public ResponseEntity<Object> resgister(@RequestBody NewUser newUser, BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return new ResponseEntity<>(new Message("Revise los campos e intente nuevamente"), HttpStatus.BAD_REQUEST);
//        User user = new User(newUser.getUserName(), newUser.getEmail(),
//                passwordEncoder.encode(newUser.getPassword()));
//        Set<Role> roles = new HashSet<>();
//        roles.add(roleService.getByRoleName(RoleList.ROLE_USER).get());
//        if (newUser.getRoles().contains("admin"))
//            roles.add(roleService.getByRoleName(RoleList.ROLE_ADMIN).get());
//        user.setRoles(roles);
//        userService.save(user);
//        return new ResponseEntity<>(new Message("Registro exitoso! Inicie sesi??n"), HttpStatus.CREATED);
//    }
    
}
