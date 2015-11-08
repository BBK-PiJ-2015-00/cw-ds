public class StackImpl extends AbstractStack {
	public StackImpl(List list) {
		super(list);
	}
	
	public boolean isEmpty() {
		return super.internalList.isEmpty();
	}
	
	public int size() {
		return super.internalList.size();
	}
	
	public void push(Object item) {
		super.internalList.add(item);
	}
	
	public ReturnObject top() {
		int index = this.size() - 1;
		return super.internalList.get(index);
	}
	
	public ReturnObject pop() {
		int index = this.size() - 1;
		return super.internalList.remove(index);
	}
}