package com.cbd.socialb.repository;

import com.cbd.socialb.node.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends Neo4jRepository<User, Long> {

    User findByUsernameOrEmail(String username, String email);

    @Query("MATCH (u:User) WHERE NOT ()-[f:FOLLOWS]->(u) RETURN u")
    List<User> findUserWithoutFollowers();

    @Query("MATCH (u:User) WHERE NOT (u)-[:FOLLOWS]->() RETURN u")
    List<User> findUserWithoutFollowing();

    @Query("MATCH (u:User)-[:FOLLOWS]->(f:User)-[:FOLLOWS]->(u) WHERE u.username = $username RETURN f")
    List<User> findFriendsByUserId(@Param("username") String username);


}
