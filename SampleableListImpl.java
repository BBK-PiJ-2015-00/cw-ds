public class SampleableListImpl implements SampleableList {
	private ReturnObject[] sampleList;
	private int size;
	
	public SampleableListImpl() {
		this.sampleList = new ReturnObject[0];
		this.size = 0;
	}
	
	public SampleableList sample() {
		int newSize = (size+1)/2;
		
		ReturnObject[] result = new ReturnObject[newSize];
		for(int i = 0; i < size; i+=2) {
			result[i/2] = sampleList[i];
		}
		
		return new SampleableListImpl(result);
	}
	
	private ErrorMessage testBounds(int index) {
		if(size==0) {
			return ErrorMessage.EMPTY_STRUCTURE;
		} else if(index<0 || index>=this.size) {
			return ErrorMessage.INDEX_OUT_OF_BOUNDS;
		} else
			return ErrorMessage.NO_ERROR;
	}
		
	public SampleableListImpl(ReturnObject[] array) {
		this.sampleList = array;
		this.size = array.length;
	}
	
	public ReturnObject[] getArray() {
		return this.sampleList;
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
		
		return sampleList[index];
	}
	
	public ReturnObject remove(int index) {
		ReturnObject object = this.get(index);
		if(object.hasError())
			return object;
		
		size--;
		if(size==0)			
			return object;
		
		ReturnObject[] temp = sampleList;
		sampleList = new ReturnObject[size];
		
		for(int i = 0; i < index; i++) {
			sampleList[i] = temp[i];
		}
		for(int i = index; i < size; i++) {
			sampleList[i] = temp[i+1];
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

		ReturnObject[] temp = sampleList;
		sampleList = new ReturnObject[size];
		
		for(int i = 0; i < index; i++) {
			sampleList[i] = temp[i];
		} 
		sampleList[index] = new ReturnObjectImpl(item);
		for(int i = index+1; i < size; i++) {
			sampleList[i] = temp[i-1];
		}
		
		return null;
	}
	
	public ReturnObject add(Object item) {
		if(item==null)
			return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
		
		size++;
		
		ReturnObject[] temp = sampleList;	
		sampleList = new ReturnObject[size];
		sampleList[size-1] = new ReturnObjectImpl(item);
		for(int i = 0; i < size-1; i++) {
			sampleList[i] = temp[i];
		}
		
		return null;
	}
}