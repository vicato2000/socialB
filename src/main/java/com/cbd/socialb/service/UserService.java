package com.cbd.socialb.service;

import com.cbd.socialb.node.User;
import com.cbd.socialb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findByUsername(String username){
        return this.userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<String> findAllUsernames(){
        return this.userRepository.findAll().stream().map(User::getUsername).toList();
    }

    @Transactional(readOnly = true)
    public User findByUsernameOrEmail(String username, String email){
        return this.userRepository.findByUsernameOrEmail(username, email);
    }

    @Transactional(readOnly = true)
    public List<User> findUserWithoutFollowers(){

        List<User> users =  this.userRepository.findUserWithoutFollowers();

        return this.returnUsers(users);
    }

    @Transactional(readOnly = true)
    public List<User> findUserWithoutFollowing(){
        List<User> users =  this.userRepository.findUserWithoutFollowing();

        return this.returnUsers(users);
    }

    @Transactional(readOnly = true)
    public List<User> findFriendsByUsername(String username){
        List<User> users =  this.userRepository.findFriendsByUserId(username);

        return this.returnUsers(users);
    }

    @Transactional(readOnly = true)
    public List<User> findFollowersByUsername(String username){
        List<User> users =  this.userRepository.findFollowersByUserId(username);

        return this.returnUsers(users);
    }

    @Transactional(readOnly = true)
    public List<User> findFollowingByUsername(String username){
        List<User> users =  this.userRepository.findFollowingByUserId(username);

        return this.returnUsers(users);
    }

    @Transactional(readOnly = false)
    public List<User> addFollow(String username1, String username2){
        User user1 = this.userRepository.findByUsername(username1);
        User user2 = this.userRepository.findByUsername(username2);

        user1.addFollowing(user2);

        user1 = this.userRepository.save(user1);

        return this.findFriendsByUsername(user1.getUsername());
    }

    @Transactional(readOnly = false)
    public User removeFollow(String username1, String username2){
        User user1 = this.userRepository.findByUsername(username1);
        User user2 = this.userRepository.findByUsername(username2);

        if (user1 == null || user2 == null){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User not found");
        }else{
            return this.userRepository.removeUserFollow(user1.getUsername(), user2.getUsername());
        }
    }


    @Transactional(readOnly = false)
    public User createUser(User user){

        User userExists = this.userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());

        if (userExists != null) {
            List<String> errors = new ArrayList<>();

            if (userExists.getUsername().equals(user.getUsername())) {
                errors.add("Username already exists");
            }

            if (userExists.getEmail().equals(user.getEmail())) {
                errors.add("Email already exists");
            }

            if (!errors.isEmpty()){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.join(", ", errors));
            }

        }

        return this.userRepository.save(user);
    }

    // Utils

    private List<User> returnUsers(List<User> users){
        List<User> result;

        List<User> allUser = this.userRepository.findAll();
        List<String> userUsername = users.stream().map(User::getUsername).toList();

        result = allUser.stream().filter(u -> userUsername.contains(u.getUsername())).toList();

        return result;

    }

}
