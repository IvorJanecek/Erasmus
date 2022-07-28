package frontend.Erasmus.service;

import frontend.Erasmus.dto.MobilnostDto;
import frontend.Erasmus.exception.MobilnostNotFoundException;
import frontend.Erasmus.mapper.MobilnostMapper;
import frontend.Erasmus.repository.MobilnostRepository;
import frontend.Erasmus.model.Mobilnost;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class MobilnostService {

    private final MobilnostRepository mobilnostRepository;
    private final MobilnostMapper mobilnostMapper;

    @Transactional
    public MobilnostDto save(MobilnostDto mobilnostDto){
        Mobilnost save = mobilnostRepository.save(mobilnostMapper.mapDtoToMobilnost(mobilnostDto));
        mobilnostDto.setId(save.getId());
        return mobilnostDto;

    }

    @Transactional(readOnly = true)
    public List<MobilnostDto> getAll() {

        return mobilnostRepository.findAll()
                .stream()
                .map(mobilnostMapper::mapMobilnostToDto)
                .collect(toList());
    }


    public Object getMobilnost(Long id) {
        Mobilnost mobilnost = mobilnostRepository.findById(id)
                .orElseThrow(() -> new MobilnostNotFoundException("No subbredits found with this id" + id));
        return mobilnostMapper.mapMobilnostToDto(mobilnost);
    }
}
