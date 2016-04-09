import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

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
	private final String CHIPS = "Chips";
	private final String CHEESE = "Cheese";
	private final String CHICKEN = "Chicken";
	private final String FRYING_OIL = "FryingOil";
	private final String BREADING = "Breading";
	private final String RICE = "Rice";
	private final String SPAM = "Spam";
	private final String NORI = "Nori";
	private final String LETTUCE = "Lettuce";
	private final String CROUTONS = "Croutons";
	private final String DRESSING = "Dressing";
	private final String MISO = "Miso";
	private final String TOFU = "Tofu";
	private final String SEAWEED = "Seaweed";
	private final String EGG = "Egg";
	private final String BEEF = "Beef";
	private final String GRAVY = "Gravy";
	private final String GARLIC = "Garlic";
	private final String SHOYU = "Shoyu";
	private final String BREAD_BUNS = "BreadBuns";
	private final String OREO = "Oreo";
	private final String MOCHI_POWDER = "MochiPowder";
	private final String ICE_CREAM = "IceCream";
	private final String WATER = "Water";
	private final String COKE = "Coke";
	private final String ROOT_BEER = "RootBeer";
	private final String JACK = "Jack";
	private final String VODKA = "Vodka";
	private final String TONIC = "Tonic";
	private final String APPLE = "Apple";
	private final String SCHMITTY_SAUCE = "SchmittySauce";
	private final int DEF = 200; //max quantity
	
	private boolean sufficientQuantity = false;
	private boolean lowStock = false;

	/***
	 * @name: Database()
	 * @param: N/a
	 *
	 * 		Ctor for Database HashMap
	 */
	public Database(){
		db=new HashMap<String, Integer>();
	}
	
	/***
	 * @name: add
	 * @param: String - key for the entry (item name)
	 * 		   int - value for the entry (item quantity)
	 *
	 * 		Adder function for the Database
	 */
	public void add(String key, int val){
		db.put(key, val);
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
		add(CHIPS, DEF);
		add(CHEESE, DEF);
		add(CHICKEN, DEF);
		add(FRYING_OIL, DEF);
		add(BREADING, DEF);
		add(RICE, DEF);
		add(SPAM, DEF);
		add(NORI, DEF);
		add(LETTUCE, DEF);
		add(CROUTONS, DEF);
		add(DRESSING, DEF);
		add(MISO, DEF);
		add(TOFU, DEF);
		add(SEAWEED, DEF);
		add(EGG, DEF);
		add(BEEF, DEF);
		add(GRAVY, DEF);
		add(GARLIC, DEF);
		add(SHOYU, DEF);
		add(BREAD_BUNS, DEF);
		add(OREO, DEF);
		add(MOCHI_POWDER, DEF);
		add(ICE_CREAM, DEF);
		add(WATER, DEF);
		add(COKE, DEF);
		add(ROOT_BEER, DEF);
		add(JACK, DEF);
		add(VODKA, DEF);
		add(TONIC, DEF);
		add(APPLE, DEF);
		add(SCHMITTY_SAUCE, DEF);
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
                sufficientQuantity = true;
				return 'y';

			}
            sufficientQuantity = false;
			return 'n';

		}
        sufficientQuantity = false;
		return 'N';
	}

	public static void printMap(PrintWriter writer, String[] foods, int[] nums) {
		for (int i = 0; i < foods.length; i++) {
			if (db.containsKey(foods[i])) {
				writer.println(foods[i] + " " + nums[i] + " " + getStockChar(foods[i], nums[i]));
			}
		}
	}

	/***
	 * @name: updateStock
	 * @param: Object - key for entry (item name)
	 * 		   int - amount required (amtReq) for the given order
	 *
	 * 		Utilize the update() function to update the database and send outputs accordingly.
	 */
	public void int updateStock(Object key, int amtReq) {
		if(sufficientQuantity == true) {
			int currentVal = getValue(key);
			int temp = currentVal - amtReq;

			update(key, temp);

			if(temp < 35) {
				lowStock = true;
			}
			else {
				lowStock = false;
			}
			if(lowStock = true) {
				try {
					//OUTPUT TO MANAGEMENT need printwriter
				}
			}
		}
	}
}
