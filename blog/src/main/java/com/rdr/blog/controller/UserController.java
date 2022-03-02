package com.rdr.blog.controller;

import com.rdr.blog.model.dto.UserDto;
import com.rdr.blog.model.form.UserForm;
import com.rdr.blog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody UserForm form){
        return service.create(form);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @Valid @RequestBody UserForm form) {
        return service.update(id, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return service.delete(id);
    }

}
