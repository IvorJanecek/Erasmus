package frontend.Erasmus.service;

import frontend.Erasmus.dto.MobilnostRequest;
import frontend.Erasmus.dto.MobilnostResponse;
import frontend.Erasmus.exception.MobilnostNotFoundException;
import frontend.Erasmus.exception.NatjecajNotFoundException;
import frontend.Erasmus.mapper.MobilnostMapper;
import frontend.Erasmus.model.Mobilnost;
import frontend.Erasmus.model.Natjecaj;
import frontend.Erasmus.model.User;
import frontend.Erasmus.repository.MobilnostRepository;
import frontend.Erasmus.repository.NatjecajRepository;
import frontend.Erasmus.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class MobilnostService {
    private final NatjecajRepository natjecajRepository;
    private final AuthService authService;
    private final MobilnostMapper mobilnostMapper;
    private final MobilnostRepository mobilnostRepository;
    private final UserRepository userRepository;

    //spremi mobilnost
    public void save(MobilnostRequest mobilnostRequest) {
        Natjecaj natjecaj = natjecajRepository.findByName(mobilnostRequest.getNatjecajName())
                .orElseThrow(() -> new NatjecajNotFoundException(mobilnostRequest.getNatjecajName()));
        mobilnostRepository.save(mobilnostMapper.map(mobilnostRequest, natjecaj, authService.getCurrentUser()));
    }

    //dohvati mobilnost po id-u
    @Transactional(readOnly = true)
    public MobilnostResponse getMobilnost(Long id) {
        Mobilnost mobilnost = mobilnostRepository.findById(id)
                .orElseThrow(() -> new MobilnostNotFoundException(id.toString()));
        return mobilnostMapper.mapToDto(mobilnost);
    }

    //dohvati sve mobilnosti
    @Transactional(readOnly = true)
    public List<MobilnostResponse> getAllMobilnosts() {
        return mobilnostRepository.findAll()
                .stream()
                .map(mobilnostMapper::mapToDto)
                .collect(toList());
    }


    //dohvati mobilnosti po odredenom natjecaju
    @Transactional(readOnly = true)
    public List<MobilnostResponse> getMobilnostByNatjecaj(Long subredditId) {
        Natjecaj natjecaj = natjecajRepository.findById(subredditId)
                .orElseThrow(() -> new NatjecajNotFoundException(subredditId.toString()));
        List<Mobilnost> mobilnosts = mobilnostRepository.findAllByNatjecaj(natjecaj);
        return mobilnosts.stream().map(mobilnostMapper::mapToDto).collect(toList());
    }

    //dohvati mobilnosti po useru
    @Transactional(readOnly = true)
    public List<MobilnostResponse> getMobilnostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return mobilnostRepository.findByUser(user)
                .stream()
                .map(mobilnostMapper::mapToDto)
                .collect(toList());
    }
}
