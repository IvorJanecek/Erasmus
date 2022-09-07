package frontend.Erasmus.mapper;

import frontend.Erasmus.dto.NatjecajRequest;
import frontend.Erasmus.model.Mobilnost;
import frontend.Erasmus.model.Natjecaj;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NatjecajMapper {



//Mapiranje u frontend
    @Mapping(target = " numberOfMobilnosts", expression = "java(mapMobilnosts(natjecaj.getMobilnosts()))")
    NatjecajRequest mapNatjecajToDto(Natjecaj natjecaj);

    default Integer mapMobilnosts(@NotNull List<Mobilnost> numberOfMobilnosts) {

        return numberOfMobilnosts.size();
    }

// mapiranje u natjecaj iz frontenda
    @InheritInverseConfiguration
    @Mapping(target = "mobilnosts", ignore = true)
    Natjecaj mapDtoToNatjecaj(NatjecajRequest natjecaj);
}
