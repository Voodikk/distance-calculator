package com.magenta.distancecalculator.controller;

import com.magenta.distancecalculator.service.upload.UploadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/distance-calculator")
public class UploadDataController {

    // Контроллер загрузки файла на сервер

    private final UploadDataService service;

    @Autowired
    public UploadDataController(UploadDataService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadData(@RequestParam("file") MultipartFile file) {

        return service.uploadDataToDB(file);
    }
}
