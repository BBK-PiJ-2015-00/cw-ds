import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

//java org.junit.runner.JUnitCore TestImprovedStack

public class TestImprovedStack {
	public ImprovedStack stack;
	
	@Before
	public void createStacks() {
		List list = new ArrayList();
		stack = new ImprovedStackImpl(list);
	}
	
	
	
	@Test
	public void testCreate() {
		assertNotNull(stack);		
		
		assertTrue(stack.getClass()==ImprovedStackImpl.class);		
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
	
	@Test
	public void testReverse() {
		testPush();
		
		Stack reversedStack = stack.reverse();
		
		assertEquals(5, stack.size());
		
		assertEquals("zero", reversedStack.pop().getReturnValue());		
		assertEquals("one", reversedStack.pop().getReturnValue());		
		assertEquals("two", reversedStack.pop().getReturnValue());		
		assertEquals("three", reversedStack.pop().getReturnValue());		
		assertEquals("four", reversedStack.pop().getReturnValue());
		assertEquals(0, reversedStack.size());
		
		//test original stack is unaffected
		assertEquals("four", stack.pop().getReturnValue());
		assertEquals("three", stack.pop().getReturnValue());
		assertEquals("two", stack.pop().getReturnValue());
		assertEquals("one", stack.pop().getReturnValue());		
		assertEquals("zero", stack.pop().getReturnValue());	
		testIsEmpty();		
	}
	
	@Test
	public void testRemove() {
		testPush();
		
		stack.remove("four");
		assertEquals("three", stack.top().getReturnValue());
		assertEquals("first size test",4, stack.size());
		
		stack.push(5);
		stack.push(5);
		stack.push(5);
		stack.push(5);
		stack.push(6);
		stack.push(5);
		stack.push(5);
		
		stack.remove(5);
		assertEquals(6, stack.pop().getReturnValue());
		assertEquals("second size test",4, stack.size());
		
		assertEquals("three", stack.pop().getReturnValue());
		assertEquals("two", stack.pop().getReturnValue());
		assertEquals("one", stack.pop().getReturnValue());
		assertEquals("zero", stack.pop().getReturnValue());
		
		testIsEmpty();
	}
	
	@Test
	public void testRemove2() {
		testPush();
		
		stack.remove("zero");
		stack.remove("two");
		assertEquals(3, stack.size());
		assertEquals("four", stack.pop().getReturnValue());
		assertEquals("three", stack.pop().getReturnValue());
		assertEquals("one", stack.pop().getReturnValue());			
		
		testIsEmpty();
	}
}













