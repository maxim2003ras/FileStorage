package com.maksim.rasporski.filestorage.service;

import com.maksim.rasporski.filestorage.entity.AppUser;
import com.maksim.rasporski.filestorage.entity.FileDB;
import com.maksim.rasporski.filestorage.repository.FileDBRepository;
import com.maksim.rasporski.filestorage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Service
public class FileStorageService {
    private FileDBRepository fileDBRepository;
    private UserRepository userRepository;

    @Autowired
    public FileStorageService(final FileDBRepository fileDBRepository, final UserRepository userRepository) {
        this.fileDBRepository = fileDBRepository;
        this.userRepository = userRepository;
    }

    public FileDB store(MultipartFile file, final UserDetails userDetails) throws IOException {
        if (file.isEmpty()) throw new IOException();
        AppUser user = userRepository.findByEmail(userDetails.getUsername()).get();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), user);
        return fileDBRepository.save(FileDB);
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles(final UserDetails userDetails) {
        return fileDBRepository.findByUser(userRepository.findByEmail(userDetails.getUsername()).get()).stream();
    }

    public void deleteFileById(final String id) {
        fileDBRepository.deleteById(id);
    }
}
