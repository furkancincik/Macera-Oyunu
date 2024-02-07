public class NormalLoc extends Location{
    public NormalLoc(Player player, String locName) {
        super(player, locName);
    }

    @Override
    public boolean onLocation(){
        return true;
    }
}
