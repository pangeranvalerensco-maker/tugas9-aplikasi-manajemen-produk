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

    // --- Validasi Manual (sesuai permintaan Anda) ---
    private void validateProduct(Product product) {
        // 1. Nama tidak boleh kosong [cite: 55]
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong.");
        }

        // 2. Harga > 0 [cite: 55]
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Harga harus lebih dari 0.");
        }

        // 3. Stok >= 0 [cite: 55]
        if (product.getStock() == null || product.getStock() < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif.");
        }
    }
    // --- Akhir Validasi Manual ---

    // 1. READ ALL (findAll)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // 2. READ BY ID (findById)
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    // 3. CREATE (save)
    public Product save(Product product) {
        // Panggil validasi sebelum menyimpan
        validateProduct(product); 
        return productRepository.save(product);
    }

    // 4. UPDATE (save - akan bertindak sebagai update jika ID sudah ada)
    public Product update(Product product) {
        // Pastikan ID ada sebelum update, walaupun JPA bisa menangani ini
        if (product.getId() == null || !productRepository.existsById(product.getId())) {
             throw new IllegalArgumentException("Produk dengan ID tersebut tidak ditemukan untuk diupdate.");
        }
        
        // Panggil validasi sebelum menyimpan/mengupdate
        validateProduct(product); 
        return productRepository.save(product);
    }

    // 5. DELETE (deleteById)
    public void deleteById(Integer id) {
        // Bisa tambahkan logic pengecekan apakah ID ada
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Produk dengan ID tersebut tidak ditemukan untuk dihapus.");
        }
        productRepository.deleteById(id);
    }
}