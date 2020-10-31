package com.info5059.casestudy.purchase;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class PurchaseOrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long poid;
    private String productid;
    private int qty;
    private BigDecimal price;
}
