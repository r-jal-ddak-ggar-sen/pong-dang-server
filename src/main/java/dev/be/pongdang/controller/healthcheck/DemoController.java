package dev.be.pongdang.controller.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.be.pongdang.service.MailService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final MailService mailService;

    @GetMapping("/demo")
    public String demo() {
        mailService.sendMail();
        return "hello";
    }
}
