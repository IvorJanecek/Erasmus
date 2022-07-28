package frontend.Erasmus.service;

import frontend.Erasmus.dto.NatjecajRequest;
import frontend.Erasmus.dto.NatjecajResponse;
import frontend.Erasmus.exception.MobilnostNotFoundException;
import frontend.Erasmus.mapper.NatjecajMapper;
import frontend.Erasmus.exception.NatjecajNotFoundException;
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
public class NatjecajService {
    private final MobilnostRepository mobilnostRepository;
    private final AuthService authService;
    private final NatjecajMapper natjecajMapper;
    private final NatjecajRepository natjecajRepository;
    private final UserRepository userRepository;
    public void save(NatjecajRequest natjecajRequest) {
        Mobilnost mobilnost = mobilnostRepository.findByName(natjecajRequest.getMobilnostName())
                .orElseThrow(() -> new MobilnostNotFoundException(natjecajRequest.getMobilnostName()));
        natjecajRepository.save(natjecajMapper.map(natjecajRequest, mobilnost, authService.getCurrentUser()));
    }
    @Transactional(readOnly = true)
    public NatjecajResponse getPost(Long id) {
        Natjecaj natjecaj = natjecajRepository.findById(id)
                .orElseThrow(() -> new NatjecajNotFoundException(id.toString()));
        return natjecajMapper.mapToDto(natjecaj);
    }

    @Transactional(readOnly = true)
    public List<NatjecajResponse> getAllPosts() {
        return natjecajRepository.findAll()
                .stream()
                .map(natjecajMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<NatjecajResponse> getPostsBySubreddit(Long subredditId) {
        Mobilnost mobilnost = mobilnostRepository.findById(subredditId)
                .orElseThrow(() -> new MobilnostNotFoundException(subredditId.toString()));
        List<Natjecaj> posts = natjecajRepository.findAllByMobilnost(mobilnost);
        return posts.stream().map(natjecajMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<NatjecajResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return natjecajRepository.findByUser(user)
                .stream()
                .map(natjecajMapper::mapToDto)
                .collect(toList());
    }
}
