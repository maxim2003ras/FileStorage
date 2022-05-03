package com.maksim.rasporski.filestorage.service;

import com.maksim.rasporski.filestorage.entity.AppUser;
import com.maksim.rasporski.filestorage.exception.UserAlreadyExistsException;
import com.maksim.rasporski.filestorage.model.UserData;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
public interface AppUserService {
    void register(UserData user) throws UserAlreadyExistsException;
    boolean checkIfUserExist(String email);

}
