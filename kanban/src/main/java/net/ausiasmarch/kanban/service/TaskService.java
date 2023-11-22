package net.ausiasmarch.kanban.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import net.ausiasmarch.kanban.entity.TaskEntity;
import net.ausiasmarch.kanban.exception.ResourceNotFoundException;
import net.ausiasmarch.kanban.repository.ListRepository;
import net.ausiasmarch.kanban.repository.TaskRepository;


@Service
public class TaskService {
    @Autowired
    TaskRepository oTaskRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    ListService oListService;

    @Autowired
    ListRepository oListRepository;

    @Autowired
    SessionService oSessionService;

    public TaskEntity get(Long id) {
        return oTaskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    public List<TaskEntity> getAllByList(Long id_list) {
        return oTaskRepository.findAllByListId(id_list);
    }

    public Page<TaskEntity> getPage(Pageable oPageable, Long listId) {
        if (listId == 0) {
            return oTaskRepository.findAll(oPageable);
        } else {
            return oTaskRepository.findByListId(listId, oPageable);
        }
        
    }

    public Long create(TaskEntity oTaskEntity) {
        oSessionService.onlyAdminsOrUsers();
        oTaskEntity.setId(null);
        oTaskEntity.setCreation_date(LocalDateTime.now());
        oTaskEntity.setState("Por hacer");
        return oTaskRepository.save(oTaskEntity).getId();
    }

    public TaskEntity update(TaskEntity oTaskEntityToSet) {
        TaskEntity oTaskEntityFromDatabase = this.get(oTaskEntityToSet.getId());
        oSessionService.onlyAdminsOrUsersWithItsOwnData(oTaskEntityFromDatabase.getList().getId());
        return oTaskRepository.save(oTaskEntityToSet);
    }

    public Long delete(Long id) {
        TaskEntity oTaskEntityFromDatabase = this.get(id);
        oSessionService.onlyAdminsOrUsersWithItsOwnData(oTaskEntityFromDatabase.getList().getId());
        oTaskRepository.deleteById(id);
        return id;
    }
    
    public Long populate(Integer amount) {
        for (int i = 0; i < amount; i++) {
            oTaskRepository.save(new TaskEntity("description" + i, "state" + i));
        }
        return oTaskRepository.count();
    }

    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oTaskRepository.deleteAll();
        oTaskRepository.resetAutoIncrement();
        oTaskRepository.flush();
        return oTaskRepository.count();
    }
}
