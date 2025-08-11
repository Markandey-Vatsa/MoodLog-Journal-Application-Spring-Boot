package net.personalProject.journalApp.controller;


import net.personalProject.journalApp.Services.UserService;
import net.personalProject.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @PostMapping("/create-user")
    public void createUser(@RequestBody @Valid User user) {
        userService.saveNewUserEntry(user);
    }
}
