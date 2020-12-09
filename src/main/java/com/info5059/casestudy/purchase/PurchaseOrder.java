package com.info5059.casestudy.purchase;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Data
public class PurchaseOrder {
    @Id
    @GeneratedValue
    private Long Id;
    private Long vendorid;
    private BigDecimal amount;
    @JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss")
    private Date podate;
    @OneToMany(mappedBy = "poid", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOrderLineItem> items = new ArrayList<PurchaseOrderLineItem>();
}
