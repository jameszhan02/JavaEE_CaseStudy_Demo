package com.info5059.casestudy.product;
import lombok.Data;
//import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
//@GenericGenerator(name = "sysuuid", strategy = "uuid")
 public class Product {
    @Id
    //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    //  @GeneratedValue(generator = "system_uuid")
    private String id;
    private int vendorid;
    private String name;
    private BigDecimal costprice;
    private BigDecimal msrp;
    private int rop;
    private int eoq;
    private int qoh;
    private int qoo;

    @Lob
    @Basic(optional = true)
    private byte[] qrcode;
    private String qrcodetxt;
}
