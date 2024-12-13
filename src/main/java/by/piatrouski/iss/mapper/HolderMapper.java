package by.piatrouski.iss.mapper;

import org.mapstruct.Mapper;

import by.piatrouski.iss.model.Holder;
import by.piatrouski.iss.model.dto.HolderDto;

@Mapper(componentModel = "spring")
public interface HolderMapper {

    HolderDto fromHolderEntity(Holder holder);
}
