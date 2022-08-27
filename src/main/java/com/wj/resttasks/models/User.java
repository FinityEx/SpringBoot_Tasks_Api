package com.wj.resttasks.models;

import com.fasterxml.jackson.annotation.*;
import com.wj.resttasks.models.Task;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value={"password"}, allowSetters = true)
@Table(name = "users")
public class User {

    @Id
    //needed for mysql
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long userid;


    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String role;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Task> tasks;

    public User(){}



    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {this.password = password;}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
