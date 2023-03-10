package com.tekion.UserAuthentication.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record RegisterRequestDto(@NotBlank String firstName, String lastName, @Email String email,
                                 @NotBlank @Length(min = 6) String password) {

}
