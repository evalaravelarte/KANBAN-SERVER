package net.ausiasmarch.kanban.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.kanban.entity.ListEntity;

public interface ListRepository extends JpaRepository<ListEntity, Long>{

    Page<ListEntity> findByUserId(Long id, Pageable oPageable);
    
    @Modifying
    @Query(value = "ALTER TABLE thread AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
