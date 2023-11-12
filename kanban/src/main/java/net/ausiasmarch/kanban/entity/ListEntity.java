package net.ausiasmarch.kanban.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "list")
public class ListEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 2048)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @OneToMany(mappedBy = "list", fetch = jakarta.persistence.FetchType.LAZY)
    private List<TaskEntity> tasks;

    public ListEntity() {
    }

    public ListEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ListEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getTasks() {
        return tasks.size();
    } 
    
    
}
