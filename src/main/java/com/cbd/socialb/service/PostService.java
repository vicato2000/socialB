package com.cbd.socialb.service;

import com.cbd.socialb.node.Comment;
import com.cbd.socialb.node.Post;
import com.cbd.socialb.node.Tag;
import com.cbd.socialb.node.User;
import com.cbd.socialb.repository.PostRepository;
import com.cbd.socialb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @Transactional(readOnly = true)
    public Post findPostById(Long id) {
        Optional<Post> post = this.postRepository.findById(id);

        if (post.isEmpty()){
            throw new RuntimeException("Post not found");
        }

        return post.get();

    }

    @Transactional(readOnly = true)
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Post> findPostByUsername(String username) {
        List<Post> posts =  postRepository.findPostByUsername(username);

        return returnPosts(posts);
    }

    @Transactional(readOnly = true)
    public Integer countLikesByPostId(Long id) {

        return postRepository.countLikesByPostId(id);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllPostLikesByUsernmae(String username) {
        List<Post> posts = postRepository.findAllPostLikesByUsernmae(username);

        return returnPosts(posts);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllPostByTag(String tag) {
        List<Post> posts = postRepository.findAllPostByTag(tag);

        return returnPosts(posts);
    }

    @Transactional(readOnly = true)
    public List<User> findAllUserLikesByPostId(Long id) {
        List<String> result = postRepository.findAllUserLikesByPostId(id);

        return returnUsers(result);
    }

    @Transactional(readOnly = false)
    public Post savePost(Post post, String username) {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Set<Tag> tags = post.getTags();

        Set<Tag> newTags = new HashSet<>();

        for (Tag tag : tags) {
            Tag newTag = tagService.findOrCreateTag(tag.getName());
            newTags.add(newTag);
        }

        post.setTags(newTags);

        post = this.postRepository.save(post);

        user.addPost(post);

        this.userRepository.save(user);



        return post;
    }

    @Transactional(readOnly = false)
    public Post likePost(Long id, String username) {

        Post post = postRepository.findById(id).orElse(null);

        if (post == null) {
            throw new RuntimeException("Post not found");
        }

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        user.addLikes(post);

        this.userRepository.save(user);

        return post;
    }

    @Transactional(readOnly = false)
    public Post unlikePost(Long id, String username) {

          Post post =  this.postRepository.deleteLikeByUsernameAndId(username, id);

          return this.findPostById(id);

    }

    @Transactional(readOnly = false)
    public Post addTag(Long id, String tag) {

        Post post = postRepository.findById(id).orElse(null);

        if (post == null) {
            throw new RuntimeException("Post not found");
        }

        Tag newTag = tagService.findOrCreateTag(tag);

        post.addTag(newTag);

        return this.postRepository.save(post);

    }

    @Transactional(readOnly = false)
    public Post removeTag(Long id, String tag) {

        Post post = postRepository.findById(id).orElse(null);

        if (post == null) {
            throw new RuntimeException("Post not found");
        }

        Tag newTag = tagService.findOrCreateTag(tag);

        if (post.getTags().stream().map(Tag::getName).toList().contains(newTag.getName())) {
            Post p = this.postRepository.deletePostTagById(id, newTag.getName());

            return this.findPostById(id);

        }else{
            throw new RuntimeException("Tag not found");
        }

    }

    @Transactional(readOnly = false)
    public Post addComment(Long id, String comment, String username) {

        Post post = postRepository.findById(id).orElse(null);

        if (post == null) {
            throw new RuntimeException("Post not found");
        }

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Comment c = new Comment();
        c.setContent(comment);
        c = this.commentService.saveComment(c);

        post.addComment(c);
        user.addComment(c);

        this.userRepository.save(user);

        return this.postRepository.save(post);

    }

    @Transactional(readOnly = false)
    public void deletePost(Long id){
        Post post  = this.postRepository.findById(id).orElse(null);

        if (post == null) {
            throw new RuntimeException("Post not found");
        }

        this.postRepository.delete(post);
    }

    // Utils
    public List<Post> returnPosts(List<Post> posts) {

        List<Post> result;

        List<Post> allPost = this.findAllPosts();

        List<Long> postIds = posts.stream().map(Post::getId).toList();

        result = allPost.stream().filter(p -> postIds.contains(p.getId())).toList();

        return result;
    }

    public List<User> returnUsers(List<String> usersnames){
        List<User> result;

        List<User> allUsers = this.userRepository.findAll();

        result = allUsers.stream().filter(u -> usersnames.contains(u.getUsername())).toList();

        return result;
    }

}
