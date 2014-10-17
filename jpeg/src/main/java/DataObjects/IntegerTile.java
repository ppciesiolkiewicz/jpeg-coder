package DataObjects;


public class IntegerTile {
	private Integer[][] values;
	IntegerTile(Integer[][] values_) {
		this.values = values_;
	}
		
	public Integer getVal(int x, int y) {
		return values[x][y];
	}
	
	public void setVal(int x, int y, Integer newVal) {
		values[x][y] = newVal;
	}
	
	public Integer[][] getValues() {
		return values;
	}
}
