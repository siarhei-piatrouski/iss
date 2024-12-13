package by.piatrouski.iss.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.piatrouski.iss.mapper.HolderMapper;
import by.piatrouski.iss.model.dto.HolderDto;
import by.piatrouski.iss.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HolderServiceImpl implements HolderService {

    private final CustomerRepository repository;
    private final HolderMapper mapper;

    @Override
    public HolderDto getHolder(Long holderId) {
        return mapper.fromHolderEntity(
                repository.getReferenceById(holderId));
    }

    @Override
    public boolean isHolderExists(Long holderId) {
        return repository.existsById(holderId);
    }
}
