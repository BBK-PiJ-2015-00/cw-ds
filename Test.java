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
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		
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