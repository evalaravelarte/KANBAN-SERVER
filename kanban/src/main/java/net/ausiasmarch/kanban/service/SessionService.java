package net.ausiasmarch.kanban.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ausiasmarch.kanban.bean.UserBean;
import net.ausiasmarch.kanban.exception.ResourceNotFoundException;
import net.ausiasmarch.kanban.helper.JWTHelper;
import net.ausiasmarch.kanban.repository.UserRepository;

@Service
public class SessionService {
    
    @Autowired
    UserRepository oUserRepository;

    public String login(UserBean oUserBean) {
        oUserRepository.findByUsernameAndPassword(oUserBean.getUsername(), oUserBean.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException("Wrong User or password"));
        return JWTHelper.generateJWT(oUserBean.getUsername());
    }

}
