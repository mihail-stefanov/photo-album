package com.mihailstefanov.photoalbum.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mihailstefanov.photoalbum.entities.Role;
import com.mihailstefanov.photoalbum.enums.RoleAuthority;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findOptionalByAuthority(RoleAuthority authority);
}
