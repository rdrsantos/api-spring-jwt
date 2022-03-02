package com.rdr.blog.service;

import com.rdr.blog.model.Post;
import com.rdr.blog.model.User;
import com.rdr.blog.model.dto.PostDto;
import com.rdr.blog.model.form.PostForm;
import com.rdr.blog.repository.PostRepository;
import com.rdr.blog.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository repository;
    private final UserRepository userRepository;

    public PostService(PostRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<PostDto> getAll(Long idUser) {
        User user = getUser(idUser);
        return PostDto.toDtos(user.getPosts());
    }

    public ResponseEntity<PostDto> getById(Long idUser, Long id) {
        User user = getUser(idUser);
        List<Post> post = user.getPosts().stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if (post.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new PostDto(post.get(0)));
    }

    public ResponseEntity<PostDto> create(Long idUser, PostForm form) {
        User user = getUser(idUser);
        Post post = repository.save(PostForm.toModel(form, user));
        return ResponseEntity.ok(new PostDto(post));
    }

    public ResponseEntity<PostDto> update(Long idUser, Long id, PostForm form) {
        User user = getUser(idUser);
        return repository.findById(id)
                .map(post -> {
                    post.setTitle(form.getTitle());
                    post.setBody(form.getBody());
                    post.setUpdated_at(LocalDateTime.now());
                    repository.save(post);
                    return ResponseEntity.ok(new PostDto(post));
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> delete(Long idUser, Long id) {
        getUser(idUser);
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private User getUser(Long idUser){
        return userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
    }

}
