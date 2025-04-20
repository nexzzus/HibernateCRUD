package com.spring.hibernate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteDTO {
    private int id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 2, max = 20, message = "El nombre debe ser entre 2 y 20 caracteres")
    private String nombre;
    @NotBlank(message = "Campo requerido")
    private String apellido;
    @Email(message = "Email no valido")
    @NotBlank(message = "Campo requerido")
    private String email;
}