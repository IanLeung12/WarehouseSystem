public class Box {
    private int boxID;
    private int weight;
    private int height;
    private int length;
    private int width;
    private int posXInTruck;
    private int positionYInTruck;
    private int inTruck;
    private int inWarehouse;
    Box () {
        this.posXInTruck = -1;
        this.positionYInTruck = -1;
        this.inTruck = -1;
        this.inWarehouse = -1;
    }
    void rotate(){
        int tempLength;
        tempLength = this.length;
        this.length = this.width;
        this.width = tempLength;
    }

    public int getBoxID() {
        return boxID;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getPosXInTruck() {
        return posXInTruck;
    }

    public int getPositionYInTruck() {
        return positionYInTruck;
    }

    public void setBoxID(int boxID) {
        this.boxID = boxID;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setPosXInTruck(int posXInTruck) {
        this.posXInTruck = posXInTruck;
    }

    public void setPositionYInTruck(int positionYInTruck) {
        this.positionYInTruck = positionYInTruck;
    }
}
