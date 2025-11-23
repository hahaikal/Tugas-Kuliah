public class Pesanan {
    Menu menu;
    int qty;

    public Pesanan(Menu menu,int qty) {
        this.menu = menu;
        this.qty = qty;
    }

    public int getTotalHarga() {
        return this.menu.harga * this.qty;
    }
}
