package by.piatrouski.iss.service;

import by.piatrouski.iss.model.dto.AssetDto;

public interface AssetService {

    AssetDto getAsset(Long assetId);

    boolean isAssetExists(Long assetId);
}
