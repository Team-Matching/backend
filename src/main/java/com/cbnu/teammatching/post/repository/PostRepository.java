package com.cbnu.teammatching.post.repository;

import com.cbnu.teammatching.category.domain.Category;
import com.cbnu.teammatching.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategory(Category category);

    @Query("SELECT p FROM Post p WHERE p.isDeleted = false ORDER BY p.creationDate DESC")
    List<Post> findAllByOrderByCreationDateDesc();

    @Query("SELECT p FROM Post p WHERE p.category = :category AND p.isDeleted = false ORDER BY p.creationDate DESC")
    List<Post> findByCategoryOrderByCreationDateDesc(Category category);

    List<Post> findByMemberId(Long memberId);
}
