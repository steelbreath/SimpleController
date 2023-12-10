package com.shpp.simplecontroller.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long ipn;

    public UserEntity(){}

    public UserEntity(Long id, String name, Long ipn) {
        this.id = id;
        this.name = name;
        this.ipn = ipn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIpn() {
        return ipn;
    }

    public void setIpn(Long ipn) {
        this.ipn = ipn;
    }
}
