package com.tugas9.mypackage.controller;

import com.tugas9.mypackage.model.Product;
import com.tugas9.mypackage.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products") // Semua rute di sini akan diawali dengan /products
public class ProductController {

    @Autowired
    private ProductService productService;

    // --- 1. ROUTE READ: /products (GET) ---
    // Fungsi: Menampilkan list produk (products.html) [cite: 41, 44]
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products"; // Merujuk ke products.html
    }

    // --- 2. ROUTE CREATE (FORM): /products/add (GET) ---
    // Fungsi: Menampilkan form tambah produk (add-product.html) [cite: 41, 47]
    @GetMapping("/add")
    public String showAddForm(Model model) {
        // Objek Product kosong yang akan diisi oleh form
        model.addAttribute("product", new Product()); 
        return "add-product"; // Merujuk ke add-product.html
    }

    // --- 3. ROUTE CREATE (INSERT): /products/add (POST) ---
    // Fungsi: Insert data ke DB 
    @PostMapping("/add")
    public String insertProduct(@ModelAttribute Product product, RedirectAttributes ra) {
        try {
            productService.save(product);
            ra.addFlashAttribute("successMessage", "Produk berhasil ditambahkan!"); // Bonus Notifikasi
            return "redirect:/products";
        } catch (IllegalArgumentException e) {
            // Jika validasi manual gagal di Service Layer, tangkap error-nya
            ra.addFlashAttribute("errorMessage", e.getMessage()); // Tampilkan pesan error
            // Tambahkan data produk yang gagal untuk diisi ulang di form
            ra.addFlashAttribute("product", product); 
            // Redirect kembali ke form add, menggunakan GET redirect (PRG Pattern)
            return "redirect:/products/add";
        }
    }
    
    // --- 4. ROUTE UPDATE (FORM): /products/edit/{id} (GET) ---
    // Fungsi: Menampilkan form edit berdasarkan ID (edit-product.html) [cite: 41, 49, 50]
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        Optional<Product> product = productService.findById(id);
        
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "edit-product"; // Merujuk ke edit-product.html
        } else {
            ra.addFlashAttribute("errorMessage", "Produk tidak ditemukan!");
            return "redirect:/products";
        }
    }

    // --- 5. ROUTE UPDATE (EXECUTE): /products/edit (POST) ---
    // Fungsi: Update data ke DB 
    // Perhatikan: Thymeleaf akan mengirimkan ID di objek Product saat submit
    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute Product product, RedirectAttributes ra) {
        try {
            productService.update(product);
            ra.addFlashAttribute("successMessage", "Produk berhasil diupdate!"); // Bonus Notifikasi
            return "redirect:/products";
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
            ra.addFlashAttribute("product", product);
            // Redirect kembali ke form edit spesifik
            return "redirect:/products/edit/" + product.getId();
        }
    }

    // --- 6. ROUTE DELETE: /products/delete/{id} (GET/POST) ---
    // Fungsi: Hapus produk berdasarkan ID [cite: 41, 61, 62]
    // Kita gunakan GetMapping sederhana untuk kemudahan dalam tutorial ini
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            productService.deleteById(id);
            ra.addFlashAttribute("successMessage", "Produk ID " + id + " berhasil dihapus!");
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/products";
    }
}