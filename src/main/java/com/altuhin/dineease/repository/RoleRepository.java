package com.altuhin.dineease.repository;

import com.altuhin.dineease.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findRoleByRoleTypeEnumKey(String roleTypeEnumKey);
}
