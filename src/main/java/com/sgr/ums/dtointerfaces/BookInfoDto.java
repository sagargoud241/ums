package com.sgr.ums.dtointerfaces;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookInfoDto {
    private String author;
    private String title;
    private Double price;
}
