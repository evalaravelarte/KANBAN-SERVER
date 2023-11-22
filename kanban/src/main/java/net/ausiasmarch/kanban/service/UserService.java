package net.ausiasmarch.kanban.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import net.ausiasmarch.kanban.entity.UserEntity;
import net.ausiasmarch.kanban.exception.ResourceNotFoundException;
import net.ausiasmarch.kanban.helper.DataGenerationHelper;
import net.ausiasmarch.kanban.repository.UserRepository;

@Service
public class UserService {

    private final String kanbanPASSWORD = "7cc6d962e40fbc035b7a898a48d8aa8d7574a1319d4724c596b22bd06f3f16ac";

    @Autowired
    UserRepository oUserRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    public UserEntity get(Long id) {
        return oUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserEntity getByUsername(String username) {
        return oUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by username"));
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

     public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            String email = DataGenerationHelper.getRandomEmail();
            String username = DataGenerationHelper.getRadomUsername();
            oUserRepository.save(new UserEntity(username, email, kanbanPASSWORD, false));
        }
        return oUserRepository.count();
    }

    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oUserRepository.deleteAll();
        oUserRepository.resetAutoIncrement();
        UserEntity oUserEntity1 = new UserEntity(1L,"evalara", "evalara@gmail.com", kanbanPASSWORD, false);
        oUserRepository.save(oUserEntity1);
        oUserEntity1 = new UserEntity(2L, "kanban@gmail.com", "kanban", kanbanPASSWORD, true);
        oUserRepository.save(oUserEntity1);
        return oUserRepository.count();
    }

}
