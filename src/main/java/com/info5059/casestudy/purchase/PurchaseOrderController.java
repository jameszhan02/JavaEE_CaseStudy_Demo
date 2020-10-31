package com.info5059.casestudy.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;
    @PostMapping("/api/pos")
    public ResponseEntity<Long> addOne(@RequestBody PurchaseOrder purchaseOrder) { // use RequestBody here
        Long poId = purchaseOrderDAO.create(purchaseOrder);
        return new ResponseEntity<Long>(poId, HttpStatus.OK);
    }
}
