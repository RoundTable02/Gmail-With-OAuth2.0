package org.example.gmailwithoauth.example.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class EmailContentDto {
    private String title;
    private String content;
    private MultipartFile file;
    private String email;
}
