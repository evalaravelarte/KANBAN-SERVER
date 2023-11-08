package net.ausiasmarch.kanban.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import net.ausiasmarch.kanban.entity.UserEntity;
import net.ausiasmarch.kanban.exception.ResourceNotFoundException;
import net.ausiasmarch.kanban.repository.UserRepository;

@Service
public class UserService {

    private final String kanbanPASSWORD = "AD67DC1D98993ADA5A163B95C92D17498100320829720FE5019E86E788EE3CE3";

    @Autowired
    UserRepository oUserRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    public UserEntity get(Long id) {
        return oUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public Page<UserEntity> getPage(Pageable oPageable) {
        return oUserRepository.findAll(oPageable);
    }

    public Long create(UserEntity oUserEntity) {
        oSessionService.onlyAdmins();
        oUserEntity.setId(null);
        oUserEntity.setPassword(kanbanPASSWORD);
        return oUserRepository.save(oUserEntity).getId();
    }

    public UserEntity update(UserEntity oUserEntityToSet) {
        UserEntity oUserEntityFromDatabase = this.get(oUserEntityToSet.getId());
        oSessionService.onlyAdminsOrUsersWithItsOwnData(oUserEntityFromDatabase.getId());
        if (oSessionService.isUser()) {
            oUserEntityToSet.setId(null);
            oUserEntityToSet.setRole(oUserEntityFromDatabase.getRole());
            oUserEntityToSet.setPassword(kanbanPASSWORD);
            return oUserRepository.save(oUserEntityToSet);
        } else {
            oUserEntityToSet.setId(null);
            oUserEntityToSet.setPassword(kanbanPASSWORD);
            return oUserRepository.save(oUserEntityToSet);
        }
    }

    public Long delete(Long id) {
        oSessionService.onlyAdmins();
        oUserRepository.deleteById(id);
        return id;
    }

    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oUserRepository.deleteAll();
        oUserRepository.resetAutoIncrement();
        return oUserRepository.count();
    }

}
