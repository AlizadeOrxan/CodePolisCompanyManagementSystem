package com.ltc.companymanagementsystem.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDto {

    private String message;

  private   int statusCode;

  private   LocalDateTime timestamp;

}
