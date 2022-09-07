package frontend.Erasmus.service;

import frontend.Erasmus.model.FileDB;
import frontend.Erasmus.model.Ugovori;
import frontend.Erasmus.repository.UgovoriRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UgovoriService {
    private UgovoriRepository ugovoriRepository;

    //spremi ugovor
    public Ugovori store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Ugovori Ugovori = new Ugovori(fileName, file.getContentType(), file.getBytes());
        return ugovoriRepository.save(Ugovori);
    }
    public Ugovori getFile(String id) {
        return ugovoriRepository.findById(id).get();
    }

    public Stream<Ugovori> getAllFiles() {
        return ugovoriRepository.findAll().stream();
    }
}