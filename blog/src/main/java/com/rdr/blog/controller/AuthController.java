package com.rdr.blog.controller;

import com.rdr.blog.configs.security.TokenDto;
import com.rdr.blog.configs.security.TokenService;
import com.rdr.blog.model.form.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    final AuthenticationManager authManager;
    final TokenService tokenService;

    public AuthController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenDto> auth(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.convert();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
