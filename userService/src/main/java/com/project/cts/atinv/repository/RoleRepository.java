package com.project.cts.atinv.repository;

import com.project.cts.atinv.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRole(String role);
}
