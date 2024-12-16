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

import by.piatrouski.iss.mapper.HolderMapper;
import by.piatrouski.iss.model.Holder;
import by.piatrouski.iss.model.dto.HolderDto;
import by.piatrouski.iss.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class HolderServiceTest {

    @Mock
    private CustomerRepository mockRepository;
    @Mock
    private HolderMapper mockMapper;

    @InjectMocks
    private HolderServiceImpl sut;

    @Test
    void getHolder() {
        final Holder holder = new Holder();
        holder.setId(0L);
        holder.setFirstName("John");
        holder.setLastName("Doe");
        holder.setEmail("John.Doe@gmail.com");
        holder.setPhoneNumber("+1111111111");

        HolderDto expectedResult = new HolderDto();
        expectedResult.setId(0L);
        expectedResult.setFirstName("John");
        expectedResult.setLastName("Doe");
        expectedResult.setEmail("John.Doe@gmail.com");
        expectedResult.setPhoneNumber("+1111111111");

        when(mockRepository.getReferenceById(0L)).thenReturn(holder);
        when(mockMapper.fromHolderEntity(holder)).thenReturn(expectedResult);

        final HolderDto result = sut.getHolder(0L);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void isHolderExists_repositoryReturnsFalse() {
        when(mockRepository.existsById(0L)).thenReturn(false);

        assertFalse(sut.isHolderExists(0L));
    }

    @Test
    void isHolderExists_repositoryReturnsTrue() {
        when(mockRepository.existsById(0L)).thenReturn(true);

        assertTrue(sut.isHolderExists(0L));
    }
}
