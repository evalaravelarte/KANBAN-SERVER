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

    @Autowired
    UserRepository oUserRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    public UserEntity get(Long id) {
        return oUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public Page<UserEntity> getPage(Pageable oPageable) {
        return oUserRepository.findAll(oPageable);
    }

    public Long create(UserEntity oUserEntity) {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();    //Coge el username del token
        UserEntity oUserEntityInSession = oUserRepository.findByUsername(strJWTusername)    //Busca el usuario en la base de datos
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (Boolean.FALSE.equals(oUserEntityInSession.getRole())) { //Admin
            oUserEntity.setId(null);
            oUserEntity.setPassword("AD67DC1D98993ADA5A163B95C92D17498100320829720FE5019E86E788EE3CE3");
            return oUserRepository.save(oUserEntity).getId();
        } else {
            throw new ResourceNotFoundException("Unauthorized");
        }
    }

    public UserEntity update(UserEntity oUserEntity) {
         String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        UserEntity oUserEntityInSession = oUserRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (Boolean.FALSE.equals(oUserEntityInSession.getRole())) {
            oUserEntity.setId(null);
            oUserEntity.setPassword("AD67DC1D98993ADA5A163B95C92D17498100320829720FE5019E86E788EE3CE3");
            return oUserRepository.save(oUserEntity);
        } else {
            throw new ResourceNotFoundException("Unauthorized");
        }
    }

    public Long delete(Long id) {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        UserEntity oUserEntityInSession = oUserRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (Boolean.FALSE.equals(oUserEntityInSession.getRole())) {
            oUserRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotFoundException("Unauthorized");
        }
    }

    @Transactional
    public Long empty() {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();

        oUserRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        oUserRepository.deleteAll();
        oUserRepository.resetAutoIncrement();
        return oUserRepository.count();
    }

}
