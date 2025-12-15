package com.sgr.ums.Controller;
import com.sgr.ums.Services.FileService.FileService;
import com.sgr.ums.Utilities.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String status = fileService.uploadFile(file);
        log.info("Adding File is Starting {}", JsonUtils.toJson(file));
        return ResponseEntity.ok(status);
    }


    @GetMapping(value = "/download/{name}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> downloadFile(@PathVariable(value = "name") String fileName) {
        UrlResource file = fileService.downloadFile(fileName);
        if (file == null) {
            log.error("Downloading file is not found ");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            log.error("Downloading File is Starting  ");
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(file);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getFileList() {
        log.info("Getting All file is Starting");
        return new ResponseEntity<>(fileService.fileList(), HttpStatus.OK);
    }
}

