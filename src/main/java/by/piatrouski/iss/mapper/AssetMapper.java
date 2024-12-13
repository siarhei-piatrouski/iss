package by.piatrouski.iss.mapper;

import org.mapstruct.Mapper;

import by.piatrouski.iss.model.Asset;
import by.piatrouski.iss.model.dto.AssetDto;

@Mapper(componentModel = "spring")
public interface AssetMapper {

    AssetDto fromAssetEntity(Asset asset);
}
