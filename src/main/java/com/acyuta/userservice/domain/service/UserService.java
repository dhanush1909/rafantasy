package com.acyuta.userservice.domain.service;

import com.acyuta.userservice.domain.dto.ChangePasswordDTO;
import com.acyuta.userservice.domain.model.User;

import java.util.List;

/**
 * User Service.
 */
public interface UserService {

    /**
     * To fetch all users.
     *
     * @return List of users.
     */
    List<User> fetchAllUsers();

    /**
     * To lock a user.
     *
     * @param user The user.
     */
    void lockUser(User user);

    /**
     * To toggle admin privileges for a user.
     *
     * @param user The user.
     */
    void toggleAdmin(User user);

    /**
     * To change password of a user.
     *
     * @param user              The user.
     * @param changePasswordDTO The dto with old & new password.
     */
    void changePassword(User user, ChangePasswordDTO changePasswordDTO);

}

