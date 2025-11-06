package com.project.cts.atinv.dto;

import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponseDTO {
    private String name;
    private String email;
    private String contactNo;
    private String role;
    private LocalDateTime createdOn;
}
