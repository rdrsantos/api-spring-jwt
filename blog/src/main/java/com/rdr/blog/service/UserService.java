package com.rdr.blog.service;

import com.rdr.blog.model.User;
import com.rdr.blog.model.dto.UserDto;
import com.rdr.blog.model.form.UserForm;
import com.rdr.blog.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    final UserRepository repository;
    final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public List<UserDto> getAll() {
        return UserDto.toDtos(repository.findAll());
    }

    public ResponseEntity<UserDto> getById(Long id){
        return repository.findById(id).map(admin -> {
                    return ResponseEntity.ok(new UserDto(admin));
                }).orElse(ResponseEntity.notFound().build());}

    public UserDto create(UserForm form) {
        form.setPassword(encoder.encode(form.getPassword()));
        User admin = repository.save(UserForm.toModel(form));
        return new UserDto(admin);
    }

    public ResponseEntity<UserDto> update(Long id, UserForm form) {
        return repository.findById(id)
                .map((user -> {
                    verifyChanges(user, form);
                    user.setName(form.getName());
                    user.setEmail(form.getEmail());
                    user.setPassword(encoder.encode(form.getPassword()));
                    user.setUpdated_at(LocalDateTime.now());
                    repository.save(user);
                    return ResponseEntity.ok(new UserDto(user));
                })).orElse(ResponseEntity.notFound().build());
    }

    private void verifyChanges(User user, UserForm form){
        if (user.getName().equalsIgnoreCase(form.getName()) &&
            user.getEmail().equalsIgnoreCase(form.getEmail())){
            throw new RuntimeException("Error: nenhuma alteração encontrada");
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        return repository.findById(id)
                .map(user -> {
                    repository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}