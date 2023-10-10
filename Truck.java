import java.util.ArrayList;

public class Truck {

    private int truckID;
    private int maxWeight;
    private int height;
    private int length;
    private int width;
    private int inWarehouse;
    ArrayList<Box> boxes = new ArrayList<>();
    Truck () {
        this.truckID = -1;
        this.inWarehouse = -1;
    }
    void addBox(Box box){
        boxes.add(box);
    }
    void removeBox(int boxID){

    }
    int getLoadedWeight(){
        int weight = 0;
        for(Box i: boxes){
            weight = weight + i.getWeight();
        }
        return(weight);
    }
    
    boolean isValid(Box box, int x, int y){
        boolean isVal = true;

        if((this.getLoadedWeight() + box.getWeight())> maxWeight){
            isVal = false;
        }



        return(isVal);
    }

    public int getTruckID() {
        return truckID;
    }

    public int getMaxWeight() {
        return maxWeight;
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
    public int getInWarehouse() {
        return inWarehouse;
    }
    public void setTruckID(int truckID) {
        this.truckID = truckID;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
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
    public void setInWarehouse(int iw) {
        this.inWarehouse = iw;
    }
}
