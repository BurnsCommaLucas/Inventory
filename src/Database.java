import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

/************************
 * @name: Database
 * @author: Lucas Burns, Nick Wagner, Will Bryan, DJ Milovic
 * @version: 4/7/2016
 *
 * 		Database class, intended for use in CS341 (Software Engineering) project, generates a Hashmap
 * 			filled with entries of ingredients for the restaurant the software is meant for
 * 			(Schmitty's!).  Also includes functions that update the database and format output to
 * 			other portions of the project.
 */
public class Database {

	public static HashMap<String, Integer> db; //Database Entries
	public static HashMap<String, Integer> cost; //Cost entries
	private final String[] INVENTORY = {"Chips", "Cheese", "Chicken", "FryingOil", "Breading", "Rice", "Spam", "Nori",
			"Lettuce", "Croutons", "Dressing", "Miso", "Tofu", "Seaweed", "Egg", "Beef", "Gravy", "Garlic", "Shoyu",
			"BreadBuns", "Oreo", "MochiPowder", "IceCream", "Water", "Coke", "RootBeer", "Jack", "Vodka", "Tonic",
			"Apple", "SchmittySauce"};
	private final int[] COST = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};//Need 31 items
	private final int DEF = 200; //max quantity

	/***
	 * @name: Database()
	 * @param: N/a
	 *
	 * 		Ctor for Database HashMap
	 */
	public Database(){
		db=new HashMap<String, Integer>();
		cost = new HashMap<String, Integer>();
	}
	
	/***
	 * @name: add
	 * @param: String - key for the entry (item name)
	 * 		   int - value for the entry (item quantity)
	 *
	 * 		Adder function for the Database
	 */
	public void add(String key, int val, int price){
		db.put(key, val);
		cost.put(key, price);
	}

	/***
	 * @name: update
	 * @param: String - key for entry (item name)
	 * 		   int - value for the entry (new item quantity)
	 *
	 * 		Function that updates an entry in the Database
	 */
	public void update(String key, int val){
		if (db.get(key) - val < 0) { return; }
		db.replace(key, val);
	}

	public boolean includes(Object key) { return db.containsKey(key); }

	public int getStock(Object key) { return db.get(key); }

	/***
	 * @name: init
	 * @param: N/A
	 *
	 * 		Initializes entries of ingredients in the Database
	 */
	public void init()
	{
		for (int i = 0; i < INVENTORY.length; i++)
		{
			add(INVENTORY[i], DEF, COST[i]);
		}
	}

	/***
	 * @name: getStockChar
	 * @param: Object - key for entry (item name)
	 * 		   int - amount required (amtReq) for the given order
	 *
	 * 		Return the char that needs to be passed back to Cook Staff, whether an item
	 * 		is in stock, low-stock, or out-of-stock.
	 */
	public static char getStockChar(Object key, int amtReq)
	{
		if (db.get(key) > 0)
		{
			if (db.get(key) > amtReq)
			{
				return 'y';
			}
			return 'n';
		}
		return 'N';
	}

	/***
	 * @name: updateStock
	 * @param: Object - key for entry (item name)
	 * 		   int - amount required (newVal) for the given order
	 *
	 * 		Utilize the update() function to update the database and send outputs accordingly.
	 */
	public void updateStock(String key, int newVal, String path) {
		update(key, newVal);
		String managementPath = path + "InventoryManagement.txt";
		try {
			PrintWriter manageWriter = new PrintWriter(managementPath);
			manageWriter.println("ItemOrdered,Quantity,Cost");
			for (int i = 0; i < INVENTORY.length; i ++) {
				int sold = DEF - db.get(INVENTORY[i]);
				manageWriter.println(INVENTORY[i] + "," + sold + "," + sold * cost.get(INVENTORY[i]));
			}
			manageWriter.close();
		} catch (FileNotFoundException e) {
		}
	}
}
