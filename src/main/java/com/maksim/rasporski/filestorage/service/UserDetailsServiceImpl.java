package com.maksim.rasporski.filestorage.service;

import com.maksim.rasporski.filestorage.entity.AppUser;
import com.maksim.rasporski.filestorage.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        AppUser currentUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found", username)));
        return new UserDetailsImpl(currentUser);
    }
}
