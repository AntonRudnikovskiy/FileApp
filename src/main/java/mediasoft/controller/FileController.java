package mediasoft.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @GetMapping("/")
    public ResponseEntity<FileSystemResource> downloadFile(@RequestParam("filePath") String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                .contentLength(file.length())
                .body(new FileSystemResource(file));
    }
}
