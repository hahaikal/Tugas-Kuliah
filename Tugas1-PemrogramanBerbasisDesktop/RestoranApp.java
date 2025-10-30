public class RestoranApp {

    public static void main(String[] args) {
        
        Menu[] menuMakanan = new Menu[4];
        menuMakanan[0] = new Menu("Nasi Padang", 25000, "Makanan");
        menuMakanan[1] = new Menu("Ayam Bakar", 18000, "Makanan");
        menuMakanan[2] = new Menu("Sate Ayam", 20000, "Makanan");
        menuMakanan[3] = new Menu("Gado-Gado", 15000, "Makanan");

        Menu[] menuMinuman = new Menu[4];
        menuMinuman[0] = new Menu("Es Teh Manis", 5000, "Minuman");
        menuMinuman[1] = new Menu("Jus Alpukat", 12000, "Minuman");
        menuMinuman[2] = new Menu("Kopi Hitam", 8000, "Minuman");
        menuMinuman[3] = new Menu("Air Mineral", 3000, "Minuman");

        tampilMenu(menuMakanan, menuMinuman);

        Menu pesanan1 = menuMakanan[0];
        int qty1 = 5;

        Menu pesanan2 = menuMakanan[1];
        int qty2 = 0;

        Menu pesanan3 = menuMinuman[1];
        int qty3 = 1;

        Menu pesanan4 = null;
        int qty4 = 0;


        System.out.println("\n\n================ STRUK PEMBAYARAN ================");
        cetakStruk(pesanan1, qty1, pesanan2, qty2, pesanan3, qty3, pesanan4, qty4);
    }

    public static void tampilMenu(Menu[] makanan, Menu[] minuman) {
        System.out.println("========= SELAMAT DATANG DI RESTORAN KAMI =========");
        System.out.println("\n--- MAKANAN ---");
        System.out.println("1. " + makanan[0].nama + " \t- Rp " + makanan[0].harga);
        System.out.println("2. " + makanan[1].nama + " \t\t- Rp " + makanan[1].harga);
        System.out.println("3. " + makanan[2].nama + " \t\t- Rp " + makanan[2].harga);
        System.out.println("4. " + makanan[3].nama + " \t\t- Rp " + makanan[3].harga);

        System.out.println("\n--- MINUMAN ---");
        System.out.println("5. " + minuman[0].nama + " \t- Rp " + minuman[0].harga);
        System.out.println("6. " + minuman[1].nama + " \t- Rp " + minuman[1].harga);
        System.out.println("7. " + minuman[2].nama + " \t\t- Rp " + minuman[2].harga);
        System.out.println("8. " + minuman[3].nama + " \t- Rp " + minuman[3].harga);
    }

    public static void cetakStruk(Menu p1, int q1, Menu p2, int q2, Menu p3, int q3, Menu p4, int q4) {
        
        int total1 = 0;
        int total2 = 0;
        int total3 = 0;
        int total4 = 0;
        int subTotal = 0;
        double pajak = 0.0;
        int biayaPelayanan = 20000;
        double diskon = 0.0;
        int gratisMinumanHarga = 0;
        String infoGratisMinuman = "";
        double grandTotal = 0.0;

        System.out.println("Item Dipesan:");
        if (p1 != null && q1 > 0) {
            total1 = p1.harga * q1;
            System.out.println("- " + p1.nama + " \t(x" + q1 + ") \t= Rp " + total1);
        }
        if (p2 != null && q2 > 0) {
            total2 = p2.harga * q2;
            System.out.println("- " + p2.nama + " \t(x" + q2 + ") \t= Rp " + total2);
        }
        if (p3 != null && q3 > 0) {
            total3 = p3.harga * q3;
            System.out.println("- " + p3.nama + " \t(x" + q3 + ") \t= Rp " + total3);
        }
        if (p4 != null && q4 > 0) {
            total4 = p4.harga * q4;
            System.out.println("- " + p4.nama + " \t(x" + q4 + ") \t= Rp " + total4);
        }

        subTotal = total1 + total2 + total3 + total4;
        System.out.println("-------------------------------------------------");
        System.out.println("SubTotal \t\t\t= Rp " + subTotal);

        pajak = 0.10 * subTotal;
        System.out.println("Pajak (10%) \t\t\t= Rp " + (int)pajak);
        System.out.println("Biaya Pelayanan \t\t= Rp " + biayaPelayanan);

        if (subTotal > 100000) {
            diskon = 0.10 * subTotal;
            System.out.println("Diskon (10%) \t\t\t= Rp -" + (int)diskon);
        }

        if (subTotal > 50000) {
            if (p1 != null && p1.kategori.equals("Minuman")) {
                gratisMinumanHarga = p1.harga;
                infoGratisMinuman = "Promo Beli 1 Gratis 1 (" + p1.nama + ")";
            } else if (p2 != null && p2.kategori.equals("Minuman")) {
                gratisMinumanHarga = p2.harga;
                infoGratisMinuman = "Promo Beli 1 Gratis 1 (" + p2.nama + ")";
            } else if (p3 != null && p3.kategori.equals("Minuman")) {
                gratisMinumanHarga = p3.harga;
                infoGratisMinuman = "Promo Beli 1 Gratis 1 (" + p3.nama + ")";
            } else if (p4 != null && p4.kategori.equals("Minuman")) {
                gratisMinumanHarga = p4.harga;
                infoGratisMinuman = "Promo Beli 1 Gratis 1 (" + p4.nama + ")";
            }
        }
        
        if (gratisMinumanHarga > 0) {
            System.out.println(infoGratisMinuman + " \t= Rp -" + gratisMinumanHarga);
        }

        grandTotal = subTotal + pajak + biayaPelayanan - diskon - gratisMinumanHarga;

        System.out.println("-------------------------------------------------");
        System.out.println("GRAND TOTAL \t\t\t= Rp " + (int)grandTotal);
        System.out.println("================ TERIMA KASIH ===================");
    }
}
