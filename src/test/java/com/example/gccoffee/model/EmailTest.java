package com.example.gccoffee.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {
    @Test
    public void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            var email = new Email("accccc");
        });
    }

    @Test
    public void testValidEmail() {
        var email = new Email("test@gmail.com");
        assertThat(email.getAddress()).isEqualTo("test@gmail.com");
    }

    @Test
    public void testEqualEmail() {
        var email1 = new Email("test@gmail.com");
        var email2 = new Email("test@gmail.com");
        assertEquals(email1, email2);
    }
}