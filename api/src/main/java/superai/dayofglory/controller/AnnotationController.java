package superai.dayofglory.controller;

import superai.dayofglory.dto.UploadTaskResponse;
import superai.dayofglory.service.AnnotationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AnnotationController {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationController.class);

    @Autowired
    private AnnotationService annotationService;

    @PostMapping("/tasks")
    public UploadTaskResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = annotationService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadTaskResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

}
