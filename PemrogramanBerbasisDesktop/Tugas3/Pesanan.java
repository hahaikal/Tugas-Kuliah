import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pesanan {
    private ArrayList<MenuItem> daftarPesanan;

    public Pesanan() {
        daftarPesanan = new ArrayList<>();
    }

    public void tambahPesanan(MenuItem item) {
        daftarPesanan.add(item);
        System.out.println("Berhasil memesan: " + item.getNama());
    }

    private double getSubTotal() {
        double subTotal = 0;
        for (MenuItem item : daftarPesanan) {
            if (!(item instanceof Diskon)) {
                subTotal += item.getHarga();
            }
        }
        return subTotal;
    }

    private double getTotalDiskonPersen() {
        double totalPersen = 0;
        for (MenuItem item : daftarPesanan) {
            if (item instanceof Diskon) {
                totalPersen += ((Diskon) item).getBesarDiskon();
            }
        }
        if (totalPersen > 1)
            totalPersen = 1;
        return totalPersen;
    }

    public void cetakStruk() {
        double subTotal = getSubTotal();
        double diskonPersen = getTotalDiskonPersen();
        double nominalPotongan = subTotal * diskonPersen;
        double totalAkhir = subTotal - nominalPotongan;

        System.out.println("\n================ STRUK PESANAN =================");

        System.out.println("DAFTAR ITEM:");
        for (MenuItem item : daftarPesanan) {
            if (!(item instanceof Diskon)) {
                System.out.printf("- %-25s : Rp %d\n", item.getNama(), (int) item.getHarga());
            }
        }

        System.out.println("------------------------------------------------");
        System.out.printf("SUBTOTAL                      : Rp %d\n", (int) subTotal);

        if (nominalPotongan > 0) {
            System.out.println("\nPROMO APPLIED:");
            for (MenuItem item : daftarPesanan) {
                if (item instanceof Diskon) {
                    Diskon diskonItem = (Diskon) item;
                    int persen = (int) (diskonItem.getBesarDiskon() * 100);
                    int nominalDiskon = (int) (subTotal * diskonItem.getBesarDiskon());
                    System.out.printf("* %-20s : -Rp %d (%d%%)\n", diskonItem.getNama(), nominalDiskon, persen);
                }
            }
            System.out.printf("POTONGAN DISKON               : -Rp %d\n", (int) nominalPotongan);
        }

        System.out.println("------------------------------------------------");
        System.out.printf("TOTAL BAYAR                   : Rp %d\n", (int) totalAkhir);
        System.out.println("================================================");
    }

    public void simpanStrukKeFile(String namaFile) {
        double subTotal = getSubTotal();
        double diskonPersen = getTotalDiskonPersen();
        double nominalPotongan = subTotal * diskonPersen;
        double totalAkhir = subTotal - nominalPotongan;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            writer.write("================ STRUK PESANAN =================\n");
            writer.write("DAFTAR ITEM:\n");

            for (MenuItem item : daftarPesanan) {
                if (!(item instanceof Diskon)) {
                    writer.write(String.format("- %-25s : Rp %d\n", item.getNama(), (int) item.getHarga()));
                }
            }

            writer.write("------------------------------------------------\n");
            writer.write("SUBTOTAL                      : Rp " + (int) subTotal + "\n");

            if (nominalPotongan > 0) {
                writer.write("\nPROMO APPLIED:\n");
                for (MenuItem item : daftarPesanan) {
                    if (item instanceof Diskon) {
                        Diskon diskonItem = (Diskon) item;
                        int persen = (int) (diskonItem.getBesarDiskon() * 100);
                        int nominalDiskon = (int) (subTotal * diskonItem.getBesarDiskon());
                        writer.write(String.format("* %-20s : -Rp %d (%d%%)\n", diskonItem.getNama(), nominalDiskon,
                                persen));
                    }
                }
                writer.write("POTONGAN DISKON               : -Rp " + (int) nominalPotongan + "\n");
            }

            writer.write("------------------------------------------------\n");
            writer.write("TOTAL BAYAR                   : Rp " + (int) totalAkhir + "\n");
            writer.write("================================================\n");

            System.out.println("--> Struk berhasil disimpan ke file: " + namaFile);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk: " + e.getMessage());
        }
    }
}