package net.ausiasmarch.kanban.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import net.ausiasmarch.kanban.entity.ListEntity;
import net.ausiasmarch.kanban.entity.UserEntity;
import net.ausiasmarch.kanban.exception.ResourceNotFoundException;
import net.ausiasmarch.kanban.repository.ListRepository;
import net.ausiasmarch.kanban.repository.UserRepository;

@Service
public class ListService {

    @Autowired
    ListRepository oListRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    UserRepository oUserRepository;

    @Autowired
    UserService oUserService;

    public ListEntity get(Long id) {
        return oListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("List not found"));
    }

    public Page<ListEntity> getPage(Pageable oPageable, Long userId) {
        if (userId == 0) {
            return oListRepository.findAll(oPageable);
        } else {
            return oListRepository.findByUserId(userId, oPageable);
        }
    }

    public Long create(ListEntity oListEntity) {
        oListEntity.setId(null);
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        UserEntity oUserEntityInSession = oUserRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (Boolean.TRUE.equals(oUserEntityInSession.getRole())) { // User
            oListEntity.setUser(oUserEntityInSession);
            return oListRepository.save(oListEntity).getId();
        } else {
            return oListRepository.save(oListEntity).getId();
        }

    }

    public ListEntity update(ListEntity oListEntity) {
        oListEntity = oListRepository.findById(oListEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("List not found"));

        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();

        UserEntity oUserEntityInSession = oUserRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (Boolean.TRUE.equals(oUserEntityInSession.getRole())) { // User
            if (oListEntity.getUser().getId().equals(oUserEntityInSession.getId())) {
                return oListRepository.save(oListEntity);
            } else {
                throw new ResourceNotFoundException("Unauthorized");
            }
        } else {
            return oListRepository.save(oListEntity);
        }

    }

    public Long delete(Long id) {
        ListEntity oListEntity = oListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("List not found"));

        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();

        UserEntity oUserEntityInSession = oUserRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (Boolean.TRUE.equals(oUserEntityInSession.getRole())) { // User
            if (oListEntity.getUser().getId().equals(oUserEntityInSession.getId())) {
                oListRepository.deleteById(id);
                return id;
            } else {
                throw new ResourceNotFoundException("Unauthorized");
            }
        } else {
            oListRepository.deleteById(id);
            return id;
        }
    }

    public Long populate(Integer amount) {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();

        UserEntity oUserEntityInSession = oUserRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (Boolean.TRUE.equals(oUserEntityInSession.getRole())) { // User
            for (int i = 0; i < amount; i++) {
                oListRepository.save(new ListEntity("name" + i));
            }
            return oListRepository.count();
        } else {
            throw new ResourceNotFoundException("Unauthorized");
        }
    }

    @Transactional
    public Long empty() {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();

        UserEntity oUserEntityInSession = oUserRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (Boolean.FALSE.equals(oUserEntityInSession.getRole())) { // User
            oListRepository.deleteAll();
            oListRepository.resetAutoIncrement();
            oListRepository.flush();
            return oListRepository.count();
        } else {
            throw new ResourceNotFoundException("Unauthorized");
        }
        
    }

}