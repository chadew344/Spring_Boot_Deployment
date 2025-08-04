package com.example.spring_boot_deployment.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class APIResponse<T>{
    private int code;
    private String message;
    private T data;
}
