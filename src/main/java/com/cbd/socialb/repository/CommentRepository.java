package com.cbd.socialb.repository;

import com.cbd.socialb.node.Comment;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "comment", path = "comment")
public interface CommentRepository extends Neo4jRepository<Comment, Long> {
}
