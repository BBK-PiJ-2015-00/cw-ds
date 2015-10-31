public class FunctionalArrayList implements FunctionalList extends ArrayList {
	public FunctionalArrayList() {
		super();
	}
	
	public ReturnObject head() {
		if(super.size()==0)
			return ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		
		return super.objectArray[0];
	}
	
	public FunctionalList rest() {
		
	}
}