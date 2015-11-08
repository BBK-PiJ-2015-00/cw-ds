import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

//java org.junit.runner.JUnitCore SampleableListTest

public class SampleableListTest {
	public SampleableList array;
	
	@Before
	public void createLists() {
		array = new SampleableListImpl();
	}
	
	
	
	@Test
	public void testCreate() {
		assertNotNull(array);
		
		assertTrue(array.getClass()==SampleableListImpl.class);			
	}	
	
	@Test
	public void testIsEmpty() {
		assertTrue(array.isEmpty());
	}
	
	@Test
	public void testAdd() {
		assertNull(array.add("zero"));
		assertFalse("Array should not be empty",array.isEmpty());		
		array.add("one");
		array.add("two");
		array.add("three");
		array.add("four");			
	}
	
	@Test
	public void testSize() {
		testAdd();		
		assertEquals(5, array.size());
	}
	
	@Test
	public void testGet() {
		testAdd();
		
		assertEquals("zero",array.get(0).getReturnValue());		
		assertEquals("two",array.get(2).getReturnValue());		
		assertEquals("four",array.get(4).getReturnValue());
	}
	
	@Test
	public void testRemove() {
		testAdd();
		
		assertEquals("two",array.remove(2).getReturnValue());		
		assertEquals(4,array.size());		
		assertEquals("four",array.remove(3).getReturnValue());		
		assertEquals("zero",array.remove(0).getReturnValue());		
		assertEquals("three",array.remove(1).getReturnValue());		
		assertEquals(1,array.size());		
		assertEquals("one",array.remove(0).getReturnValue());
		
		testIsEmpty();
	}
	
	@Test
	public void testAddIndex() {
		assertNull(array.add(0, "first"));
		
		testAdd();
		
		assertNull(array.add(6,"new end"));		
		assertNull(array.add(2,"new middle"));		
		assertNull(array.add(0, "new first"));
		
		int arrayEnd = array.size() - 1;
		assertEquals("new end",array.get(arrayEnd).getReturnValue());		
		assertEquals("new first",array.get(0).getReturnValue());		
		assertEquals("new middle",array.get(3).getReturnValue());
	}
	
	@Test
	public void testErrorOutOfBounds() {
		ErrorMessage error = ErrorMessage.INDEX_OUT_OF_BOUNDS;
		
		//Test when empty
		assertEquals(error, array.add(100, "100").getError());	
		assertEquals(error, array.add(-1, "100").getError());
		
		//test when full
		testAdd();
		assertEquals(error, array.add(100, "100").getError());	
		assertEquals(error, array.add(-1, "100").getError());
		
		assertEquals(error, array.get(100).getError());
		assertEquals(error, array.get(-1).getError());
		
		assertEquals(error, array.remove(100).getError());
		assertEquals(error, array.remove(-1).getError());
		
		//multiple errors should prioritise INDEX_OUT_OF_BOUNDS not INVALID_ARGUMENT 
		Object obj = null;	
		assertEquals(error, array.add(100,obj).getError());
	}
	
	@Test
	public void testErrorInvalidArgument() {
		ErrorMessage error = ErrorMessage.INVALID_ARGUMENT;
		Object obj = null;
		
		assertEquals(error, array.add(obj).getError());		
		assertEquals(error, array.add(0,obj).getError());	
	}
	
	@Test
	public void testErrorEmptyStructure() {
		ErrorMessage error = ErrorMessage.EMPTY_STRUCTURE;
		
		assertEquals(error, array.get(0).getError());		
		assertEquals(error, array.remove(0).getError());
	}

	@Test
	public void testSample() {
		SampleableList sList = new SampleableListImpl();
		
		sList = array.sample();
		assertTrue(sList.isEmpty());
		
		array.add("just one");
		sList = array.sample();
		assertEquals(1, sList.size());		
		assertEquals("just one", sList.get(0).getReturnValue());
		array.add("now two");
		sList = array.sample();
		assertEquals(1, sList.size());		
		assertEquals("just one", sList.get(0).getReturnValue());
		array.remove(0);
		array.remove(0);
				
		testAdd();		
		sList = array.sample();
		assertEquals(3, sList.size());
		assertEquals("zero", sList.get(0).getReturnValue());
		assertEquals("two", sList.get(1).getReturnValue());
		assertEquals("four", sList.get(2).getReturnValue());
		
		array.add("one more");
		sList = array.sample();
		assertEquals(3, sList.size());		
		assertEquals("four", sList.get(2).getReturnValue());
	}
}













