package com.sgr.ums.Controller;

import com.sgr.ums.Entity.Author;
import com.sgr.ums.RequestModel.AuthorRequestModel.AddAuthorRequest;
import com.sgr.ums.RequestModel.AuthorRequestModel.DeleteAuthorRequest;
import com.sgr.ums.RequestModel.AuthorRequestModel.UpdateAuthorRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Services.AuthorService.AuthorService;
import com.sgr.ums.dtointerfaces.CountryAuthorDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/add-author")
    ResponseEntity<ApiResponse<Author>> addAuthor(@Valid @RequestBody AddAuthorRequest request) {
        return ResponseEntity.ok(authorService.addAuthor(request));
    }

    @PutMapping("/update-author")
    ResponseEntity<ApiResponse<Author>> updateAuthor(@Valid @RequestBody UpdateAuthorRequest request) {
        return ResponseEntity.ok(authorService.updateAuthor(request));
    }

    @GetMapping("/get-all-author")
    ResponseEntity<ApiResponse<List<Author>>> getAllAuthor() {
        return ResponseEntity.ok(authorService.getAllAuthor());
    }

    @GetMapping("/get-author/{id}")
    ResponseEntity<ApiResponse<Author>> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @DeleteMapping("/delete-author")
    ResponseEntity<ApiResponse<Author>> deleteAuthor(@Valid @RequestBody DeleteAuthorRequest request) {
        return ResponseEntity.ok(authorService.deleteAuthor(request));
    }

    @GetMapping("/getbycountry/{country}")
    ResponseEntity<ApiResponse<List<CountryAuthorDto>>> getByCountry(@PathVariable String country) {
        return ResponseEntity.ok(authorService.getByCountry(country));
    }
}
