package com.research.task2;

import com.research.model.Role;
import com.research.model.User;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CopilotTask2 {

    public static List<User> parseValidUsers(List<String> lines) {
        return lines.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(line -> {
                    try {
                        String[] parts = line.split(",", -1);
                        if (parts.length != 4) return null;

                        int id = Integer.parseInt(parts[0].trim());
                        String email = parts[1].trim();
                        int age = Integer.parseInt(parts[2].trim());
                        Role role = Role.valueOf(parts[3].trim().toUpperCase(Locale.ROOT));

                        if (id <= 0
                                || !email.contains("@")
                                || !email.contains(".")
                                || age < 18
                                || age > 100) {
                            return null;
                        }

                        return new User(id, email, age, role);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }
}