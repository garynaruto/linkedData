package findDiff;

public class matchType{
	public Item deleteItem;
	public Item addItem;
	public String type;
	public double Delta=-1.0;
	public matchType(Item a,Item b,String s){
		this.deleteItem = a;
		this.addItem = b;
		this.type = s;
	}
	@Override
	public String toString(){
		return deleteItem.toStringLonLat()+", : ,"+addItem.toStringLonLat()+","+type;
	}
}
