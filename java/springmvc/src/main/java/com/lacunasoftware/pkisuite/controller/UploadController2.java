package com.lacunasoftware.pkisuite.controller;

import com.lacunasoftware.pkisuite.util.Arquivo;
import com.lacunasoftware.pkisuite.util.StorageMock;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class UploadController2 {

    @RequestMapping(value = "/upload/json",
            method = { RequestMethod.POST },
            consumes = "application/json",
            produces = 	"application/json"
    )
    public Arquivo uploadJson(
            @RequestBody Arquivo arquivo
    ) throws IOException {
        byte[] docBytes = Base64.decodeBase64(arquivo.getBase64Encode());
        InputStream fileStream = new ByteArrayInputStream(docBytes);
        String originalFilename = arquivo.getFilename();
        String fileId = StorageMock.store(fileStream, null, originalFilename);
        Arquivo response = new Arquivo();
        response.setFileId(fileId);
        response.setBase64Encode("");
        response.setFilename(arquivo.getFilename());
        return response;
    }

}
