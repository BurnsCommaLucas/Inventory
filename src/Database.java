import java.util.HashMap;



public class Database {

	HashMap<String, Integer> db; //Database
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
	private final String DRESSING = "Dressing"
	private final String MISO = "Miso";
	private final String TOFU = "Tofu";
	private final String SEAWEED = "Seaweed";
	private final String EGG = "Egg"
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
	private final Char = '\n';
	
	
	
	public Database(){
		db=new HashMap<String, Integer>();
	}
	
	public void add(String key, int val){
		db.put(key, val);
	}
	
	public void update(String key, int val){
		db.replace(key, val);
	}

	add("Chips",);
	
}
