public class ToolStore extends NormalLoc {
    public ToolStore(Player player) {
        super(player, "Mağza");
    }


    @Override
    public boolean onLocation() {
        System.out.println("Mağazaya Hoşgeldiniz !");
        System.out.println();
        System.out.println("1-- Silahlar");
        System.out.println("2-- Zırhlar");
        System.out.println("3-- Çıkış Yap");
        int selectCase = Location.input.nextInt();
        while (selectCase < 1 || selectCase > 3) {
            System.out.println("Geçersiz bir değer, tekrar deneyiniz.");
            selectCase = Location.input.nextInt();
        }
        switch (selectCase) {
            case 1:
                printWeapon();
                buyWeapon();
                break;
            case 2:
                printArmor();
                buyArmor();
                break;
            case 3:
                System.out.println("Tekrar Bekleriz !");
                return true;
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("--------- Silahlar ---------");
        System.out.println();
        for (Weapon w : Weapon.weapons()) {
            System.out.println("ID:" + w.getId() + " | " + w.getName() + " < Para:" + w.getPrice() + " - Hasar:" + w.getDamage() + "  >");
        }
    }

    public void buyWeapon(){
        System.out.print("Bir silah seçiniz: ");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 1 || selectWeaponID > Weapon.weapons().length) {
            System.out.println("Geçersiz bir değer, tekrar deneyiniz.");
            selectWeaponID = Location.input.nextInt();
        }

        Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);

        if (selectedWeapon != null) {
            if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                System.out.println("Yeterli Paranız Bulunmamaktadır !");
            } else {
                //Satın almanın gerçekleştiği alan.
                System.out.println();
                System.out.println(selectedWeapon.getName() + " silahını satın aldınız.");
                int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                this.getPlayer().setMoney(balance);
                System.out.println("Kalan Paranız: " + this.getPlayer().getMoney());
                this.getPlayer().getInventory().setWeapon(selectedWeapon);
            }
        }
    }

    public void printArmor() {
        System.out.println("--------- Zırhlar ---------");
        System.out.println();
        for (Armor a : Armor.armors()) {
            System.out.println("ID:" + a.getId() + " | " + a.getName() + " < Para:" + a.getPrice() + " - Block:" + a.getBlock() + "  >");
        }
    }


    public void buyArmor(){
        System.out.print("Bir zırh seçiniz: ");
        int selectArmorID=input.nextInt();
        while (selectArmorID < 1 || selectArmorID > Armor.armors().length) {
            System.out.println("Geçersiz bir değer, tekrar deneyiniz.");
            selectArmorID = Location.input.nextInt();
        }

        Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);

        if(selectedArmor != null){
            if(selectedArmor.getPrice()>this.getPlayer().getMoney()){
                System.out.println("Yeterli Paranız Bulunmamaktadır !");
            }else{
                //Satın almanın gerçekleştiği alan.
                System.out.println();
                System.out.println(selectedArmor.getName()+" zırhı satın aldınız.");
                int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                System.out.println("Kalan paranız: "+this.getPlayer().getMoney());
                this.getPlayer().getInventory().setArmor(selectedArmor);
            }
        }


    }


}
