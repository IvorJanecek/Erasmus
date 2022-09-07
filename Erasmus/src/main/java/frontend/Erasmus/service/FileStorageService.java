package frontend.Erasmus.service;

import frontend.Erasmus.model.FileDB;
import frontend.Erasmus.repository.FileDBRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FileStorageService {

    private FileDBRepository fileDBRepository;

    //spremi fajlove
    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        return fileDBRepository.save(FileDB);
    }

    //dohvati fajl po id-u
    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    //dohvati sve fajlove
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}