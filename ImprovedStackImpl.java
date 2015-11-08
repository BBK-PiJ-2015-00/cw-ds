public class ImprovedStackImpl implements ImprovedStack{
	private List internalList;
	
	public ImprovedStackImpl(List list) {
		this.internalList = list;
	}
	
	public ImprovedStack reverse() {
		List result = new ArrayList();
		
		int size = internalList.size();
		for(int i = size - 1; i >= 0; i--) {
			ReturnObject item = new ReturnObjectImpl(internalList.get(i).getReturnValue());
			result.add(item.getReturnValue());
		}
		
		return new ImprovedStackImpl(result);
	}
	
	public void remove(Object object) {
		int size = internalList.size();
		
		for(int i = 0; i < size; i++) {
			Object item = internalList.get(i).getReturnValue();
			if(item.equals(object)) {
				internalList.remove(i);
				size--;
				i--;
			}
		}
	}
	
	public boolean isEmpty() {
		return internalList.isEmpty();
	}
	
	public int size() {
		return internalList.size();
	}
	
	public void push(Object item) {
		internalList.add(item);
	}
	
	public ReturnObject top() {
		int index = this.size() - 1;
		return internalList.get(index);
	}
	
	public ReturnObject pop() {
		int index = this.size() - 1;
		return internalList.remove(index);
	}
}