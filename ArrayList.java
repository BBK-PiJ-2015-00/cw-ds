public class ArrayList implements List {
	private ReturnObject[] objectArray;
	private int size;
	
	private ErrorMessage testBounds(int index) {
		if(size==0) {
			return ErrorMessage.EMPTY_STRUCTURE;
		} else if(index<0 || index>=this.size) {
			return ErrorMessage.INDEX_OUT_OF_BOUNDS;
		} else
			return ErrorMessage.NO_ERROR;
	}
		
	public ArrayList() {
		this.objectArray = new ReturnObject[0];
		this.size = 0;
	}
	
	public ArrayList(ReturnObject[] array) {
		this.objectArray = array;
		this.size = array.length;
	}
	
	public ReturnObject[] getArray() {
		return this.objectArray;
	}
	
	public boolean isEmpty() {
		return this.size==0;
	}
	
	public int size() {
		return this.size;
	}
	
	public ReturnObject get(int index) {
		ErrorMessage em = this.testBounds(index);
		if(em.toString()!="NO_ERROR")
			return new ReturnObjectImpl(em);
		
		return objectArray[index];
	}
	
	public ReturnObject remove(int index) {
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
		if(size==0 && index==0) {
			return this.add(item);
		}
		
		if(index<0 || index>this.size)
			return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);	
		
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