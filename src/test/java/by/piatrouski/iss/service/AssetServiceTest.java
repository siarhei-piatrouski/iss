package by.piatrouski.iss.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import by.piatrouski.iss.mapper.AssetMapper;
import by.piatrouski.iss.model.Asset;
import by.piatrouski.iss.model.dto.AssetDto;
import by.piatrouski.iss.repository.AssetRepository;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class AssetServiceTest {

    @Mock
    private AssetRepository mockRepository;
    @Mock
    private AssetMapper mockMapper;

    @InjectMocks
    private AssetServiceImpl sut;

    @Test
    void getAsset() {
        Asset asset = new Asset();
        asset.setId(0L);
        asset.setName("Tesla Inc.");
        asset.setIsin("US88160R1014");
        asset.setType("Common Shares");
        asset.setIssueDate(LocalDate.of(2010, 4, 12));

        AssetDto expectedResult = new AssetDto();
        expectedResult.setId(0L);
        expectedResult.setName("Tesla Inc.");
        expectedResult.setIsin("US88160R1014");
        expectedResult.setType("Common Shares");
        expectedResult.setIssueDate(LocalDate.of(2010, 4, 12));

        when(mockRepository.getReferenceById(0L)).thenReturn(asset);
        when(mockMapper.fromAssetEntity(asset)).thenReturn(expectedResult);

        AssetDto result = sut.getAsset(0L);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void isAssetExists_repositoryReturnsFalse() {
        when(mockRepository.existsById(0L)).thenReturn(false);

        assertFalse(sut.isAssetExists(0L));
    }

    @Test
    void isAssetExists_repositoryReturnsTrue() {
        when(mockRepository.existsById(0L)).thenReturn(true);

        assertTrue(sut.isAssetExists(0L));
    }

}
