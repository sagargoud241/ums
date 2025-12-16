package com.sgr.ums.Services.AuthorService;

import com.sgr.ums.Entity.Author;
import com.sgr.ums.Mapper.AuthorMapper;
import com.sgr.ums.Repository.AuthorRepository;
import com.sgr.ums.RequestModel.AuthorRequestModel.AddAuthorRequest;
import com.sgr.ums.RequestModel.AuthorRequestModel.DeleteAuthorRequest;
import com.sgr.ums.RequestModel.AuthorRequestModel.UpdateAuthorRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.Utilities.JsonUtils;
import com.sgr.ums.Utilities.Utility;
import com.sgr.ums.dtointerfaces.CountryAuthorDto;
import com.sgr.ums.dtointerfaces.ICountryAuthorDto;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.io.filefilter.FileFilterUtils.toList;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public ApiResponse<Author> addAuthor(AddAuthorRequest request) {
        log.info("Starting AddNewAuthor: {}", JsonUtils.toJson(request));
        try {
            Optional<Author> optional = authorRepository.findByEmail(request.getEmail());
            if (optional != null && !optional.isEmpty()) {
                return ApiResponse.failure("Email is Already Used");
            }
            Author author = AuthorMapper.addAuthor(request);
            author.setCreatedBy(Utility.getDefaultUsername());
            author.setCreatedDate(LocalDateTime.now());
            author.setActive(true);
            author.setDeleted(false);
            authorRepository.save(author);

            return ApiResponse.success(author, "Add SuccessFully");
        } catch (Exception e) {
            log.error("Exception while Adding new Author:{}", e.getMessage(), e);
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Transactional
    @Override
    public ApiResponse<Author> updateAuthor(UpdateAuthorRequest request) {
        try {
            log.info("Starting UpdateAuthor:{}", JsonUtils.toJson(request));
            Optional<Author> existing = authorRepository.findById(request.getId());
            if (existing == null || existing.isEmpty()) {
                return ApiResponse.failure("Author not found.");
            }
            Author author = existing.get();
            // email unique x aki nai
            if (!author.getEmail().equals(request.getEmail())) {
                Optional<Author> optional = authorRepository.findByEmail(request.getEmail());
                if (optional != null && !optional.isEmpty()) {
                    return ApiResponse.failure("Email is Already Used");
                }
            }
            author = AuthorMapper.UpdateAuthor(author, request);
            author.setUpdatedBy(Utility.getDefaultUsername());
            author.setUpdatedDate(LocalDateTime.now());
            author.setActive(true);
            author.setDeleted(false);
            authorRepository.save(author);
            return ApiResponse.success(author, "Updated SuccessFully");
        } catch (Exception e) {
            log.error("Exception while Updating the Author:{}", e.getMessage(), e);
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Override
    public ApiResponse<List<Author>> getAllAuthor() {
        try {
            log.info(" starting Getting AllAuthor");
            List<Author> author = authorRepository.findAll();
            return ApiResponse.success(author, "Fetch successFully");
        } catch (Exception e) {
            log.error("Exception While GettingAllAuthor:{}", e.getMessage(), e);
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Override
    public ApiResponse<Author> getAuthorById(Long id) {
        try {
            log.info("Getting the AuthorById:{}", JsonUtils.toJson(id));
            Optional<Author> optional = authorRepository.findById(id);
            if (optional.isEmpty()) {
                return ApiResponse.failure("Author not found");
            }
            if (optional.get().getDeleted()) {
                return ApiResponse.failure("Author not found");
            }
            return ApiResponse.success(optional.get(), "Fetch SuccessFully");
        } catch (Exception e) {
            log.error("Exception While GettingAllAuthor:{}", e.getMessage(), e);
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Override
    public ApiResponse<Author> deleteAuthor(DeleteAuthorRequest request) {
        try {
            log.info("Starting Deleting Author:{}", JsonUtils.toJson(request));
            Optional<Author> optional = authorRepository.findById(request.getId());
            if (optional.isEmpty()) {
                return ApiResponse.failure("Author not found");
            }
            if (optional.get().getDeleted()) {
                return ApiResponse.failure("Author not found");
            }
            Author author = optional.get();
            author.setRemarks(request.getRemarks());
            author.setActive(false);
            author.setDeleted(true);
            author.setUpdatedBy(Utility.getDefaultUsername());
            author.setUpdatedDate(LocalDateTime.now());
            authorRepository.save(author);
            return ApiResponse.success(author, "Delete SuccessFully");
        } catch (Exception e) {
            log.error("Exception While GettingAllAuthor:{}", e.getMessage(), e);
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Override
    public ApiResponse<List<CountryAuthorDto>> getByCountry(String country) {
        try {

            log.info(" getByCountry all emails:{}", JsonUtils.toJson(country));

            List<ICountryAuthorDto> raw = authorRepository.getEmailsByCountry(country);

            List<CountryAuthorDto> list = raw.stream()
                    .map(row -> new CountryAuthorDto(row.getEmail()))
                    .toList();
            return ApiResponse.success(list, "Fetch Successfully");
        } catch (Exception e) {

            log.error("Exception While getByCountry:{}", e.getMessage(), e);
            return ApiResponse.exception(e.getMessage());
        }

    }

}