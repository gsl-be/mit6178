package exercise;

import java.util.HashMap;
import java.util.Set;

public class GroceryStore {
    
    private HashMap<Food, Integer> inventory;
    
    public GroceryStore() {
        this.inventory = new HashMap<Food, Integer>();
    }
    
    /**
     * Adds the specified amount of foodItem to the inventory
     * If foodItem is already in inventory, increase its current amount by quantity
     * else add foodItem and quantity to inventory
     * 
     * @param foodItem the Food object to be added to inventory
     * @param quantity the number of foodItem to be added to inventory
     */
    public void addToStore(Food foodItem, int quantity) {
        // TODO implement this method
    	if (quantity < 1) {
    		return;
    	}
    	HashMap<Food, Integer> myInventory = getInventory();
    	Integer count = new Integer(quantity);
    	if(myInventory.containsKey(foodItem)) {
    		count += myInventory.get(foodItem);
    		myInventory.put(foodItem, count);
    	} else {
    		myInventory.put(foodItem, count);
    	}
    	this.inventory = myInventory;
    	
    }
    
    /**
     * Checks if inventory has the specified amount of foodItem
     * 
     * @param foodItem the Food object you want to buy
     * @param quantity the number of foodItem you want to buy
     * @return true if inventory contains at least quantity number of foodItem, false otherwise
     */
    public boolean canBuy(Food foodItem, int quantity) {
        // TODO implement this method
    	HashMap<Food, Integer> myInventory = getInventory();
    	if(myInventory.containsKey(foodItem) && myInventory.get(foodItem) <= quantity) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /**
     * Reduces inventory according to what's bought in groceryList and returns the total cost
     * First checks if every Food object in groceryList can be bought at the desired quantity
     * If not, return 0.0
     * If you can buy every item, reduce inventory by the amount specified in groceryList and return the total cost
     * 
     * @param groceryList a map of Food objects to quantity desired 
     */
    public double buy(HashMap<Food, Integer> groceryList) {
        // TODO implement this method
    	Set<Food> myFoodSet = groceryList.keySet();
    	double total = 0.0;
    	HashMap<Food, Integer> myInventory = getInventory();
    	if(myInventory.isEmpty()) {
    		return total;
    	}
    	for(Food item : myFoodSet) {
			int count = groceryList.get(item);
			if(groceryList.get(item) > myInventory.get(item)) {
				count = myInventory.get(item);
			}
			total += item.price * count;
    	}
    	return total;
    }
    
    public HashMap<Food, Integer> getInventory() {
        return new HashMap<Food, Integer>(this.inventory);
    }
   
}
