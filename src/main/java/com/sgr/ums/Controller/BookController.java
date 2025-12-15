package com.sgr.ums.Controller;


import com.sgr.ums.Entity.Book;
import com.sgr.ums.RequestModel.Book.AddBook;
import com.sgr.ums.RequestModel.Book.DeleteBook;
import com.sgr.ums.RequestModel.Book.UpdateBook;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Services.BookService.BookService;
import com.sgr.ums.dtointerfaces.BookInfoDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    ResponseEntity<ApiResponse<Book>> createBook(@Valid @RequestBody AddBook book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @GetMapping("/get-list")
    ResponseEntity<ApiResponse<List<Book>>> getAllBook() {
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @GetMapping("/get/{id}")
    ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ApiResponse<Book>> deleteBookById(@PathVariable long id) {
        return ResponseEntity.ok(bookService.deleteBookById(id));
    }

    @DeleteMapping("/delete_Book")
    ResponseEntity<ApiResponse<Book>> deleteBook(@Valid @RequestBody DeleteBook book) {
        return ResponseEntity.ok(bookService.deleteBook(book));
    }

    @PutMapping("/update_Book")
    ResponseEntity<ApiResponse<Book>> updateBook(@Valid @RequestBody UpdateBook book) {
        return ResponseEntity.ok(bookService.updateBook(book));
    }

    @GetMapping("/get/bookinfo")
    ResponseEntity<ApiResponse<List<BookInfoDto>>> getBookById() {
        return ResponseEntity.ok(bookService.getBooks());
    }

}
