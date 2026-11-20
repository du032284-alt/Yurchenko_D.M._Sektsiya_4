package com.research.task2;

import com.research.model.Role;
import com.research.model.User;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Task2BenchmarkTest {

    @Test
    public void testAllModels() {
        List<String> input = Arrays.asList(
                "1, user@test.com, 25, user",        // валідний (нижній регістр ролі)
                " 2 , admin@site.org , 18 , ADMIN ", // валідний (пробіли + межа віку 18)
                "3, old@site.org, 100, USER",        // валідний (межа віку 100)
                "0, badid@test.com, 30, USER",       // невалідний: id <= 0
                "4, bademail.com, 30, USER",         // невалідний: немає @
                "5, minor@test.com, 17, USER",       // невалідний: вік < 18
                "6, role@test.com, 25, SUPERUSER",   // невалідний: неіснуюча роль
                "7, broken, 25",                     // невалідний: 3 поля замість 4
                "abc, num@test.com, twenty, USER",   // невалідний: текст замість чисел
                "",                                  // порожній рядок
                null                                 // null елемент
        );

        List<User> expected = List.of(
                new User(1, "user@test.com", 25, Role.USER),
                new User(2, "admin@site.org", 18, Role.ADMIN),
                new User(3, "old@site.org", 100, Role.USER)
        );

        // 1. ChatGPT
        assertEquals(expected, GptTask2.parseUsers(input));

        // 2. Google Gemini
        assertEquals(expected, new GeminiTask2().parseValidUsers(input));

        // 3. GitHub Copilot
        assertEquals(expected, CopilotTask2.parseValidUsers(input));
    }
}