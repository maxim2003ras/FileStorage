package com.maksim.rasporski.filestorage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "files")
public class FileDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    public FileDB(final String fileName, final String contentType, final byte[] bytes, final AppUser user) {
        this.fileName = fileName;
        fileType = contentType;
        data = bytes;
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private AppUser user;
}
