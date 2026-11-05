package com.example.spring.course.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank
    @Schema(description = "Nome Utente", example = "Stefano")
    private String nome;
    @NotBlank
    @Email(message = "Rispetta il formato corretto delle email")
    private String email;
    @NotNull
    @Min(value = 18, message = "I minorenni non possono registrarsi")
    @Positive
    private int eta;
    @NotBlank
    @Schema(description = "Città", example = "VA")
    @Pattern(regexp = "([A-Z]{2})")
    private String citta;
    @NotBlank
    @Pattern(regexp = "([A-Z]{2})")
    private String nazione;

}