import java.util.ArrayList;
import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    Object startingObject = new Object();


    public BattleLoc(Player player, String locName, Obstacle obstacle, String award, int maxObstacle) {
        super(player, locName);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;

    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şu an buradasınız: " + this.getLocName());
        System.out.println("Dikkatli Ol ! Burada " + obsNumber + " tane  " + this.getObstacle().getName() + " yaşıyor!");
        System.out.println("<S>avaş veya <K>aç : ");
        String selectCase = input.nextLine();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("S") && combat(obsNumber)) {
            System.out.println(this.getLocName() + " Tüm Düşmanları Yendiniz !");
            return true;

        }

        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz !");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStats();
            obstacleStats(i);
            // kimin başladıgına kara ver
            //---
            whoStarting();

            if (getStartingObject() == this.getPlayer()) {
                //player başlar
                String selectCombat;
                while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                    System.out.println("<V>ur veya <K>aç");
                    selectCombat = input.nextLine().toUpperCase();
                    if (selectCombat.equals("V")) {
                        System.out.println("Siz Vurdunuz !");
                        this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println();
                            System.out.println("Canavar Size Vurdu !");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                    } else {
                        return false;
                    }
                }
                if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                    System.out.println(i + "." + " Düşmanı Yendiniz !");
                    System.out.println(this.getObstacle().getAward() + " Para Kazandınız !");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                    System.out.println("Güncel Paranız :" + getPlayer().getMoney());
                } else {
                    return false;
                }
                //bitişi
            } else {
                //ilk obstacle  başlar

                while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                    System.out.println("Canavar size Vurdu !");
                    int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                    if (obstacleDamage < 0) {
                        obstacleDamage = 0;
                    }
                    System.out.println("<V>ur veya <K>aç");
                    String selectCombat = input.nextLine().toUpperCase();
                    if (selectCombat.equals("V")) {
                        System.out.println("Siz vurdunuz !");
                        this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println();
                            System.out.println("Canavar size vurdu !");
                            obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                    } else {
                        return false;
                    }
                }
                //bitişi
            }

        }
        isAwarded();

        return true;
    }


    //hit sonrasi info
    public void afterHit() {
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı: " + this.getObstacle().getHealth());
        System.out.println();
    }

    public void playerStats() {
        System.out.println("Oyuncu Degerleri");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        getPlayer().printInfo();
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println();

    }


    public void obstacleStats(int i) {
        System.out.println("Canavar Degerleri");
        System.out.println("-------------------------------------------------------------");
        System.out.println(i + "." + " Canavar : " + this.getObstacle().getName() +
                "\t||\tHasar : " + this.getObstacle().getDamage() +
                "\t||\tSağlık : " + this.getObstacle().getHealth() +
                "\t||\tÖdül : " + this.getObstacle().getAward());
        System.out.println("-------------------------------------------------------------");
        System.out.println();
    }


    //İlk Başlayanı Belirleyen metot
    public void whoStarting() {
        Random random = new Random();
        int randomNum = random.nextInt(2);

        //seçimin yapılacagı yer
        if (randomNum == 0) {
            startingObject = this.getPlayer();
        } else {
            startingObject = this.getObstacle();
        }
    }

    public int randomObstacleNumber() {
        Random r = new Random();
        if (this.getObstacle().getName().equals("Yılan")) {
            return r.nextInt(5) + 1;
        } else {
            return r.nextInt(this.getMaxObstacle()) + 1;
        }
    }


    //Ödül İhtimali Belirleme
    public Award determineAward() {
        Random random = new Random();
        int chance = random.nextInt(100); // 0 ile 99 arasında rastgele bir sayı alır

        if (chance < 15) {
            return new Award("Tüfek", 1, 3); // Tüfek kazanma ihtimali %15
        } else if (chance < 35) {
            return new Award("Kılıç", 1, 2); // Kılıç kazanma ihtimali %20
        } else if (chance < 65) {
            return new Award("Tabanca", 1, 1); // Tabanca kazanma ihtimali %30
        } else if (chance < 80) {
            return new Award("Ağır Zırh", 1, 5); // Ağır Zırh kazanma ihtimali %15
        } else if (chance < 95) {
            return new Award("Orta Zırh", 1, 3); // Orta Zırh kazanma ihtimali %15
        } else if (chance < 100) {
            return new Award("Hafif Zırh", 1, 1); // Hafif Zırh kazanma ihtimali %20
        } else {
            return null; // Hiçbir şey kazanamama ihtimali %5
        }
    }


    //Ödül Çağırdık
    public void isAwarded() {
        Award award = determineAward();
        boolean alreadyHasAward= false;
        if (award != null) {
            // Ödül kazanıldı, burada ödül ile yapılacak işlemleri gerçekleştirdik

            if (award.getName().equals("Tüfek") || award.getName().equals("Kılıç") || award.getName().equals("Tabanca")) {
                // Silah ödülü kazanıldıysa
                Weapon currentWeapon = this.getPlayer().getInventory().getWeapon();
                currentWeapon.setDamage(currentWeapon.getDamage() + award.getDamageOrBlock());
                System.out.println("Ödül kazanıldı: " + award.getName() + " (Hasar: " + award.getDamageOrBlock() + ")");
                if (currentWeapon.getName().equals(award.getName())) {
                    alreadyHasAward = true;
                } else {
                    currentWeapon.setDamage(currentWeapon.getDamage() + award.getDamageOrBlock());
                }
            } else if (award.getName().equals("Ağır Zırh") || award.getName().equals("Orta Zırh") || award.getName().equals("Hafif Zırh")) {
                // Zırh ödülü kazanıldıysa
                Armor currentArmor = this.getPlayer().getInventory().getArmor();
                currentArmor.setBlock(currentArmor.getBlock() + award.getDamageOrBlock());
                if (currentArmor.getName().equals(award.getName())) {
                    alreadyHasAward = true;
                } else {
                    currentArmor.setBlock(currentArmor.getBlock() + award.getDamageOrBlock());
                }

            }
            if (!alreadyHasAward) {
                System.out.println("Ödül kazanıldı: " + award.getName() + " (Zırh: " + award.getDamageOrBlock() + ")");
            } else {
                System.out.println("Zaten bu ödülü bulunduğunuz için tekrar kazanılmadı: " + award.getName());
            }


            System.out.println();
        } else {
            // Hiçbir şey kazanılmadı
            System.out.println("Ödül kazanılamadı.");
            System.out.println();
        }
    }


    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public Object getStartingObject() {
        return startingObject;
    }

    public void setStartingObject(Object startingObject) {
        this.startingObject = startingObject;
    }
}
