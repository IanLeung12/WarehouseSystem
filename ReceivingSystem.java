import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReceivingSystem implements ActionListener {

    Warehouse warehouse;
    JFrame frame;
    JPanel currentPanel;

    JButton addBtn;
    JButton addTruck;
    JButton deleteBox;
    JButton deleteTruck;
    JButton quitBtn;
    JButton viewBoxes;
    JButton viewTrucks;
    
    JPanel addBoxPanel;
    JPanel addTruckPanel;
    JPanel deleteBoxPanel;
    JPanel deleteTruckPanel;
    JPanel showBoxPanel;
    JPanel showTruckPanel;
    
    JButton createBoxButton;
    JButton createTruckButton;

    JButton deleteBoxButton;
    JButton deleteTruckButton;

    File saveFile;

    ReceivingSystem() {
        this.warehouse = new Warehouse(0,100,20);
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
        deleteBoxPanel.setLayout(new BorderLayout());
        showBoxPanel = new JPanel();
        showBoxPanel.setLayout(new FlowLayout(FlowLayout, 5, 5));
        showTruckPanel = new JPanel();
        showTruckPanel.setLayout(new FlowLayout(FlowLayout, 5, 5));

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
        addBtn.setPreferredSize(new Dimension(250, 70));
        addBtn.addActionListener(e -> changePanel(1));
        addBtn.setBackground(Color.lightGray);
        addBtn.setBorder(BorderFactory.createEtchedBorder());
        sidebar.add(addBtn);


        addTruck = new JButton();
        addTruck.setText("Add Truck");
        addTruck.setFont(new Font("Raleway", Font.PLAIN, 20));
        addTruck.setFocusable(false);
        addTruck.setPreferredSize(new Dimension(250, 70));
        addTruck.addActionListener(e -> changePanel(2));
        addTruck.setBackground(Color.lightGray);
        addTruck.setBorder(BorderFactory.createEtchedBorder());
        sidebar.add(addTruck);


        deleteBox = new JButton();
        deleteBox.setText("Delete Box");
        deleteBox.setFont(new Font("Raleway", Font.PLAIN, 20));
        deleteBox.setFocusable(false);
        deleteBox.setPreferredSize(new Dimension(250, 70));
        deleteBox.addActionListener(e -> changePanel(3));
        deleteBox.setBackground(Color.lightGray);
        deleteBox.setBorder(BorderFactory.createEtchedBorder());
        sidebar.add(deleteBox);

        deleteTruck = new JButton();
        deleteTruck.setText("Delete Truck");
        deleteTruck.setFont(new Font("Raleway", Font.PLAIN, 20));
        deleteTruck.setFocusable(false);
        deleteTruck.setPreferredSize(new Dimension(250, 70));
        deleteTruck.addActionListener(e -> changePanel(4));
        deleteTruck.setBackground(Color.lightGray);
        deleteTruck.setBorder(BorderFactory.createEtchedBorder());
        sidebar.add(deleteTruck);

        quitBtn = new JButton();
        quitBtn.setText("Quit");
        quitBtn.setFont(new Font("Raleway", Font.PLAIN, 20));
        quitBtn.setFocusable(false);
        quitBtn.setPreferredSize(new Dimension(250, 70));
        quitBtn.addActionListener(e -> this.uploadInventory(saveFile));
        quitBtn.addActionListener(e -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));
        quitBtn.setBackground(Color.lightGray);
        quitBtn.setBorder(BorderFactory.createEtchedBorder());
        sidebar.add(quitBtn);
        //----------------
        viewBoxes = new JButton();
        viewBoxes.setText("View Boxes");
        viewBoxes.setFont(new Font("Raleway", Font.PLAIN, 20));
        viewBoxes.setFocusable(false);
        viewBoxes.setPreferredSize(new Dimension(250, 70));
        viewBoxes.addActionListener(e -> this.setBoxView());
        //----------------
        viewTrucks = new JButton();
        viewTrucks.setText("View Trucks");
        viewTrucks.setFont(new Font("Raleway", Font.PLAIN, 20));
        viewTrucks.setFocusable(false);
        viewTrucks.setPreferredSize(new Dimension(250, 70));
        viewTrucks.addActionListener(e -> this.setTruckView());
        //----------------
        JTextArea allb = new JTextArea();
        allb.setFont(new Font("Arial", Font.PLAIN, 20));
        showBoxPanel.add(allb);
        
        JTextArea.allt = new JTextArea();
        allt.setFont(new Font("Arial", Font.PLAIN, 20));
        showBoxPanel.add(allt);
        
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

//        JLabel boxColorText = new JLabel();
//        boxColorText.setText("Box Width: ");
//        boxColorText.setFont(new Font("Arial", Font.PLAIN, 30));
//        boxColorText.add(boxWidthText);
//
//        JTextField boxColor = new JTextField();
//        boxWidth.setPreferredSize(new Dimension(300, 50));
//        boxWidth.setFont(new Font("Consolas", Font.PLAIN,30));
//        addBoxPanel.add(boxWidth);


        createBoxButton = new JButton("Create Box");
        createBoxButton.setPreferredSize(new Dimension(150, 40));
        createBoxButton.setFocusable(false);
        addBoxPanel.add(createBoxButton);

        createBoxButton.addActionListener(e -> createBox(parseInt(boxId.getText()), parseInt(boxWeight.getText()), parseInt(boxHeight.getText()), parseInt(boxLength.getText()), parseInt(boxWidth.getText())));

        /**
         * aaaaaaaaaaaaaaaaaaaaaaaaaaaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
         */
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

        createTruckButton.addActionListener(e -> createTruck(parseInt(truckId.getText()), parseInt(truckMaxWeight.getText()), parseInt(truckHeight.getText()), parseInt(truckLength.getText()), parseInt(truckWidth.getText())));


        /**
         * aaaaaaaaaaaaaaaaaaaaaaaaaaaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
         */

        JPanel boxDeletionPanel = new JPanel();
        boxDeletionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        boxDeletionPanel.setPreferredSize(new Dimension(new Dimension(420, 400)));
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

        deleteBoxButton.addActionListener(e -> removeBox(parseInt(deletedBoxText.getText())));

        JPanel boxesPanel = new JPanel();
        boxesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        for(Box b : this.warehouse.getInventory()){
            boxesPanel.add(new JLabel(b.toString()), FlowLayout.LEADING);
        }
        deleteBoxPanel.add(boxesPanel);


        JPanel truckDeletionPanel = new JPanel();
        truckDeletionPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
        truckDeletionPanel.setPreferredSize(new Dimension(430, 400));
        deleteTruckPanel.add(truckDeletionPanel, BorderLayout.NORTH);

        JLabel deletedTruckId = new JLabel();
        deletedTruckId.setText("Id of truck to be deleted:");
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

        deleteTruckButton.addActionListener(e -> removeTruck(parseInt(deletedTruckText.getText())));


        JPanel trucksPanel = new JPanel();
        trucksPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,15));
        for(Truck t:this.warehouse.getTrucks()){
            trucksPanel.add(new JLabel(t.toString()), FlowLayout.LEADING);
        }
        deleteTruckPanel.add(trucksPanel);

//        addTruckPanel.setBackground(new Color(255, 255, 0));
//        deleteTruckPanel.setBackground(new Color(0, 0, 255));
//        addBoxPanel.setBackground(new Color(0, 255, 255));
        //deleteBoxPanel.setBackground(new Color(255, 0, 255));
//        currentPanel.setBackground(new Color(0,255,0));
        //titlePanel.setBackground(new Color(255,0,0));

    }

    /**
     * start
     * will make the j frame and panels visible to user
     */
    void start() {

        String fileName = JOptionPane.showInputDialog("Please print out the exact name of the info file(with file extensions)");
        this.saveFile = new File(fileName);
        this.downloadInventory(this.saveFile);
        this.frame.setVisible(true);
    }

//    void quit(){
//        this.saveFile
//    }

    /**
     * createBox
     * creates a box and adds to warehouse
     * @param boxID - the new id
     * @param weight - new weigh t
     * @param height- new height
     * @param length- new length
     * @param width - new width
     */
    public void setBoxView() {
      
    }
    public void setTruckView() {
    
    }
    public void createBox(int boxID, int weight, int height, int length, int width) {
        if(weight == 0 || height == 0 || length == 0 || width == 0){
            System.out.println("something is wrong with your parameters. PLease enter integers.");
        }
        else {
            this.warehouse.addBox(new Box(boxID, weight, height, length, width));
            System.out.println(this.warehouse.toString());
        }
    }

    /**
     * removeBox
     * removes a box from the inventory of the warehouse
     * @param boxID - the id of box to be removed
     */
    public void removeBox(int boxID) {
        this.warehouse.removeBox(boxID);
    }

    /**
     * createTruck
     * creates a new truck and adds it to warehouse list of trucks
     * @param truckID - id of truck
     * @param maxWeight - max weight of truck
     * @param height - ehight of truck
     * @param length - length of truck
     * @param width - width of truck
     */
    public void createTruck(int truckID, int maxWeight, int height, int length, int width) {
        this.warehouse.addTruck(new Truck(truckID, maxWeight, height, length, width));
        System.out.println(this.warehouse.toString());
    }

    /**
     * removeTruck
     * removes truck from list of trucks in the warehouse
     * @param truckID - id to remove
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
            ArrayList<Truck> trucks = new ArrayList<>(); // arraylist of trucks for easier manipulation
            Warehouse warehouse = null;

            while (scanner.hasNext()) {

                // take in line and tget data in different forms
                line = scanner.nextLine();
                infoStr = line.split(" ");
                info = toInt(infoStr);

                if (infoStr[0].equals("Warehouse")) { // creates a warehouse with data if line starts iwth warehosue
                    location = "inventory";
                    warehouse = new Warehouse(info.get(0), info.get(1), info.get(2));

                } else if (infoStr[0].equals("Truck")) {// creates a truck with data if line starts iwth truck
                    location = "truck";
                    trucks.add(new Truck(info.get(0),info.get(1),info.get(2),info.get(3),info.get(4))); // adds to arraylsit

                } else if (infoStr[0].equals("Box")) { // creates box with info
                    if (location.equals("inventory")) { // adds to warehouse inventory
                        warehouse.addBox(new Box((info.get(0)), info.get(1), info.get(2), info.get(3),info.get(4), info.get(5),  info.get(6), info.get(7)));

                    } else { // adds box to truck in final index of trucks arraylist (always most recently made truck)
                        trucks.get(trucks.size() - 1).addBox(new Box((info.get(0)), info.get(1), info.get(2), info.get(3),info.get(4), info.get(5),  info.get(6), info.get(7)));
                    }

                }


            }

            this.setWarehouse(warehouse);
            this.warehouse.setTrucks(trucks);
            // still doesnt get value into main and cant add params or return bc of uml

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getName());
        }


    }

    /**
     * Arraylist
     * @param array - changing string arrays into int arrays for ease of use - helper method
     * @return - Intager array equivilent of array
     */
    private ArrayList<Integer> toInt(String[] array) {
        ArrayList<Integer> ints= new ArrayList<>();

        for (int index = 1; index < array.length; index++) {// starts at one to not copy the object type into a int
            ints.add(Integer.parseInt(array[index]));
        }

        return ints;
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
     * setWarehouse
     * sets warehouse object
     * @param warehouse
     */
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
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
                return 0;
            }

        }
        return returnNum;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

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
}
