package com.cbd.socialb.node;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Date;
import java.util.UUID;

@Node("Comment")
@Getter
@Setter
public class Comment {

    //Attributes

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    private String content;

    @NotNull
    private Date creationDate = new Date();

    //Relationships


}
