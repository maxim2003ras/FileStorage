package com.maksim.rasporski.filestorage.exception;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(final String message) {
        super(message);
    }
}
