public class Test {
	public static void main (String[] args) {
		Test t= new Test();
		t.launch();
		
		System.out.println("\nEND");
	}
	
	private void launch() {
		List array = new ArrayList();
		List linked = new LinkedList();
		
		testList(array);
		testList(linked);
	}
	
	private void testList(List list) {
		ReturnObject obj = new ReturnObjectImpl();

		String s = (list.isEmpty())? "Empty" : "Not Empty";
		System.out.println (s);
		
		ErrorMessage error;
		obj = list.get(0);
		error = obj.getError();
		System.out.println(error.toString());
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		
		s = (list.isEmpty())? "Empty" : "Not Empty";
		System.out.println (s);
		
		list.add(2, "z");
		list.remove(3);
		obj = list.get(89);
		
		ErrorMessage EM = obj.getError();		
		
		System.out.println(EM.toString());
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(i + ": " + list.get(i).getReturnValue());
		}
	}
}