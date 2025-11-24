import java.util.Scanner;
import java.util.InputMismatchException;

public class ManajemenRestoran {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menuRestoran = new Menu();
        Pesanan pesananPelanggan = new Pesanan();
        String fileMenu = "data_menu.txt";

        menuRestoran.muatMenuDariFile(fileMenu);

        boolean berjalan = true;
        while (berjalan) {
            System.out.println("\n=== APLIKASI MANAJEMEN RESTORAN (TUGAS 3) ===");
            System.out.println("1. Tambah Item Baru ke Menu");
            System.out.println("2. Tampilkan Daftar Menu");
            System.out.println("3. Pesan Menu (Pelanggan)");
            System.out.println("4. Hitung Total & Cetak Struk");
            System.out.println("5. Simpan & Keluar");
            System.out.print("Pilih menu: ");

            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
                        System.out.println("\nPilih Tipe Item:");
                        System.out.println("1. Makanan");
                        System.out.println("2. Minuman");
                        System.out.println("3. Diskon (Promo)");
                        System.out.print("Pilihan: ");
                        int tipe = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Nama Item: ");
                        String nama = scanner.nextLine();

                        if (tipe == 1) {
                            System.out.print("Harga: ");
                            double harga = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.print("Jenis (misal: Pedas/Manis): ");
                            String jenis = scanner.nextLine();
                            menuRestoran.tambahItem(new Makanan(nama, harga, jenis));
                        } else if (tipe == 2) {
                            System.out.print("Harga: ");
                            double harga = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.print("Jenis (misal: Dingin/Panas): ");
                            String jenis = scanner.nextLine();
                            menuRestoran.tambahItem(new Minuman(nama, harga, jenis));
                        } else if (tipe == 3) {
                            System.out.print("Besar Diskon (Desimal, cth: 0,1 untuk 10%): ");
                            double diskon = scanner.nextDouble();
                            menuRestoran.tambahItem(new Diskon(nama, diskon));
                        }
                        System.out.println("Item berhasil ditambahkan!");
                        break;

                    case 2:
                        menuRestoran.tampilkanSemuaMenu();
                        break;

                    case 3:
                        menuRestoran.tampilkanSemuaMenu();
                        if (menuRestoran.getJumlahMenu() > 0) {
                            System.out.print("Masukkan Nomor Menu yang dipesan (0 untuk selesai): ");
                            while (true) {
                                System.out.print("Nomor Menu > ");
                                int idx = scanner.nextInt();
                                if (idx == 0) break;
                                
                                MenuItem item = menuRestoran.getItem(idx - 1);
                                if (item != null) {
                                    pesananPelanggan.tambahPesanan(item);
                                } else {
                                    System.out.println("Nomor menu tidak valid!");
                                }
                            }
                        }
                        break;

                    case 4:
                        pesananPelanggan.cetakStruk();
                        pesananPelanggan.simpanStrukKeFile("struk_pelanggan.txt");
                        break;

                    case 5:
                        menuRestoran.simpanMenuKeFile(fileMenu);
                        System.out.println("Data menu disimpan. Terima kasih!");
                        berjalan = false;
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Masukkan angka yang valid!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }
        scanner.close();
    }
}