package com.example.devs._core.utils;

public interface JwtVO {
    public static final String HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
    public static final Long EXP = (long) (1000 * 60 * 60);
}