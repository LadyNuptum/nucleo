package com.geek.back.services;

import com.geek.back.models.Rol;

public interface RolService extends Service<Rol> {
    Rol findByNombreOrCreate(String nombre);
}