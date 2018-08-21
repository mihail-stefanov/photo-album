package com.mihailstefanov.photoalbum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mihailstefanov.photoalbum.entities.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
