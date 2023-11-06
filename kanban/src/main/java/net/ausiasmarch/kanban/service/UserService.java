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

    public Long create(UserEntity oUserEntity) {
        oUserEntity.setId(null);
        return oUserRepository.save(oUserEntity).getId();
    }

    public UserEntity update(UserEntity oUserEntity) {
        return oUserRepository.save(oUserEntity);
    }

    public Long delete(Long id) {
        oUserRepository.deleteById(id);
        return id;
    }

    public Page<UserEntity> getPage(Pageable oPageable) {
        return oUserRepository.findAll(oPageable);
    }

   @Transactional
    public Long empty(){
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();

        oUserRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        oUserRepository.deleteAll();
        oUserRepository.resetAutoIncrement();
        return oUserRepository.count();
    }  

    
}
