package com.atithinivas.reviewservice.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReviewUpdateDto {

    @Size(max = 255)
    @Schema(example = "Updated: great value for money")
    private String comment;

    // keep String because entity uses String; constrain to 1..5
    @Pattern(regexp = "^[1-5]$", message = "rating must be 1,2,3,4 or 5")
    @Schema(example = "5", description = "Allowed values: 1-5")
    private String rating;
}