package com.shpp.simplecontroller.entity;

import com.shpp.simplecontroller.validation.IPN;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PositiveOrZero(message = "Id must be positive or zero!")
    private Long id;

    @Size(min = 3, max = 25)
    private String name;

    @IPN
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
