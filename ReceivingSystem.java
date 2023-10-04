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
    nb.weight = keyboard.nextInt();
    System.out.println("Please enter the height of the box.");
    nb.height = keyboard.nextInt();
    System.out.println("Please enter the length of the box.");
    nb.length = keyboard.nextInt();
    System.out.println("Please enter the width of the box.");
    nb.width = keyboard.nextInt();
    nw.addBox(nb);
    w.set(wSpot, nw);
    return;
  }
}