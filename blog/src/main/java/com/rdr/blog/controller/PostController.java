package com.rdr.blog.controller;

import com.rdr.blog.model.dto.PostDto;
import com.rdr.blog.model.form.PostForm;
import com.rdr.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{idUser}/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<PostDto> getAll(@PathVariable Long idUser) {
        return service.getAll(idUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable Long idUser, @PathVariable Long id){
        return service.getById(idUser, id);
    }

    @PostMapping
    public ResponseEntity<PostDto> create(@PathVariable Long idUser, @RequestBody PostForm form) {
        return service.create(idUser, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long idUser, @PathVariable Long id) {
        return service.delete(idUser, id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> update(@PathVariable Long idUser, @PathVariable Long id, @RequestBody PostForm form) {
        return service.update(idUser, id, form);
    }
}
