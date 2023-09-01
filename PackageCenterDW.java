public class PackageCenterDW implements PackageCenterInterface {
    private  int matrixPos;
    private  String place;
    public PackageCenterDW(String place1, int matrixPos) {
        this.place = place1;
        this.matrixPos = matrixPos;

    }

    @Override
    public String getName() {
        return place;
    }

    @Override
    public int getMatrixPosition() {

        return matrixPos;
    }
    @Override
    public void setMatrixPosition(int pos) {
        // TODO Auto-generated method stub
        this.matrixPos = pos;
    }
}
