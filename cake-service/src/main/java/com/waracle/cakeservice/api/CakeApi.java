package com.waracle.cakeservice.api;

import com.waracle.cakeservice.dto.CakeDto;
import com.waracle.cakeservice.service.impl.CakeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CakeApi {
    private final CakeServiceImpl cakeService;


    @GetMapping("/cakes/{id}")
    public ResponseEntity<CakeDto> get(@PathVariable String id){
        return ResponseEntity.ok(cakeService.get(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/","/cakes"})
    public ResponseEntity<List<CakeDto>> getAll(){
        return ResponseEntity.ok(cakeService.getAll());
    }

    @PostMapping
    public ResponseEntity<CakeDto> save(@RequestBody CakeDto cakeDto){
        return ResponseEntity.ok(cakeService.save(cakeDto));
    }

    @PutMapping("/cakes/{id}")
    public ResponseEntity<CakeDto> update(@PathVariable("id") String id, @RequestBody CakeDto cakeDto){
        return ResponseEntity.ok(cakeService.update(id, cakeDto));
    }

    @DeleteMapping("/cakes/{id}")
    public void delete(@PathVariable String id  ){
        cakeService.delete(id);
    }
}
