package co.simplon.dreamteam.dev.dtos.in;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.dreamteam.dev.dtos.validators.FileSize;
import co.simplon.dreamteam.dev.dtos.validators.FileType;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateDeveloper(@Size(max = 10) String description,

		@FileType(types = {
				MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
				MediaType.IMAGE_PNG_VALUE }) @FileSize(max = FileSize.ONE_MB) MultipartFile picture,

		@Size(max = 200) @Pattern(regexp = "^(https?://)?(www\\.)?linkedin\\.com/.*$", message = "Invalid LinkedIn URL") String linkedin){
}
