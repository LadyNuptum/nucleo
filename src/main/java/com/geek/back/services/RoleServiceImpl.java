package com.geek.back.services;

import com.geek.back.models.Role;
import com.geek.back.repositories.RolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RolRepository rolRepository;

    public RoleServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Role> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return rolRepository.save(role);
    }

    @Override
    public Optional<Role> deleteById(Long id) {
        return rolRepository.findById(id).map(r -> { rolRepository.deleteById(id); return r; });
    }

    @Override
    public Role findByNombreOrCreate(String name) {
        return rolRepository.findByName(name)
                .orElseGet(() -> rolRepository.save(Role.builder().name(name).build()));
    }
}