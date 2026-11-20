package com.research.task2;

import com.research.model.Role;
import com.research.model.User;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class ClaudeTask2 {

    public static List<User> parseValidUsers(List<String> lines) {
        if (lines == null) {
            return List.of();
        }

        return lines.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .map(line -> {
                    String[] parts = line.split(",", -1);
                    if (parts.length != 4) {
                        return Optional.<User>empty();
                    }
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String email = parts[1].trim();
                        int age = Integer.parseInt(parts[2].trim());
                        Role role = Role.valueOf(parts[3].trim().toUpperCase(Locale.ROOT));

                        if (id <= 0
                                || !email.contains("@")
                                || !email.contains(".")
                                || age < 18
                                || age > 100) {
                            return Optional.<User>empty();
                        }
                        return Optional.of(new User(id, email, age, role));
                    } catch (IllegalArgumentException e) {
                        return Optional.<User>empty();
                    }
                })
                .flatMap(Optional::stream)
                .toList();
    }
}