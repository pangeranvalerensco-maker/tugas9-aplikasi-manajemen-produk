package com.tugas9.mypackage.repository;

import com.tugas9.mypackage.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository<EntityClass, IdType>
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Dengan menggunakan JpaRepository, kita tidak perlu menulis
    // implementasi untuk save, findAll, findById, deleteById, dan update.
    // Operasi-operasi ini otomatis tersedia.
}