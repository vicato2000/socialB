package com.cbd.socialb.node;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;
import java.util.UUID;

@Node("Tag")
@Getter
@Setter
public class Tag {

    //Attributes

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    //Relationships

}
