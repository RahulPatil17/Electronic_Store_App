package com.electronic.store.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public String userId;
    @Column(name = "user_name")
    public String name;
    @Column(name = "user_email", unique = true)
    public String email;
    @Column(name = "user_password", length = 10)
    public String password;
    public String gender;
    @Column(length = 1000)
    public String about;
    @Column(name = "user_image_name")
    public String imageName;

}
