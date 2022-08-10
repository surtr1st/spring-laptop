package com.ps17931.controller;

import java.io.File;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ps17931.service.UploadService;

@RestController
@RequestMapping("/rest")
public class UploadRestController {

    private final UploadService service;

    @Autowired
    private UploadRestController(UploadService service) {
        this.service = service;
    }

    @RequestMapping(
        path = "/upload/{first}/{second}",
        method = RequestMethod.POST,
        consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<JsonNode> upload(
            @RequestPart MultipartFile file,
            @PathVariable("first") String firstFolder,
            @PathVariable("second") String secondFolder
    )
    {
        File saveFile = service.save(file, firstFolder, secondFolder);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("name", saveFile.getName());
        node.put("size", saveFile.length());
        return new ResponseEntity<>(node, HttpStatus.OK);
    }

}
