package com.electronic.store.dtos;

import com.sun.istack.NotNull;
import lombok.*;


import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class UserDto {
    @NotNull
    @Size(min = 4, message = "Username Must be Min 4 Chars...!!")
    public String name;
    @Email
    public String email;
    @NotEmpty
    @Size(min = 3, max = 10, message = "Min 3 chars and Max 10 Chars..!!")
    public String password;
    public String gender;
    @Size(min = 15, max = 100, message = "Min 10 Chars and Max 100 Chars...!!")
    public String about;
    public String imageName;

}
