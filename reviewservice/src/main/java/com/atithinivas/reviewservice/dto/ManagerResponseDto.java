package com.atithinivas.reviewservice.dto;

import lombok.*;

import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * DTO for manager’s response to a guest review.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagerResponseDto implements Serializable {

    @NotBlank(message = "managerResponse cannot be blank")
    @Size(max = 1000)
    private String managerResponse;
}