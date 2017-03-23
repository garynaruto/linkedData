package findDiff;

public class Item implements Comparable<Item> {
	public String cityCode;//省市縣市代碼
	public String townshipCode;//鄉鎮市區代碼
	public String village;//村里
	public String neighbor;//鄰
	public String road;//路段
	public String region;//地區
	public String alley;//巷
	public String lane;//弄,
	public String number;//號
	public double TWD97x;//橫座標
	public double TWD97y;//縱座標
	public double Threshold = 0.02;
	public String[] line;
	
	public Item(String[] s){
		this.cityCode = s[0];//省市縣市代碼
		this.townshipCode = s[1];//鄉鎮市區代碼
		this.village = s[2];//村里
		this.neighbor = s[3];//鄰
		this.road = s[4];//路段
		this.region = s[5];//地區	
		this.alley = s[6];//巷
		this.lane = s[7];//弄,
		this.number = s[8];//號
		this.TWD97x = Double.parseDouble(s[9]);//橫座標
		this.TWD97y = Double.parseDouble(s[10]);//縱座標
		this.line = s;
	}
	@Override
    public int compareTo(Item other) {
        return this.number.compareTo(other.number);
    }
	public String equal(Item other){
		boolean addrFlg = true;
		boolean coordinateFlg = true;
		if(!this.cityCode.equals(other.cityCode)){
			addrFlg = false;
		}
		if(!this.townshipCode.equals(other.townshipCode)){
			addrFlg = false;
		}		
		if(!this.village.equals(other.village)){
			addrFlg = false;
		}
		if(!this.neighbor.equals(other.neighbor)){
			addrFlg = false;
		}
		if(!this.road.equals(other.road)){
			addrFlg = false;
		}
		if(!this.region.equals(other.region)){
			addrFlg = false;
		}
		if(!this.alley.equals(other.alley)){
			addrFlg = false;
		}
		if(!this.lane.equals(other.lane)){
			addrFlg = false;
		}
		if(!this.number.equals(other.number)){
			addrFlg = false;
		}		
		
		
		if(Math.abs(this.TWD97x-other.TWD97x)>Threshold){
			coordinateFlg = false;
		}
		if(Math.abs(this.TWD97y-other.TWD97y)>Threshold){
			coordinateFlg = false;
		}
		
		//return value
		if(!addrFlg && !coordinateFlg){
			return "No";//完全不相等
		}
		else if(addrFlg && !coordinateFlg){
			return "addr";//地址相同
		}
		else if(!addrFlg && coordinateFlg){
			return "coordinate";//座標相同
		}else {
			return "Equivalent";//相等
		}
			
	}
	public boolean cmpCoordinate(Item other){
		if(Math.abs(this.TWD97x-other.TWD97x)>Threshold){
			return false;
		}
		if(Math.abs(this.TWD97y-other.TWD97y)>Threshold){
			return false;
		}
		return true;		
	}
	@Override
	public String toString(){
		String out = line[0];
		
		for(int i=1; i<line.length; i++){
			out = out+","+line[i];
		}
		return out;
	}
	
	
	public String toStringLonLat(){
		String out = line[0];
		
		for(int i=1; i<line.length-2; i++){
			out = out+","+line[i];
		}
		TMToLatLon t =new TMToLatLon();
		double[] ans = t.convert(this.TWD97x, this.TWD97y);
		out = out+","+ans[0]+","+ans[1];
		
		return out;
	}
}
