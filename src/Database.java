import java.util.HashMap;



public class Database {

	HashMap<String, Integer> db; //Database
	
	public Database(){
		db=new HashMap<String, Integer>();
	}
	
	public void add(String key, int val){
		db.put(key, val);
	}
	
	public void update(String key, int val){
		db.replace(key, val);
	}

	
}
