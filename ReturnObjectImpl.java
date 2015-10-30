public class ReturnObjectImpl implements ReturnObject {
	private Object object;
	
	public ReturnObjectImpl(Object object) {
		this.object = object;
	}
	
	public ReturnObjectImpl() {
		this.object = null;
	}
	
	public boolean hasError() {
		if(this.object.getClass().isEnum())
			return true;
		else
			return false;
	}
	
	public ErrorMessage getError() {
		ErrorMessage EM = ErrorMessage.NO_ERROR;
		
		if(this.hasError()) {
			String s = this.object.toString();
			if(s=="EMPTY_STRUCTURE") {
				EM = ErrorMessage.EMPTY_STRUCTURE;
			} if(s=="INDEX_OUT_OF_BOUNDS") {
				EM = ErrorMessage.INDEX_OUT_OF_BOUNDS;
			} if(s=="INVALID_ARGUMENT") {
				EM = ErrorMessage.INVALID_ARGUMENT;
			}
		}
		
		return EM;
	}
	
	public Object getReturnValue() {
		if(this.hasError())
			return null;
		else
			return this.object;
	}
}