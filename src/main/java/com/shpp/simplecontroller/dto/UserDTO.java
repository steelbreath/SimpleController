package com.shpp.simplecontroller.dto;

import com.shpp.simplecontroller.validation.IPN;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


public class UserDTO {

    @Size(min = 3, max = 25)
    private String name;

    @IPN
    private Long ipn;

    public UserDTO() {
    }

    public UserDTO(String name, Long ipn) {
        this.name = name;
        this.ipn = ipn;
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
