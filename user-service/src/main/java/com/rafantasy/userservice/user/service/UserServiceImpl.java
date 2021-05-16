package com.rafantasy.userservice.user.service;

import com.rafantasy.userservice.role.converter.RoleConverter;
import com.rafantasy.userservice.user.dto.ChangePasswordDTO;
import com.rafantasy.userservice.role.Role;
import com.rafantasy.userservice.role.RoleType;
import com.rafantasy.userservice.user.model.User;
import com.rafantasy.userservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static com.rafantasy.userservice.common.Constants.WRONG_PASSWORD_PROVIDED;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleConverter roleConverter;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void lockUser(User user) {
        userRepository.save(user.setIsNonLocked(!user.getIsNonLocked()));
    }

    @Override
    public void toggleAdmin(User user) {
        var roles = user.getRoles();

        // If ROLE_ADMIN is present, remove it. Else, add ROLE_ADMIN.
        if (roles.stream().map(Role::getRoleType).collect(Collectors.toSet()).contains(RoleType.ROLE_ADMIN)) {
            user.setRoles(roles.stream().filter(role -> !role.getRoleType().equals(RoleType.ROLE_ADMIN)).collect(Collectors.toSet()));
        } else {
            roles.add(roleConverter.convert(RoleType.ROLE_ADMIN));
        }

        userRepository.save(user);
    }

    @Override
    public void changePassword(User user, ChangePasswordDTO changePasswordDTO) {

        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, WRONG_PASSWORD_PROVIDED);

        userRepository.save(user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword())));
    }
}
