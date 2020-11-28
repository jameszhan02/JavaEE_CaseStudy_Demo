package com.info5059.casestudy.pdfreport;

import com.info5059.casestudy.vendor.VendorRepository;
import com.info5059.casestudy.vendor.VendorRepository;
import com.info5059.casestudy.product.ProductRepository;
import com.info5059.casestudy.purchase.PurchaseOrderDAO;
import com.itextpdf.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
@CrossOrigin
@RestController
public class ReportPDFController {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PurchaseOrderDAO poDAO;
    @RequestMapping(value = "/ReportPDF", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> streamPDF(HttpServletRequest request) throws IOException, java.io.IOException {
        // get formatted pdf as a stream

//        String reportId = Long.toString(repid);
//        System.out.println(reportId);
        ByteArrayInputStream bis = ReportPDFGenerator.generateReport(request.getParameter("poid"), poDAO, vendorRepository, productRepository);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=examplereport.pdf");
        // dump stream to browser
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
