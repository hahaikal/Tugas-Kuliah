import java.io.*;
import java.util.ArrayList;
// import java.util.Scanner;

public class Menu {
    private ArrayList<MenuItem> daftarMenu;

    public Menu() {
        daftarMenu = new ArrayList<>();
    }

    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
    }

    public void tampilkanSemuaMenu() {
        System.out.println("\n=== DAFTAR MENU RESTORAN ===");
        if (daftarMenu.isEmpty()) {
            System.out.println("(Menu masih kosong)");
        } else {
            for (int i = 0; i < daftarMenu.size(); i++) {
                System.out.print((i + 1) + ". ");
                daftarMenu.get(i).tampilMenu();
            }
        }
    }

    public MenuItem getItem(int index) {
        if (index >= 0 && index < daftarMenu.size()) {
            return daftarMenu.get(index);
        }
        return null;
    }
    
    public int getJumlahMenu() {
        return daftarMenu.size();
    }

    public void simpanMenuKeFile(String namaFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            for (MenuItem item : daftarMenu) {
                String baris = "";
                if (item instanceof Makanan) {
                    baris = "Makanan;" + item.getNama() + ";" + item.getHarga() + ";" + ((Makanan) item).getJenisMakanan();
                } else if (item instanceof Minuman) {
                    baris = "Minuman;" + item.getNama() + ";" + item.getHarga() + ";" + ((Minuman) item).getJenisMinuman();
                } else if (item instanceof Diskon) {
                    baris = "Diskon;" + item.getNama() + ";0;" + ((Diskon) item).getBesarDiskon();
                }
                writer.write(baris);
                writer.newLine();
            }
            System.out.println("Data menu berhasil disimpan ke " + namaFile);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file: " + e.getMessage());
        }
    }

    public void muatMenuDariFile(String namaFile) {
        File file = new File(namaFile);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                String tipe = data[0];
                String nama = data[1];
                double harga = Double.parseDouble(data[2]);
                
                if (tipe.equals("Makanan")) {
                    String info = data[3];
                    tambahItem(new Makanan(nama, harga, info));
                } else if (tipe.equals("Minuman")) {
                    String info = data[3];
                    tambahItem(new Minuman(nama, harga, info));
                } else if (tipe.equals("Diskon")) {
                    double besarDiskon = Double.parseDouble(data[3]);
                    tambahItem(new Diskon(nama, besarDiskon));
                }
            }
            System.out.println("Data menu berhasil dimuat dari " + namaFile);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }
    }
}