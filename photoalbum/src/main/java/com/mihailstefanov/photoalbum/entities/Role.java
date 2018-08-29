package com.mihailstefanov.photoalbum.entities;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import com.mihailstefanov.photoalbum.enums.RoleAuthority;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
	
	private static final long serialVersionUID = 9203709143346521484L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(nullable = false, unique = true, updatable = false)
	private String id;
	
	@Basic
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RoleAuthority authority;
	
	@ManyToMany(mappedBy = "authorities")
	private Set<User> users;
	
	public Role() {
	}
	
	public Role(RoleAuthority authority) {
		this.authority = authority;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getAuthority() {
		return authority.name();
	}

	public void setAuthority(RoleAuthority authority) {
		this.authority = authority;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
