package frontend.Erasmus.mapper;

import frontend.Erasmus.dto.MobilnostRequest;
import frontend.Erasmus.dto.MobilnostResponse;
import frontend.Erasmus.model.Mobilnost;
import frontend.Erasmus.model.Natjecaj;
import frontend.Erasmus.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MobilnostMapper {


    //mapiranje iz frontenda
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "mobilnostRequest.description")
    @Mapping(target = "natjecaj", source = "natjecaj")
    @Mapping(target = "user", source = "user")
    public abstract Mobilnost map(MobilnostRequest mobilnostRequest, Natjecaj natjecaj, User user);

    //mapiranje za frontend
    @Mapping(target = "id", source = "natjecaj.id")
    @Mapping(target = "natjecajName", source = "natjecaj.name")
    @Mapping(target = "userName", source = "user.username")
    public abstract MobilnostResponse mapToDto(Mobilnost mobilnost);


}
