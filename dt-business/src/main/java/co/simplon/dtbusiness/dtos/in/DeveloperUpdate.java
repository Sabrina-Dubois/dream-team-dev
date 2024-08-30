package co.simplon.dtbusiness.dtos.in;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public record DeveloperUpdate(
        String description,
        MultipartFile picture,
        String linkedin,
        Set<SkillCreateOrAdd> skills
        ) {

}
