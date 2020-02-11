package com.waracle.cakeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CakeDto {
    private String id;
    private String title;
    private String desc;
    private String image;
}
