package frontend.Erasmus.mapper;

import frontend.Erasmus.dto.PrijavaRequest;

import frontend.Erasmus.dto.PrijavaResponse;
import frontend.Erasmus.model.Natjecaj;
import frontend.Erasmus.model.Prijava;
import frontend.Erasmus.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrijavaMapper {



    @Mapping(target = "prijavaId", ignore = true)
    @Mapping(target = "prijavaName", source = "prijavaRequest.prijavaName")
    @Mapping(target = "natjecaj", source = "natjecaj")
    @Mapping(target = "user", source = "user")
    Prijava map(PrijavaRequest prijavaRequest, Natjecaj natjecaj, User user);

    @Mapping(target = "natjecajId", expression = "java(prijava.getNatjecaj().getId())")
    @Mapping(target = "username", expression = "java(prijava.getUser().getUsername())")
    PrijavaResponse mapToDto(Prijava prijava);
}