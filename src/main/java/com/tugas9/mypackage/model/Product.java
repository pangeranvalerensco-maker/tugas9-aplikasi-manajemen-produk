package com.tugas9.mypackage.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity // Menandakan bahwa class ini adalah JPA entity
@Table(name = "products") // Menghubungkan class ini dengan tabel 'products'
public class Product {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // id, sesuai spesifikasi [cite: 21]

    private String name; // name, sesuai spesifikasi [cite: 22]

    private BigDecimal price; // price (gunakan BigDecimal untuk uang/harga) [cite: 23]

    private Integer stock; // stock [cite: 24]

    @Column(name = "created_at")
    private LocalDateTime createdAt; // created_at [cite: 25]

    // Wajib menerapkan encapsulation (private + getter/setter) 

    // 1. Constructor (Opsional, tapi membantu)
    public Product() {
        this.createdAt = LocalDateTime.now(); // Set waktu saat objek dibuat
    }

    // 2. Getter dan Setter
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}