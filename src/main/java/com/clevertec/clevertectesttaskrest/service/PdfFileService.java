package com.clevertec.clevertectesttaskrest.service;

import com.clevertec.clevertectesttaskrest.web.model.CheckModel;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface PdfFileService {
    void generatePdf(HttpServletResponse response, CheckModel checkModel) throws IOException, DocumentException;
}
