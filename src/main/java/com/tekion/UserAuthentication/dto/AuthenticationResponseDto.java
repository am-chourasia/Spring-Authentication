package com.tekion.UserAuthentication.dto;

import lombok.*;

@Builder
public record AuthenticationResponseDto(String token) { }
