import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

//java org.junit.runner.JUnitCore ListTest

public class ListTest {
	public List array;
	public List linked;
	
	@Before
	public void createLists() {
		array = new ArrayList();
		linked = new LinkedList();
	}
	
	
	
	@Test
	public void testCreate() {
		assertNotNull(array);
		assertNotNull(linked);
		
		assertTrue(array.getClass()==ArrayList.class);
		assertTrue(linked.getClass()==LinkedList.class);		
	}	
	
	@Test
	public void testIsEmpty() {
		assertTrue(array.isEmpty());
		assertTrue(linked.isEmpty());
	}
	
	@Test
	public void testAdd() {
		assertNull(array.add("zero"));
		assertFalse("Array should not be empty",array.isEmpty());		
		array.add("one");
		array.add("two");
		array.add("three");
		array.add("four");	
		
		assertNull(linked.add("zero"));
		assertFalse("Linked should not be empty",linked.isEmpty());		
		linked.add("one");
		linked.add("two");
		linked.add("three");
		linked.add("four");			
	}
	
	@Test
	public void testSize() {
		testAdd();
		
		assertEquals(5, array.size());
		assertEquals(5, linked.size());
	}
	
	@Test
	public void testGet() {
		testAdd();
		
		assertEquals("zero",array.get(0).getReturnValue());
		assertEquals("zero",linked.get(0).getReturnValue());
		
		assertEquals("two",array.get(2).getReturnValue());
		assertEquals("two",linked.get(2).getReturnValue());
		
		assertEquals("four",array.get(4).getReturnValue());
		assertEquals("four",linked.get(4).getReturnValue());
	}
	
	@Test
	public void testRemove() {
		testAdd();
		
		assertEquals("two",array.remove(2).getReturnValue());
		assertEquals("two",linked.remove(2).getReturnValue());
		
		assertEquals(4,array.size());
		assertEquals(4,linked.size());
		
		assertEquals("four",array.remove(3).getReturnValue());
		assertEquals("four",linked.remove(3).getReturnValue());
		
		assertEquals("zero",array.remove(0).getReturnValue());
		assertEquals("zero",linked.remove(0).getReturnValue());
		
		assertEquals("three",array.remove(1).getReturnValue());
		assertEquals("three",linked.remove(1).getReturnValue());
		
		assertEquals(1,array.size());
		assertEquals(1,linked.size());
		
		assertEquals("one",array.remove(0).getReturnValue());
		assertEquals("one",linked.remove(0).getReturnValue());
		
		testIsEmpty();
	}
	
	@Test
	public void testAddIndex() {
		assertNull(array.add(0, "first"));
		assertNull(linked.add(0, "first"));
		
		testAdd();
		
		assertNull(array.add(6,"new end"));
		assertNull(linked.add(6,"new end"));
		
		assertNull(array.add(2,"new middle"));
		assertNull(linked.add(2,"new middle"));
		
		assertNull(array.add(0, "new first"));
		assertNull(linked.add(0, "new first"));
		
		int arrayEnd = array.size() - 1;
		int linkedEnd = linked.size() - 1;
		assertEquals("new end",array.get(arrayEnd).getReturnValue());
		assertEquals("new end",linked.get(linkedEnd).getReturnValue());
		
		assertEquals("new first",array.get(0).getReturnValue());
		assertEquals("new first",linked.get(0).getReturnValue());
		
		assertEquals("new middle",array.get(3).getReturnValue());
		assertEquals("new middle",linked.get(3).getReturnValue());
	}
	
	@Test
	public void testErrorOutOfBounds() {
		ErrorMessage error = ErrorMessage.INDEX_OUT_OF_BOUNDS;
		
		//Test when empty
		assertEquals(error, array.add(100, "100").getError());
		assertEquals(error, linked.add(100, "100").getError());		
		assertEquals(error, array.add(-1, "100").getError());
		assertEquals(error, linked.add(-1, "100").getError());
		
		//test when full
		testAdd();
		assertEquals(error, array.add(100, "100").getError());
		assertEquals(error, linked.add(100, "100").getError());		
		assertEquals(error, array.add(-1, "100").getError());
		assertEquals(error, linked.add(-1, "100").getError());	
		
		assertEquals(error, array.get(100).getError());
		assertEquals(error, linked.get(100).getError());
		assertEquals(error, array.get(-1).getError());
		assertEquals(error, linked.get(-1).getError());
		
		assertEquals(error, array.remove(100).getError());
		assertEquals(error, linked.remove(100).getError());
		assertEquals(error, array.remove(-1).getError());
		assertEquals(error, linked.remove(-1).getError());
		
		//multiple errors should prioritise INDEX_OUT_OF_BOUNDS not INVALID_ARGUMENT 
		Object obj = null;	
		assertEquals(error, array.add(100,obj).getError());
		assertEquals(error, linked.add(100,obj).getError());
	}
	
	@Test
	public void testErrorInvalidArgument() {
		ErrorMessage error = ErrorMessage.INVALID_ARGUMENT;
		Object obj = null;
		
		assertEquals(error, array.add(obj).getError());
		assertEquals(error, linked.add(obj).getError());
		
		assertEquals(error, array.add(0,obj).getError());
		assertEquals(error, linked.add(0,obj).getError());		
	}
	
	@Test
	public void testErrorEmptyStructure() {
		ErrorMessage error = ErrorMessage.EMPTY_STRUCTURE;
		
		assertEquals(error, array.get(0).getError());
		assertEquals(error, linked.get(0).getError());
		
		assertEquals(error, array.remove(0).getError());
		assertEquals(error, linked.remove(0).getError());
	}
}













