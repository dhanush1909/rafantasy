package com.rafantasy.userservice.user.controller;

import com.rafantasy.userservice.user.dto.ChangePasswordDTO;
import com.rafantasy.userservice.user.model.User;
import com.rafantasy.userservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * User Controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;
    /**
     * Fetches all the users.
     *
     * @return List of users.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.fetchAllUsers();
    }

    /**
     * To fetch a user with matching username.
     *
     * @param user The user with matched username.
     * @return The user with matched username.
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{username}")
    public User getAUser(@PathVariable("username") User user) {
        return user;
    }

    /**
     * Method to lock or unlock a user.
     *
     * @param user The target user whose isNonLocked property is to be toggled.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/lock-toggle/{username}")
    public void lockOrUnlockAUser(@PathVariable("username") User user) {
        userService.lockUser(user);
    }

    /**
     * Method to change the password of a user.
     *
     * @param user              The user with matched username.
     * @param changePasswordDTO DTO with old and new password details.
     */
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/change-password/{username}")
    public void changePassword(@PathVariable("username") User user,
                               @Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        userService.changePassword(user, changePasswordDTO);
    }

    /**
     * Method to toggle Admin privileges.
     *
     * @param user User with matching username.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin-toggle/{username}")
    public void toggleAdmin(@PathVariable("username") User user) {
        userService.toggleAdmin(user);
    }
}
