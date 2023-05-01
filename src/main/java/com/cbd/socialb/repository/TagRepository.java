package com.cbd.socialb.repository;

import com.cbd.socialb.node.Tag;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tag", path = "tag")
public interface TagRepository extends Neo4jRepository<Tag, Long> {

    public Tag findByName(String name);

}
