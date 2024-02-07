public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super(player, "Guvenli Ev");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli Evdesiniz !");
        System.out.println("Canınız Yenilendi !");
        return true;
    }


}
