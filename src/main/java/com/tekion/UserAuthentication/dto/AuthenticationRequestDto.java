package com.tekion.UserAuthentication.dto;

import lombok.Builder;

@Builder
public record AuthenticationRequestDto(String email, String password) {
}
