package frontend.Erasmus.mapper;

import frontend.Erasmus.dto.MobilnostDto;
import frontend.Erasmus.model.Mobilnost;
import frontend.Erasmus.model.Natjecaj;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MobilnostMapper {


    @Mapping(target = "numberOfNatjecajs", expression = "java(mapNatjecaj(mobilnost.getNatjecajs()))")
    MobilnostDto mapMobilnostToDto(Mobilnost mobilnost);

    default Integer mapNatjecaj(List<Natjecaj> numberOfNatjecajs) {
        return numberOfNatjecajs.size();
    }


    @InheritInverseConfiguration
    @Mapping(target = "natjecajs", ignore = true)
    Mobilnost mapDtoToMobilnost(MobilnostDto mobilnostDto);
}
