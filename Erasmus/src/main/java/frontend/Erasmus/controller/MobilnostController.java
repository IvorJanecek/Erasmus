package frontend.Erasmus.controller;


import frontend.Erasmus.dto.MobilnostRequest;
import frontend.Erasmus.dto.MobilnostResponse;
import frontend.Erasmus.service.MobilnostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/mobilnost")
@AllArgsConstructor
public class MobilnostController {

    private final MobilnostService mobilnostService;

    @PostMapping
    public ResponseEntity<Void> createMobilnost(@RequestBody MobilnostRequest mobilnostRequest) {
        mobilnostService.save(mobilnostRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MobilnostResponse>> getAllMobilnosts() {
        return status(HttpStatus.OK).body(mobilnostService.getAllMobilnosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MobilnostResponse> getMobilnost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(mobilnostService.getMobilnost(id));
    }

    @GetMapping("by-subreddit/{id}")
    public ResponseEntity<List<MobilnostResponse>> getPostsBySubreddit(Long id) {
        return status(HttpStatus.OK).body(mobilnostService.getMobilnostByNatjecaj(id));
    }

    @GetMapping("by-user/{name}")
    public ResponseEntity<List<MobilnostResponse>> getMobilnostsByUsername(String name) {
        return status(HttpStatus.OK).body(mobilnostService.getMobilnostsByUsername(name));
    }
}
