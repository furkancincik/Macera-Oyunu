import javax.tools.Tool;
import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int money;
    private String charName;
    private String name;
    private Scanner input = new Scanner(System.in);
    private Inventory inventory;


    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar() {
        Samurai samurai = new Samurai();
        Knight knight = new Knight();
        Archer archer = new Archer();

        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println();
        System.out.println("Lutfen bir karakter seciniz ! ");
        System.out.println("--------------------------------------------------------------------------");

        for (GameChar gameChar : charList) {
            System.out.println("ID:" + gameChar.getId() + " Karakter : " + gameChar.getCharName() +
                    "\t||\tHasar : " + gameChar.getDamage() +
                    "\t||\tSağlık : " + gameChar.getHealth() +
                    "\t||\tPara : " + gameChar.getMoney());
        }
        System.out.println("--------------------------------------------------------------------------");
        int selectChar = input.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
        /*System.out.println("Karakteriniz : " + this.getCharName() +
                "\t||\tHasar : " + this.getDamage() +
                "\t||\tSağlık : " + this.getHealth() +
                "\t||\tPara : " + this.getMoney());

         */
    }


    public void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getCharName());
    }

    public void printInfo() {
        System.out.println("Silahınız : " + this.getInventory().getWeapon().getName() +
                "\t||\tHasar : " + this.getDamage() +
                "\t||\tSağlık : " + this.getHealth() +
                "\t||\tPara : " + this.getMoney());
        System.out.println("Zırhınız  : " + this.getInventory().getArmor().getName() +
                "\t||\tBloklama : " + this.getDamage() +
                "\t||\tSağlık : " + this.getHealth() +
                "\t||\tPara : " + this.getMoney());
    }


    public int getDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
