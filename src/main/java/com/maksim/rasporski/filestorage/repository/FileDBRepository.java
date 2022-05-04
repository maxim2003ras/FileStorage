package com.maksim.rasporski.filestorage.repository;

import com.maksim.rasporski.filestorage.entity.AppUser;
import com.maksim.rasporski.filestorage.entity.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
    List<FileDB> findByUser(final AppUser user);
}
