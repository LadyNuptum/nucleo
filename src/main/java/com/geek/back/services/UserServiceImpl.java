package com.geek.back.services;

import com.geek.back.models.Role;
import com.geek.back.models.User;
import com.geek.back.repositories.RolRepository;
import com.geek.back.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RolRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RolRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        // Resuelve roles: si vienen con id los carga; si vienen con nombre los busca o crea.
        Set<Role> resolved = new HashSet<>();
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            for (Role r : user.getRoles()) {
                roleRepository.findByName(r.getName())
                        .ifPresent(resolved::add);
            }
        }
        if (resolved.isEmpty()) {
            Role defaultRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Default role ROLE_USER not found in DB"));
            resolved.add(defaultRole);
        }

        user.setRoles(resolved);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> deleteById(Long id) {
        return userRepository.findById(id).map(u -> { userRepository.deleteById(id); return u; });
    }

    @Override
    public User createWithRoleNames(User user, Set<String> roleNames) {
        Set<Role> roles = roleNames.stream()
                .map(name -> roleRepository.findByName(name)
                        .orElseGet(() -> roleRepository.save(Role.builder().name(name).build())))
                .collect(Collectors.toSet());
        user.setRoles(roles);
        return userRepository.save(user);
    }
}