package com.research.model;

public record User(
        int id,
        String email,
        int age,
        Role role
) {}