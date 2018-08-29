package com.mihailstefanov.photoalbum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mihailstefanov.photoalbum.entities.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
	List<Photo> findByUser_Id(String userId);
}
