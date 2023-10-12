import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;
import java.util.Arrays;


/**
 * ReceivingSystem
 * @author Dilen De Silva, Michael Khart
 * ICS4UE
 * @version 1.0 - 2023/10/10
 * Receiving system - a system which is responsible for starting of the warehouse management program. By extention,
 * it uploads and downloads data stored in text files, is able to move around, create, and delete objects through the
 * implementation of JAVA SWING
 */
public class ReceivingSystem implements ActionListener {

    Warehouse warehouse;
    JFrame frame;
    JPanel currentPanel;

    JButton addBtn;
    JButton addTruck;
    JButton deleteBox;
    JButton deleteTruck;
    JButton quitBtn;
    JPanel addBoxPanel;
    JPanel addTruckPanel;
    JPanel deleteBoxPanel;
    JPanel deleteTruckPanel;

    JPanel boxes;
    ArrayList<JLabel> boxLabels;

    JPanel trucks;
    ArrayList<JLabel> truckLabels;


    JButton createBoxButton;
    JButton createTruckButton;

    JButton deleteBoxButton;
    JButton deleteTruckButton;

    File saveFile;

    /**
     * Constructor
     * initializes all the needed buttons, frames etc for the GUI
     */
    ReceivingSystem(Warehouse warehouse) {
        this.warehouse = warehouse;
        frame = new JFrame("Receiving System");
        frame.setSize(800, 680);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //set icon for app
        ImageIcon appIcon = new ImageIcon("warehouseicon.png");
        frame.setIconImage(appIcon.getImage());

        //creating title of page
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(800, 100));
        frame.add(titlePanel,BorderLayout.NORTH);

        JLabel title = new JLabel();
        title.setText("Warehouse Receiving System");
        title.setFont(new Font("Calibri", Font.PLAIN, 50));

        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        titlePanel.add(title);

        title.setVisible(true);

        addBoxPanel = new JPanel();
        addBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        addTruckPanel = new JPanel();
        addTruckPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        deleteBoxPanel = new JPanel();
        deleteBoxPanel.setLayout(new BorderLayout());
        deleteTruckPanel = new JPanel();
        deleteTruckPanel.setLayout(new BorderLayout());


        currentPanel = new JPanel();
        frame.add(currentPanel, BorderLayout.CENTER);

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(250, 500));
        frame.add(sidebar,BorderLayout.WEST);
        sidebar.setLayout(new FlowLayout());


        addBtn = new JButton();
        addBtn.setText("Add Box");
        addBtn.setFont(new Font("Raleway", Font.PLAIN, 20));
        addBtn.setFocusable(false);
        addBtn.setPreferredSize(new Dimension(250, 100));
        addBtn.addActionListener(e -> changePanel(1));
        addBtn.setBackground(Color.lightGray);
        addBtn.setBorder(BorderFactory.createEtchedBorder());
        sidebar.add(addBtn);


        addTruck = new JButton();
        addTruck.setText("Add Truck");
        addTruck.setFont(new Font("Raleway", Font.PLAIN, 20));
        addTruck.setFocusable(false);
        addTruck.setPreferredSize(new Dimension(250, 100));
        addTruck.addActionListener(e -> changePanel(2));
        addTruck.setBackground(Color.lightGray);
        addTruck.setBorder(BorderFactory.createEtchedBorder());
        sidebar.add(addTruck);


        deleteBox = new JButton();
        deleteBox.setText("Delete Box");
        deleteBox.setFont(new Font("Raleway", Font.PLAIN, 20));
        deleteBox.setFocusable(false);
        deleteBox.setPreferredSize(new Dimension(250, 100));
        deleteBox.addActionListener(e -> changePanel(3));
        deleteBox.addActionListener(e -> showBoxes());
        deleteBox.setBackground(Color.lightGray);
        deleteBox.setBorder(BorderFactory.createEtchedBorder());
        sidebar.add(deleteBox);

        deleteTruck = new JButton();
        deleteTruck.setText("Delete Truck");
        deleteTruck.setFont(new Font("Raleway", Font.PLAIN, 20));
        deleteTruck.setFocusable(false);
        deleteTruck.setPreferredSize(new Dimension(250, 100));
        deleteTruck.addActionListener(e -> changePanel(4));
        deleteTruck.addActionListener(e -> showTrucks());
        deleteTruck.setBackground(Color.lightGray);
        deleteTruck.setBorder(BorderFactory.createEtchedBorder());
        sidebar.add(deleteTruck);

        quitBtn = new JButton();
        quitBtn.setText("Save & Quit");
        quitBtn.setFont(new Font("Raleway", Font.PLAIN, 20));
        quitBtn.setFocusable(false);
        quitBtn.setPreferredSize(new Dimension(250, 100));
        quitBtn.addActionListener(e -> quit());
        quitBtn.setBackground(Color.lightGray);
        quitBtn.setBorder(BorderFactory.createEtchedBorder());
        sidebar.add(quitBtn);


        JLabel boxIdText = new JLabel();
        boxIdText.setText("     Box ID: ");
        boxIdText.setFont(new Font("Arial", Font.PLAIN, 30));
        addBoxPanel.add(boxIdText);

        JTextField boxId = new JTextField();
        boxId.setPreferredSize(new Dimension(300, 50));
        boxId.setFont(new Font("Consolas", Font.PLAIN,30));
        addBoxPanel.add(boxId);

        JLabel boxWeightText = new JLabel();
        boxWeightText.setText("Box Weight:");
        boxWeightText.setFont(new Font("Arial", Font.PLAIN, 30));
        addBoxPanel.add(boxWeightText);

        JTextField boxWeight = new JTextField();
        boxWeight.setPreferredSize(new Dimension(300, 50));
        boxWeight.setFont(new Font("Consolas", Font.PLAIN,30));
        addBoxPanel.add(boxWeight);

        JLabel boxHeightText = new JLabel();
        boxHeightText.setText("Box Height:");
        boxHeightText.setFont(new Font("Arial", Font.PLAIN, 30));
        addBoxPanel.add(boxHeightText);

        JTextField boxHeight = new JTextField();
        boxHeight.setPreferredSize(new Dimension(300, 50));
        boxHeight.setFont(new Font("Consolas", Font.PLAIN,30));
        addBoxPanel.add(boxHeight);

        JLabel boxLengthText = new JLabel();
        boxLengthText.setText("Box Length:");
        boxLengthText.setFont(new Font("Arial", Font.PLAIN, 30));
        addBoxPanel.add(boxLengthText);

        JTextField boxLength = new JTextField();
        boxLength.setPreferredSize(new Dimension(300, 50));
        boxLength.setFont(new Font("Consolas", Font.PLAIN,30));
        addBoxPanel.add(boxLength);

        JLabel boxWidthText = new JLabel();
        boxWidthText.setText("Box Width: ");
        boxWidthText.setFont(new Font("Arial", Font.PLAIN, 30));
        addBoxPanel.add(boxWidthText);

        JTextField boxWidth = new JTextField();
        boxWidth.setPreferredSize(new Dimension(300, 50));
        boxWidth.setFont(new Font("Consolas", Font.PLAIN,30));
        addBoxPanel.add(boxWidth);

        JLabel boxColorText = new JLabel();
        boxColorText.setText("Color(R,G,B):");
        boxColorText.setFont(new Font("Arial", Font.PLAIN, 30));
        addBoxPanel.add(boxColorText);

        JTextField boxColor = new JTextField();
        boxColor.setPreferredSize(new Dimension(300, 50));
        boxColor.setFont(new Font("Consolas", Font.PLAIN,30));
        addBoxPanel.add(boxColor);

        createBoxButton = new JButton("Create Box");
        createBoxButton.setPreferredSize(new Dimension(150, 40));
        createBoxButton.setFocusable(false);
        addBoxPanel.add(createBoxButton);

        createBoxButton.addActionListener(e -> {createBox(parseInt(boxId.getText()), parseInt(boxWeight.getText()), parseInt(boxHeight.getText()), parseInt(boxLength.getText()), parseInt(boxWidth.getText()),
        getColor(boxColor.getText())); boxId.setText(""); boxWeight.setText(""); boxHeight.setText(""); boxLength.setText(""); boxWidth.setText(""); boxColor.setText("");});


        JLabel truckIdText = new JLabel();
        truckIdText.setText("    Truck ID: ");
        truckIdText.setFont(new Font("Arial", Font.PLAIN, 30));
        addTruckPanel.add(truckIdText);

        JTextField truckId = new JTextField();
        truckId.setPreferredSize(new Dimension(300, 50));
        truckId.setFont(new Font("Consolas", Font.PLAIN,30));
        addTruckPanel.add(truckId);

        JLabel truckWeightText = new JLabel();
        truckWeightText.setText("Truck Weight:");
        truckWeightText.setFont(new Font("Arial", Font.PLAIN, 30));
        addTruckPanel.add(truckWeightText);

        JTextField truckMaxWeight = new JTextField();
        truckMaxWeight.setPreferredSize(new Dimension(300, 50));
        truckMaxWeight.setFont(new Font("Consolas", Font.PLAIN,30));
        addTruckPanel.add(truckMaxWeight);

        JLabel truckHeightText = new JLabel();
        truckHeightText.setText("Truck Height:");
        truckHeightText.setFont(new Font("Arial", Font.PLAIN, 30));
        addTruckPanel.add(truckHeightText);

        JTextField truckHeight = new JTextField();
        truckHeight.setPreferredSize(new Dimension(300, 50));
        truckHeight.setFont(new Font("Consolas", Font.PLAIN,30));
        addTruckPanel.add(truckHeight);

        JLabel truckLengthText = new JLabel();
        truckLengthText.setText("Truck Length:");
        truckLengthText.setFont(new Font("Arial", Font.PLAIN, 30));
        addTruckPanel.add(truckLengthText);

        JTextField truckLength = new JTextField();
        truckLength.setPreferredSize(new Dimension(300, 50));
        truckLength.setFont(new Font("Consolas", Font.PLAIN,30));
        addTruckPanel.add(truckLength);

        JLabel truckWidthText = new JLabel();
        truckWidthText.setText("Truck Width: ");
        truckWidthText.setFont(new Font("Arial", Font.PLAIN, 30));
        addTruckPanel.add(truckWidthText);

        JTextField truckWidth = new JTextField();
        truckWidth.setPreferredSize(new Dimension(300, 50));
        truckWidth.setFont(new Font("Consolas", Font.PLAIN,30));
        addTruckPanel.add(truckWidth);

        createTruckButton = new JButton("Create Truck");
        createTruckButton.setPreferredSize(new Dimension(150, 40));
        createTruckButton.setFocusable(false);
        addTruckPanel.add(createTruckButton);

        createTruckButton.addActionListener(e -> {createTruck(parseInt(truckId.getText()), parseInt(truckMaxWeight.getText()), parseInt(truckHeight.getText()), parseInt(truckLength.getText()), parseInt(truckWidth.getText()));
        truckId.setText(""); truckMaxWeight.setText(""); truckHeight.setText(""); truckLength.setText(""); truckWidth.setText("");});


        JPanel boxDeletionPanel = new JPanel();
        boxDeletionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        boxDeletionPanel.setPreferredSize(new Dimension(new Dimension(420, 150)));
        deleteBoxPanel.add(boxDeletionPanel, BorderLayout.NORTH);

        JLabel deletedBoxId = new JLabel();
        deletedBoxId.setText("ID of box to be deleted:");
        deletedBoxId.setFont(new Font("Arial", Font.PLAIN, 25));
        boxDeletionPanel.add(deletedBoxId);

        JTextField deletedBoxText = new JTextField();
        deletedBoxText.setPreferredSize(new Dimension(130, 40));
        deletedBoxText.setFont(new Font("Consolas", Font.PLAIN,30));
        boxDeletionPanel.add(deletedBoxText);

        deleteBoxButton = new JButton("Submit");
        deleteBoxButton.setPreferredSize(new Dimension(100,40));
        deleteBoxButton.setFocusable(false);
        boxDeletionPanel.add(deleteBoxButton);

        deleteBoxButton.addActionListener(e -> {removeBox(parseInt(deletedBoxText.getText()));deletedBoxText.setText("");});

        this.boxes = new JPanel();
        boxes.setLayout(new FlowLayout(FlowLayout.CENTER, 200,8));
        JScrollPane scrollableBoxes = new JScrollPane(this.boxes);
        deleteBoxPanel.add(scrollableBoxes, BorderLayout.CENTER);


        JPanel truckDeletionPanel = new JPanel();
        truckDeletionPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
        truckDeletionPanel.setPreferredSize(new Dimension(420, 150));
        deleteTruckPanel.add(truckDeletionPanel, BorderLayout.NORTH);

        JLabel deletedTruckId = new JLabel();
        deletedTruckId.setText(" ID of truck to be deleted: ");
        deletedTruckId.setFont(new Font("Arial", Font.PLAIN, 25));
        truckDeletionPanel.add(deletedTruckId);

        JTextField deletedTruckText = new JTextField();
        deletedTruckText.setPreferredSize(new Dimension(120, 40));
        deletedTruckText.setFont(new Font("Consolas", Font.PLAIN,30));
        truckDeletionPanel.add(deletedTruckText);

        deleteTruckButton = new JButton("Submit");
        deleteTruckButton.setPreferredSize(new Dimension(100, 40));
        deleteTruckButton.setFocusable(false);
        truckDeletionPanel.add(deleteTruckButton);

        deleteTruckButton.addActionListener(e -> {removeTruck(parseInt(deletedTruckText.getText())); deletedTruckText.setText("");});

        this.trucks = new JPanel();
        trucks.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 8));
        JScrollPane scrollableTrucks = new JScrollPane(this.trucks);
        deleteTruckPanel.add(scrollableTrucks, BorderLayout.CENTER);
    }

    /**
     * start
     * will make the j frame and panels visible to user
     */
    public void start() {
        String fileName = JOptionPane.showInputDialog("Please print out the exact name of the info file (without file extensions)");
        this.saveFile = new File(fileName + ".txt");
        this.downloadInventory(this.saveFile);
        this.frame.setVisible(true);
    }

    /**
     * quit
     * will close the window and while saving info to file
     */
    void quit(){
        this.uploadInventory(this.saveFile);
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * createBox
     * creates a box and adds to warehouse
     * @param boxID - the new id
     * @param weight - new weigh t
     * @param height- new height
     * @param length- new length
     * @param width - new width
     * @param color - mew color
     */
    public void createBox(int boxID, int weight, int height, int length, int width, Color color) {
        if ((weight == -1)|| (height == -1) || (length == -1) || (width == -1) || boxID == -1) {
            throw new IllegalArgumentException("something is wrong with your parameters. PLease enter integers.");
        } else if ((weight == 0)|| (height == 0) || (length == 0) || (width == 0)) {
            throw new IllegalArgumentException("0 cannot be the property of a box.");
        } else if(isEqualToOtherBoxID(boxID)){
            throw new IllegalArgumentException("This ID is already being used.");
        } else {
            this.warehouse.addBox(new Box(boxID, weight, height, length, width, color));
        }
    }

    /**
     * removeBox
     * removes a box from the inventory of the warehouse
     * @param boxID - the id of box to be removed
     */
    public void removeBox(int boxID) {
        this.warehouse.removeBox(boxID);
        showBoxes();
    }

    /**
     * createTruck
     * creates a new truck and adds it to warehouse list of trucks
     * @param truckID - id of truck
     * @param maxWeight - max weight of truck
     * @param height - height of truck
     * @param length - length of truck
     * @param width - width of truck
     */
    public void createTruck(int truckID, int maxWeight, int height, int length, int width) {
        if ((maxWeight == -1) || (height == -1) || (length == -1) || (width == -1) || truckID == -1) {
            throw new IllegalArgumentException("Something is wrong with your parameters. Please enter integers.");

        } else if ((maxWeight == 0) || (height == 0) || (length == 0) || (width == 0)) {
            throw new IllegalArgumentException("0 cannot be the property of a box.");

        } else if(isEqualToOtherTruckID(truckID) ){
            throw new IllegalArgumentException("This ID is already being used.");

        } else {
            this.warehouse.addTruck(new Truck(truckID, maxWeight, height, length, width));
        }
    }

    /**
     * removeTruck
     * removes truck from list of trucks in the warehouse
     * @param truckID - id to identify truck
     */
    public void removeTruck(int truckID) {
        this.warehouse.removeTruck(truckID);
    }


    /**
     * uploadInventory
     * uploads all data stored in warehouse to a txt file using the toString methods in all objects
     * @param file - the file to upload info too
     */

    public void uploadInventory(File file) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print(this.warehouse.toString());
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getName());
        }
    }

    /**
     * downloadInventory
     * scans a given file and uses all info to initiate a warehouse with values
     * @param file - the file with info
     */

    public void downloadInventory(File file){
        try {
            Scanner scanner = new Scanner(file);
            String line;   // line taken by scanner
            String[] infoStr; // string array of info
            String location = "inventory"; // variable to tell if boxes should be stored in warehouse or in truck
            ArrayList<Integer> info = new ArrayList<>(); // arraylist of info for easier manipulation
            Color newColor = null;

            while (scanner.hasNext()) {

                // take in line and tget data in different forms
                line = scanner.nextLine();
                infoStr = line.split(" ");
                info = toInt(infoStr);

                if (infoStr[0].equals("Box")) { // onl;y box objects have an RGB code in their saved data
                    newColor = getColor(infoStr[infoStr.length - 1]);
                }

                if (infoStr[0].equals("Warehouse")) { // creates a warehouse with data if line starts iwth warehosue
                    location = "inventory";
                    this.warehouse.setWarehouseID(info.get(0));
                    this.warehouse.setMAX_BOXES(info.get(1));
                    this.warehouse.setMAX_TRUCKS(info.get(2));

                } else if (infoStr[0].equals("Truck")) {// creates a truck with data if line starts iwth truck
                    location = "truck";
                    this.warehouse.addTruck((new Truck(info.get(0),info.get(1),info.get(2),info.get(3),info.get(4)))); // adds to arraylsit

                } else if (infoStr[0].equals("Box")) { // creates box with info
                    if (location.equals("inventory")) { // adds to warehouse inventory
                        this.warehouse.addBox(new Box((info.get(0)), info.get(1), info.get(2), info.get(3),info.get(4), newColor));

                    } else { // adds box to truck in final index of trucks arraylist (always most recently made truck)
                        this.warehouse.getTruck(this.warehouse.getTrucks().size() - 1).addBox(new Box((info.get(0)), info.get(1), info.get(2), info.get(3),info.get(4), info.get(5),  info.get(6), info.get(7), newColor));
                    }

                }

                newColor = null;
            }


        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getName() + " Path: " + this.saveFile.getAbsolutePath());
        }


    }

    /**
     * toInt
     * @param array - changing string arrays into int arrays for ease of use - helper method
     * @return - integer array equivalent of array
     */
    private ArrayList<Integer> toInt(String[] array) {
        ArrayList<Integer> ints= new ArrayList<>();

        if (array[0].equals("Box")) {
            for (int index = 1; index < (array.length - 1); index++) {// starts at one to not copy the object type into a int or color
                ints.add(Integer.parseInt(array[index]));
            }
        } else {
            for (int index = 1; index < (array.length); index++) {// starts at one to not copy the object type into a int or color
                ints.add(Integer.parseInt(array[index]));
            }
        }
        return ints;
    }

    /**
     * showBoxes
     * Updates boxes to visualize everytime box deleter is opened
     */
    void showBoxes(){
        this.boxLabels = new ArrayList<JLabel>();
        this.boxes.removeAll();
        for(Box b: getWarehouse().getInventory()){

            String[] labelArray = b.toStringForWarehouse().split(" ");
            String[] newLabelArray = {labelArray[1],labelArray[2],labelArray[3],labelArray[4],labelArray[5],labelArray[6]};
            StringBuilder string = new StringBuilder();
            for (String value : newLabelArray){
                string.append(value + " ");
            }
            String labelText = string.toString();
            JLabel label = new JLabel(labelText);
            label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            boxLabels.add(label);
        }
        JLabel boxFormat = new JLabel("ID Width Height Length Weight R,G,B");
        boxes.add(boxFormat);
        for(JLabel j : boxLabels){
            boxes.add(j);
        }
        boxes.setPreferredSize(new Dimension(500,this.warehouse.getInventory().size()*25));
    }

    /**
     * showBoxes
     * Updates boxes to visualize everytime box deleter is opened
     */
    void showTrucks(){
        this.truckLabels = new ArrayList<JLabel>();
        this.trucks.removeAll();
        for(Truck t: getWarehouse().getTrucks()){
            int[] labelArray = t.asArray();
            String[] newLabelArray = {String.valueOf(labelArray[0]),String.valueOf(labelArray[1]),String.valueOf(labelArray[2]),String.valueOf(labelArray[3]),String.valueOf(labelArray[4])};
            StringBuilder string = new StringBuilder();
            for (String value : newLabelArray){
                string.append(value + " ");
            }
            String labelText = string.toString();
            JLabel label = new JLabel(labelText);
            label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            truckLabels.add(label);
        }
        JLabel truckFormat = new JLabel("ID MaxWeight Height Length Width");
        trucks.add(truckFormat);
        for(JLabel j : truckLabels){
            trucks.add(j);
        }
        trucks.setPreferredSize(new Dimension(500,this.warehouse.getInventory().size()*25));
    }

    /**
     * getWarehouse
     * gets warehouse object
     * @return - the object
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * parseInt
     * @param num - Num currently stored as a String
     * @return a real integer
     */
    static int parseInt(String num){
        int returnNum = 0;
        for (int i =0; i<num.length();i++) {
            if(((int)num.charAt(i)>=48)&&((int)num.charAt(i)<=57)) {
                returnNum = returnNum*10+ ((int)num.charAt(i)-48);
            } else {
                return -1;
            }
        }

        return returnNum;
    }
    @Override
    public void actionPerformed(ActionEvent e) {}

    /**
     * getColor
     * will turn an RGB code into a Color object
     * @param rgb - rgb code in string format
     * @return - Color obejct
     */
    public Color getColor(String rgb) {
        String[] arrayRGB = rgb.split(",");

        try {
            int red = Integer.parseInt(arrayRGB[0].trim());
            int green = Integer.parseInt(arrayRGB[1].trim());
            int blue = Integer.parseInt(arrayRGB[2].trim());

            return (new Color(red, green, blue));
        } catch (NumberFormatException e) {
            throw (new IllegalArgumentException("getColor method has received a not RGB value and cannot convert - savefile info has been corrupted"));
        }
    }
    /**
     * changePanel
     * changes that panel
     * @param choice - user choice
     */
    public void changePanel(int choice){
        if (choice > 4 || choice < 1){
            throw new IllegalArgumentException("Invalid choice: Argument must be between 1 and 4");
        }
        if(choice == 4){
            currentPanel.setVisible(false);
            addBoxPanel.setVisible(false);
            addTruckPanel.setVisible(false);
            deleteBoxPanel.setVisible(false);
            deleteTruckPanel.setVisible(true);
            frame.add(deleteTruckPanel);
        }
        if(choice == 3){
            currentPanel.setVisible(false);
            addBoxPanel.setVisible(false);
            addTruckPanel.setVisible(false);
            deleteBoxPanel.setVisible(true);
            deleteTruckPanel.setVisible(false);
            frame.add(deleteBoxPanel);
        }
        if(choice == 2){
            currentPanel.setVisible(false);
            addBoxPanel.setVisible(false);
            addTruckPanel.setVisible(true);
            deleteBoxPanel.setVisible(false);
            deleteTruckPanel.setVisible(false);
            frame.add(addTruckPanel);
        }
        if(choice == 1){
            currentPanel.setVisible(false);
            addBoxPanel.setVisible(true);
            addTruckPanel.setVisible(false);
            deleteBoxPanel.setVisible(false);
            deleteTruckPanel.setVisible(false);
            frame.add(addBoxPanel);        }
    }

    /**
     * Checks if an ID is already in use
     * @param truckID
     * @return boolean isEqual
     */
    boolean isEqualToOtherTruckID(int truckID){
        boolean isEqual = false;
        for (Truck t : warehouse.getTrucks()){
            if (truckID == t.getTruckID()){
                isEqual = true;
            }
        }
        return(isEqual);
    }

    /**
     * Checks if an ID is already in use
     * @param boxID
     * @return isEquals
     */
    boolean isEqualToOtherBoxID(int boxID){
        boolean isEqual = false;
        for (Box b : warehouse.getInventory()){
            if (boxID == b.getBoxID()){
                isEqual = true;
            }
        }
        for (Truck truck : warehouse.getTrucks()){
            for(Box b : truck.getBoxes()){
                if (boxID == b.getBoxID()){
                    isEqual = true;
                }
            }
        }
        return(isEqual);
    }
}