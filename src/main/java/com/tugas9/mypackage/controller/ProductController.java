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
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product()); 
        return "add-product";
    }

    @PostMapping("/add")
    public String insertProduct(@ModelAttribute Product product, RedirectAttributes ra) {
        try {
            productService.save(product);
            ra.addFlashAttribute("successMessage", "Produk berhasil ditambahkan!"); 
            return "redirect:/products";
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
            ra.addFlashAttribute("product", product); 
            return "redirect:/products/add";
        }
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        Optional<Product> product = productService.findById(id);
        
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "edit-product"; 
        } else {
            ra.addFlashAttribute("errorMessage", "Produk tidak ditemukan!");
            return "redirect:/products";
        }
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute Product product, RedirectAttributes ra) {
        try {
            productService.update(product);
            ra.addFlashAttribute("successMessage", "Produk berhasil diupdate!"); 
            return "redirect:/products";
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
            ra.addFlashAttribute("product", product);
            return "redirect:/products/edit/" + product.getId();
        }
    }

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