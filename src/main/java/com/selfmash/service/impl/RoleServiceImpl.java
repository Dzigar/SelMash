package com.selfmash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.RoleDAO;
import com.selfmash.model.Role;
import com.selfmash.service.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    public Role getRole(long id) {
        return roleDAO.getRole(id);
    }

}
