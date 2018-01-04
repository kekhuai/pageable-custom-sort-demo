package com.example.pageablecustomsortdemo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import java.util.Objects;

@Entity
@Table(name = "USER")
public class User implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private long id;
    
    private String username;
    
    private String password;
    
    private String name;

    @Id
    @Column(name = "ID")
    @TableGenerator(name = "userGenarator", initialValue = 100)
    @GeneratedValue(generator = "userGenarator")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "USERNAME", nullable = false, unique = true, length = 64)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD", nullable = false, length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "NAME", length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof User)) {
            return false;
        }
        User castOther = (User) other;
        return Objects.equals(id, castOther.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
