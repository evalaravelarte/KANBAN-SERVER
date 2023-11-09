package net.ausiasmarch.kanban.api;

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

import net.ausiasmarch.kanban.entity.ListEntity;
import net.ausiasmarch.kanban.service.ListService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/list")
public class ListApi {

    @Autowired
    ListService oListService;

    @GetMapping("/{id}")
    public ResponseEntity<ListEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oListService.get(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody ListEntity oListEntity) {
        return ResponseEntity.ok(oListService.create(oListEntity));
    }

    @PutMapping("")
    public ResponseEntity<ListEntity> update(@RequestBody ListEntity oListEntity) {
        return ResponseEntity.ok(oListService.update(oListEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oListService.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<ListEntity>> getPage(Pageable oPageable, @RequestParam(value = "user", defaultValue = "0", required = false) Long userId) {
        return ResponseEntity.ok(oListService.getPage(oPageable, userId));
    }

   /*  @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oListService.populate(amount));
    } */

    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oListService.empty());
    }
    
}
