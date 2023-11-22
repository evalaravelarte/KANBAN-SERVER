package net.ausiasmarch.kanban.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.kanban.entity.TaskEntity;
import net.ausiasmarch.kanban.service.TaskService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/task")
public class TaskApi {

    @Autowired
    TaskService oTaskService;

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oTaskService.get(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody TaskEntity oTaskEntity) {
        return ResponseEntity.ok(oTaskService.create(oTaskEntity));
    }

    @PutMapping("")
    public ResponseEntity<TaskEntity> update(@RequestBody TaskEntity oTaskEntity) {
        return ResponseEntity.ok(oTaskService.update(oTaskEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oTaskService.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<TaskEntity>> getPage(Pageable oPageable, @RequestParam(value = "list", defaultValue = "0", required = false) Long listId) {
        return ResponseEntity.ok(oTaskService.getPage(oPageable,listId));
    }
    
    /* 
    @GetMapping("/{id_list}")
    public ResponseEntity<List<TaskEntity>> getAllByList(@PathVariable("id_list") Long id_list) {
        return ResponseEntity.ok(oTaskService.getAllByList(id_list));
    }*/

    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oTaskService.populate(amount));
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oTaskService.empty());
    }
}
