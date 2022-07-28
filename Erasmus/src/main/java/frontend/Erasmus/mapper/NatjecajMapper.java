package frontend.Erasmus.mapper;

import frontend.Erasmus.dto.NatjecajResponse;
import frontend.Erasmus.dto.NatjecajRequest;
import frontend.Erasmus.model.Mobilnost;
import frontend.Erasmus.model.Natjecaj;
import frontend.Erasmus.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NatjecajMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "natjecajRequest.description")
    @Mapping(target = "mobilnost", source = "mobilnost")
    @Mapping(target = "user", source = "user")
    public abstract Natjecaj map(NatjecajRequest natjecajRequest, Mobilnost mobilnost, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "mobilnostName", source = "mobilnost.name")
    @Mapping(target = "userName", source = "user.username")
    public abstract NatjecajResponse mapToDto(Natjecaj natjecaj);


}
