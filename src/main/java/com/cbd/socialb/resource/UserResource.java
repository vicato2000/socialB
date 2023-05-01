package com.cbd.socialb.resource;

import com.cbd.socialb.node.User;
import com.cbd.socialb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@CrossOrigin(origins = "*")
public class UserResource {


    public UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{username}", produces = "application/json")
    public User getUserByUsername(@PathVariable("username") String username){
        return this.userService.findByUsername(username);
    }

    @GetMapping(value = "/users", produces = "application/json")
    public List<User> getAll(){

        return this.userService.findAll();
    }


    @GetMapping(value = "/users/usernames", produces = "application/text")
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

    @GetMapping(value = "/users/{username}/followers", produces = "application/json")
    public List<User> getFollowersByUsername(@PathVariable("username") String username){
        return this.userService.findFollowersByUsername(username);
    }

    @GetMapping(value = "/users/{username}/following", produces = "application/json")
    public List<User> getFollowingByUsername(@PathVariable("username") String username){
        return this.userService.findFollowingByUsername(username);
    }

    @PostMapping(value = "/users/new", consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody User user){
        return this.userService.createUser(user);
    }

    @PostMapping(value = "/users/{username1}/follow/{username2}", produces = "application/json")
    public List<User> addFollow(@PathVariable("username1") String username1, @PathVariable("username2") String username2){
        return this.userService.addFollow(username1, username2);
    }

    @PutMapping(value = "/users/{username1}/unfollow/{username2}", produces = "application/json")
    public User removeFollow(@PathVariable("username1") String username1, @PathVariable("username2") String username2){
        return this.userService.removeFollow(username1, username2);
    }


}
