import java.awt.*;
import java.util.ArrayList;

public class Warehouse {
    private int WarehouseID;
    private final int MAX_BOXES;
    private final int MAX_TRUCKS;
    private ArrayList<Box> inventory = new ArrayList<>();
    private ArrayList<Truck> trucks = new ArrayList<>();

    /**
     * Warehouse constructor
     * @param id - the id of warehouse
     * @param maxBoxes - maxBoxes of warehouse
     * @param maxTrucks - max trucks of warehouse
     */
    Warehouse(int id, int maxBoxes, int maxTrucks) {
        this.WarehouseID = id;
        this.MAX_BOXES = maxBoxes;
        this.MAX_TRUCKS = maxTrucks;
    }

    /**
     * addBox
     * adds a box to the warehouse inventory
     * @param box - the box to add
     */
    public void addBox(Box box) {
        this.inventory.add(box);
    }

    /**
     * removeBox
     * removes box
     * @param boxID - the id of which box to remove
     */
    public void removeBox(int boxID) {

        for (Box box : this.inventory) {
            if (box.getBoxID() == boxID) {
                this.inventory.remove(box);
            }
        }

    }

    /**
     * addTruck
     * adds the truck to warehouse list
     * @param truck - truck to add
     */
    public void addTruck(Truck truck) {
        this.trucks.add(truck);
    }

    /**
     * removeTruck
     * removes the truck from list
     * @param truckID - id of truck to remove
     */
    public void removeTruck(int truckID) {
        for (Truck truck : this.trucks) {
            if (truck.getTruckID() == truckID) {
                this.trucks.remove(truck);
            }
        }
    }

    // will be overridden
    public void draw(Graphics g) {}

    /**
     * asArray
     * will return all values of Warehouse into a int array
     * @return - int array of values
     */
    public int[] asArray() {
        return new int[]{this.WarehouseID, this.MAX_BOXES, this.MAX_TRUCKS};
    }

    /**
     * toString override
     * will make a string out of warehouse and values - used in uploading info to files
     * @return - the string object
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Warehouse"); // gets object type and its initation values into string
        int[] values = this.asArray();

        for (int value : values) { // adds all values
            string.append(" ");
            string.append(value);
        }

        for (Box box : this.inventory) { // adds all string representation of boxes in inventory to string
            string.append("\n");
            string.append(box.toString());
        }

        for (Truck truck : this.trucks) { // adds all string representation of trucks and their boxes to string
            string.append("\n");
            string.append(truck.toString());
        }


        return string.toString();
    }


    /**
     * setTrucks
     * sets the list of trucks in warehouse to given arraaylist
     * @param trucks - arraylist of trucks to become the list for warehouse
     */
    public void setTrucks(ArrayList<Truck> trucks) {
        this.trucks = trucks;
    }
    public void setInventory(ArrayList<Box> inventory){
        this.inventory = inventory;
    }

    public ArrayList<Box> getInventory() {
        return inventory;
    }

    public ArrayList<Truck> getTrucks() {
        return trucks;
    }
}