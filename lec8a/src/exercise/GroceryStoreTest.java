package exercise;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class GroceryStoreTest {
    
    final Food COFFEE = new Food("coffee", 1.25);
    final Food APPLE = new Food("apple", 0.5);
    final Food BAGEL = new Food("bagel", 1.00);

    @Test
    public void emptyGroceryStoreTest() {
        GroceryStore store = new GroceryStore();
        
        HashMap<Food, Integer> startingInventory = store.getInventory();
        assertTrue(startingInventory.isEmpty());
    	assertFalse(store.canBuy(APPLE, 1));
    	assertFalse(store.canBuy(BAGEL, 1));
        assertFalse(store.canBuy(COFFEE, 2));
        
        HashMap<Food, Integer> groceryList = new HashMap<Food, Integer>();
        groceryList.put(APPLE, 1);
        groceryList.put(BAGEL, 1);
        assertEquals(0.0, store.buy(groceryList), 0.0001);
        assertTrue(store.getInventory().isEmpty());
    }
    
    @Test
    public void appleEmptyTest() {
    	GroceryStore appleStore = new GroceryStore();
    	appleStore.addToStore(APPLE, 0);
    	HashMap<Food, Integer> appleInventory = appleStore.getInventory();
        assertTrue(appleInventory.isEmpty());
    	assertFalse(appleStore.canBuy(APPLE, 1));
    	assertFalse(appleStore.canBuy(BAGEL, 1));
    	assertFalse(appleStore.canBuy(COFFEE, 1));
    	HashMap<Food, Integer> groceryList = new HashMap<Food, Integer>();
        groceryList.put(APPLE, 1);
        groceryList.put(COFFEE, 1);
        groceryList.put(BAGEL, 1);
        assertEquals(0.0, appleStore.buy(groceryList), 0.0001);
        assertTrue(appleStore.getInventory().isEmpty());
    }
    
    @Test
    public void coffeeEmptyTest() {
    	GroceryStore appleStore = new GroceryStore();
    	appleStore.addToStore(COFFEE, 0);
    	HashMap<Food, Integer> appleInventory = appleStore.getInventory();
        assertTrue(appleInventory.isEmpty());
    	assertFalse(appleStore.canBuy(APPLE, 1));
    	assertFalse(appleStore.canBuy(BAGEL, 1));
    	assertFalse(appleStore.canBuy(COFFEE, 1));
    	HashMap<Food, Integer> groceryList = new HashMap<Food, Integer>();
        groceryList.put(APPLE, 1);
        groceryList.put(COFFEE, 1);
        groceryList.put(BAGEL, 1);
        assertEquals(0.0, appleStore.buy(groceryList), 0.0001);
        assertTrue(appleStore.getInventory().isEmpty());
    }
    
    @Test
    public void bagelEmptyTest() {
    	GroceryStore appleStore = new GroceryStore();
    	appleStore.addToStore(BAGEL, 0);
    	HashMap<Food, Integer> appleInventory = appleStore.getInventory();
        assertTrue(appleInventory.isEmpty());
    	assertFalse(appleStore.canBuy(APPLE, 1));
    	assertFalse(appleStore.canBuy(BAGEL, 1));
    	assertFalse(appleStore.canBuy(COFFEE, 1));
    	HashMap<Food, Integer> groceryList = new HashMap<Food, Integer>();
        groceryList.put(APPLE, 1);
        groceryList.put(COFFEE, 1);
        groceryList.put(BAGEL, 1);
        assertEquals(0.0, appleStore.buy(groceryList), 0.0001);
        assertTrue(appleStore.getInventory().isEmpty());
    }
    
    @Test
    public void fullTest() {
    	GroceryStore appleStore = new GroceryStore();
    	appleStore.addToStore(APPLE, 30);
    	appleStore.addToStore(APPLE, 10);
    	appleStore.addToStore(BAGEL, 20);
    	appleStore.addToStore(COFFEE, 5);
    	HashMap<Food, Integer> appleInventory = appleStore.getInventory();
        assertTrue(appleStore.canBuy(APPLE, 40));
        assertTrue(appleStore.canBuy(BAGEL, 20));
        assertTrue(appleStore.canBuy(COFFEE, 5));
    	HashMap<Food, Integer> groceryList = new HashMap<Food, Integer>();
        groceryList.put(APPLE, 1);
        groceryList.put(COFFEE, 1);
        groceryList.put(BAGEL, 1);
        assertEquals(2.75, appleStore.buy(groceryList), 0.0001);
    }
    // TODO write a few more tests!

}
