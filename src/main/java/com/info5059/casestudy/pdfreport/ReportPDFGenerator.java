package com.info5059.casestudy.pdfreport;

import com.info5059.casestudy.vendor.Vendor;
import com.info5059.casestudy.vendor.VendorRepository;
import com.info5059.casestudy.product.Product;
import com.info5059.casestudy.product.ProductRepository;
import com.info5059.casestudy.purchase.PurchaseOrder;
import com.info5059.casestudy.purchase.PurchaseOrderDAO;
import com.info5059.casestudy.purchase.PurchaseOrderLineItem;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ReportPDFGenerator - a class for creating dynamic expense report output in PDF
 * format using the iText 7 library
 *
 * @author Evan
 */
public abstract class ReportPDFGenerator extends AbstractPdfView {
    public static ByteArrayInputStream generateReport(String poid,
                                                      PurchaseOrderDAO poDAO,
                                                      VendorRepository vendorRepository,
                                                      ProductRepository productRepository) throws IOException {
        URL imageUrl = ReportPDFGenerator.class.getResource("/static/assets/Dota-2-Logo.png");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        // Initialize PDF document to be written to a stream not a file
        PdfDocument pdf = new PdfDocument(writer);
        // Document is the main object
        Document document = new Document(pdf);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        // add the image to the document
        Image img = new Image(ImageDataFactory.create(imageUrl))
                .scaleAbsolute(120, 60)
                .setFixedPosition(80, 710);
        document.add(img);
        // now let's add a big heading
        document.add(new Paragraph("\n\n"));
        //money unitu
        Locale locale = new Locale("en", "US");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        try {
            PurchaseOrder poReport = poDAO.findOne(Long.parseLong(poid));
            System.out.println(poReport);
            document.add(new Paragraph(String.format("Purchase Order"))
                    .setFont(font)
                    .setFontSize(24)
                    .setMarginRight(75)
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setBold());
            document.add(new Paragraph("Report#:" + poid)
                    .setFont(font)
                    .setFontSize(16)
                    .setBold()
                    .setMarginRight(90)
                    .setMarginTop(-10)
                    .setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("\n\n"));
            Optional<Vendor> opt = vendorRepository.findById(poReport.getVendorid());

            if (opt.isPresent()) {
                Vendor vendor = opt.get();
                Table employeeTable = new Table(1);
                employeeTable.setWidth(new UnitValue(UnitValue.PERCENT, 30));
                employeeTable.setBackgroundColor(ColorConstants.LIGHT_GRAY);
                Cell cell = new Cell().add(new Paragraph(vendor.getName())
                        .setBorder(Border.NO_BORDER)
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                employeeTable.addCell(cell);
                cell = new Cell().add(new Paragraph(vendor.getAddress1())
                        .setBorder(Border.NO_BORDER)
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                employeeTable.addCell(cell);
                cell = new Cell().add(new Paragraph(vendor.getCity())
                        .setBorder(Border.NO_BORDER)
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                employeeTable.addCell(cell);
                cell = new Cell().add(new Paragraph(vendor.getProvince())
                        .setBorder(Border.NO_BORDER)
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                employeeTable.addCell(cell);
                cell = new Cell().add(new Paragraph(vendor.getEmail())
                        .setBorder(Border.NO_BORDER)
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                employeeTable.addCell(cell);
                document.add(new Paragraph("Vendor:"));
                document.add(employeeTable);
                document.add(new Paragraph("\n\n"));
            }


            //start create table in the pdf
            Table expenseTable = new Table(5);
            expenseTable.setWidth(new UnitValue(UnitValue.PERCENT, 100));
            // row info
            Cell cell = new Cell().add(new Paragraph("Product Code")
                    .setFont(font)
                    .setFontSize(12)
                    .setBold())
                    .setTextAlignment(TextAlignment.CENTER);
            expenseTable.addCell(cell);
            cell = new Cell().add(new Paragraph("Description")
                    .setFont(font)
                    .setFontSize(12)
                    .setBold())
                    .setTextAlignment(TextAlignment.CENTER);
            expenseTable.addCell(cell);
            cell = new Cell().add(new Paragraph("Qty Sold")
                    .setFont(font)
                    .setFontSize(12)
                    .setBold())
                    .setTextAlignment(TextAlignment.CENTER);
            expenseTable.addCell(cell);
            cell = new Cell().add(new Paragraph("Price")
                    .setFont(font)
                    .setFontSize(12)
                    .setBold())
                    .setTextAlignment(TextAlignment.CENTER);
            expenseTable.addCell(cell);
            cell = new Cell().add(new Paragraph("Ext. Price")
                    .setFont(font)
                    .setFontSize(12)
                    .setBold())
                    .setTextAlignment(TextAlignment.CENTER);
            expenseTable.addCell(cell);
            // report total
            BigDecimal tot = new BigDecimal(0.0);
            // dump out the line items
            for (PurchaseOrderLineItem line : poReport.getItems()) {
                Optional<Product> optx = productRepository.findById(line.getProductid());
                if (optx.isPresent()) {
                    Product product = optx.get();
                    //display row info
                    cell = new Cell().add(new Paragraph(product.getId()))
//                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.CENTER);
                    expenseTable.addCell(cell);
                    cell = new Cell().add(new Paragraph(product.getName()))
//                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.CENTER);
                    expenseTable.addCell(cell);
                    cell = new Cell().add(new Paragraph(Integer.toString(line.getQty())))
//                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.CENTER);
                    expenseTable.addCell(cell);
                    System.out.println(line.getPrice());
                    cell = new Cell().add(new Paragraph(formatter.format(line.getPrice())))
//                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.RIGHT);
                    expenseTable.addCell(cell);
                    cell = new Cell().add(new Paragraph(formatter.format(BigDecimal.valueOf(line.getQty()).multiply(line.getPrice()))))
//                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.RIGHT);
                    expenseTable.addCell(cell);
                    tot = tot.add(BigDecimal.valueOf(line.getQty()).multiply(line.getPrice()), new MathContext(8, RoundingMode.UP));
                }
            }

            cell = new Cell(1, 4).add(new Paragraph("Sub Total: "))
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT.RIGHT);
            expenseTable.addCell(cell);
            cell = new Cell().add(new Paragraph(formatter.format(tot)))
                    .setTextAlignment(TextAlignment.RIGHT.RIGHT);
            expenseTable.addCell(cell);
            cell = new Cell(1, 4).add(new Paragraph("Tax: "))
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT.RIGHT);
            expenseTable.addCell(cell);
            cell = new Cell().add(new Paragraph(formatter.format(BigDecimal.valueOf(0.15).multiply(tot))))
                    .setTextAlignment(TextAlignment.RIGHT.RIGHT);
            expenseTable.addCell(cell);
            cell = new Cell(1, 4).add(new Paragraph("PO Total: "))
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT.RIGHT);
            expenseTable.addCell(cell);
            cell = new Cell().add(new Paragraph(formatter.format(BigDecimal.valueOf(1.15).multiply(tot))))
                    .setTextAlignment(TextAlignment.RIGHT.RIGHT)
                    .setBackgroundColor(ColorConstants.YELLOW);
            expenseTable.addCell(cell);
            document.add(expenseTable);
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph(String.valueOf(new Date()))
                    .setTextAlignment(TextAlignment.CENTER));
            document.close();
        } catch (Exception ex) {
            Logger.getLogger(ReportPDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ByteArrayInputStream(baos.toByteArray());
    }
}
