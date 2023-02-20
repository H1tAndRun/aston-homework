package ru.aston.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.dao.RoleDao;
import ru.aston.model.Role;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleDao roleDao;

    public List<Role> findAllRoles(){
       return roleDao.findAllRoles();
    }
}
