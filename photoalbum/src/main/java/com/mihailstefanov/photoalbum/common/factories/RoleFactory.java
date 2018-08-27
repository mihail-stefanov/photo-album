package com.mihailstefanov.photoalbum.common.factories;

import com.mihailstefanov.photoalbum.entities.Role;

public final class RoleFactory {
	
	public RoleFactory() {
	}
	
	public final Role createRole(String authority) {
		Role role = new Role();
		role.setAuthority(authority);
		return role;
	}
}
