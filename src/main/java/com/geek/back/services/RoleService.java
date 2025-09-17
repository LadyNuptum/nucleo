package com.geek.back.services;

import com.geek.back.models.Role;

public interface RoleService extends Service<Role> {
    Role findByNombreOrCreate(String nombre);
}