package frontend.Erasmus.controller;

import frontend.Erasmus.dto.NatjecajRequest;
import frontend.Erasmus.service.NatjecajService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/natjecaj")
@AllArgsConstructor
@Slf4j
public class NatjecajController {

    private final NatjecajService natjecajService;

    @PostMapping
    public ResponseEntity<NatjecajRequest> createSubreddit(@RequestBody NatjecajRequest natjecajRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(natjecajService.save(natjecajRequest));
    }

    @GetMapping
    public ResponseEntity<List<NatjecajRequest>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(natjecajService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getNatjecaj(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(natjecajService.getNatjecaj(id));
    }
}
