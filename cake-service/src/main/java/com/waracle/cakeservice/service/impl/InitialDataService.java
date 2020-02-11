package com.waracle.cakeservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakeservice.entity.Cake;
import com.waracle.cakeservice.repo.CakeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitialDataService {

    @Value("${spcloud.cassandra.initDataUrl}")
    private  String initDataUrl;

    private final CakeRepository cakeRepository;

    @PostConstruct
    public void init() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);

        ObjectMapper mapper = new ObjectMapper();
        Cake[] cakes = restTemplate.getForObject(initDataUrl, Cake[].class);

        cakeRepository.deleteAll();
        List<Cake> cakeList = Arrays.asList(cakes);
        cakeList.forEach(cake -> cakeRepository.save(cake));
    }

}
