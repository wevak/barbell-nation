package com.barbellnation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrainerReqDTO {

    @NotBlank(message = "Trainer name is required !!!")
    private String name;

    @Email(message = "Invalid email format !")
    private String email;

    @NotBlank(message = "Phone is required !!!")
    private String phone;

    @NotBlank(message = "Gender is required !!!")
    private String gender;

    @NotBlank(message = "Speciality is required !!!")
    private String speciality;

    @NotNull(message = "Package ID is required !!!")
    private Long packageId;  // ✅ This is what addTrainer() needs
}
