package net.ausiasmarch.kanban.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min=6, max=255)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric")
    private String username;

    @Email
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @NotBlank
    private String password = "AD67DC1D98993ADA5A163B95C92D17498100320829720FE5019E86E788EE3CE3";

    private Boolean role = false;

    @OneToMany(mappedBy = "user", fetch = jakarta.persistence.FetchType.LAZY)
    private List<ListEntity> lists;

    /* @OneToMany(mappedBy = "user", fetch = jakarta.persistence.FetchType.LAZY)
    private List<TaskEntity> tasks; */

    public UserEntity() {
        lists = new ArrayList<>();
        //tasks = new ArrayList<>();
    }

    public UserEntity(Long id, String username, String email, String password, Boolean role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserEntity(String username, String email, String password, Boolean role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public int getLists() {
        return lists.size();
    }

    /* public int getTasks() {
        return tasks.size();
    } */

    
}