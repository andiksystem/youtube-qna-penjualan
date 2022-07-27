CREATE TABLE jenis_pelanggan (
    id VARCHAR(20) PRIMARY KEY,
    nama VARCHAR(255)
);

CREATE TABLE pelanggan (
    id VARCHAR(60) PRIMARY KEY,
    nama VARCHAR(255),
    alamat VARCHAR(255),
    telepon VARCHAR(60),
    jenis_pelanggan_id VARCHAR(60) REFERENCES jenis_pelanggan(id)
);

CREATE TABLE produk (
    id VARCHAR(60) PRIMARY KEY,
    nama VARCHAR(255),
    default_harga DECIMAL(22,2)
);

CREATE TABLE produk_harga (
    id VARCHAR(60) PRIMARY KEY,
    produk_id VARCHAR(60) REFERENCES produk(id),
    jenis_pelanggan_id VARCHAR(60) REFERENCES jenis_pelanggan(id),
    harga DECIMAL(22,2)
);

CREATE TABLE penjualan (
    id VARCHAR(60) PRIMARY KEY,
    nomor VARCHAR(60),
    tanggal DATE,
    pelanggan_id VARCHAR(60) REFERENCES pelanggan(id),
    keterangan VARCHAR(255),
    jumlah DECIMAL(22,2),
    waktu_dibuat TIMESTAMP
);

CREATE TABLE penjualan_produk (
    id VARCHAR(60) PRIMARY KEY,
    penjualan_id VARCHAR(60) REFERENCES penjualan(id),
    produk_id VARCHAR(60) REFERENCES produk(id),
    deskripsi VARCHAR(255),
    kuantitas DECIMAL(22,2),
    harga DECIMAL(22,2),
    jumlah DECIMAL(22,2)
);

INSERT INTO jenis_pelanggan (id, nama) VALUES ('P1', 'Prioritas 1');
INSERT INTO jenis_pelanggan (id, nama) VALUES ('P2', 'Prioritas 2');
INSERT INTO jenis_pelanggan (id, nama) VALUES ('P3', 'Prioritas 3');

INSERT INTO pelanggan (id, nama, alamat, telepon, jenis_pelanggan_id) VALUES ('001', 'Andik Hermawan', 'Ponorogo', '081234567890', 'P3');
INSERT INTO pelanggan (id, nama, alamat, telepon, jenis_pelanggan_id) VALUES ('002', 'Raudlatul Hasanah', 'Ponorogo', '081234567891', 'P3');
INSERT INTO pelanggan (id, nama, alamat, telepon, jenis_pelanggan_id) VALUES ('003', 'Sabrina Salsabila', 'Ponorogo', '081234567892', 'P2');
INSERT INTO pelanggan (id, nama, alamat, telepon, jenis_pelanggan_id) VALUES ('004', 'Aisyah Rahma', 'Ponorogo', '081234567893', 'P1');
	
INSERT INTO produk (id, nama, default_harga) VALUES ('001', 'Kapal Api Special', 10000);
INSERT INTO produk (id, nama, default_harga) VALUES ('002', 'Top Kopi Susu', 9000);
INSERT INTO produk (id, nama, default_harga) VALUES ('003', 'Torabika Susu', 9500);
INSERT INTO produk (id, nama, default_harga) VALUES ('004', 'Gudang Garam Surya 16', 27000);
INSERT INTO produk (id, nama, default_harga) VALUES ('005', 'Dji Sam Soe', 20000);

INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('001P3', '001', 'P3', 9800);
INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('001P2', '001', 'P2', 9900);
INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('001P1', '001', 'P1', 10000);

INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('002P3', '002', 'P3', 8800);
INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('002P2', '002', 'P2', 8900);
INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('002P1', '002', 'P1', 9000);

INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('003P3', '003', 'P3', 9300);
INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('003P2', '003', 'P2', 9400);
INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('003P1', '003', 'P1', 9500);

INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('004P3', '004', 'P3', 26500);
INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('004P2', '004', 'P2', 26750);
INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('004P1', '004', 'P1', 27000);

INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('005P3', '005', 'P3', 19500);
INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('005P2', '005', 'P2', 19750);
INSERT INTO produk_harga (id, produk_id, jenis_pelanggan_id, harga) VALUES ('005P1', '005', 'P1', 20000);
