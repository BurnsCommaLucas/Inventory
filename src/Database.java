import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;


public class Database {

	public static HashMap<String, Integer> db; //Database
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
	private final int DEF = 200;
	
	
	public Database(){
		db=new HashMap<String, Integer>();
	}
	
	public void add(String key, int val){
		db.put(key, val);
	}
	
	public void update(String key, int val){
		if (db.get(key) - val < 0) { return; }
		db.replace(key, val);
	}

	public boolean includes(Object key) { return db.containsKey(key); }

	public int getStock(Object key) { return db.get(key); }

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
}
