package com.maksim.rasporski.filestorage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    public UserRoles(final String role) {
        this.role = role;
    }
}
