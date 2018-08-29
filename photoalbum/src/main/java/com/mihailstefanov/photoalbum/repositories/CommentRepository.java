package com.mihailstefanov.photoalbum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mihailstefanov.photoalbum.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
