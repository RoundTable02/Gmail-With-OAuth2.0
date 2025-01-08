package org.example.gmailwithoauth.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gmailwithoauth.gmail.GmailAPIProvider;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExampleService {

    private final GmailAPIProvider apiProvider;

    public void sendMail(String email, String title, String content, MultipartFile file) {
        try {
            apiProvider.sendMessage(
                    email,
                    title,
                    content,
                    file
            );

        } catch (MessagingException | IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Not able to process request.");
        }
    }
}
