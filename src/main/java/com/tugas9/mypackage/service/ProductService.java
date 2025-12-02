package com.tugas9.mypackage.service;

import com.tugas9.mypackage.model.Product;
import com.tugas9.mypackage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong.");
        }

        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Harga harus lebih dari 0.");
        }

        if (product.getStock() == null || product.getStock() < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif.");
        }
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        validateProduct(product); 
        return productRepository.save(product);
    }

    public Product update(Product product) {
        if (product.getId() == null || !productRepository.existsById(product.getId())) {
             throw new IllegalArgumentException("Produk dengan ID tersebut tidak ditemukan untuk diupdate.");
        }
        
        validateProduct(product); 
        return productRepository.save(product);
    }

    public void deleteById(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Produk dengan ID tersebut tidak ditemukan untuk dihapus.");
        }
        productRepository.deleteById(id);
    }
}