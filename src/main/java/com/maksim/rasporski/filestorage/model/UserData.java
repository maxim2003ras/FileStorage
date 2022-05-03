package com.maksim.rasporski.filestorage.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Getter
@Setter
public class UserData {
    @NotBlank(message = "Некорректное имя пользователя")
    private String username;
    @Size(min = 6, message = "Минимальная длина пароля 6 символов")
    private String password;
    @Email(message = "Неккоректный email")
    private String email;
}
