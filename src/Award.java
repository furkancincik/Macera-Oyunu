public class Award {
    private String name;
    private int amount;
    private int damageOrBlock;

    public Award(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public Award(String name, int amount, int damageOrBlock) {
        this(name, amount);
        this.damageOrBlock = damageOrBlock;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDamageOrBlock() {
        return damageOrBlock;
    }

    public void setDamageOrBlock(int damageOrBlock) {
        this.damageOrBlock = damageOrBlock;
    }
}
