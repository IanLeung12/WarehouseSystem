import java.awt.*;
import java.util.ArrayList;

/**
 * Warehouse
 * @author Dilen De Silva, Michael Khart
 * ICS4UE
 * @version 1.0 - 2023/10/09
 * Warehouse stores all the boxes and trucks
 * It is able to add and delete them if needed
 */

public class Warehouse {
    private int WarehouseID;
    private int MAX_BOXES;
    private int MAX_TRUCKS;
    private ArrayList<Box> inventory = new ArrayList<>();
    private ArrayList<Truck> trucks = new ArrayList<>();



    Warehouse() {

    }


    /**
     * Warehouse
     * warehosue constructor
     * @param id - the id of warehosue
     * @param maxBoxes - maxboxes of warehouse
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
        if (this.inventory.size() + 1 > this.MAX_BOXES) {
            throw new RuntimeException("Too many boxes in warehouse");
        } else {
            this.inventory.add(box);
        }
    }








    /**
     * removeBox
     * removes box
     * @param boxID - the id of which box to remove
     */
    public void removeBox(int boxID) {

        this.inventory.removeIf(box -> box.getBoxID() == boxID);

        for (Truck truck : trucks) {
            truck.removeBox(boxID);
        }
    }

    /**
     * addTruck
     * adds the truck to warehouse list
     * @param truck - truck to add
     */
    public void addTruck(Truck truck) {
        if (this.trucks.size() + 1 > this.MAX_TRUCKS) {
            throw new RuntimeException("Too many trucks in warehouse");
        } else {
            this.trucks.add(truck);
        }
    }

    /**
     * removeTruck
     * removes the truck from list
     * @param truckID - id of truck to remove
     */
    public void removeTruck(int truckID) {

        for (Truck truck : this.trucks) {
            if (truck.getTruckID() == truckID) {
                this.inventory.addAll(truck.getBoxes());
            }
        }

        this.trucks.removeIf(truck -> truck.getTruckID() == truckID);
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
     * will make a string out of warehouse and values - used in uplaoding info to files
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
            string.append(box.toStringForWarehouse());
        }

        for (Truck truck : this.trucks) { // adds all string representation of trucks and their boxes to string
            string.append("\n");
            string.append(truck.toString());
        }


        return string.toString();
    }



    /**
     * setTrucks
     * sets the list of trucks in warehouse to gievn arrayalist
     * @param trucks - arraylist of trucks to become the list for warehosue
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


    public Truck getTruck(int i) {

        return (this.trucks.get(i));

    }

    public int getWarehouseID() {
        return WarehouseID;
    }

    public void setWarehouseID(int warehouseID) {
        WarehouseID = warehouseID;
    }

    public int getMAX_BOXES() {
        return MAX_BOXES;
    }

    public void setMAX_BOXES(int MAX_BOXES) {
        this.MAX_BOXES = MAX_BOXES;
    }

    public int getMAX_TRUCKS() {
        return MAX_TRUCKS;
    }

    public void setMAX_TRUCKS(int MAX_TRUCKS) {
        this.MAX_TRUCKS = MAX_TRUCKS;
    }
}