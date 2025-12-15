package com.sgr.ums.Services.AuthorService;

import com.sgr.ums.Entity.Author;
import com.sgr.ums.RequestModel.AuthorRequestModel.AddAuthorRequest;
import com.sgr.ums.RequestModel.AuthorRequestModel.DeleteAuthorRequest;
import com.sgr.ums.RequestModel.AuthorRequestModel.UpdateAuthorRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.dtointerfaces.CountryAuthorDto;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface AuthorService {
    ApiResponse<Author> addAuthor(AddAuthorRequest request);

    ApiResponse<Author> updateAuthor(UpdateAuthorRequest request);

    ApiResponse<List<Author>> getAllAuthor();

    ApiResponse<Author> getAuthorById(Long id);

    ApiResponse<Author> deleteAuthor(DeleteAuthorRequest request);

    ApiResponse<List<CountryAuthorDto>> getByCountry(String country);
}
