package com.example.hexagonal.context.post.adapter.out.persistenc;

import com.example.hexagonal.context.post.domain.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMasterMapper {
    int insert(Post post);
    int update(Post post);
    int delete(Long id);
    Optional<Post> findById(@Param("id") Long id);
    List<Post> findAll();
} 