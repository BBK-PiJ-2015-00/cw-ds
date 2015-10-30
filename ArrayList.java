public class ArrayList implements List {
	private ReturnObject[] objectArray;
	private int size;
	
	private boolean outBounds(int index) {
		if(size==0 || index>=size || index<0)
			return true;
		
		else
			return false;
	}
		
	public ArrayList() {
		this.objectArray = new ReturnObject[0];
		this.size = 0;
	}
	
	public boolean isEmpty() {
		return this.size==0;
	}
	
	public int size() {
		return this.size;
	}
	
	public ReturnObject get(int index) {
		if(this.outBounds(index)) {
			return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
		}		
		return objectArray[index];
	}
	
	public ReturnObject remove(int index) {
		if(this.outBounds(index)) {
			return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
		}
		
		ReturnObject object = this.get(index);
		if(object.hasError())
			return object;
		
		size--;
		if(size==0)			
			return object;
		
		ReturnObject[] temp = objectArray;
		objectArray = new ReturnObject[size];
		
		for(int i = 0; i < index; i++) {
			objectArray[i] = temp[i];
		}
		for(int i = index; i < size; i++) {
			objectArray[i] = temp[i+1];
		}
		
		return object;
	}
	
	public ReturnObject add(int index, Object item) {
		if(this.outBounds(index)) {
			return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
		}
		
		if(item==null)
			return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
		
		size++;
		
		ReturnObject[] temp = objectArray;
		objectArray = new ReturnObject[size];
		
		for(int i = 0; i < index; i++) {
			objectArray[i] = temp[i];
		} 
		objectArray[index] = new ReturnObjectImpl(item);
		for(int i = index+1; i < size; i++) {
			objectArray[i] = temp[i-1];
		}
		
		return null;
	}
	
	public ReturnObject add(Object item) {
		if(item==null)
			return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
		
		size++;
		
		ReturnObject[] temp = objectArray;	
		objectArray = new ReturnObject[size];
		objectArray[size-1] = new ReturnObjectImpl(item);
		for(int i = 0; i < size-1; i++) {
			objectArray[i] = temp[i];
		}
		
		return null;
	}
}