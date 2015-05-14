package morse;

import java.util.Arrays;

public class BooleanArray {

	private boolean[] array;
	
	public BooleanArray(boolean ... array) {
		super();
		
		this.array = array;
	}

	public boolean[] getArray() {
		return array;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(array);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BooleanArray other = (BooleanArray) obj;
		if (array.length != other.array.length) {
			return false;
		} else {
			return sameContent(other);	
		}
	}
	
	private boolean sameContent(BooleanArray other) {
		for(int i = 0; i<array.length;i++) {
			if(array[i]!=other.array[i])
				return false;
		}		
		return true;		
	}
	
}
