package com.sgr.ums.RequestModel.Book;

import lombok.Data;
import java.time.LocalDate;
@Data
public class AddBook {

    private String title;

    private String author;

    private String isbn;

    private Double price;

    private LocalDate publishedDate;

    private Integer stock;
}
