public abstract class MenuItem {
    protected String nama;
    protected double harga;
    protected String kategori;

    public MenuItem(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public abstract void tampilMenu();

    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public String getKategori() { return kategori; }
}

class Makanan extends MenuItem {
    private String jenisMakanan;

    public Makanan(String nama, double harga, String jenisMakanan) {
        super(nama, harga, "Makanan");
        this.jenisMakanan = jenisMakanan;
    }

    @Override
    public void tampilMenu() {
        System.out.println("[Makanan] " + nama + " \t| Rp " + (int)harga + " | Info: " + jenisMakanan);
    }
    
    public String getJenisMakanan() { return jenisMakanan; }
}

class Minuman extends MenuItem {
    private String jenisMinuman;

    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman");
        this.jenisMinuman = jenisMinuman;
    }

    @Override
    public void tampilMenu() {
        System.out.println("[Minuman] " + nama + " \t| Rp " + (int)harga + " | Info: " + jenisMinuman);
    }
    
    public String getJenisMinuman() { return jenisMinuman; }
}

class Diskon extends MenuItem {
    private double besarDiskon;

    public Diskon(String nama, double besarDiskon) {
        super(nama, 0, "Diskon");
        this.besarDiskon = besarDiskon;
    }

    @Override
    public void tampilMenu() {
        System.out.println("[Promo]   " + nama + " \t| Diskon: " + (int)(besarDiskon * 100) + "%");
    }

    public double getBesarDiskon() {
        return besarDiskon;
    }
}