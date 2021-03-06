import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

//java org.junit.runner.JUnitCore TestStack

public class TestStack {
	public Stack stack;
	
	@Before
	public void createStacks() {
		List list = new ArrayList();
		stack = new StackImpl(list);
	}
	
	
	
	@Test
	public void testCreate() {
		assertNotNull(stack);		
		
		assertTrue(stack.getClass()==StackImpl.class);		
	}	
	
	@Test
	public void testIsEmpty() {
		assertTrue(stack.isEmpty());
	}
	
	@Test
	public void testPush() {
		stack.push("zero");
		assertFalse("stack should not be empty",stack.isEmpty());		
		stack.push("one");
		stack.push("two");
		stack.push("three");
		stack.push("four");			
	}
	
	@Test
	public void testSize() {
		testPush();		
		assertEquals(5, stack.size());
	}
	
	@Test
	public void testTop() {
		testPush();
		
		assertEquals("four",stack.top().getReturnValue());
	}
	
	@Test
	public void testPop() {
		testPush();
		
		assertEquals("four",stack.pop().getReturnValue());		
		assertEquals(4,stack.size());		
		assertEquals("three",stack.pop().getReturnValue());		
		assertEquals("two",stack.pop().getReturnValue());		
		assertEquals("one",stack.pop().getReturnValue());		
		assertEquals(1,stack.size());		
		assertEquals("zero",stack.pop().getReturnValue());
		
		testIsEmpty();
	}
	
	@Test
	public void testPushNull() {
		Object obj = null;
		
		stack.push(obj);
		//pushing null will push null;
		assertEquals(null, stack.top().getReturnValue());
	}
	
	@Test
	public void testErrorEmptyStructure() {
		ErrorMessage error = ErrorMessage.EMPTY_STRUCTURE;
		
		assertEquals(error, stack.top().getError());		
		assertEquals(error, stack.pop().getError());
	}
}













