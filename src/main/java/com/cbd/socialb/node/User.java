package com.cbd.socialb.node;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Node("User")
@Getter
@Setter
public class User {

    //Attributes

    @Id
    @GeneratedValue
    private Long id;


    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    //Relationships

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.OUTGOING)
    @JsonIgnore
    private Set<User> following;


    public void addFollowing(User user){

        if (this.following == null) {
            this.following = new HashSet<>();
        }
        this.following.add(user);

    }

    public void addFollowings(List<User> users){

        users.forEach(this::addFollowing);

    }

    public void removeFollowing(User user){
        this.following.remove(user);
    }


    @Relationship(type = "POSTED", direction = Relationship.Direction.OUTGOING)
    private Set<Post> posts;

    public void addPost(Post post){

        if(this.posts == null){
            this.posts = new HashSet<>();
        }

        this.posts.add(post);

    }

    @Relationship(type = "COMMENTED", direction = Relationship.Direction.OUTGOING)
    private Set<Comment> comments;

    public void addComment (Comment comment) {

        if (this.comments == null){
            this.comments = new HashSet<>();
        }

        this.comments.add(comment);

    }

    @Relationship(type = "LIKED", direction = Relationship.Direction.OUTGOING)
    private Set<Post> likes;

    public void addLikes(Post post){

        if (this.likes == null){
            this.likes = new HashSet<>();
        }

        this.likes.add(post);

    }

}
