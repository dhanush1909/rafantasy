package com.acyuta.userservice.domain.service;

import com.acyuta.userservice.domain.model.Role;
import com.acyuta.userservice.domain.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Role repository.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * To find a Role entry when provided with its role type.
     * @param roleType The role type.
     * @return Role entry, if present.
     */
    Optional<Role> findByRoleType(RoleType roleType);
}
