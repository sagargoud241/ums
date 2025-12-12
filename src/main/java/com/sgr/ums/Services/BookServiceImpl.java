package com.sgr.ums.Services;

import com.sgr.ums.Authorization.UserDetail;
import com.sgr.ums.Entity.Book;
import com.sgr.ums.Repository.BookRepository;
import com.sgr.ums.RequestModel.Book.AddBook;
import com.sgr.ums.RequestModel.Book.DeleteBook;
import com.sgr.ums.RequestModel.Book.UpdateBook;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Utilities.JsonUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserDetail currentUserUtil;

    @Override
    public ApiResponse<Book> createBook(AddBook request) {
        log.info("Starting createBook with title: {}", JsonUtils.toJson(request));
        try {
            Book book = new Book();
            book = bookRepository.createBook(request.getTitle(), request.getAuthor(), request.getIsbn(), request.getPrice(), request.getPublishedDate()
                    , request.getStock(), currentUserUtil.getUsername(), LocalDateTime.now(), true, false);

            // bookRepository.save(book);
            return ApiResponse.success(book, " Create SuccessFully");
        } catch (Exception e) {
            log.error("Exception While Create Book:{}", e.getMessage(), e);
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Override
    public ApiResponse<List<Book>> getAllBook() {
        log.info("Fetching all books");
        List<Book> book = bookRepository.findAll();
        log.info("Total books fetched: {}", book.size());
        return ApiResponse.success(book, "Fetch successFully");

    }

    @Override
    public ApiResponse<Book> getBookById(Long id) {
        log.info("Fetching book with ID: {}", JsonUtils.toJson(id));
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            return ApiResponse.failure("Book not found");
        }
        log.info("Book fetched successfully with ID: {}", JsonUtils.toJson(id));
        return ApiResponse.success(book.get(), "Fetch Successfully");
    }

    @Override
    public ApiResponse<Book> deleteBookById(Long id) {
        log.info("Delete book with ID: {}", JsonUtils.toJson(id));
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isEmpty()) {
            return ApiResponse.failure("Student Not Found");
        }
        Book book = optional.get();
        bookRepository.delete(book);
        log.info("Book Delete successfully with ID: {}", JsonUtils.toJson(id));
        return ApiResponse.success(null, "Delete Successfully");
    }

    @Override
    @Transactional
    public ApiResponse<Book> deleteBook(DeleteBook request) {
        log.info("Starting DeleteBook with title: {}", JsonUtils.toJson(request));
        // Optional<Book> optional1 = bookRepository.findByIdNativeQuery(request.getId());
        try {
            Optional<Book> optional = bookRepository.findById(request.getId());
            if (optional.isEmpty()) {
                return ApiResponse.failure("Book not found");
            }
            if (optional.get().getDeleted()) {
                return ApiResponse.failure("Book not found");
            }
            bookRepository.deleteBook(request.getId(), request.getRemarks(), LocalDateTime.now(), currentUserUtil.getUsername(), false, true);
            return ApiResponse.success(null, "Delete Successfully");
        } catch (Exception e) {
            log.error("Exception While DeleteBook By Request:{}", e.getMessage(), e);
            return ApiResponse.exception(e.getMessage());
        }
    }


    @Override
    @Transactional
    public ApiResponse<Book> updateBook(UpdateBook request) {
        log.info("Starting UpdateBook with title: {}", JsonUtils.toJson(request));
        try {
            Optional<Book> optional = bookRepository.findById(request.getId());
            if (optional.isEmpty() || optional.get().getDeleted()) {
                return ApiResponse.failure("Book not found");
            }
            int updated = bookRepository.updateBook(
                    request.getId(),
                    request.getTitle(),
                    request.getAuthor(),
                    request.getIsbn(),
                    request.getPrice(),
                    request.getStock(),
                    currentUserUtil.getUsername(),
                    LocalDateTime.now()
            );

            if (updated == 0) {
                return ApiResponse.failure("Update failed");
            }

            Book updatedBook = bookRepository.findById(request.getId())
                    .orElse(null);

            return ApiResponse.success(updatedBook, "Updated successfully");

        } catch (Exception e) {
            log.error("Exception While UpdateBook By Request:{}", e.getMessage(), e);
            return ApiResponse.exception(e.getMessage());
        }
    }
}


