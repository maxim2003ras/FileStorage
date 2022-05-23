package com.maksim.rasporski.filestorage.controller;

import com.maksim.rasporski.filestorage.entity.FileDB;
import com.maksim.rasporski.filestorage.entity.dto.ResponseFile;
import com.maksim.rasporski.filestorage.service.FileStorageService;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Controller
@EnableWebMvc
public class FileController {
    private FileStorageService fileStorageService;

    public FileController(final FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            fileStorageService.store(file, userDetails);
            return "fileOK";
        } catch (Exception e) {
            return "upload-error";
        }
    }

    @GetMapping("/files")
    public String getAllFiles(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<ResponseFile> files = fileStorageService.getAllFiles(userDetails).map(dbFile -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/files/")
                            .path(dbFile.getId())
                            .toUriString();
                    return new ResponseFile(
                            dbFile.getFileName(),
                            fileDownloadUri,
                            dbFile.getFileType(),
                            dbFile.getData().length);

                }).collect(Collectors.toList());

        model.addAttribute("files", files);
        return "files";
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable String id) {
        FileDB fileDB = fileStorageService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDB.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFileName() + "\"").body(new ByteArrayResource(fileDB.getData()));
    }

    @PostMapping("/files/{id}")
    public String deleteFile(@PathVariable String id) {
        fileStorageService.deleteFileById(id);
        return "redirect:/files";
    }
}
