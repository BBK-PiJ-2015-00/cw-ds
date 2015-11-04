public class FunctionalLinkedList extends LinkedList implements FunctionalList {
	public FunctionalLinkedList() {
		super();
	}
	
	public FunctionalLinkedList(LinkedList linkedList) {
		super(linkedList);
	}	
	
	public ReturnObject head() {
		if(super.size()==0)
			return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		
		return super.getObject();
	}
	
	public FunctionalList rest() {
		if(super.getNext()==null) {
			return new FunctionalLinkedList();
		} else {
			LinkedList result = super.getNext();
			return new FunctionalLinkedList(result);
		}
	}
}