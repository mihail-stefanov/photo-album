package com.mihailstefanov.photoalbum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mihailstefanov.photoalbum.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
