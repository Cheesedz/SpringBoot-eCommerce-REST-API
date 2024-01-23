package com.Cheesedz.repository;

import com.Cheesedz.model.role.Role;
import com.Cheesedz.model.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}