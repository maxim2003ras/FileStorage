package com.maksim.rasporski.filestorage.logic;

import com.maksim.rasporski.filestorage.entity.UserRoles;
import com.maksim.rasporski.filestorage.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Component
public class DataLoader {
    private RoleRepository roleRepository;

    public DataLoader(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        saveRolesToDB();
    }

    private void saveRolesToDB() {
        try {
            Arrays.stream(Roles.values()).filter(role -> !roleRepository.existsByRole(role.name())).forEach(role -> roleRepository.save(new UserRoles(role.name())));
        } catch (Exception ignored) {

        }
    }


}
