package com.geek.back.services;

import com.geek.back.models.Rol;
import com.geek.back.models.Usuario;
import com.geek.back.repositories.RolRepository;
import com.geek.back.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        // Resuelve roles: si vienen con id los carga; si vienen con nombre los busca o crea.
        Set<Rol> resolved = new HashSet<>();
        if (usuario.getRoles() != null) {
            for (Rol r : usuario.getRoles()) {
                if (r.getId() != null) {
                    rolRepository.findById(r.getId()).ifPresent(resolved::add);
                } else if (r.getNombre() != null) {
                    Rol rol = rolRepository.findByNombre(r.getNombre())
                            .orElseGet(() -> rolRepository.save(Rol.builder().nombre(r.getNombre()).build()));
                    resolved.add(rol);
                }
            }
        }
        usuario.setRoles(resolved);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> deleteById(Long id) {
        return usuarioRepository.findById(id).map(u -> { usuarioRepository.deleteById(id); return u; });
    }

    @Override
    public Usuario createWithRoleNames(Usuario usuario, Set<String> roleNames) {
        Set<Rol> roles = roleNames.stream()
                .map(name -> rolRepository.findByNombre(name)
                        .orElseGet(() -> rolRepository.save(Rol.builder().nombre(name).build())))
                .collect(Collectors.toSet());
        usuario.setRoles(roles);
        return usuarioRepository.save(usuario);
    }
}