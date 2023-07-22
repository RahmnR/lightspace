package com.enigma.lightspace.service;

import com.enigma.lightspace.entity.Role;
import com.enigma.lightspace.entity.constant.ERole;

public interface RoleService {
    public Role getOrSave(ERole role);
}
