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
            System.out.println("2- Eşya Dükkanı");
            System.out.println("3- Mağara(Zombi)");
            System.out.println("4- Orman(Vampir)");
            System.out.println("5- Nehir(Ayı)");
            System.out.println("0- Oyunu Sonlandır");
            System.out.println();
            System.out.println("Lutfen Gitmek İstediğiniz Bölgeyi Seçiniz ");
            System.out.println();

            int selectLoc = input.nextInt();;

            input.nextLine();//buffer clearing

            switch (selectLoc){
                case 0:
                    location=null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location=new Cave(player);
                    break;
                case 4:
                    location=new Forest(player);
                    break;
                case 5:
                    location=new River(player);
                    break;
                default:
                    System.out.println("Lütfen Geçerli Bir Giriş Yapiniz.");
            }
            if (location==null){
                System.out.println("Oyun Bitti.");
                break;
            }
            else if (!location.onLocation()){
                System.out.println("GAME OVER !");
                break;
            }

        }




    }




}
