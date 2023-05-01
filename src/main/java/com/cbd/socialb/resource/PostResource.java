package com.cbd.socialb.resource;

import com.cbd.socialb.node.Comment;
import com.cbd.socialb.node.Post;
import com.cbd.socialb.node.User;
import com.cbd.socialb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts", produces = "application/json")
    public List<Post> getAllPosts() {
        return postService.findAllPosts();
    }

    @GetMapping(value = "/posts/{id}", produces = "application/json")
    public Post getPostById(@PathVariable("id") Long id) {
        return postService.findPostById(id);
    }

    @GetMapping(value = "/posts/username/{username}", produces = "application/json")
    public List<Post> getPostByUsername(@PathVariable("username") String username) {
        return postService.findPostByUsername(username);
    }

    @GetMapping(value = "/posts/{id}/count-likes", produces = "application/json")
    public Integer countLikesByPostId(@PathVariable("id") Long id) {
        return postService.countLikesByPostId(id);
    }

    @GetMapping(value = "/posts/{username}/likes", produces = "application/json")
    public List<Post> getAllPostLikesByUsernmae(@PathVariable("username") String username) {
        return postService.findAllPostLikesByUsernmae(username);
    }

    @GetMapping(value = "/posts/tag/{tag}", produces = "application/json")
    public List<Post> getAllPostByTag(@PathVariable("tag") String tag) {
        return postService.findAllPostByTag(tag);
    }

    @GetMapping(value = "/posts/{id}/likes/users", produces = "application/json")
    public List<User> getAllUserLikesByPostId(@PathVariable("id") Long id) {
        return postService.findAllUserLikesByPostId(id);
    }

    @PostMapping(value = "/posts/{username}", produces = "application/json", consumes = "application/json")
    public Post createPost(@PathVariable("username") String username, @RequestBody Post post){
        return postService.savePost(post,username);
    }

    @PutMapping(value = "/posts/{id}/likes/{username}", produces = "application/json")
    public Post likePost(@PathVariable("id") Long id, @PathVariable("username") String username) {
        return postService.likePost(id, username);
    }

    @PutMapping(value = "/posts/{id}/unlikes/{username}", produces = "application/json")
    public Post unlikePost(@PathVariable("id") Long id, @PathVariable("username") String username) {
        return postService.unlikePost(id, username);
    }

    @PutMapping(value = "/posts/{id}/tags/{tag}/add", produces = "application/json")
    public Post addTagToPost(@PathVariable("id") Long id, @PathVariable("tag") String tag) {
        return postService.addTag(id, tag);
    }

    @PutMapping(value = "/posts/{id}/tags/{tag}/remove", produces = "application/json")
    public Post removeTagFromPost(@PathVariable("id") Long id, @PathVariable("tag") String tag) {
        return postService.removeTag(id, tag);
    }

    @PutMapping(value = "/posts/{id}/comments/{username}", produces = "application/json")
    public Post addCommentToPost(@PathVariable("id") Long id, @PathVariable("username") String username, @RequestBody Comment comment) {
        return postService.addComment(id, comment.getContent(), username);
    }

    @DeleteMapping(value = "/posts/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
    }

}
