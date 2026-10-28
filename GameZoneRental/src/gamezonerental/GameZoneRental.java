package gamezonerental;

import java.util.Scanner;

public class GameZoneRental {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String transaksiLagi;

        do {
            tampilkanJudul();
            prosesTransaksi();

            System.out.print("\nTransaksi Baru (Y/T): ");
            transaksiLagi = input.nextLine().trim();
            while (!transaksiLagi.equalsIgnoreCase("Y")
                    && !transaksiLagi.equalsIgnoreCase("T")) {
                System.out.print("Masukkan Y untuk ya atau T untuk tidak: ");
                transaksiLagi = input.nextLine().trim();
            }
        } while (transaksiLagi.equalsIgnoreCase("Y"));

        System.out.println("\nTerima kasih telah menggunakan GameZone Rental PS!");
        input.close();
    }

    private static void tampilkanJudul() {
        System.out.println("====================================");
        System.out.println("         GAMEZONE RENTAL PS");
        System.out.println("====================================");
    }

    private static void prosesTransaksi() {
        System.out.print("Nama Pelanggan : ");
        String nama = input.nextLine().trim();
        while (nama.isEmpty()) {
            System.out.print("Nama tidak boleh kosong. Masukkan nama: ");
            nama = input.nextLine().trim();
        }

        System.out.println("1. PlayStation 4 - Rp8000/jam");
        System.out.println("2. PlayStation 5 - Rp12000/jam");
        System.out.print("Pilihan         : ");
        int pilihan = bacaPilihanKonsol();

        String konsol;
        int hargaPerJam;
        if (pilihan == 1) {
            konsol = "PlayStation 4";
            hargaPerJam = 8000;
        } else {
            konsol = "PlayStation 5";
            hargaPerJam = 12000;
        }

        System.out.print("Lama Bermain (jam): ");
        int durasi = bacaDurasi();

        System.out.print("Tambah Minuman (Y/T): ");
        String pilihanMinuman = input.nextLine().trim();
        while (!pilihanMinuman.equalsIgnoreCase("Y")
                && !pilihanMinuman.equalsIgnoreCase("T")) {
            System.out.print("Masukkan Y untuk ya atau T untuk tidak: ");
            pilihanMinuman = input.nextLine().trim();
        }

        int biayaSewa = hitungBiayaSewa(hargaPerJam, durasi);
        int diskon = hitungDiskon(biayaSewa, durasi);
        int biayaMinuman = pilihanMinuman.equalsIgnoreCase("Y") ? 5000 : 0;
        int totalBayar = biayaSewa - diskon + biayaMinuman;

        tampilkanStruk(nama, konsol, durasi, biayaSewa, diskon,
                biayaMinuman, totalBayar);
    }

    private static int bacaPilihanKonsol() {
        while (true) {
            String teks = input.nextLine().trim();
            try {
                int pilihan = Integer.parseInt(teks);
                if (pilihan == 1 || pilihan == 2) {
                    return pilihan;
                }
            } catch (NumberFormatException e) {
                // Tampilkan pesan di bawah jika input bukan angka.
            }
            System.out.print("Pilihan harus 1 atau 2. Masukkan kembali: ");
        }
    }

    private static int bacaDurasi() {
        while (true) {
            String teks = input.nextLine().trim();
            try {
                int durasi = Integer.parseInt(teks);
                if (durasi > 0) {
                    return durasi;
                }
            } catch (NumberFormatException e) {
                // Tampilkan pesan di bawah jika input bukan bilangan bulat.
            }
            System.out.print("Durasi harus berupa bilangan bulat positif. Masukkan kembali: ");
        }
    }

    private static int hitungBiayaSewa(int hargaPerJam, int durasi) {
        return hargaPerJam * durasi;
    }

    private static int hitungDiskon(int biayaSewa, int durasi) {
        if (durasi >= 3) {
            return biayaSewa * 10 / 100;
        } else {
            return 0;
        }
    }

    private static void tampilkanStruk(String nama, String konsol, int durasi,
            int biayaSewa, int diskon, int biayaMinuman, int totalBayar) {
        System.out.println("------------------------------------");
        System.out.println("           STRUK GAMEZONE");
        System.out.println("------------------------------------");
        System.out.println("Pelanggan       : " + nama);
        System.out.println("Konsol          : " + konsol);
        System.out.println("Durasi          : " + durasi + " jam");
        System.out.println("Biaya Sewa      : " + formatRupiah(biayaSewa));
        System.out.println("Diskon (10%)    : " + formatRupiah(diskon));
        System.out.println("Minuman         : " + formatRupiah(biayaMinuman));
        System.out.println("TOTAL BAYAR     : " + formatRupiah(totalBayar));
        System.out.println("====================================");
    }

    private static String formatRupiah(int jumlah) {
        return "Rp" + jumlah;
    }
}