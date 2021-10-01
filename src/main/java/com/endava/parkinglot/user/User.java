package com.endava.parkinglot.user;

import com.endava.parkinglot.user.role.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(schema = "parking",
        name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name="id",
            updatable = false
    )
    private int id;

    @NotEmpty(message ="You must provide an username!")
    @Size(min=5,max=30)
    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;

    @NotEmpty(message = "You must provide a password")
    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;



    @NotEmpty(message = "Please add your phone number")
    @Column(
            name = "phone_number",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String phone;

    @NotEmpty(message = "Please add your email")
    @Column(
            name = "email",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String email;


    @Column(
            name = "enabled",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private boolean isEnabled=true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "parking",
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


}
