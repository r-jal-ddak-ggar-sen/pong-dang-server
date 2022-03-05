package dev.be.pongdang.common.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PasswordUtil {

    private final PasswordEncoder passwordEncoder;

    public String getEncodedValue(String rawValue) {
        return passwordEncoder.encode(rawValue);
    }

    public boolean isPwEquals(String rawPassword, String targetPassword) {
        return passwordEncoder.matches(rawPassword, targetPassword);
    }
}
