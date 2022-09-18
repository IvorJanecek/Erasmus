package frontend.Erasmus.service;

import frontend.Erasmus.dto.PrijavaRequest;
import frontend.Erasmus.dto.PrijavaResponse;
import frontend.Erasmus.exception.NatjecajNotFoundException;
import frontend.Erasmus.mapper.PrijavaMapper;
import frontend.Erasmus.model.Natjecaj;
import frontend.Erasmus.model.Prijava;
import frontend.Erasmus.model.User;
import frontend.Erasmus.repository.NatjecajRepository;
import frontend.Erasmus.repository.PrijavaRepository;
import frontend.Erasmus.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class PrijavaService {

    private final PrijavaRepository prijavaRepository;
    private final PrijavaMapper prijavaMapper;
    private final NatjecajRepository natjecajRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public void save(PrijavaRequest prijavaRequest) {
        Natjecaj natjecaj = natjecajRepository.findById(prijavaRequest.getNatjecajId())
                .orElseThrow(() -> new NatjecajNotFoundException(prijavaRequest.getNatjecajId().toString()));
        prijavaRepository.save(prijavaMapper.map(prijavaRequest, natjecaj, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PrijavaResponse getPrijava(Long id) {
        Prijava prijava = prijavaRepository.findById(id)
                .orElseThrow(() -> new NatjecajNotFoundException(id.toString()));
        return prijavaMapper.mapToDto(prijava);
    }

    @Transactional(readOnly = true)
    public List<PrijavaResponse> getAllPrijave() {
        return prijavaRepository.findAll()
                .stream()
                .map(prijavaMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PrijavaResponse> getPrijaveByNatjecaj(Long natjecajId) {
        Natjecaj natjecaj = natjecajRepository.findById(natjecajId).orElseThrow(() -> new NatjecajNotFoundException(natjecajId.toString()));
        return prijavaRepository.findAllByNatjecaj(natjecaj)
                .stream()
                .map(prijavaMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PrijavaResponse> getPrijaveByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return prijavaRepository.findByUser(user)
                .stream()
                .map(prijavaMapper::mapToDto)
                .collect(toList());
    }
}
