package com.cbd.socialb.node;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;

import java.util.*;

@Node("Post")
@Getter
@Setter
public class Post {

    //Attributes

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String content;

    @NotNull
    private Date creationDate = new Date();

    @NotNull
    private Boolean isPublic = true;

    //Relationships

    @Relationship(type = "TAGGED", direction = Relationship.Direction.OUTGOING)
    private Set<Tag> tags;

    public void addTag(Tag tag){

        if (this.tags == null){
            this.tags = new HashSet<>();
        }

        this.tags.add(tag);

    }

    public void addTags(List<Tag> tags){

        for (Tag tag : tags){
            this.addTag(tag);
        }

    }

    public void removeTag(Tag tag){
        this.tags.remove(tag);
    }

    @Relationship(type = "COMMENT", direction = Relationship.Direction.OUTGOING)
    private Set<Comment> comments;


    public void addComment (Comment comment){

        if(this.comments == null){
            this.comments = new HashSet<>();
        }

        this.comments.add(comment);
    }

}
