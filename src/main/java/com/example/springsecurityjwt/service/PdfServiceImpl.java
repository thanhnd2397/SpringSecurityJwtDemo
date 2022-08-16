package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.helper.Constants;
import com.example.springsecurityjwt.model.entities.type.VariableItemType;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
@Primary
public class PdfServiceImpl implements PdfService {
    @Override
    public File exportPdfFile() throws Exception {
        File fileExport = new File("example.pdf");
        drawFile(fileExport);
        return fileExport;
    }

    private void drawFile(File file) throws Exception {
        try (PDDocument doc = new PDDocument()) {
//            OTFParser otfParser = new OTFParser();
//            OpenTypeFont otf = otfParser.
            PDFont font = PDType1Font.HELVETICA_BOLD;
            PDPage myPage = new PDPage(PDRectangle.A4);
            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {
                cont.setLineWidth(0.1f);
                // draw line horizontal
                float lineHorizontal = Constants.LINE_HORIZONTAL * Constants.POINTS_PER_MM;
                horizontal(cont, lineHorizontal);
                // draw line vertical
                float lineVertical = Constants.LINE_VERTICAL * Constants.POINTS_PER_MM;
                vertical(cont, lineVertical);
                draw(doc, cont, font, 5, 5, 2, 200, 140, "C:\\Users\\thanhnd\\Desktop\\Project\\SpringSecurtity-Jwt\\image1.jpeg");
                drawSquareWhenHorizon(cont);
                draw(doc, cont, font, 100, 170, 1, 0, 0, null);
                draw(doc, cont, font, 89, 104, 2, 41, 57, "C:\\Users\\thanhnd\\Desktop\\Project\\SpringSecurtity-Jwt\\image2.jpeg");
            }
            PDPage myPage1 = new PDPage(PDRectangle.A4);
            doc.addPage(myPage);
            doc.addPage(myPage1);

            doc.close();
        }
    }

    private void drawSquareWhenHorizon(PDPageContentStream cont) throws IOException {
        cont.setNonStrokingColor(Color.white);
        cont.addRect(87f * Constants.POINTS_PER_MM, 135 * Constants.POINTS_PER_MM, 45 * Constants.POINTS_PER_MM, 60 * Constants.POINTS_PER_MM);
        cont.fill();
    }

    private void horizontal(PDPageContentStream cont, float lineHorizontal) throws IOException {
        drawLineHorizontal(cont, 5 * Constants.POINTS_PER_MM, lineHorizontal);
        drawLineHorizontal(cont, 292 * Constants.POINTS_PER_MM, lineHorizontal);
    }

    private void vertical(PDPageContentStream cont, float lineVertical) throws IOException {
        drawLineVertical(cont, 5 * Constants.POINTS_PER_MM, lineVertical);
        drawLineVertical(cont, 205 * Constants.POINTS_PER_MM, lineVertical);
    }

    private void drawLineHorizontal(PDPageContentStream cont, float cardY, float lineHorizontal)
            throws IOException {
        cont.moveTo(5 * Constants.POINTS_PER_MM, cardY);
        cont.lineTo(lineHorizontal, cardY);
        cont.stroke();
    }

    private void drawLineVertical(PDPageContentStream cont, float cardX, float lineVertical)
            throws IOException {
        cont.moveTo(cardX, 292 * Constants.POINTS_PER_MM);
        cont.lineTo(cardX, lineVertical);
        cont.stroke();
    }

    private void draw(PDDocument doc, PDPageContentStream cont, PDFont font,float xmm, float ymm, Integer status, float width, float height, String imageLink) throws IOException {
        transformBeforeDraw(cont, xmm, ymm,0);
        drawElementInCard(doc, cont, font, status, width, height , imageLink);
        transformAfterDraw(cont, xmm, ymm,0);
    }

    private void transformBeforeDraw(PDPageContentStream cont, float xmm, float ymm, float x)
            throws IOException {
        cont.transform(
                Matrix.getRotateInstance(Math.toRadians(0), (x + xmm) * Constants.POINTS_PER_MM,
                        (Constants.A4_HEIGHT - ymm) * Constants.POINTS_PER_MM));
    }

    private void transformAfterDraw(PDPageContentStream cont, float xmm, float ymm, float x) throws IOException {
        cont.transform(Matrix.getRotateInstance(Math.toRadians(0), 0, 0));
        cont.transform(Matrix.getRotateInstance(0, -(x + xmm ) * Constants.POINTS_PER_MM,
                -(Constants.A4_HEIGHT - ymm) * Constants.POINTS_PER_MM));
    }

    private void drawElementInCard(PDDocument doc, PDPageContentStream cont, PDFont font, Integer status, float width, float height, String imageLink) throws IOException {

        if (Objects.equals(status, VariableItemType.TEXT.getValue())) {
            drawText(cont, font);
        }
        if (Objects.equals(status, VariableItemType.IMAGE.getValue())) {
            drawImage(doc, cont, width, height, imageLink);
        }

    }

    private void drawText(PDPageContentStream cont, PDFont font) throws IOException {
        cont.beginText();
        cont.setNonStrokingColor(Color.BLACK);
        cont.newLineAtOffset(0, 0);
        cont.setFont(font, 12 / Constants.SCALE * Constants.POINTS_PER_MM);
        cont.showText("**************");
        cont.endText();
    }

    private void drawImage(PDDocument doc, PDPageContentStream cont, float width, float height,  String imageLink) throws IOException {
        PDImageXObject pdImage = PDImageXObject.createFromFile(imageLink, doc);
        cont.drawImage(pdImage, 0, -height * Constants.POINTS_PER_MM,
                (width) * Constants.POINTS_PER_MM,
                (height) * Constants.POINTS_PER_MM);
    }
}
