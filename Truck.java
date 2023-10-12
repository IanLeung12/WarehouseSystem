import java.awt.*;
import java.util.ArrayList;
public class Truck {

    private int truckID;
    private int maxWeight;
    private int height;
    private int length;
    private int width;
    ArrayList<Box> boxes = new ArrayList<>();


    /**
     * Truck Constructor
     * @param truckID -  its id
     * @param maxWeight - max weight
     * @param height -0 height
     * @param length - length
     * @param width - width
     */
    public Truck(int truckID, int maxWeight, int height, int length, int width) {
        this.truckID = truckID;
        this.maxWeight = maxWeight;
        this.height = height;
        this.length = length;
        this.width = width;
        // array of boxes always initialized - if we need to add a full new arraylist there is method

    }

    /**
     * addBox
     * adds a box object to the truck object
     * @param box - the box to add
     */
    public void addBox(Box box){
        boxes.add(box);
    }

    /**
     * removeBox
     * removes the box from the trucks box list
     * @param boxID - the id of the box we would like to remove
     */
    public void removeBox(int boxID){

        for (Box box : this.boxes) {
            if (box.getBoxID() == boxID) {
                this.boxes.remove(box);
            }
        }


    }

    /**
     * getLoadedWeight
     * give the total weight of all boxes inthe truck arraylsit
     * @return- the total weight
     */
    public int getLoadedWeight(){
        int weight = 0;
        for(Box box: boxes){
            weight += box.getWeight();
        }
        return(weight);
    }

    /**
     * isValid
     * check if a box is valid for the truck by checking dimentions
     * @param box - the box which is being checked
     * @param x - the x cordinate of boxx
     * @param y - cordintate
     * @return - if it is valid(bool)
     */
    public boolean isValid(Box box, int x, int y){        // idkf what this is supposed to do

        if ((box.getHeight() + box.getPositionZInTruck()) > this.height) {
            return false;
        } else if ((box.getLength() + box.getPosXInTruck()) > this.getLength()) {
            return false;
        } else if ((box.getWidth() + box.getPositionYInTruck()) > this.getWidth()) {
            return false;
        } else if (this.getLoadedWeight() + box.getWeight() > this.maxWeight) {
            return false;
        }

        return true;
    }


    // will be overridden by graphics group
    public void draw(Graphics g, int x, int y) {

    }


    /**
     * asArray
     * when called will turn the trucks values into an int array with all of its values in int type
     * @return - the array of ints
     */
    public int[] asArray() {
        return new int[]{this.truckID, this.maxWeight, this.height, this.length, this.width};
    }

    /**
     * toString
     * used to print the trucks values - used in the uploading values of all things to save to files
     * @return - string representation
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Truck"); // starts with object type
        int[] values = this.asArray(); // gets vales of itself

        for (int value : values) { // adds all values to the string
            string.append(" ");
            string.append(value);
        }

        for (Box box : this.boxes) { // adds all the box string representations to string (ON NEW LINES )
            string.append("\n");
            string.append(box.toString());
        }

        return string.toString();
    }

    public String justTruckToString(){
        StringBuilder string = new StringBuilder("Truck"); // starts with object type
        int[] values = this.asArray(); // gets vales of itself

        for (int value : values) { // adds all values to the string
            string.append(" ");
            string.append(value);
        }
        return string.toString();
    }


    /**
     * getBoxes
     * gets all the boxes from the truck list
     * @return - list boxes
     */
    public ArrayList<Box> getBoxes() {
        return boxes;
    }


    /**
     * Gets the unique identifier of the truck
     * @return The truck's unique identifier.
     */
    public int getTruckID() {
        return truckID;
    }

    /**
     * Gets the length of the truck
     * @return The length of the truck in meters
     */
    public int getLength() {
        return length;
    }

    /**
     * Gets the width of the truck
     * @return The width of the truck in meters
     */
    public int getWidth() {
        return width;
    }

}