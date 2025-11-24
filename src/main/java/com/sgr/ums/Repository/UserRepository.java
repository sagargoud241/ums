package com.sgr.ums.Repository;

import com.sgr.ums.Entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(@NotBlank(message = "Email is required") String email);

    Optional<User> findByUuid(UUID uuid);

    Optional<User> findByUserNameAndIsActive(@NotBlank(message = "User Name is required") String userName, boolean isActive);
}


