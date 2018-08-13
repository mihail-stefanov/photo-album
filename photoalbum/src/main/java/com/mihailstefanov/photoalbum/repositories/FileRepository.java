package com.mihailstefanov.photoalbum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mihailstefanov.photoalbum.entities.File;

public interface FileRepository extends JpaRepository<File, Long> {
}
