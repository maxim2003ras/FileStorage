package com.maksim.rasporski.filestorage.service;

import com.maksim.rasporski.filestorage.entity.AppUser;
import com.maksim.rasporski.filestorage.exception.UserAlreadyExistsException;
import com.maksim.rasporski.filestorage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Service
public class AppUserServiceImpl implements AppUserService{
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserServiceImpl(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(final AppUser user) throws UserAlreadyExistsException {
        if (checkIfUserExist(user.getEmail())) {
            throw new UserAlreadyExistsException("User with this email has been already created!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean checkIfUserExist(final String email) {
        return userRepository.existsByEmail(email);
    }
}
