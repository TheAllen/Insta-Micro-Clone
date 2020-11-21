package com.TheAllen.Auth.Service.models;

import lombok.Builder;

@Builder
public class Role {

    private String name;
    public final static Role USER = new Role("USER");
    public final static Role SERVICE = new Role("SERVICE");

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
