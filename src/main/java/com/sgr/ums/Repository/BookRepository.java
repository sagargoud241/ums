package com.sgr.ums.Repository;

import com.sgr.ums.Entity.Book;
import com.sgr.ums.dtointerfaces.BookInfo;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // CREATE
    @Modifying
    @Query(value = """
            INSERT INTO books(title, author, isbn, price, published_date, stock)
            VALUES (:title, :author, :isbn, :price, :publishedDate, :stock)
            """, nativeQuery = true)
    void insertBook(@Param("title") String title,
                    @Param("author") String author,
                    @Param("isbn") String isbn,
                    @Param("price") Double price,
                    @Param("publishedDate") LocalDate publishedDate,
                    @Param("stock") Integer stock);


    // READ (all)
    @Query(value = "SELECT * FROM books", nativeQuery = true)
    List<Book> findAllBooksNative();


    // READ (single)
    @Query(value = "SELECT * FROM books WHERE id = :id", nativeQuery = true)
    Book findBookByIdNative(@Param("id") Long id);


    // UPDATE
    @Modifying
    @Query(value = """
            UPDATE books
            SET title = :title,
                author = :author,
                isbn = :isbn,
                price = :price,
                published_date = :publishedDate,
                stock = :stock
            WHERE id = :id
            """, nativeQuery = true)
    void updateBookNative(@Param("id") Long id,
                          @Param("title") String title,
                          @Param("author") String author,
                          @Param("isbn") String isbn,
                          @Param("price") Double price,
                          @Param("publishedDate") LocalDate publishedDate,
                          @Param("stock") Integer stock);


    // DELETE
    @Modifying
    @Query(value = "DELETE FROM books WHERE id = :id", nativeQuery = true)
    void deleteBookNative(@Param("id") Long id);


    @Transactional
    @Query(value = """
            INSERT INTO books(
                title, author, isbn, price, published_date,
                stock, created_by, created_date,
                is_active, is_deleted
            ) VALUES (
                :title, :author, :isbn, :price, :publishedDate,
                :stock, :username, :now,
                :isActive, :isDeleted
            )
            RETURNING *
            """, nativeQuery = true)
    Book createBook(
            @Param("title") String title,
            @Param("author") String author,
            @Param("isbn") String isbn,
            @Param("price") Double price,
            @Param("publishedDate") LocalDate publishedDate,
            @Param("stock") Integer stock,
            @Param("username") String username,
            @Param("now") LocalDateTime now,
            @Param("isActive") boolean isActive,
            @Param("isDeleted") boolean isDeleted
    );

    // Delete
    @Transactional
    @Modifying
    @Query(value = """
                   UPDATE books
            SET remarks = :remarks,      
                updated_date = :updated_date,
                updated_by = :updated_by,
                is_active = :is_active,
                is_deleted = :is_deleted
            WHERE id = :id
            """, nativeQuery = true)
    void deleteBook(
            @Param("id") Long id,
            @Param("remarks") String remarks,
            @Param("updated_date") LocalDateTime updated_date,
            @Param("updated_by") String updated_by,
            @Param("is_active") boolean is_active,
            @Param("is_deleted") boolean is_deleted
    );

    @Query(value = "SELECT *  FROM books WHERE id = :id", nativeQuery = true)
    Optional<Book> findByIdNativeQuery(@NotNull(message = "Id is required") Long id);


    @Modifying
    @Transactional
    @Query(value = """
            UPDATE books
            SET title = :title,
                author = :author,
                isbn = :isbn,
                price = :price,
                stock = :stock,
                updated_by = :updatedBy,
                updated_date = :updatedDate
            WHERE id = :id
              AND is_deleted = false
            """, nativeQuery = true)
    int updateBook(
            @Param("id") Long id,
            @Param("title") String title,
            @Param("author") String author,
            @Param("isbn") String isbn,
            @Param("price") Double price,
            @Param("stock") Integer stock,
            @Param("updatedBy") String updatedBy,
            @Param("updatedDate") LocalDateTime updatedDate
    );

    @Query(
            value = """
        SELECT 
            b.author AS author,
            b.title  AS title,
            b.price  AS price
        FROM books b
        INNER JOIN author a ON a.full_name = b.author
        """,
            nativeQuery = true
    )
    List<BookInfo> getBooks();


}

