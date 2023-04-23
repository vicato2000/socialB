package com.cbd.socialb;

import com.cbd.socialb.node.Comment;
import com.cbd.socialb.node.Post;
import com.cbd.socialb.node.Tag;
import com.cbd.socialb.node.User;
import com.cbd.socialb.repository.CommentRepository;
import com.cbd.socialb.repository.PostRepository;
import com.cbd.socialb.repository.TagRepository;
import com.cbd.socialb.repository.UserRepository;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ShellComponent
@ShellCommandGroup("Data Commands")
public class DataCommands {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;


    public DataCommands(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.tagRepository = tagRepository;
    }

    @ShellMethod("Loads initial data into the database")
    public void loadData() {

        this.tagRepository.deleteAll();
        this.commentRepository.deleteAll();
        this.postRepository.deleteAll();
        this.userRepository.deleteAll();

        // Cration users

        List<User> usersToAdd = new ArrayList<>();

        User user1 = new User();
        user1.setUsername("username1");
        user1.setEmail("user1@u.com");
        user1.setFirstName("FirstName1");
        user1.setLastName("LastName1");

        User user2 = new User();
        user2.setUsername("username2");
        user2.setEmail("user2@u.com");
        user2.setFirstName("FirstName2");
        user2.setLastName("LastName2");

        User user3 = new User();
        user3.setUsername("username3");
        user3.setEmail("user3@u.com");
        user3.setFirstName("FirstName3");
        user3.setLastName("LastName3");

        User user4 = new User();
        user4.setUsername("username4");
        user4.setEmail("user4@u.com");
        user4.setFirstName("FirstName4");

        User user5 = new User();
        user5.setUsername("username5");
        user5.setEmail("user5@u.com");
        user5.setFirstName("FirstName5");
        user5.setLastName("LastName5");

        User user6 = new User();
        user6.setUsername("username6");
        user6.setEmail("user6@u.com");
        user6.setFirstName("FirstName6");
        user6.setLastName("LastName6");

        User user7 = new User();
        user7.setUsername("username7");
        user7.setEmail("user7@u.com");
        user7.setFirstName("FirstName7");
        user7.setLastName("LastName7");

        User user8 = new User();
        user8.setUsername("username8");
        user8.setEmail("user8@u.com");
        user8.setFirstName("FirstName8");
        user8.setLastName("LastName8");

        User user9 = new User();
        user9.setUsername("username9");
        user9.setEmail("user9@u.com");
        user9.setFirstName("FirstName9");
        user9.setLastName("LastName9");

        User user10 = new User();
        user10.setUsername("username10");
        user10.setEmail("user10@u.com");
        user10.setFirstName("FirstName10");
        user10.setLastName("LastName10");

        usersToAdd.add(user1);
        usersToAdd.add(user2);
        usersToAdd.add(user3);
        usersToAdd.add(user4);
        usersToAdd.add(user5);
        usersToAdd.add(user6);
        usersToAdd.add(user7);
        usersToAdd.add(user8);
        usersToAdd.add(user9);
        usersToAdd.add(user10);

        List<User> users = this.userRepository.saveAll(usersToAdd);

        user1 = users.stream().filter(u -> u.getUsername().equals("username1")).findFirst().get();
        user2 = users.stream().filter(u -> u.getUsername().equals("username2")).findFirst().get();
        user3 = users.stream().filter(u -> u.getUsername().equals("username3")).findFirst().get();
        user4 = users.stream().filter(u -> u.getUsername().equals("username4")).findFirst().get();
        user5 = users.stream().filter(u -> u.getUsername().equals("username5")).findFirst().get();
        user6 = users.stream().filter(u -> u.getUsername().equals("username6")).findFirst().get();
        user7 = users.stream().filter(u -> u.getUsername().equals("username7")).findFirst().get();
        user8 = users.stream().filter(u -> u.getUsername().equals("username8")).findFirst().get();
        user9 = users.stream().filter(u -> u.getUsername().equals("username9")).findFirst().get();
        user10 = users.stream().filter(u -> u.getUsername().equals("username10")).findFirst().get();


        List<User> followingUser1 = new ArrayList<>();
        followingUser1.add(user2);
        followingUser1.add(user3);
        followingUser1.add(user5);
        followingUser1.add(user6);
        followingUser1.add(user8);

        user1.addFollowings(followingUser1);

        this.userRepository.save(user1);

        List<User> followingUser2 = new ArrayList<>();
        followingUser2.add(user1);
        followingUser2.add(user4);
        followingUser2.add(user6);
        followingUser2.add(user7);

        user2.addFollowings(followingUser2);

        this.userRepository.save(user2);

        List<User> followingUser3 = new ArrayList<>();
        followingUser3.add(user6);
        followingUser3.add(user8);
        followingUser3.add(user10);

        user3.addFollowings(followingUser3);

        this.userRepository.save(user3);

        List<User> followingUser4 = new ArrayList<>();
        followingUser4.add(user1);
        followingUser4.add(user10);

        user4.addFollowings(followingUser4);

        this.userRepository.save(user4);

        // Creation tags

        List<Tag> tagsToAdd = new ArrayList<>();

        Tag tag1 = new Tag();
        tag1.setName("tag1");

        Tag tag2 = new Tag();
        tag2.setName("tag2");

        Tag tag3 = new Tag();
        tag3.setName("tag3");

        Tag tag4 = new Tag();
        tag4.setName("tag4");

        tagsToAdd.add(tag1);
        tagsToAdd.add(tag2);
        tagsToAdd.add(tag3);
        tagsToAdd.add(tag4);

        List<Tag> tags = this.tagRepository.saveAll(tagsToAdd);

        for (Tag tag : tags){
            switch (tag.getName()){
                case "tag1":
                    tag1 = tag;
                    continue;
                case "tag2":
                    tag2 = tag;
                    continue;
                case "tag3":
                    tag3 = tag;
                    continue;
                default:
                    tag4=tag;
                    continue;
            }
        }

        // Create comments

        List<Comment> commentsToAdd = new ArrayList<>();

        Comment comment1 = new Comment();
        comment1.setContent("Comment1");

        Comment comment2 = new Comment();
        comment2.setContent("Comment2");

        Comment comment3 = new Comment();
        comment3.setContent("Comment3");

        Comment comment4 = new Comment();
        comment4.setContent("Comment4");

        Comment comment5 = new Comment();
        comment5.setContent("Comment5");

        Comment comment6 = new Comment();
        comment6.setContent("Comment6");

        Comment comment7 = new Comment();
        comment7.setContent("Comment7");

        Comment comment8 = new Comment();
        comment8.setContent("Comment8");

        Comment comment9 = new Comment();
        comment9.setContent("Comment9");

        Comment comment10 = new Comment();
        comment10.setContent("Comment10");

        Comment comment11 = new Comment();
        comment11.setContent("Comment11");

        Comment comment12 = new Comment();
        comment12.setContent("Comment12");

        Comment comment13 = new Comment();
        comment13.setContent("Comment13");

        Comment comment14 = new Comment();
        comment14.setContent("Comment14");

        Comment comment15 = new Comment();
        comment15.setContent("Comment15");

        commentsToAdd.add(comment1);
        commentsToAdd.add(comment2);
        commentsToAdd.add(comment3);
        commentsToAdd.add(comment4);
        commentsToAdd.add(comment5);
        commentsToAdd.add(comment6);
        commentsToAdd.add(comment7);
        commentsToAdd.add(comment8);
        commentsToAdd.add(comment9);
        commentsToAdd.add(comment10);
        commentsToAdd.add(comment11);
        commentsToAdd.add(comment12);
        commentsToAdd.add(comment13);
        commentsToAdd.add(comment14);
        commentsToAdd.add(comment15);

        List<Comment> comments = this.commentRepository.saveAll(commentsToAdd);

        // Users add comment

        for (int i = 0; i < comments.size(); i++){
            User u = users.get(i%users.size());
            u.addComment(comments.get(i));
            this.userRepository.save(u);
        }


        // Create posts

        List<Post> postsToAdd = new ArrayList<>();

        Post post1 = new Post();
        post1.setTitle("Title1");
        post1.setContent("Content1");
        post1.addTag(tags.get(1%tags.size()));
        post1.addComment(comments.get(1%comments.size()));

        Post post2 = new Post();
        post2.setTitle("Title2");
        post2.setContent("Content2");
        post2.addTag(tags.get(2%tags.size()));
        post2.addComment(comments.get(2%comments.size()));

        Post post3 = new Post();
        post3.setTitle("Title3");
        post3.setContent("Content3");
        post3.addTag(tags.get(3%tags.size()));
        post3.addComment(comments.get(3%comments.size()));

        Post post4 = new Post();
        post4.setTitle("Title4");
        post4.setContent("Content4");
        post4.addTag(tags.get(4%tags.size()));
        post4.addComment(comments.get(4%comments.size()));

        Post post5 = new Post();
        post5.setTitle("Title5");
        post5.setContent("Content5");
        post5.addTag(tags.get(5%tags.size()));
        post5.addComment(comments.get(5%comments.size()));

        Post post6 = new Post();
        post6.setTitle("Title6");
        post6.setContent("Content6");
        post6.addTag(tags.get(6%tags.size()));
        post6.addComment(comments.get(6%comments.size()));

        Post post7 = new Post();
        post7.setTitle("Title7");
        post7.setContent("Content7");
        post7.setIsPublic(false);
        post7.addTag(tags.get(7%tags.size()));
        post7.addComment(comments.get(7%comments.size()));

        Post post8 = new Post();
        post8.setTitle("Title8");
        post8.setContent("Content8");
        post8.addTag(tags.get(8%tags.size()));
        post8.addComment(comments.get(8%comments.size()));

        Post post9 = new Post();
        post9.setTitle("Title9");
        post9.setContent("Content9");
        post9.addTag(tags.get(9%tags.size()));
        post9.addComment(comments.get(9%comments.size()));

        Post post10 = new Post();
        post10.setTitle("Title10");
        post10.setContent("Content10");
        post10.addTag(tags.get(10%tags.size()));
        post10.addComment(comments.get(10%comments.size()));

        Post post11 = new Post();
        post11.setTitle("Title11");
        post11.setContent("Content11");
        post11.addTag(tags.get(11%tags.size()));
        post11.addComment(comments.get(11%comments.size()));

        Post post12 = new Post();
        post12.setTitle("Title12");
        post12.setContent("Content12");
        post12.addTag(tags.get(12%tags.size()));
        post12.addComment(comments.get(12%comments.size()));

        Post post13 = new Post();
        post13.setTitle("Title13");
        post13.setContent("Content13");
        post13.addTag(tags.get(13%tags.size()));
        post13.addComment(comments.get(13%comments.size()));

        Post post14 = new Post();
        post14.setTitle("Title14");
        post14.setContent("Content14");
        post14.setIsPublic(false);
        post14.addTag(tags.get(14%tags.size()));
        post14.addComment(comments.get(14%comments.size()));

        Post post15 = new Post();
        post15.setTitle("Title15");
        post15.setContent("Content15");
        post15.addTag(tags.get(15%tags.size()));
        post15.addComment(comments.get(15%comments.size()));

        postsToAdd.add(post1);
        postsToAdd.add(post2);
        postsToAdd.add(post3);
        postsToAdd.add(post4);
        postsToAdd.add(post5);
        postsToAdd.add(post6);
        postsToAdd.add(post7);
        postsToAdd.add(post8);
        postsToAdd.add(post9);
        postsToAdd.add(post10);
        postsToAdd.add(post11);
        postsToAdd.add(post12);
        postsToAdd.add(post13);
        postsToAdd.add(post14);
        postsToAdd.add(post15);

        List<Post> posts = this.postRepository.saveAll(postsToAdd);

        for (int i = 0; i < posts.size(); i++) {
            User u1 = users.get(i%users.size());
            User u2 = users.get((i+1)%users.size());

            u1.addPost(posts.get(i));
            u2.addLikes(posts.get(i));

            this.userRepository.save(u1);
            this.userRepository.save(u2);

        }

    }
}
