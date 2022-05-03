package com.maksim.rasporski.filestorage.service;

import com.maksim.rasporski.filestorage.entity.AppUser;
import com.maksim.rasporski.filestorage.exception.UserAlreadyExistsException;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
public interface AppUserService {
    void register(AppUser user) throws UserAlreadyExistsException;
    boolean checkIfUserExist(String email);

}
