public class LinkedList implements List {
	private ReturnObject object;
	private LinkedList next;
	
	private ErrorMessage testBounds(int index) {
		if(object==null) {
			return ErrorMessage.EMPTY_STRUCTURE;
		} if(index<0 || index>=this.size()) {
			return ErrorMessage.INDEX_OUT_OF_BOUNDS;
		} else
			return ErrorMessage.NO_ERROR;
	}
	
	public LinkedList() {
		this.object = null;
		this.next = null;
	}
	
	public LinkedList(LinkedList linkedList) {
		this.object = linkedList.object;
		this.next = linkedList.next;
	}	
	
	public ReturnObject getObject() {
		return this.object;
	}
	
	public LinkedList getNext() {
		return this.next;
	}
	
	public LinkedList(ReturnObjectImpl object) {
		this.object = object;
		this.next = null;
	}
	
	public boolean isEmpty() {
		return this.object == null;
	}
	
	public int size() {
		if(object == null) {
			return 0;
		} else if (next == null) {
			return 1;
		}
		
		return 1 + next.size();
	}
	
	public ReturnObject get(int index) {
		ErrorMessage em = this.testBounds(index);
		if(em.toString()!="NO_ERROR")
			return new ReturnObjectImpl(em);
		
		LinkedList current = this;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		
		return current.object;
	}
	
	public ReturnObject remove(int index) {
		ErrorMessage em = this.testBounds(index);
		if(em.toString()!="NO_ERROR")
			return new ReturnObjectImpl(em);
		
		if(index==0) {		
			ReturnObject result = this.object;
			if(this.next==null) {
				this.object = null;
			} else {
				this.object = this.next.getObject();
				this.next = this.next.getNext();
			}
			return result;
		}
		
		LinkedList current = this;
		LinkedList last = current;
		for(int i = 0; i < index; i++) {
			last = current;
			current = current.next;
		}
		
		last.next = current.next;
		
		return current.object;
	}
	
	public ReturnObject add(int index, Object item) {
		if(object==null && index==0)
			return this.add(item);	
		
		if(index<0 || index>this.size())
			return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
		
		if(item==null)
			return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);			
		
		ReturnObjectImpl toAdd = new ReturnObjectImpl(item);
		LinkedList newItem = new LinkedList(toAdd);
		
		if(index==0) {
			LinkedList temp = new LinkedList(this);
			this.next = temp;
			this.object = toAdd;			
			return null;
		}			
		
		LinkedList current = this;
		LinkedList last = current;
		for(int i = 0; i < index; i++) {
			last = current;
			if(current.next!=null) {
				current = current.next;
			} else {
				current.next = newItem;
				return null;
			}
		}		
		
		last.next = newItem;
		newItem.next = current;
		
		return null;
	}
	
	public ReturnObject add(Object item) {
		if(item==null) {
			return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
		} else if(this.object==null) {
			object = new ReturnObjectImpl(item);
			return null;
		} else if(this.next==null) {
			ReturnObjectImpl toAdd = new ReturnObjectImpl(item);
			this.next = new LinkedList(toAdd);
			return null;
		}
		
		return next.add(item);
	}
}