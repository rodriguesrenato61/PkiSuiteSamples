package com.lacunasoftware.pkisuite.controller;

import com.lacunasoftware.pkisuite.util.Arquivo;
import com.lacunasoftware.pkisuite.util.StorageMock;
import com.lacunasoftware.pkisuite.util.Util;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

@RestController
public class DownloadController2 {

    @RequestMapping(value = "/download/json/{fileId:.+}",
    method = { RequestMethod.GET },
    produces = "application/json")
    public Arquivo downloadJson(
            @PathVariable String fileId
    ) throws IOException {

        if (Util.isNullOrEmpty(fileId)) {
            return null;
        }

        if (!StorageMock.exists(fileId)) {
            return null;
        }

        String filename = StorageMock.retriveFilename(fileId);
        InputStream is = StorageMock.openRead(fileId);
        byte[] content = IOUtils.toByteArray(is);

        Arquivo response = new Arquivo();
        response.setBase64Encode(Base64.encodeBase64String(content));
        response.setFilename(filename);
        response.setFileId(fileId);

       return response;
    }

}
