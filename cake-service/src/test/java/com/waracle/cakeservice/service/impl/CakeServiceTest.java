package com.waracle.cakeservice.service.impl;


import com.waracle.cakeservice.dto.CakeDto;
import com.waracle.cakeservice.entity.Cake;
import com.waracle.cakeservice.repo.CakeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CakeServiceTest {

    @Mock
    private CakeRepository cakeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    CakeServiceImpl cakeService;

    @Test
    public void testFindAll()
    {
        Cake cake1 = new Cake();
        Cake cake2 = new Cake();
        Cake cake3 = new Cake();
        Cake cake4 = new Cake();

        when(cakeRepository.findAll()).thenReturn(Arrays.asList(cake1, cake2, cake3, cake4));

        List<CakeDto> cakes = cakeService.getAll();

        assertThat(cakes.size()).isEqualTo(4);
    }

    @Test
    public void testFindById()
    {
        Cake cake1 = new Cake("id1", "title", "desc", "image");

        when(cakeRepository.findById("id1")).thenReturn(Optional.of(cake1));

        CakeDto cakeDto = cakeService.get("id1");

        assertThat(cakeDto.equals(modelMapper.map(cake1, CakeDto.class)));
    }

    @Test
    void testFindCakeByIdWithTimeoutVerification() {
        String cakeId = "id";

        when(cakeRepository.findById(cakeId))
                .thenReturn(Optional.of(new Cake()));

        new Thread(() -> {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cakeRepository.findById(cakeId);
        }).start();

        verify(cakeRepository, timeout(100).times(1)).findById(cakeId);
    }

}
