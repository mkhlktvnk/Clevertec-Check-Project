package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.service.PdfFileService;
import com.clevertec.clevertectesttaskrest.web.model.CheckItemModel;
import com.clevertec.clevertectesttaskrest.web.model.CheckModel;
import com.clevertec.clevertectesttaskrest.web.model.DiscountCardModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PdfFileServiceImpl implements PdfFileService {
    @SneakyThrows
    public void generatePdf(HttpServletResponse response, CheckModel checkModel) {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        addDocumentTitle(document);
        addContent(document, checkModel);
        document.close();
    }

    private void addDocumentTitle(Document document) throws DocumentException {
        Paragraph checkParagraph = new Paragraph();
        Font checkParagraphFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        checkParagraphFont.setSize(20);

        checkParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        checkParagraph.setSpacingAfter(20);
        checkParagraph.add(new Phrase("Check", checkParagraphFont));

        document.add(checkParagraph);
    }

    private void addContent(Document document, CheckModel checkModel) throws DocumentException {
        Paragraph checkId = new Paragraph();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(14);
        checkId.setAlignment(Paragraph.ALIGN_CENTER);
        checkId.add(new Phrase("ID: " + checkModel.getId(), font));
        document.add(checkId);

        Paragraph time = new Paragraph();
        time.setAlignment(Paragraph.ALIGN_CENTER);
        time.add(new Phrase("Time: " + checkModel.getCreatedAt()));
        time.setSpacingAfter(20);
        document.add(time);

        addProductsTable(document, checkModel.getItems());
        addDiscountInfo(document, checkModel.getDiscountCard());
        addTotalPriceInfo(document, checkModel);
    }

    @SneakyThrows
    private void addProductsTable(Document document, List<CheckItemModel> items) {
        PdfPTable pdfPTable = new PdfPTable(4);
        pdfPTable.setSpacingAfter(20);
        addTableHeaders(pdfPTable);
        addTableContent(pdfPTable, items);
        document.add(pdfPTable);
    }

    private void addTableHeaders(PdfPTable pdfPTable) {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setBackgroundColor(BaseColor.GREEN);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(BaseColor.BLACK);

        pdfPCell.setPhrase(new Phrase("Product", font));
        pdfPTable.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase("Price", font));
        pdfPTable.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase("Quantity", font));
        pdfPTable.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase("Total price", font));
        pdfPTable.addCell(pdfPCell);
    }

    private void addTableContent(PdfPTable pdfPTable, List<CheckItemModel> items) {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setBackgroundColor(BaseColor.WHITE);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(BaseColor.BLACK);
        items.forEach((item) -> {
            pdfPCell.setPhrase(new Phrase(item.getProduct().getName(), font));
            pdfPTable.addCell(pdfPCell);

            pdfPCell.setPhrase(new Phrase(String.valueOf(item.getProduct().getPrice()), font));
            pdfPTable.addCell(pdfPCell);

            pdfPCell.setPhrase(new Phrase(String.valueOf(item.getQuantity()), font));
            pdfPTable.addCell(pdfPCell);

            pdfPCell.setPhrase(new Phrase(String.valueOf(item.getTotalPrice()), font));
            pdfPTable.addCell(pdfPCell);
        });
    }

    @SneakyThrows
    private void addDiscountInfo(Document document, DiscountCardModel discountCardModel) {
        if (discountCardModel == null) {
            return;
        }
        Paragraph discountParagraph = new Paragraph();
        discountParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(14);
        discountParagraph.add(new Phrase("Discount: " + discountCardModel.getDiscount() + "%", font));
        document.add(discountParagraph);
    }

    @SneakyThrows
    private void addTotalPriceInfo(Document document, CheckModel checkModel) {
        Paragraph totalPriceParagraph = new Paragraph();
        totalPriceParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(14);
        totalPriceParagraph.add(new Phrase("Total price: " + checkModel.getTotalPrice(), font));
        document.add(totalPriceParagraph);
    }
}
