package com.sgr.ums.Services.BookService;

import com.sgr.ums.Entity.Book;
import com.sgr.ums.RequestModel.Book.AddBook;
import com.sgr.ums.RequestModel.Book.DeleteBook;
import com.sgr.ums.RequestModel.Book.UpdateBook;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.dtointerfaces.BookInfoDto;

import java.util.List;

public interface BookService {
    ApiResponse<Book> createBook(AddBook book);

    ApiResponse<List<Book>> getAllBook();

    ApiResponse<Book> getBookById(Long id);

    ApiResponse<Book> deleteBookById(Long id);

    ApiResponse<Book> deleteBook(DeleteBook book);

    ApiResponse<Book> updateBook(UpdateBook request);

     ApiResponse<List<BookInfoDto>> getBooks();
}
