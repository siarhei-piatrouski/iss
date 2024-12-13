package by.piatrouski.iss.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.piatrouski.iss.mapper.AssetMapper;
import by.piatrouski.iss.model.dto.AssetDto;
import by.piatrouski.iss.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssetServiceImpl implements AssetService {

    private final AssetRepository repository;
    private final AssetMapper mapper;

    @Override
    public AssetDto getAsset(Long assetId) {
        return mapper.fromAssetEntity(
                repository.getReferenceById(assetId));
    }

    @Override
    public boolean isAssetExists(Long assetId) {
        return repository.existsById(assetId);
    }
}
