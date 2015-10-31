public class FunctionalArrayList extends ArrayList implements FunctionalList {
	public FunctionalArrayList() {
		super();
	}
	
	public FunctionalArrayList(ReturnObject[] array) {
		super(array);
	}	
	
	public ReturnObject head() {
		if(super.size()==0)
			return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		
		ReturnObject[] result = super.getArray();
		
		return result[0];
	}
	
	public FunctionalList rest() {
		int size = super.size() - 1;
				
		size = Math.max(0,size);
		ReturnObject[] result = new ReturnObject[size];
		
		if(size==0)	return new FunctionalArrayList(result);
		
		ReturnObject[] temp = super.getArray();
		for(int i = 0; i < size; i++) {
			result[i] = temp[i+1];
		}

		return new FunctionalArrayList(result);
	}
}