package DataObjects;


public class DoubleTile {
	private Double[][] values;
	DoubleTile(Double[][] values_) {
		this.values = values_;
	}
		
	public double getVal(int x, int y) {
		return values[x][y];
	}
	
	public void setVal(int x, int y, Double newVal) {
		values[x][y] = newVal;
	}
	
	public Double[][] getValues() {
		return values;
	}
}
