package com.acyuta.userservice.domain.controller;

import com.acyuta.userservice.domain.dto.ChangePasswordDTO;
import com.acyuta.userservice.domain.model.Image;
import com.acyuta.userservice.domain.model.User;
import com.acyuta.userservice.domain.service.ImageRepository;
import com.acyuta.userservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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

    private final ImageRepository imageRepository;

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

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{username}/upload-image")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable("username") User user) {
        System.out.println(user.getId());
        try {
            imageRepository.save(new Image()
                    .setUserId(user.getId())
                    .setImageBytes(image.getBytes()));
        } catch (IOException e) {
            log.error("Error while uploading image - ", e.getMessage());
        }
        return ResponseEntity.ok("Saved image");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{username}/get-images")
    public List<Image> uploadImage(@PathVariable("username") User user) {
        return imageRepository.findAllByUserId(user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{username}/get-images/{imageId}")
    public Image getImage(@PathVariable("username") User user, @PathVariable("imageId") Image image) {
        return image;
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{username}/get-images/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable("username") User user, @PathVariable("imageId") Image image) {
        imageRepository.delete(image);
        return ResponseEntity.ok("deleted");
    }
}
