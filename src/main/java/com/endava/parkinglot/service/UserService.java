package com.endava.parkinglot.service;

import com.endava.parkinglot.user.MyUserDetails;
import com.endava.parkinglot.user.User;
import com.endava.parkinglot.user.role.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    @Override
    default UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
    List<User> getUsers();
    List getRoleNames(User user);
    User getUserWithId(int id);
    User updateUser(User updatedUser);
    User saveUser(User user);
    void deleteUserById(int id);
    boolean userExists(String username);
    MyUserDetails getCurrentlyLoggedUser(Authentication authentication);



}