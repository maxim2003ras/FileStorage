package com.maksim.rasporski.filestorage.repository;

import com.maksim.rasporski.filestorage.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Repository
public interface RoleRepository extends JpaRepository<UserRoles, Long> {
    boolean existsByRole(String roleName);
}
