package net.ausiasmarch.kanban.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import net.ausiasmarch.kanban.entity.ListEntity;
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

    @Autowired
    SessionService oSessionService;

    public ListEntity get(Long id) {
        return oListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("List not found"));
    }

    public List<ListEntity> getAllByUser(Long id_user) {
        return oListRepository.findAllByUserId(id_user);
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
        oSessionService.onlyAdminsOrUsers();
        if (oSessionService.isUser()) { // User
            oListEntity.setUser(oSessionService.getSessionUser());
            return oListRepository.save(oListEntity).getId();
        } else {
            return oListRepository.save(oListEntity).getId();
        }

    }

    public ListEntity update(ListEntity oListEntityToSet) {
        ListEntity oListEntityFromDatabase = this.get(oListEntityToSet.getId());
        oSessionService.onlyAdminsOrUsersWithItsOwnData(oListEntityFromDatabase.getUser().getId());
        if (oSessionService.isUser()) {
            if (oListEntityToSet.getUser().getId().equals(oSessionService.getSessionUser().getId())) {
                return oListRepository.save(oListEntityToSet);
            } else {
                throw new ResourceNotFoundException("Unauthorized");
            }
        } else {
            return oListRepository.save(oListEntityToSet);
        }
    }


    public Long delete(Long id) {
        ListEntity oListEntityFromDatabase = this.get(id);
        oSessionService.onlyAdminsOrUsersWithItsOwnData(oListEntityFromDatabase.getUser().getId());
        oListRepository.deleteById(id);
        return id;
    }

    /*public Long populate(Long amount) {
        
    }*/

    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oListRepository.deleteAll();
        oListRepository.resetAutoIncrement();
        oListRepository.flush();
        return oListRepository.count();
        
    }

}
