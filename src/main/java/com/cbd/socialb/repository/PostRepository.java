package com.cbd.socialb.repository;

import com.cbd.socialb.node.Post;
import com.cbd.socialb.node.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "post", path = "post")
public interface PostRepository extends Neo4jRepository<Post, Long> {

    @Query("MATCH (u:User)-[:POSTED]->(p:Post) WHERE u.username = $username RETURN p")
    public List<Post> findPostByUsername(@Param("username") String username);

    @Query("MATCH (p:Post)<-[:LIKED]-(u:User) WHERE id(p) = $id RETURN COUNT(u)")
    public Integer countLikesByPostId(@Param("id") Long id);

    @Query("MATCH (p:Post)<-[:LIKED]-(u:User) WHERE u.username = $username RETURN p")
    public List<Post> findAllPostLikesByUsernmae(@Param("username") String username);

    @Query("MATCH (u:User)-[:LIKED]->(p:Post) WHERE id(p) = $id RETURN DISTINCT u.username")
    public List<String> findAllUserLikesByPostId(@Param("id") Long id);

    @Query("MATCH (p:Post)-[:TAGGED]->(t:Tag) WHERE t.name = $tag RETURN p")
    public List<Post> findAllPostByTag(@Param("tag") String tag);

    @Query("MATCH (p:Post)<-[l:LIKED]-(u:User) WHERE u.username = $username AND id(p) = $id DELETE l RETURN p")
    public Post deleteLikeByUsernameAndId(@Param("username") String username,@Param("id") Long id);

    @Query("MATCH (p:Post)-[tr:TAGGED]->(t:Tag) WHERE id(p) = $id  and t.name = $tag DELETE tr RETURN p")
    public Post deletePostTagById(@Param("id") Long id, @Param("tag") String tag);





}
