package uz.pdp.g34callingexternalapis.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import uz.pdp.g34callingexternalapis.domain.Post;
import uz.pdp.g34callingexternalapis.dto.CommentDto;
import uz.pdp.g34callingexternalapis.dto.PostDto;
import uz.pdp.g34callingexternalapis.repo.PostRepository;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/{id}/comments")
    public PostDto getPostWithComments(@PathVariable Long id) {
        Post post = postRepository.findByGenId(id).orElseThrow(
                () -> new RuntimeException("Post with id " + id + " not found")
        );
        RestTemplate restTemplate = new RestTemplate();
        CommentDto[] comments = restTemplate.getForObject(
                "http://localhost:8080/comments/post/" + post.getGenId(),
                CommentDto[].class
        );

        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .comments(List.of(comments))
                .build();
    }

    @DeleteMapping("/{id}/comments")
    public ResponseEntity<?> deleteCommentsByPostId(@PathVariable String id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Post with id " + id + " not found")
        );

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(
                "http://localhost:8080/comments/post/" + post.getGenId()
        );
        return ResponseEntity.noContent().build();
    }
}
