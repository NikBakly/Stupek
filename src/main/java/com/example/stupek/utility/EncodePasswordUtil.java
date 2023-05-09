package com.example.stupek.utility;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.CharBuffer;


@NoArgsConstructor
public class EncodePasswordUtil {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public static String getEncodingPassword(char[] password) {
        return new BCryptPasswordEncoder().encode(CharBuffer.wrap(password));
    }
}
