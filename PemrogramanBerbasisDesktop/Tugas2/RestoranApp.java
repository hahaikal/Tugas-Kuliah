import java.util.ArrayList;
import java.util.Scanner;

public class RestoranApp {

    static final Scanner scanner = new Scanner(System.in);
    static ArrayList<Menu> daftarMenu = new ArrayList<>();

    /**
     * Method Main
     */
    public static void main(String[] args) {
        initMenu();

        while (true) {
            System.out.println("\n----- APLIKASI RESTORAN SEDERHANA -----");
            System.out.println("1. Menu Pelanggan (Pesan)");
            System.out.println("2. Menu Pemilik (Manajemen Menu)");
            System.out.println("3. Keluar");

            int pilihan = getIntInput("Pilih menu (1-3): ", 1, 3);

            switch (pilihan) {
                case 1:
                    menuPelanggan();
                    break;
                case 2:
                    menuPemilik();
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan aplikasi restoran kami!");
                    scanner.close();
                    System.exit(0);
            }
        }
    }

    /**
     * Method untuk mengisi menu awal ke 'daftar Menu'
     */
    public static void initMenu() {
        daftarMenu.add(new Menu("Nasi Padang", 25000, "Makanan"));
        daftarMenu.add(new Menu("Ayam Bakar", 18000, "Makanan"));
        daftarMenu.add(new Menu("Sate Ayam", 20000, "Makanan"));
        daftarMenu.add(new Menu("Gado-Gado", 15000, "Makanan"));
        daftarMenu.add(new Menu("Es Teh Manis", 5000, "Minuman"));
        daftarMenu.add(new Menu("Jus Alpukat", 12000, "Minuman"));
        daftarMenu.add(new Menu("Kopi Hitam", 8000, "Minuman"));
        daftarMenu.add(new Menu("Air Mineral", 3000, "Minuman"));
    }

    /**
     * Method untuk menampilkan daftar menu
     */
    public static void tampilMenu() {
        System.out.println("\n-----Daftar Menu-----");
        int nomor = 1;

        System.out.println("\n-- Kategory: Makanan --");
        for (Menu menu : daftarMenu) {
            if (menu.kategori.equals("Makanan")) {
                System.out.printf(nomor + ". " + menu.nama + "\t\t(Rp. " + menu.harga + ")");
                nomor++;
            }
        }

        System.out.println("\n-- Kategory: Minuman --");
        for (Menu menu : daftarMenu) {
            if (menu.kategori.equals("Minuman")) {
                System.out.printf(nomor + ". " + menu.nama + "\t\t(Rp. " + menu.harga + ")");
                nomor++;
            }
        }

        if (daftarMenu.isEmpty()) {
            System.out.println("Menu masih kosong.");
        }
    }

    /**
     * Logika Menu Pelanggan
     */
    public static void menuPelanggan() {
        tampilMenu();

        ArrayList<Pesanan> pesananList = new ArrayList<>();

        while (true) {
            System.out.println("\n(Ketik 'selesai' untuk selesai memesan)");
            String input = getStringInput("Masukkan nomor menu: ");

            if (input.equalsIgnoreCase("selesai")) {
                break;
            }

            int nomorMenu;
            try {
                nomorMenu = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Input tidak valid. Masukkan nomor menu atau 'selesai'.");
                continue;
            }

            if (nomorMenu < 1 || nomorMenu > daftarMenu.size()) {
                System.out.println("Error: Nomor menu tidak ada dalam daftar.");
                continue;
            }

            Menu menuDipesan = daftarMenu.get(nomorMenu - 1);

            int qty = getIntInput("Masukkan jumlah untuk '" + menuDipesan.nama + "': ", 1, 99);

            pesananList.add(new Pesanan(menuDipesan, qty));
            System.out.println("'" + menuDipesan.nama + "' (x" + qty + ") telah ditambahkan.");
        }

        if (pesananList.isEmpty()) {
            System.out.println("Anda tidak memesan apa-apa. Kembali ke menu utama.");
        } else {
            cetakStruk(pesananList);
        }
    }

    /**
     * Method Cetak Struk Pemesanan
     */
    public static void cetakStruk(ArrayList<Pesanan> pesananList) {
        System.out.println("\n-------- STRUK PEMESANAN --------");
        int subTotal = 0;

        for (Pesanan p : pesananList) {
            System.out.println("- " + p.menu.nama + "\t(x" + p.qty + ") \t: Rp. " + p.getTotalHarga());
            subTotal += p.getTotalHarga();
        }

        System.out.println("---------------------------------");
        System.out.println("Subtotal \t\t\t= Rp. " + subTotal);

        double pajak = subTotal * 0.10;
        int biayaPelayanan = 20000;
        double diskon = 0.0;
        int gratisMinuman = 0;
        String infoGratisMinuman = "";

        System.out.println("Pajak (10%) \t\t\t= Rp. " + (int) pajak);
        System.out.println("Biaya Pelayanan \t\t= Rp. " + biayaPelayanan);

        if (subTotal > 100000) {
            diskon = subTotal * 0.10;
            System.out.println("Diskon (10%) \t\t\t= Rp. " + (int) diskon);
        }

        if (subTotal > 50000) {
            for (Pesanan p : pesananList) {
                if (p.menu.kategori.equals("Minuman")) {
                    gratisMinuman = p.menu.harga;
                    infoGratisMinuman = "Promo beli 1 Gratis 1 (" + p.menu.nama + ")";
                    break;
                }
            }
        }

        if (gratisMinuman > 0) {
            System.out.println(infoGratisMinuman + " \t= Rp.-" + gratisMinuman);
        }

        double grandTotal = subTotal + pajak + biayaPelayanan - diskon - gratisMinuman;
        System.out.println("---------------------------------");
        System.out.println("Grand Total \t\t\t= Rp. " + (int) grandTotal);
        System.out.println("-------- TERIMA KASIH --------");
    }

    /**
     * Logika Menu Pemilik
     */
    public static void menuPemilik() {
        while (true) {
            System.out.println("\n----- MANAJEMENMENU PEMILIK -----");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Lihat daftar Menu");
            System.out.println("5. Kembali ke Menu Utama");

            int pilihan = getIntInput("Pilih menu (1-5): ", 1, 5);

            switch (pilihan) {
                case 1:
                    tambahMenu();
                    break;
                case 2:
                    ubahMenu();
                    break;
                case 3:
                    hapusMenu();
                    break;
                case 4:
                    tampilMenu();
                    break;
                case 5:
                    return;
            }
        }
    }

    /**
     * Method Tambah Menu
     */
    public static void tambahMenu() {
        System.out.println("\n----- TAMBAH MENU BARU-----");
        String nama = getStringInput("Masukkan nama menu: ");
        int harga = getIntInput("Masukkan harga menu: ", 0, Integer.MAX_VALUE);

        String kategori;
        while (true) {
            kategori = getStringInput("Masukkan kategori menu (Makanan/Minuman): ");
            if (kategori.equalsIgnoreCase("Makanan") || kategori.equalsIgnoreCase("Minuman")) {
                kategori = kategori.substring(0, 1).toUpperCase() + kategori.substring(1).toLowerCase();
                break;
            } else {
                System.out.println("Error: Kategori harus 'Makanan' atau 'Minuman'.");
            }
        }

        daftarMenu.add(new Menu(nama, harga, kategori));
        System.out.println("Menu '" + nama + "' telah ditambahkan ke Menu.");
    }

    /**
     * Method ubah menu
     */
    public static void ubahMenu() {
        tampilMenu();
        if (daftarMenu.isEmpty()) {
            return;
        }

        System.out.println("\n----- UBAH HARGA MENU -----");
        int nomorMenu = getIntInput("Masukkan nomor menu yang akan diubah: ", 1, daftarMenu.size());

        Menu menuDiubah = daftarMenu.get(nomorMenu - 1);
        int hargaBaru = getIntInput("Masukkan harga baru untuk '" + menuDiubah.nama + "': ", 0, Integer.MAX_VALUE);

        boolean yakin = getKonfirmasi("Anda yakin ingin mengubah harga '" + menuDiubah.nama + "'? (Ya/Tidak): ");

        if (yakin) {
            menuDiubah.harga = hargaBaru;
            System.out.println("Harga berhasil diubah.");
        } else {
            System.out.println("Perubahan dibatalkan.");
        }
    }

    /**
     * Method hapus menu
     */
    public static void hapusMenu() {
        tampilMenu();
        if (daftarMenu.isEmpty())
            return;

        System.out.println("\n----- HAPUS MENU -----");
        int nomorMenu = getIntInput("Masukkan nomor menu yang akan dihapus: ", 1, daftarMenu.size());

        Menu menuDihapus = daftarMenu.get(nomorMenu - 1);

        boolean yakin = getKonfirmasi("Anda yakin ingin menghapus '" + menuDihapus.nama + "'? (Ya/Tidak): ");

        if (yakin) {
            daftarMenu.remove(nomorMenu - 1);
            System.out.println("Menu berhasil dihapus.");
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }

    /**
     * Helper untuk mengambil input String yang tidak kosong.
     */
    public static String getStringInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (input == null || input.trim().isEmpty()) {
                System.out.println("Error: Input tidak boleh kosong.");
            } else {
                break; // Input valid
            }
        }
        return input;
    }

    /**
     * Helper untuk mengambil input Integer yang aman dan berada dalam rentang.
     */
    public static int getIntInput(String prompt, int min, int max) {
        int input;
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine(); // Selalu baca sebagai string
                input = Integer.parseInt(line); // Coba konversi ke angka

                if (input < min || input > max) {
                    System.out.println("Error: Pilihan harus antara " + min + " dan " + max + ".");
                } else {
                    break; // Angka valid dan dalam rentang
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Input harus berupa angka.");
            }
        }
        return input;
    }

    /**
     * Helper untuk konfirmasi (Ya/Tidak).
     */
    public static boolean getKonfirmasi(String prompt) {
        while (true) {
            String input = getStringInput(prompt);
            if (input.equalsIgnoreCase("Ya")) {
                return true;
            } else if (input.equalsIgnoreCase("Tidak")) {
                return false;
            } else {
                System.out.println("Error: Masukkan 'Ya' atau 'Tidak'.");
            }
        }
    }
}