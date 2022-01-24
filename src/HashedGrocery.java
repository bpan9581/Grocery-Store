/*Brian Pan 112856241 Recitation 02*/

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileInputStream;
import java.util.Set;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

/**
 * Creates a hashtable for Grocery Items
 */
public class HashedGrocery{
    private int businessDay = 1;
    private Hashtable<String, Item> hashTable = new Hashtable<>();

    /**
     * Getter method
     * @return
     * Returns the business day of the store
     */
    public int getBusinessDay() {
        return businessDay;
    }

    /**
     * Setter method
     * @param businessDay
     * Sets the business day of the store
     */
    public void setBusinessDay(int businessDay) {
        this.businessDay = businessDay;
    }

    /**
     * Getter method
     * @return
     * Returns the hashtable of grocery items
     */
    public Hashtable<String, Item> getHashTable() {
        return hashTable;
    }

    /**
     * Setter method
     * @param hashTable
     * The hashtable of grocery items
     */
    public void setHashTable(Hashtable<String, Item> hashTable) {
        this.hashTable = hashTable;
    }

    /**
     * Adds items into the hashtable
     * @param item
     * Item in the grocery store
     */
    public void addItem(Item item){
        if(hashTable.containsKey(item.getItemCode())){
            throw new ItemCodeAlreadyExistException();
        }
        else {
            hashTable.put(item.getItemCode(), item);
            System.out.println(item.getItemCode() + ": " + item.getName() + " is added to the inventory.");
        }
    }

    /**
     * Changes the amount of items in stock
     * @param item
     * Items in the grocery store
     * @param adjustByQty
     * Amount quantity in stock is changed by
     */
    public void updateItem(Item item, int adjustByQty){
        if(!hashTable.containsKey(item.getItemCode())){
            throw new ItemDoesNotExistException();
        }
        else if(item.getQtyInStore() < -adjustByQty) {
            System.out.println(item.getItemCode() + " : Not enough in stock for sale. Not updated.");
        }
        else {
            item.setQtyInStore(item.getQtyInStore() + adjustByQty);
            hashTable.replace(item.getItemCode(), item);
            if(item.getAverageSalesPerDay() * 3 > item.getQtyInStore() && item.getOnOrder() == 0){
                item.setOnOrder(item.getAverageSalesPerDay() * 2);
                item.setArrivalDay(businessDay + 3);
            }
            if(adjustByQty < 0)
                System.out.println(item.getItemCode() + ": "+ -adjustByQty +
                        " units of " + item.getName() + " are sold.");
            else if(adjustByQty > 0) {
                System.out.println(adjustByQty + " units of " + item.getName());
                item.setOnOrder(0);
                item.setArrivalDay(0);
            }
        }
    }

    /**
     * Adds items from a catalog into the hashtable
     * @param filename
     * Name of the catalog being searched through
     * @throws IOException
     * @throws ParseException
     */
    public void addItemCatalog(String filename) throws IOException, ParseException {
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            JSONParser parser = new JSONParser();
            JSONArray objs = (JSONArray) parser.parse(isr); // objs is a JSONArray which contains all JSONObjects found in the InputStream
            Item newItem = new Item();
        for(int i = 0; i < objs.size(); i++) {
            try {
                JSONObject obj = (JSONObject) objs.get(i);      // obj is the first JSONObject in the objs JSONArray
                String itemName = (String) obj.get("itemName");
                String itemCode = (String) obj.get("itemCode");
                int avgSales =  Integer.parseInt((String) obj.get("avgSales"));
                int qtyInStore = Integer.parseInt((String) obj.get("qtyInStore"));
                double price = Double.parseDouble((String) obj.get("price"));
                newItem = new Item(itemCode, itemName, qtyInStore, avgSales, price);
                addItem(newItem);
            }catch (ItemCodeAlreadyExistException ex) {
                System.out.println(newItem.getItemCode() + ": Cannot add item as item code already exist.");
            }
        }
    }

    /**
     * Sales from a list of sales
     * @param filename
     * Name of the file being searched through
     * @throws IOException
     * @throws ParseException
     */
    public void processSales(String filename) throws IOException, ParseException {
        FileInputStream fis = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(fis);
        JSONParser parser = new JSONParser();
        JSONArray objs = (JSONArray) parser.parse(isr); // objs is a JSONArray which contains all JSONObjects found in the InputStream
        String itemCode = "";
        for(int i = 0; i < objs.size(); i++) {
            try {
                JSONObject obj = (JSONObject) objs.get(i);      // obj is the first JSONObject in the objs JSONArray
                itemCode = (String) obj.get("itemCode");
                int qtySold = -Integer.parseInt((String) obj.get("qtySold"));
                if(!hashTable.containsKey(itemCode)) {
                    throw new ItemDoesNotExistException();
                }
                updateItem(hashTable.get(itemCode), qtySold);
            }catch (ItemDoesNotExistException ex){
                System.out.println(itemCode + ": Cannot buy as it is not in the grocery store");
            }
        }
    }

    /**
     * Increases business day
     */
    public void nextBusinessDay(){
        businessDay++;
        int orders = 0;
        System.out.println("Advancing business day...");
        System.out.println();
        Set<String> keys = hashTable.keySet();
        for(String key: keys){
            if(hashTable.get(key).getArrivalDay() == businessDay) {
                int onOrder = hashTable.get(key).getOnOrder();
                if(onOrder > 0 && hashTable.get(key).getArrivalDay() == businessDay && orders == 0){
                    orders++;
                    System.out.println("Orders have arrived for:");
                }
                updateItem(hashTable.get(key), onOrder);
            }
        }
        if(orders == 0){
            System.out.println("No orders have arrived");
        }
    }

    /**
     * Converts the hashtable items into a string
     * @return
     * Returns string form of hashtable
     */
    public String toString(){
        String tableToString = "";
        Set<String> keys = hashTable.keySet();
        for(String key: keys){
            tableToString += hashTable.get(key).toString() + "\n";
        }
        return tableToString;
    }
}
