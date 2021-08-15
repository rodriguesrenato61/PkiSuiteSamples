package com.lacunasoftware.pkisuite.controller;

import com.lacunasoftware.pkiexpress.PadesSignatureStarter;
import com.lacunasoftware.pkiexpress.SignatureFinisher;
import com.lacunasoftware.pkiexpress.SignatureStartResult;
import com.lacunasoftware.pkiexpress.StandardSignaturePolicies;
import com.lacunasoftware.pkisuite.dto.PadesSignatureExpressCompleteDTO;
import com.lacunasoftware.pkisuite.dto.PadesSignatureExpressCompleteResponseDTO;
import com.lacunasoftware.pkisuite.dto.PadesSignatureExpressStartDTO;
import com.lacunasoftware.pkisuite.dto.PadesSignatureExpressStartResponseDTO;
import com.lacunasoftware.pkisuite.util.StorageMock;
import com.lacunasoftware.pkisuite.util.Util;
import com.lacunasoftware.pkisuite.util.express.PadesVisualElements;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class PadesSignatureExpressController2 {

    @RequestMapping(value = "/pades-signature-express2/start",
        method = { RequestMethod.POST },
        consumes = "application/json",
        produces = "application/json")
    public PadesSignatureExpressStartResponseDTO start(
          @RequestBody PadesSignatureExpressStartDTO request
    ) throws IOException {

        // Get an instance of the PadesSignatureStarter class, responsible for receiving
        // the signature elements and start the signature process.
        PadesSignatureStarter signatureStarter = new PadesSignatureStarter();

        // Set PKI default options (see Util.java)
        Util.setPkiDefaults(signatureStarter);

        // Set signature policy.
        signatureStarter.setSignaturePolicy(StandardSignaturePolicies.PadesBasicWithLTV);

        // Set PDF to be signed.
        signatureStarter.setPdfToSign(StorageMock.getDataPath(request.getFileToSign()));

        // Set Base64-encoded certificate's content to signature starter.
        signatureStarter.setCertificateBase64(request.getCertContent());

        // Set visual representation. We provide a Java class that represents the visual
        // representation model.
        signatureStarter.setVisualRepresentation(PadesVisualElements.getVisualRepresentation());

        // Start the signature process. Receive as response a SignatureStartResult instance
        // containing the following fields:
        // - toSignHash: The hash to be signed.
        // - digestAlgorithm: The digest algorithm that will inform the Web PKI component
        // to compute the signature.
        // - transferFile: A temporary file to be passed to "complete" step.
        SignatureStartResult result = signatureStarter.start();

        // If you want to delete the temporary files created by this step use the method
        // dispose(). This method MUST be called after the start() method, because it
        // deletes some files needed by the method.
        signatureStarter.dispose();

        // Render the fields received form start() method as hidden fields to be used on
        // the javascript or on the "complete" step.

        PadesSignatureExpressStartResponseDTO response = new PadesSignatureExpressStartResponseDTO();
        response.setCertContent(request.getCertContent());
        response.setCertThumb(request.getCertThumb());
        response.setToSignHash(result.getToSignHash());
        response.setDigestAlgorithm(result.getDigestAlgorithm());
        response.setTransferFile(result.getTransferFile());

        return response;
    }


    @RequestMapping(value = "/pades-signature-express2/complete",
            method = { RequestMethod.POST },
            consumes = "application/json",
            produces = "application/json")
    public PadesSignatureExpressCompleteResponseDTO complete (
            @RequestBody PadesSignatureExpressCompleteDTO request
            ) throws IOException {

        // Get an instance of the SignatureFinisher class, responsible for completing the
        // signature process.
        SignatureFinisher signatureFinisher = new SignatureFinisher();

        // Set PKI default options (see Util.java)
        Util.setPkiDefaults(signatureFinisher);

        // Set PDF to be signed. It's the same file we used on "start" step.
        signatureFinisher.setFileToSign(StorageMock.getDataPath(request.getFileToSign()));

        // Set transfer file.
        signatureFinisher.setTransferFilePath(request.getTransferFileId());

        // Set the signature value.
        signatureFinisher.setSignature(request.getSignature());

        // Generate path for output file and add to signature finisher.
        String outputFile = StorageMock.generateFileId("pdf");
        signatureFinisher.setOutputFilePath(StorageMock.getDataPath(outputFile));

        // Complete the signature process.
        signatureFinisher.complete();

        // If you want to delete the temporary files created by this step, use the method
        // dispose(). This method MUST be called after the complete() method, because it
        // deletes some files needed by the method.
        signatureFinisher.dispose();

        PadesSignatureExpressCompleteResponseDTO response = new PadesSignatureExpressCompleteResponseDTO();
        response.setOutputFile(outputFile);

        // Render the signature page (templates/pades-signature-express/signature-info.html).

        return response;
    }

}
