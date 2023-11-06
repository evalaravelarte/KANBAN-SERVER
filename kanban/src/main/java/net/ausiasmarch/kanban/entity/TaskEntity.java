package net.ausiasmarch.kanban.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String description;

    private Date creation_date;

    private Date end_date;

    @NotNull
    @NotBlank
    private String state;

    @ManyToOne
    @JoinColumn(name = "id_list")
    private ListEntity list;

   /* @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user; */ 

    
    public TaskEntity() {
    }

    public TaskEntity(Long id, String description, Date creation_date, Date end_date, String state) {
        this.id = id;
        this.description = description;
        this.creation_date = creation_date;
        this.end_date = end_date;
        this.state = state;
    }

    public TaskEntity(String description, Date creation_date, Date end_date, String state) {
        this.description = description;
        this.creation_date = creation_date;
        this.end_date = end_date;
        this.state = state;
    }

    public TaskEntity(String description, String state) {
        this.description = description;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ListEntity getList() {
        return list;
    }

    public void setList(ListEntity list) {
        this.list = list;
    }

   /*  public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    } */

    
}
