package com.info5059.casestudy.purchase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Component
public class PurchaseOrderDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Long create(PurchaseOrder purchaseOrder) {
        PurchaseOrder realOrder = new PurchaseOrder();
        realOrder.setPodate(new Date());
        realOrder.setVendorid(purchaseOrder.getVendorid());
        realOrder.setAmount(purchaseOrder.getAmount());
        entityManager.persist(realOrder);
        for(PurchaseOrderLineItem item :purchaseOrder.getItems()) {
            PurchaseOrderLineItem realItem = new PurchaseOrderLineItem();
            realItem.setPoid(realOrder.getId());
            realItem.setPrice(item.getPrice());
            realItem.setProductid(item.getProductid());
            realItem.setQty(item.getQty());
            entityManager.persist(realItem);
        }
        return realOrder.getId();
    }
    public PurchaseOrder findOne(Long id) {
        PurchaseOrder order = entityManager.find(PurchaseOrder.class, id);
        if (order == null) {
            throw new EntityNotFoundException("Can't find purchaseOrder for ID " + id);
        }
        return order;
    }

    public Iterable<PurchaseOrderLineItem> findByVendor(Long vendorId) {
        Iterable<PurchaseOrderLineItem> res = entityManager.createQuery("select r from PurchaseOrder r where r.vendorid = :id")
                .setParameter("id", vendorId)
                .getResultList();
        System.out.println(vendorId);
        System.out.println(res);
        return res;
    }
}
