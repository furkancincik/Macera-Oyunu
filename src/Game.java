import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Macera Oyununa Hoşgeldiniz !");
        System.out.println("Lutfen bir isim giriniz : ");
        //String playerName = input.nextLine();
        Player player = new Player("Furkan");
        System.out.println("Sayın " + player.getName() + " Hosgeldiniz !");
        player.selectChar();

        Location location=null;
        while (true){
            player.printInfo();
            System.out.println();
            System.out.println("---------BÖLGELER---------");
            System.out.println("1- Güvenli Ev");
            System.out.println("2- Mağaza");
            System.out.println();
            System.out.println("Lutfen Gitmek İstediğiniz Bölgeyi Seçiniz ");
            System.out.println();
            int selectLoc = input.nextInt();;
            switch (selectLoc){
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                default:
                    location = new SafeHouse(player);
            }
            if (!location.onLocation()){
                System.out.println("GAME OVER !");
                break;
            }

        }




    }




}
