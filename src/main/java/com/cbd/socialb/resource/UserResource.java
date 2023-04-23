package com.cbd.socialb.resource;

import com.cbd.socialb.node.User;
import com.cbd.socialb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class UserResource {


    public UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/users", produces = "application/json")
    public List<User> getAll(){

        return this.userService.findAll();
    }


    @GetMapping(value = "/users/usernames", produces = "application/json")
    public List<String> getAllUsers(){
        return this.userService.findAllUsernames();
    }

    @GetMapping(value = "/users/without-followers", produces = "application/json")
    public List<User> getUsersWithoutFollowers(){
        return this.userService.findUserWithoutFollowers();
    }

    @GetMapping(value = "/users/without-following", produces = "application/json")
    public List<User> getUsersWithoutFollowing(){
        return this.userService.findUserWithoutFollowing();
    }

    @GetMapping(value = "/users/{username}/friends", produces = "application/json")
    public List<User> getFriendsByUsername(@PathVariable("username") String username){
        return this.userService.findFriendsByUsername(username);
    }

    @PostMapping(value = "/users/new", consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody User user){
        return this.userService.createUser(user);
    }


}
