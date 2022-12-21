package com.clevertec.clevertectesttaskrest.web.contoller;

import com.clevertec.clevertectesttaskrest.domain.Check;
import com.clevertec.clevertectesttaskrest.service.CheckService;
import com.clevertec.clevertectesttaskrest.service.PdfFileService;
import com.clevertec.clevertectesttaskrest.web.model.CheckModel;
import com.clevertec.clevertectesttaskrest.web.model.CheckRequest;
import com.clevertec.clevertectesttaskrest.web.model.converter.CheckMapper;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CheckController {
    private final CheckService checkService;
    private final PdfFileService pdfFileService;
    private final CheckMapper checkMapper = CheckMapper.INSTANCE;

    @GetMapping("checks")
    public List<CheckModel> getChecks(@PageableDefault Pageable pageable) {
        return checkMapper.allToModel(checkService.getByPaging(pageable));
    }

    @GetMapping("checks/{id}")
    public CheckModel getCheck(@PathVariable Long id) {
        return checkMapper.toModel(checkService.getById(id));
    }

    @GetMapping("/checks/export/{id}")
    public void exportCheckAsPdf(@PathVariable Long id, HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        CheckModel checkModel = checkMapper.toModel(checkService.getById(id));
        pdfFileService.generatePdf(response, checkModel);
    }

    @PostMapping("/checks")
    public CheckModel createCheck(@RequestBody CheckRequest checkRequest) {
        Check check = checkService.createCheck(checkRequest);
        return checkMapper.toModel(check);
    }
}
