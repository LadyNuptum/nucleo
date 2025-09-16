package com.geek.back.services;

import com.geek.back.models.Usuario;

public interface UsuarioService extends Service<Usuario> {
    Usuario createWithRoleNames(Usuario usuario, java.util.Set<String> roleNames);
}