package findDiff;

public class Item implements Comparable<Item> {
	public String cityCode;//�٥������N�X
	public String townshipCode;//�m���ϥN�X
	public String village;//����
	public String neighbor;//�F
	public String road;//���q
	public String region;//�a��
	public String alley;//��
	public String lane;//��,
	public String number;//��
	public double TWD97x;//��y��
	public double TWD97y;//�a�y��
	public double Threshold = 0.02;
	public String[] line;
	
	public Item(String[] s){
		this.cityCode = s[0];//�٥������N�X
		this.townshipCode = s[1];//�m���ϥN�X
		this.village = s[2];//����
		this.neighbor = s[3];//�F
		this.road = s[4];//���q
		this.region = s[5];//�a��	
		this.alley = s[6];//��
		this.lane = s[7];//��,
		this.number = s[8];//��
		this.TWD97x = Double.parseDouble(s[9]);//��y��
		this.TWD97y = Double.parseDouble(s[10]);//�a�y��
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
			return "No";//�������۵�
		}
		else if(addrFlg && !coordinateFlg){
			return "addr";//�a�}�ۦP
		}
		else if(!addrFlg && coordinateFlg){
			return "coordinate";//�y�ЬۦP
		}else {
			return "Equivalent";//�۵�
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
