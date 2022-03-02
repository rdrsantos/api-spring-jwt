package com.rdr.blog.configs.security;

import com.rdr.blog.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    private String secret = "dsglsdgmdfbdf2b145fd1n0fs1n544t0401f5b1gf45nb74gtdngh1n5gf16n5f1g56nbt5n";
    private String expiration = "86400000";


    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date today = new Date();
        Date expirateDate = new Date(today.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("Api de teste")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirateDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean tokenIsValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Long getIdUser(String token) {
        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }
}
