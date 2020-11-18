package com.TheAllen.Auth.Service.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private String name;
    public final static Role USER = new Role("USER");
    public final static Role SERVICE = new Role("SERVICE");

}
