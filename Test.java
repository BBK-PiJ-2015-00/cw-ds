public class Test {
	public static void main (String[] args) {
		Test t= new Test();
		t.launch();
		
		System.out.println("\nEND");
	}
	
	private void launch() {
		List array = new ArrayList();
		List linked = new LinkedList();
		FunctionalList functionalArray = new FunctionalArrayList();
		FunctionalList functionalLinked = new FunctionalLinkedList();
		
		testList(array);
		testList(linked);
		//testFunctional(functionalArray);
		//testFunctional(functionalLinked);
	}
	
	private void testFunctional(FunctionalList list) {
		testList(list);
		
		ReturnObject obj = new ReturnObjectImpl();
		obj = list.head();
		if(obj.hasError()) {			
			ErrorMessage error = obj.getError();
			System.out.println(error.toString());			
		} else {
			System.out.println("Head\t" + obj.getReturnValue());
			
			FunctionalList theRest = list.rest();
			System.out.print("Rest(" + theRest.size() + ")");
			for(int i = 0; i < theRest.size(); i++) {
				System.out.print("\t" + theRest.get(i).getReturnValue() + "\n");
			}
		}
	}
	
	private void testList(List list) {
		ReturnObject obj = new ReturnObjectImpl();

		String s = (list.isEmpty())? "Empty" : "Not Empty";
		System.out.println (s);
		
		ErrorMessage error;
		//obj = list.remove(0);
		obj = list.add(0,"a");
		if(obj!=null) {
			error = obj.getError();
			System.out.println(error.toString());
		} else {
			System.out.println("success");
		}
		
		//list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		
		s = (list.isEmpty())? "Empty" : "Not Empty";
		System.out.println (s);
		
		list.add(2, "z");
		list.remove(3);
		obj = list.get(89);
		
		error = obj.getError();		
		
		System.out.println(error.toString());
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(i + ": " + list.get(i).getReturnValue());
		}
		
		error = null;
	}
}