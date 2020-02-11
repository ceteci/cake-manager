package com.waracle.cakeservice.service.impl;

import com.waracle.cakeservice.dto.CakeDto;
import com.waracle.cakeservice.entity.Cake;
import com.waracle.cakeservice.repo.CakeRepository;
import com.waracle.cakeservice.util.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CakeServiceImpl implements com.waracle.cakeservice.service.CakeService {

    private final CakeRepository cakeRepository;
    private final ModelMapper modelMapper;

    public CakeDto get(String id) {
        Cake cake = cakeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cake not found"));
        return modelMapper.map(cake, CakeDto.class);
    }

    public List<CakeDto> getAll() {
        List<Cake> all = cakeRepository.findAll();
        return MapperUtils.mapAll(all, CakeDto.class);
    }

    @Transactional
    public CakeDto save(CakeDto cakeDto) {
        Cake cake = modelMapper.map(cakeDto, Cake.class);
        cake = cakeRepository.save(cake);
        cakeDto.setId(cake.getId());
        return cakeDto;
    }

    @Transactional
    public CakeDto update(String id, CakeDto cakeDto) {
        Optional<Cake> cake = cakeRepository.findById(id);
        Cake updatedCake = cake.map(cake1 -> {
            cake1.setDesc(cakeDto.getDesc());
            cake1.setTitle(cakeDto.getTitle());
            cake1.setImage(cakeDto.getImage());
            return cake1;
        }).orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(cakeRepository.save(updatedCake), CakeDto.class);
    }

    @Transactional
    public void delete(String id) {
        Cake cake = cakeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        cakeRepository.delete(cake);
    }

}
