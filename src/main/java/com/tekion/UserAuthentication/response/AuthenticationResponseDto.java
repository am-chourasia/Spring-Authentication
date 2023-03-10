package com.tekion.UserAuthentication.response;

import lombok.*;

@Builder
public record AuthenticationResponseDto(String token) { }
