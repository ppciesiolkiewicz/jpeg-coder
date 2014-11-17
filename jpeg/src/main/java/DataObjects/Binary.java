package DataObjects;

public class Binary extends Number {
	
	public String value;
	
	public Binary (String value){
		this.value=value;
	}

	@Override
	public double doubleValue() {
		// TODO Auto-generated method stub
		return -1D;
	}

	@Override
	public float floatValue() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		return -1;
	}
	
	@Override
	public String toString(){
		return value;
	}
}
