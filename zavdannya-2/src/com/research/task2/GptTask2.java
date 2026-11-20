package com.research.task2;

import com.research.model.Role;
import com.research.model.User;
import java.util.List;
import java.util.Arrays;

public class GptTask2 {

    public static List<User> parseUsers(List<String> records) {
        return records.stream()
                .filter(s -> s != null && !s.isBlank())
                .map(s -> s.split(",", -1))
                .filter(fields -> fields.length == 4)
                .flatMap(fields -> {
                    try {
                        int id = Integer.parseInt(fields[0].trim());
                        String email = fields[1].trim();
                        int age = Integer.parseInt(fields[2].trim());
                        Role role = Role.valueOf(fields[3].trim().toUpperCase());

                        if (id <= 0 || !email.contains("@") || !email.contains(".")
                                || age < 18 || age > 100) {
                            return java.util.stream.Stream.empty();
                        }

                        return java.util.stream.Stream.of(
                                new User(id, email, age, role)
                        );
                    } catch (Exception e) {
                        return java.util.stream.Stream.empty();
                    }
                })
                .toList();
    }
}