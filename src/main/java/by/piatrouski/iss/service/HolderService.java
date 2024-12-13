package by.piatrouski.iss.service;

import by.piatrouski.iss.model.dto.HolderDto;

public interface HolderService {

    HolderDto getHolder(Long holderId);

    boolean isHolderExists(Long holderId);
}
