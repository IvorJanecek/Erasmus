package frontend.Erasmus.controller;

import frontend.Erasmus.dto.NatjecajRequest;
import frontend.Erasmus.dto.NatjecajResponse;
import frontend.Erasmus.service.NatjecajService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/natjecajs")
@AllArgsConstructor
public class NatjecajController {
    private final NatjecajService natjecajService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody NatjecajRequest natjecajRequest) {
        natjecajService.save(natjecajRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<NatjecajResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(natjecajService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NatjecajResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(natjecajService.getPost(id));
    }

    @GetMapping("by-subreddit/{id}")
    public ResponseEntity<List<NatjecajResponse>> getPostsBySubreddit(Long id) {
        return status(HttpStatus.OK).body(natjecajService.getPostsBySubreddit(id));
    }

    @GetMapping("by-user/{name}")
    public ResponseEntity<List<NatjecajResponse>> getPostsByUsername(String name) {
        return status(HttpStatus.OK).body(natjecajService.getPostsByUsername(name));
    }
}
