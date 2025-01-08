package org.example.gmailwithoauth.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gmailwithoauth.example.controller.dto.EmailContentDto;
import org.example.gmailwithoauth.example.service.ExampleService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping
@RequiredArgsConstructor
@RestController
public class ExampleController {

    private final ExampleService exampleService;

    @PostMapping("/mail")
    public String sendMail(@ModelAttribute EmailContentDto emailDto) {
        exampleService.sendMail(
                emailDto.getEmail(),
                emailDto.getTitle(),
                emailDto.getContent(),
                emailDto.getFile()
        );

        return "Sending Mail Successful";
    }
}
