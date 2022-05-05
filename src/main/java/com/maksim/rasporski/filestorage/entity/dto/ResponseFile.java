package com.maksim.rasporski.filestorage.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Data
@AllArgsConstructor
public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private long size;
}
