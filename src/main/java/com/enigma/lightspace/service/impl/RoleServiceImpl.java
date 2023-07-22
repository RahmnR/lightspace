package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.Category;
import com.enigma.lightspace.entity.Role;
import com.enigma.lightspace.entity.constant.ERole;
import com.enigma.lightspace.repository.RoleRepository;
import com.enigma.lightspace.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getOrSave(ERole role) {
        return roleRepository.findByRole(role).orElseGet(() ->
                roleRepository.save(Role.builder().role(role).build()));
    }
}
