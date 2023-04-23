package com.cbd.socialb.repository;

import com.cbd.socialb.node.Post;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "post", path = "post")
public interface PostRepository extends Neo4jRepository<Post, Long> {



}
