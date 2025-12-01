# 📦 CRUD Produk Sederhana (Spring Boot & Thymeleaf)

Ini adalah mini-proyek untuk implementasi dasar operasi CRUD (Create, Read, Update, Delete) pada entitas Produk, dibangun sebagai pondasi sebelum masuk ke konsep relasi data.

## 🛠️ Tech Stack

* **Backend:** Spring Boot (Java) 
* **View:** Thymeleaf  (HTML templating)
* **Database:** MySQL 
* **Akses Data:** Spring Data JPA (`JpaRepository`) 
* **Arsitektur:** Layering bersih (Controller – Service – Repository) 

## 🎯 Fungsionalitas Utama (Wajib Jalan)

Aplikasi ini mengelola satu entitas saja, yaitu **Produk** dengan fitur:

* **Create (Tambah):** Form input dan penyimpanan ke DB. Ada validasi sederhana (Nama tidak boleh kosong, Harga > 0, Stok ≥ 0)
* **Read (Lihat):** Menampilkan daftar produk dalam tabel di `/products` 
* **Update (Edit):** Edit data produk berdasarkan ID
* **Delete (Hapus):** Menghapus data berdasarkan ID

## 🚦 Endpoint (Route)

| Route | Method | Fungsi |
| :--- | :--- | :--- |
| `/products` | `GET` | Menampilkan list produk  |
| `/products/add` | `GET` | Menampilkan form tambah produk  |
| `/products/add` | `POST` | Memproses input dan menyimpan ke DB  |
| `/products/edit/{id}` | `GET` | Menampilkan form edit produk berdasarkan ID  |
| `/products/edit` | `POST` | Memproses update data ke DB  |
| `/products/delete/{id}` | `GET` | Menghapus produk  |

## 🚀 Cara Setup & Run

### 1. Database MySQL

Jalankan skrip `products.sql` yang tersedia untuk membuat tabel:

```sql
-- Database: db_product_app 
-- Tabel: products (id, name, price, stock, created_at) 