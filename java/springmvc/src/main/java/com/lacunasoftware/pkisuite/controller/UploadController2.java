package com.lacunasoftware.pkisuite.controller;

import com.lacunasoftware.pkisuite.dto.UploadPdfPkiExpressDTO;
import com.lacunasoftware.pkisuite.dto.UploadPdfPkiExpressResponseDTO;
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
    public UploadPdfPkiExpressResponseDTO uploadJson(
            @RequestBody UploadPdfPkiExpressDTO request
    ) throws IOException {
        byte[] docBytes = Base64.decodeBase64(request.getBase64Encode());
        InputStream fileStream = new ByteArrayInputStream(docBytes);
        String originalFilename = request.getFilename();
        String fileId = StorageMock.store(fileStream, null, originalFilename);
        UploadPdfPkiExpressResponseDTO response = new UploadPdfPkiExpressResponseDTO();
        response.setFileId(fileId);
       
        return response;
    }

}
