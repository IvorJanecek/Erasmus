package frontend.Erasmus.service;

import frontend.Erasmus.dto.NatjecajRequest;
import frontend.Erasmus.exception.NatjecajNotFoundException;
import frontend.Erasmus.mapper.NatjecajMapper;
import frontend.Erasmus.model.Natjecaj;
import frontend.Erasmus.repository.NatjecajRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class NatjecajService {

    private final NatjecajRepository natjecajRepository;

    private final NatjecajMapper natjecajMapper;

    //spremi natjecaj
    @Transactional
    public NatjecajRequest save(NatjecajRequest natjecajRequest){
        Natjecaj save = natjecajRepository.save(natjecajMapper.mapDtoToNatjecaj(natjecajRequest));
        natjecajRequest.setId(save.getId());
        return natjecajRequest;

    }

    //Prikazi sve natjecaje
    @Transactional(readOnly = true)
    public List<NatjecajRequest> getAll() {

        return natjecajRepository.findAll()
                .stream()
                .map(natjecajMapper::mapNatjecajToDto)
                .collect(toList());
    }


    //uzmi natjecaj po id
    @Transactional
    public Object getNatjecaj(Long id) {
        Natjecaj natjecaj = natjecajRepository.findById(id)
                .orElseThrow(() -> new NatjecajNotFoundException("No subbredits found with this id" + id));
        return natjecajMapper.mapNatjecajToDto(natjecaj);
    }
}
