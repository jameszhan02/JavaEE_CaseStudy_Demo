package com.info5059.casestudy.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@CrossOrigin
@RestController
public class PurchaseOrderController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;
    @PostMapping("/api/pos")
    public ResponseEntity<Long> addOne(@RequestBody PurchaseOrder purchaseOrder) { // use RequestBody here
        Long poId = purchaseOrderDAO.create(purchaseOrder);
        return new ResponseEntity<Long>(poId, HttpStatus.OK);
    }

    @GetMapping("/api/pos/{id}")
    public ResponseEntity<Iterable<PurchaseOrderLineItem>> findByVendor (@PathVariable long id){
        Iterable<PurchaseOrderLineItem> res = purchaseOrderDAO.findByVendor(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
