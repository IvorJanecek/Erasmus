package frontend.Erasmus.controller;


import frontend.Erasmus.dto.MobilnostDto;
import frontend.Erasmus.service.MobilnostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mobilnost")
@AllArgsConstructor
@Slf4j
public class MobilnostController {

    private final MobilnostService mobilnostService;

    @PostMapping
    public ResponseEntity<MobilnostDto> createSubreddit(@RequestBody MobilnostDto mobilnostDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(mobilnostService.save(mobilnostDto));
    }

    @GetMapping
    public ResponseEntity<List<MobilnostDto>> getAllSubreddits(){
        return ResponseEntity.status(HttpStatus.OK).body(mobilnostService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSubreddit(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mobilnostService.getMobilnost(id));
    }
}
