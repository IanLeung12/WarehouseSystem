import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class ReceivingSystem {


  public void createBox(ArrayList<Warehouse> w) throws IOException {
    Scanner keyboard = new Scanner(System.in);
    Box nb = new Box();
    int warehouseID;
    int wSpot = -1;
    Warehouse nw = new Warehouse();
    boolean found = false;
    System.out.println("Please enter the ID of the warehouse this box is being put into");
    warehouseID = keyboard.nextInt();
    for (int i = 0; i < w.size(); i++) {
      if (warehouseID == w.get(i).warehouseID) {
        nw = w.get(i);
        wSpot = i;
        found = true;
        break;
      }
    }
    if (!found) {
      System.out.println("ID does not exist.");
      return;
    }
    System.out.println("Please enter the weight of the box.");
    nb.setWeight(keyboard.nextInt());
    System.out.println("Please enter the height of the box.");
    nb.setHeight(keyboard.nextInt());
    System.out.println("Please enter the length of the box.");
    nb.setLength(keyboard.nextInt());
    System.out.println("Please enter the width of the box.");
    nb.setWidth(keyboard.nextInt());
    nw.addBox(nb);
    w.set(wSpot, nw);
    return;
  }
  //----
  public void uploadInventory(File file, int ftype, ArrayList<Warehouse> w, ArrayList<Truck> t) throws IOException {
    Scanner readF = new Scanner(file);
    Box nb = new Box();
    Truck nt = new Truck();
    Warehouse nw = new Warehouse();
    if (ftype == 1) {
      while(readF.hasNext()) {
        nb.setBoxID(readF.nextInt());
        nb.setWeight(readF.nextInt());
        nb.setHeight(readF.nextInt());
        nb.setLength(readF.nextInt());
        nb.setWidth(readF.nextInt());
        nb.setPosXinTruck(readF.nextInt());
        nb.setPositionYinTruck(readF.nextInt());
        nb.setinTruck(readF.nextInt());
        nb.setinWarehouse(readF.nextInt());
        if (nb.getinTruck() != -1) {
          for (int i = 0; i < t.size(); i++) {
            if (t.get(i).getTruckID() == nb.getinTruck()) {
              t.get(i).addBox(nb);
              break;
            }
          }
        } else {
          for (int i = 0; i < w.size(); i++) {
            if (w.get(i).getWarehouseID() == nb.getinWarehouse()) {
              w.get(i).addBox(nb);
              break;
            }
          }
        }
      }
      nb = new Box();
    } else if (ftype == 2){
      while (readF.hasNext()) {
        nt.truckID = readF.nextInt();
        nt.maxWeight = readF.nextInt();
        nt.height = readF.nextInt();
        nt.length = readF.nextInt();
        nt.width = readF.nextInt();
        nt.inWarehouse = readF.nextInt();
        t.add(nt) ;
        if (nt.inWarehouse != -1) {
          for (int i = 0; i < w.size(); i++) {
            if (w.get(i).warehouseID == t.inWarehouse) {
              w.get(i).addTruck(nt);
              break;
            }
          }
        }
        nt = new Truck();
      }
    } else {
      while (readF.hasNext()) {
        nw.warehouseID = readF.nextInt();
        nw.maxBoxes = readF.nextInt();
        nw.maxTrucks = readF.nextInt();
        w.add(nw);
        nw = new Warehouse();
      }
    }
  }
  //----
  void start(){

  }
}
