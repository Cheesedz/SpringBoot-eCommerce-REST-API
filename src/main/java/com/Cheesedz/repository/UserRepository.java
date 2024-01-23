package com.Cheesedz.repository;

import com.Cheesedz.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    List<User> findByUsername(@NotNull String userName);
    Optional<User> findByUsernameOrEmail(String userName, String email);
    Boolean existsByUsername(@NotNull String username);
    Boolean existsByEmail(@NotNull String email);
}
