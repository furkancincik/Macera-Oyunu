public class Inventory {
    Weapon weapon;
    Armor armor;

    public Inventory() {
        this.weapon = new Weapon(0, "Yumruk",0,0);
        this.armor = new Armor(0,"Çıplak",0,0);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }


}
