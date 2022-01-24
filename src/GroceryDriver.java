/*Brian Pan 112856241 Recitation 02*/

import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.*;

/**
 * Stimulates a grocery stores item management
 */
public class GroceryDriver {
    public static void main(String [] args){
        HashedGrocery groceryDriver = new HashedGrocery();
        System.out.println("Business Day " + groceryDriver.getBusinessDay() + "\n");
        System.out.println("Menu :\n" +
                " \n" +
                "(L) Load item catalog    \n" +
                "(A) Add items              \n" +
                "(B) Process Sales      \n" +
                "(C) Display all items\n" +
                "(N) Move to next business day  \n" +
                "(Q) Quit  \n\n");
        boolean quit = false;
        Scanner stdin = new Scanner(System.in);
        while (!quit){
            System.out.println("Enter option:");
            String option = stdin.nextLine().toUpperCase();
            switch (option){
                case "L":
                    try {
                        System.out.println("Enter file to load:");
                        String file = stdin.nextLine();
                        groceryDriver.addItemCatalog(file);
                    }catch (ParseException ex){
                        System.out.println("The file is in the wrong format");
                    }catch (IOException ex){
                        System.out.println("The file entered was not found");
                    }
                    break;
                case "A":
                    Item newItem = new Item();
                    try {
                        System.out.println("Enter item code:");
                        String itemCode = stdin.nextLine();
                        System.out.println("Enter item name:");
                        String name = stdin.nextLine();
                        System.out.println("Enter Quantity in store:");
                        int qtyInStore = stdin.nextInt();
                        stdin.nextLine();
                        System.out.println("Enter Average sales per day:");
                        int averageSales = stdin.nextInt();
                        stdin.nextLine();
                        System.out.println("Enter price:");
                        double price = stdin.nextDouble();
                        stdin.nextLine();
                        newItem = new Item(itemCode,name,qtyInStore,averageSales,price);
                        groceryDriver.addItem(newItem);
                    }catch (ItemCodeAlreadyExistException ex){
                        System.out.println(newItem.getItemCode() + ": Cannot add item as item code already exist.");
                    }
                    break;
                case "B":
                    try {
                        System.out.println("Enter file to load:");
                        String file = stdin.nextLine();
                        groceryDriver.processSales(file);
                    }catch (ParseException ex){
                        System.out.println("The file is in the wrong format");
                    }catch (IOException ex){
                        System.out.println("The file entered was not found");
                    }
                    break;
                case "C":
                    System.out.println("Item code   Name                Qty   AvgSales    Price   OnOrder" +
                            "    ArrOnBusDay\n" +
                            "--------------------------------------------------------------------------------");
                    System.out.println(groceryDriver);
                    break;
                case "N":
                    groceryDriver.nextBusinessDay();
                    break;
                case "Q":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid menu input");
                    break;
            }
        }
    }
}
