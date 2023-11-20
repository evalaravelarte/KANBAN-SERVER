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

    public Page<TaskEntity> getPage(Pageable oPageable) {
        return oTaskRepository.findAll(oPageable);
    }

    public Long create(TaskEntity oTaskEntity) {
        oTaskEntity.setId(null);
        oTaskEntity.setCreation_date(LocalDateTime.now());
        return oTaskRepository.save(oTaskEntity).getId();
    }

    public TaskEntity update(TaskEntity oTaskEntity) {
        return oTaskRepository.save(oTaskEntity);
    }

    public Long delete(Long id) {
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
        oTaskRepository.deleteAll();
        oTaskRepository.resetAutoIncrement();
        oTaskRepository.flush();
        return oTaskRepository.count();
    }
}
