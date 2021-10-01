package com.endava.parkinglot.controllers;

import com.endava.parkinglot.service.UserService;
import com.endava.parkinglot.user.User;
import com.endava.parkinglot.utils.Mappings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.endava.parkinglot.utils.Mappings.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping(USERS)
    public ResponseEntity showUserList(){
         return new ResponseEntity(userService.getUsers(),HttpStatus.OK);
    }


    @GetMapping(USER+"/{id}")
    public ResponseEntity showUserWithId(@PathVariable int id){
        return new ResponseEntity(userService.getUserWithId(id), HttpStatus.OK);
    }

    @PostMapping(ADD_USER)
    public ResponseEntity addNewUser(@RequestBody User user){

        return new ResponseEntity(userService.saveUser(user),HttpStatus.CREATED);

    }

    @DeleteMapping(DELETE_USER+"/{id}")
    public ResponseEntity deleteUserById( @PathVariable int id){
  userService.deleteUserById(id);
  return ResponseEntity.ok(HttpStatus.OK);

    }

    @PutMapping(UPDATE_USER)
    public ResponseEntity updateUser(@RequestBody User updatedUser){

        return new ResponseEntity(userService.updateUser(updatedUser),HttpStatus.OK);
    }




}
