/*Brian Pan 112856241 Recitation 02*/

/**
 * Object class representing Items in a grocery story with value such as its name, number in stock, item code,
 * and average sales per day
 */
public class Item{
    private int qtyInStore, averageSalesPerDay, onOrder, arrivalDay = 0;
    private String itemCode, name;
    private double price;

    /**
     * Getter method
     * @return
     * Returns the amount of items in stock
     */
    public int getQtyInStore() {
        return qtyInStore;
    }

    /**
     * Setter method
     * @param qtyInStore
     * The amount of items in stock
     */
    public void setQtyInStore(int qtyInStore) {
        this.qtyInStore = qtyInStore;
    }

    /**
     * Getter method
     * @return
     * Average amount of sales per day
     */
    public int getAverageSalesPerDay() {
        return averageSalesPerDay;
    }

    /**
     * Setter method
     * @param averageSakesOerDay
     * Average amount of sales per day
     */
    public void setAverageSalesPerDay(int averageSakesOerDay) {
        this.averageSalesPerDay = averageSakesOerDay;
    }

    /**
     * Getter method
     * @return
     * Returns the amount of items on order
     */
    public int getOnOrder() {
        return onOrder;
    }

    /**
     * Setter method
     * @param onOrder
     * The amount of items on order
     */
    public void setOnOrder(int onOrder) {
        this.onOrder = onOrder;
    }

    /**
     * Getter method
     * @return
     * Returns the business days orders arrives
     */
    public int getArrivalDay() {
        return arrivalDay;
    }

    /**
     * Setter method
     * @param arrivalDay
     * The business day the orders arrive
     */
    public void setArrivalDay(int arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

    /**
     * Getter method
     * @return
     * Returns the item's code
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Setter method
     * @param itemCode
     * The item's code
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * Getter method
     * @return
     * Returns the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method
     * @param name
     * The name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method
     * @return
     * Returns the price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter method
     * @param price
     * The price of the item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * No args Constructor
     */
    public Item(){}

    /**
     * Args constructor
     * @param itemCode
     * Code of item
     * @param name
     * Name of item
     * @param qtyInStore
     * Amount in stock
     * @param averageSalesPerDay
     * Average amount of sales per day
     * @param price
     * Price of item
     */
    public Item(String itemCode, String name, int qtyInStore, int averageSalesPerDay, double price){
        this.itemCode = itemCode;
        this.name = name;
        this.qtyInStore =qtyInStore;
        this.averageSalesPerDay = averageSalesPerDay;
        this.price = price;
        this.onOrder = onOrder;
    }

    /**
     * Converts Item object into string notation
     * @return
     * String representation of item
     */
    public String toString(){
        return String.format("%-12s%-21s%-10d%-4d%8.2f%10d%15d", itemCode, name,
                qtyInStore, averageSalesPerDay, price, onOrder, arrivalDay);
    }
}
