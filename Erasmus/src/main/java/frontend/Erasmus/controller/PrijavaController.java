package frontend.Erasmus.controller;

import frontend.Erasmus.dto.PrijavaRequest;
import frontend.Erasmus.dto.PrijavaResponse;
import frontend.Erasmus.service.PrijavaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/api/prijave/")
@AllArgsConstructor
public class PrijavaController {

    private final PrijavaService prijavaService;

    @PostMapping
    public ResponseEntity<Void> postPrijava(@RequestBody PrijavaRequest prijavaRequest) {
        prijavaService.save(prijavaRequest);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/by-natjecaj/{natjecajId}")
    public ResponseEntity<List<PrijavaResponse>> getPrijaveByNatjecaj(@PathVariable Long natjecajId) {
        return ResponseEntity.status(OK)
                .body(prijavaService.getPrijaveByNatjecaj(natjecajId));
    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<List<PrijavaResponse>> GetAllPrijave(@PathVariable String userName){
        return ResponseEntity.status(OK)
                .body(prijavaService.getPrijaveByUsername(userName));
    }
}
