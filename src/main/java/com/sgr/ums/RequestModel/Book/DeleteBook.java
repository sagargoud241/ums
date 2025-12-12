package com.sgr.ums.RequestModel.Book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteBook {

    @NotNull(message = "Id is required")
    private Long id;

    @NotBlank(message = "Remarks is Required")
    private String remarks;
}
