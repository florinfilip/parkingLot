package com.endava.parkinglot.service.Impl;

import com.endava.parkinglot.exceptions.UserExistsException;
import com.endava.parkinglot.repositories.UserRepository;
import com.endava.parkinglot.service.UserService;
import com.endava.parkinglot.user.MyUserDetails;
import com.endava.parkinglot.user.role.Role;
import lombok.RequiredArgsConstructor;
import com.endava.parkinglot.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        User user = userOptional.orElseThrow(()->new UsernameNotFoundException("Username not found in database"));
        return new MyUserDetails(user);
    }

    @Override
    public List<User> getUsers() {
return userRepository.findAll()
        .stream()
        .collect(Collectors.toList());
    }

    @Override
    public User getUserWithId(int id){
        return userRepository.findById(id).get();

    }

    @Override
    public User saveUser(User newUser) throws UserExistsException {

       isUsernameAvailable(newUser.getUsername());

        User existingUser = userRepository.findById(newUser.getId()).orElse(new User());

        existingUser.setUsername(
                Optional.ofNullable(newUser.getUsername())
                        .orElse(existingUser.getUsername()));
        existingUser.setPassword(
                Optional.ofNullable(newUser.getPassword()).isPresent() ?
                        passwordEncoder.encode(newUser.getPassword()) :
                        existingUser.getPassword());
        existingUser.setEnabled(
                Optional.ofNullable(newUser.isEnabled())
                        .orElse(existingUser.isEnabled()));
        existingUser.setEmail(
                Optional.ofNullable(newUser.getEmail())
                        .orElse(existingUser.getEmail()));
        existingUser.setPhone(
                Optional.ofNullable(newUser.getPhone())
                        .orElse(existingUser.getPhone()));
        existingUser.setRoles(
                Optional.ofNullable(newUser.getRoles())
                        .orElse(existingUser.getRoles()));
        return userRepository.save(existingUser);

    }

    @Override
    public User updateUser(User updatedUser){
         userRepository.findById(updatedUser.getId()).orElseThrow(
                ()->new RuntimeException("Employee with id "+updatedUser.getId()+" not found")
        );
        return saveUser(updatedUser);
    }


public boolean isUsernameAvailable(String username){
    if(userRepository.findUserByUsername(username).isEmpty()
    )
        return true;
    else
        throw new UserExistsException("Username already in use!");
}



    @Override
    public void deleteUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if( userOptional.isPresent()){
            userRepository.deleteById(userOptional.get().getId());
        }
        else
        {
            throw new RuntimeException("User with the id " + id + " not found!");
        }
    }


    @Override
    public boolean userExists(String username) {

        return userRepository.findAll().stream()
                .filter(user->user.getUsername().equalsIgnoreCase(username))
                .findAny()
                .isPresent();
    }

    @Override
    public MyUserDetails getCurrentlyLoggedUser(Authentication authentication){

        String name = authentication.getName();
        Optional<User> userOptional = userRepository.findUserByUsername(name);
                User loggedUser = userOptional.orElseThrow(
                        ()->new UserExistsException("No user logged found")
                );
                return new MyUserDetails(loggedUser);

    }


        public List getRoleNames(User user){
       return user.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());

        }


}
