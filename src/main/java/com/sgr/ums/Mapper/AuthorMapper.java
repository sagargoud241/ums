package com.sgr.ums.Mapper;

import com.sgr.ums.Entity.Author;
import com.sgr.ums.RequestModel.AuthorRequestModel.AddAuthorRequest;
import com.sgr.ums.RequestModel.AuthorRequestModel.UpdateAuthorRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.sql.Update;


public class AuthorMapper {
    public static Author addAuthor(AddAuthorRequest request) {
        Author author = new Author();
        author.setFirstName(request.getFirstName());
        author.setMiddleName(request.getMiddleName());
        author.setLastName(request.getLastName());
        author.setEmail(request.getEmail());
        author.setAddress(request.getAddress());
        author.setCity(request.getCity());
        return author;
    }

    public static Author UpdateAuthor(Author author,UpdateAuthorRequest request) {
       // Author author = new Author();
        author.setFirstName(request.getFirstName());
        author.setMiddleName(request.getMiddleName());
        author.setLastName(request.getLastName());
        author.setEmail(request.getEmail());
        author.setAddress(request.getAddress());
        author.setCity(request.getCity());
        return author;
    }
}