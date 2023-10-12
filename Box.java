import java.awt.*;

/**
 * Box
 * @author Dilen De Silva, Michael Khart
 * ICS4UE
 * @version 1.0 - 2023/10/09
 * Is the smallest unit, just has parameters to identify itself and see if it can fit in to a truck
 */
public class Box {
    private int boxID;
    private int weight;
    private int height;
    private int length;
    private int width;
    private int positionXInTruck = -1;
    private int positionYInTruck = -1;
    private int positionZInTruck = -1;
    private Color color;

    /**
     * Box
     * constructor
     * @param boxID - id
     * @param weight - weight
     * @param height - hight
     * @param length - length
     * @param width - width
     * @param color - the color
     */
    Box(int boxID, int weight, int height, int length, int width, Color color) {
        this.boxID = boxID;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.color = color;
    }

    /**
     * Box
     * Constructor for box
     * @param boxID - its id
     * @param weight - its weight
     * @param height - its height
     * @param length - its length
     * @param width - its width
     * @param color - the color
     * @param positionXInTruck - its position in truck
     * @param positionYInTruck - its position y in truck
     * @param positionZInTruck - its position z in truck
     */
    Box(int boxID, int weight, int height, int length, int width, int positionXInTruck, int positionYInTruck, int positionZInTruck, Color color) {
        this.boxID = boxID;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.color = color;
        this.positionXInTruck = positionXInTruck;
        this.positionYInTruck = positionYInTruck;
        this.positionZInTruck = positionZInTruck;
    }


    /**
     * rotate
     * will change the length and width of box
     */
    void rotate(){
        int tempLength;
        tempLength = this.length;
        this.length = this.width;
        this.width = tempLength;
    }

    // will be overridden
    public void draw(Graphics g) {}


    /**
     * asaRRAY
     * will return the value of this box as an int array
     * @return
     */
    public int[] asArray() {
        return new int[]{this.boxID, this.weight, this.height, this.length, this.width, this.positionXInTruck, this.positionYInTruck, this.positionZInTruck};
    }

    public int[] asArrayInWarehouse() {
        return new int[]{this.boxID, this.weight, this.height, this.length, this.width};
    }

    /**\
     * toString override
     * will point the object type and then all of its values - used in upl;adding info to files
     * @return - the string representation
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Box"); // object type
        int[] values = this.asArray(); // its values

        for (int value : values) {
            string.append(" ");
            string.append(value);
        }

        string.append(" ");
        string.append(getRGB());

        return string.toString();
    }

    /**\
     * toStringForWarehouse
     * will point the object type and then all of its values - used in upl;adding info to files - only for boxes in warehouse
     * @return - the string representation
     */
    public String toStringForWarehouse() {
        StringBuilder string = new StringBuilder("Box"); // object type
        int[] values = this.asArrayInWarehouse(); // its values

        for (int value : values) {
            string.append(" ");
            string.append(value);
        }

        string.append(" ");
        string.append(getRGB());

        return string.toString();
    }

    public String getRGB() {
        int red = this.color.getRed();
        int green = this.color.getGreen();
        int blue = this.color.getBlue();

        // Construct the RGB string
        String rgbString = red + "," + green + "," + blue;

        return rgbString;
    }



    /**
     * getBoxID
     * will get the boxes ID
     * @return - the boxes ID
     */
    public int getBoxID() {
        return boxID;
    }

    /**
     * getHeight
     * Gets the height of the object
     * @return The height of the object
     */
    public int getHeight() {
        return height;
    }

    /**
     * getLength
     * Gets the length of the object
     * @return The length of the object
     */
    public int getLength() {
        return length;
    }

    /**
     * getColor - returns color
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * setColor
     * @param color - new color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * getWidth
     * Gets the width of the object
     * @return The width of the object
     */
    public int getWidth() {
        return width;
    }

    /**
     * getPosXInTruck
     * Gets the X-coordinate position of the object within the truck
     * @return The X-coordinate position within the truck
     */
    public int getPosXInTruck() {
        return positionXInTruck;
    }

    /**
     * getPositionYInTruck
     * Gets the Y-coordinate position of the object within the truck
     * @return The Y-coordinate position within the truck
     */
    public int getPositionYInTruck() {
        return positionYInTruck;
    }

    /**
     * getPositionZInTruck
     * Gets the Z-coordinate position of the object within the truck
     * @return The Z-coordinate position within the truck
     */
    public int getPositionZInTruck() {
        return positionZInTruck;
    }

    /**
     * getWeight
     * Gets the weight of the object
     * @return The weight of the object
     */
    public int getWeight() {
        return weight;
    }


}