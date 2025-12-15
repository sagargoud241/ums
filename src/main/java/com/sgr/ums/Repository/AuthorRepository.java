package com.sgr.ums.Repository;

import com.sgr.ums.Entity.Author;
import com.sgr.ums.dtointerfaces.ICountryAuthorDto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository <Author,Long> {
    Optional<Author> findByEmail(@NotBlank(message = "Email is required") String email);

    @Query(
            value = """
            SELECT email
            FROM author
            WHERE LOWER(city) = LOWER(:country)
        """,
            nativeQuery = true
    )
    List<ICountryAuthorDto> getEmailsByCountry(@Param("country") String country);

}

