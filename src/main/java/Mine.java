public class Mine {

    final static char MINE_FIG = 'o';
    MinePosition minePosition;
    MinePosition prevMinePosition;

    public Mine(MinePosition minePosition) {
        this.minePosition=minePosition;
        MinePosition prevMinePosition=new MinePosition(1, 1); //borde kunna vara samma från början??
    }

    public void move (){
        prevMinePosition=minePosition;
        minePosition.setX(minePosition.getX()-1);
    }

    public MinePosition getMinePosition() {
        return minePosition;
    }

    public void setMinePosition(MinePosition minePosition) {
        this.minePosition = minePosition;
    }

    public MinePosition getPrevMinePosition() {
        return prevMinePosition;
    }

    public void setPrevMinePosition(MinePosition prevMinePosition) {
        this.prevMinePosition = prevMinePosition;
    }
}
