import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import junit.framework.*;

public class Testing_No_Remove extends TestCase{
	
	private static int points = 0;
	
	public void testInsert(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals("", b.toString());
		points += 1;
		b.insert(7);
		assertEquals("[7]", b.toString());
		points += 2;
		b.insert(4);
		assertEquals("[4], [7]", b.toString());	
		b.insert(10);
		assertEquals("[4], [7], [10]", b.toString());	
		b.insert(2);
		assertEquals("[2], [4], [7], [10]", b.toString());	
		b.insert(5);
		assertEquals("[2], [4], [5], [7], [10]", b.toString());	
		points += 12;
		assertFalse(b.insert(5));
		points += 2;
		assertTrue(b.insert(1));
		points += 2;
		
		try {
			b.insert(null);
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)) {
				fail("Did not throw NoSuchElementException");				
			}
		}
		points += 1;
	}

	public void testHeight(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(-1, b.height());
		points += 1;
		
		b.insert(3);
		assertEquals(0, b.height());
		points += 2;
		
		b.insert(4);
		b.insert(5);
		assertEquals(2, b.height());
		points += 2;
	}
	
	public void testSize(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(0, b.size());
		points += 1;
		
		b.insert(3);
		assertEquals(1, b.size());
		points += 2;
		
		b.insert(4);
		b.insert(5);
		assertEquals(3, b.size());
		points += 2;
	}
	
	public void testIsEmpty(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertTrue(b.isEmpty());
		points += 1;
		
		b.insert(3);
		assertFalse(b.isEmpty());
		points += 2;
	}
	
	public void testToArrayList(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(new ArrayList<Object>(), b.toArrayList());
		points += 2;
		
		b.insert(3);
		b.insert(4);
		b.insert(5);

		ArrayList<Object> temp = new ArrayList<Object>();
		temp.add(3);temp.add(4);temp.add(5);
		assertEquals(temp, b.toArrayList());
		points += 8;
	}
	
	public void testToArray(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(0, b.toArray().length);
		points += 1;
		
		b.insert(3);
		b.insert(4);
		b.insert(5);
		Object[] temp = {3,4,5};
		Object[] foo = b.toArray();
		for (int j = 0; j < temp.length; j++){
			assertEquals(temp[j], foo[j]);			
		}
		points += 2;
	}
	
	public void testToString(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals("", b.toString());
		points += 1;
		
		b.insert(3);
		b.insert(4);
		b.insert(5);
		assertEquals( "[3], [4], [5]", b.toString());	
		points += 6;
	}
	
	public void testPreOrderIterator(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		Iterator<Integer> i = b.preOrderIterator();
		assertFalse(i.hasNext());
		points += 1;
		
		b.insert(3);
		b.insert(4);
		b.insert(5);
		b.insert(1);
		b.insert(0);
		b.insert(2);

		i = b.preOrderIterator();
		int k = 0;
		Object[] temp = {3, 1, 0, 2, 4, 5};
		boolean[] tempValues = {true, true, true, true, true, false};
		assertEquals(true, i.hasNext());
		while (i.hasNext()){
			assertEquals(temp[k], i.next());	
			assertEquals(tempValues[k++], i.hasNext());
		}
		points += 11;
		try {
			i.next();
			fail("Did not throw NoSuchElementException");
		} catch (Exception e){
			if (!(e instanceof NoSuchElementException)) {
				fail("Did not throw NoSuchElementException");				
			}
		}
		points += 1;
		try {
			i = b.preOrderIterator();
			i.next();
			b.insert(99);
			i.next();
			fail("Did not throw ConcurrentModificationException");
		} catch (Exception e){
			if (!(e instanceof ConcurrentModificationException)) {
				fail("Did not throw ConcurrentModificationException");				
			}
		}
		points += 2;
	}
	
	public void testIterator(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		Iterator<Integer> i = b.iterator();
		assertFalse(i.hasNext());
		points += 1;
		
		b.insert(3);
		b.insert(4);
		b.insert(5);
		b.insert(1);
		b.insert(0);
		b.insert(2);

		i = b.iterator();
		int k = 0;
		Object[] temp = {0, 1, 2, 3, 4, 5};
		boolean[] tempValues = {true, true, true, true, true, false};
		assertEquals(true, i.hasNext());		
		while (i.hasNext()){
			assertEquals(temp[k], i.next());	
			assertEquals(tempValues[k++], i.hasNext());
		}
		points += 8;
		try {
			i.next();
			fail("Did not throw NoSuchElementException");
		} catch (Exception e){
			if (!(e instanceof NoSuchElementException)) {
				fail("Did not throw NoSuchElementException");				
			}
		}
		points += 1;
		try {
			i = b.iterator();
			i.next();
			b.insert(99);
			i.next();
			fail("Did not throw ConcurrentModificationException");
		} catch (Exception e){
			if (!(e instanceof ConcurrentModificationException)) {
				fail("Did not throw ConcurrentModificationException");				
			}
		}
		points += 2;
	}
	
	public void testPrintPoints(){
		System.out.println(points);
	}
	
	public static void main(String args[]) {
		junit.swingui.TestRunner.run(Testing_No_Remove.class);
	}	
	
}
