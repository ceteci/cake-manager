package com.waracle.cakeservice.service;

import com.waracle.cakeservice.dto.CakeDto;
import com.waracle.cakeservice.entity.Cake;

import java.util.List;
import java.util.Optional;

public interface CakeService {

    CakeDto get(String id);
    List<CakeDto> getAll();
    CakeDto save(CakeDto cakeDto);
    CakeDto update(String id, CakeDto cakeDto);
    void delete(String id);

}
