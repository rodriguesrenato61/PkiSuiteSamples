package com.lacunasoftware.pkisuite.controller;

import com.lacunasoftware.pkisuite.dto.DownloadPdfResponseDTO;
import com.lacunasoftware.pkisuite.util.StorageMock;
import com.lacunasoftware.pkisuite.util.Util;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;


@RestController
public class DownloadController2 {

    @RequestMapping(value = "/download/json/{fileId:.+}",
    method = { RequestMethod.GET },
    produces = "application/json")
    public DownloadPdfResponseDTO downloadJson(
            @PathVariable String fileId
    ) throws IOException {

        if (Util.isNullOrEmpty(fileId)) {
            return null;
        }

        if (!StorageMock.exists(fileId)) {
            return null;
        }

        InputStream is = StorageMock.openRead(fileId);
        byte[] content = IOUtils.toByteArray(is);

        DownloadPdfResponseDTO response = new DownloadPdfResponseDTO();
        response.setBase64Encode(Base64.encodeBase64String(content));

       return response;
    }

}
