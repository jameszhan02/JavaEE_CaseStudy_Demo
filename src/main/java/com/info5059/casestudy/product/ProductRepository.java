package com.info5059.casestudy.product;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//public interface ProductRepository {
//
//}

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    // will return the number of rows deleted
    @Modifying
    @Transactional
    @Query("delete from Product where id = ?1")
    int deleteOne(String id);
    @Query("select p from Product p where p.id = ?1")
    Optional<Product> findById(String id);
}
