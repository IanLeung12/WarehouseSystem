import java.util.ArrayList;
import java.io.File;

public class Warehouse {
    int WarehouseID;
    ArrayList<Box> inventory = new ArrayList<>();
    ArrayList<Truck> trucks = new ArrayList<>();
    final int MAX_BOXES;
    final int MAX_TRUCKS;

    Warehouse(int id, int maxBoxes, int maxTrucks) {
        this.WarehouseID = id;
        this.MAX_BOXES = maxBoxes;
        this.MAX_TRUCKS = maxTrucks;
    }

    public void addBox(Box box) {}
    public void removeBox(int boxID) {};
    public void addTruck(Truck truck) {}
    public void removeTruck(int truckID) {}
    public void uploadInventory(File file) {}
    public void downloadInventory(File file) {}
    //public void draw(Graphics g) {}
    //public String toString() {return}





}