package frontend.Erasmus.controller;

import frontend.Erasmus.dto.ResponseFile;
import frontend.Erasmus.dto.ResponseMessage;
import frontend.Erasmus.service.UgovoriService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UgovorController {

    private UgovoriService ugovoriService;

    //objavi ugovor
    @PostMapping("/uploadugovor")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("ugovor") MultipartFile ugovor) {
        String message = "";
        try {
            ugovoriService.store(ugovor);
            message = "Uploaded the file successfully: " + ugovor.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the ugovor: " + ugovor.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    //dohvati ugovore
    @GetMapping("/ugovori")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> ugovorifajl = ugovoriService.getAllFiles().map(ugovori -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/ugovori/")
                    .path(ugovori.getId())
                    .toUriString();
            return new ResponseFile(
                    ugovori.getName(),
                    fileDownloadUri,
                    ugovori.getType(),
                    ugovori.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(ugovorifajl);
    }
}
