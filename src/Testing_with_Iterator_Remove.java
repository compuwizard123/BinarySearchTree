import java.util.Iterator;

import junit.framework.*;

public class Testing_with_Iterator_Remove extends TestCase{
	
	private static int points = 0;
	
	public void testPolymorphism(){
		BinarySearchTree<SSI> b = new BinarySearchTree<SSI>();
		b.insert(new Student(5,"Joe", "Stooge"));
		b.insert(new Student(3,"Larry", "Stooge"));
		b.insert(new Student(7,"Curly", "Stooge"));	
		b.insert(new Staff(9, "Moe", "Stooge"));

		assertEquals("[3 Larry Stooge], [5 Joe Stooge], [7 Curly Stooge], [9 Moe Stooge]", b.toString());
		points += 20;
	}
	
	public void testGet(){
		BinarySearchTree<SSI> b = new BinarySearchTree<SSI>();
		b.insert(new Student(5,"Joe", "Stooge"));
		b.insert(new Student(3,"Larry", "Stooge"));
		b.insert(new Student(7,"Curly", "Stooge"));
		b.insert(new Staff(9, "Moe", "Stooge"));
		assertEquals("5 Joe Stooge", b.get(new SSI(5)).toString());
		points += 5;
	}
	
	public void testRemove(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals("", b.toString());
		points += 1;
		//removal from empty tree
		assertFalse(b.remove(7));
		points += 1;

		// remove just root
		b.insert(4);
		assertTrue(b.remove(4));
		assertEquals("", b.toString());	
		points += 2;
		
		// remove right child in simple tree
		b.insert(10);
		b.insert(4);
		b.insert(14);
		assertTrue(b.remove(14));
		points += 1;
		Integer[] a = {10, 4};
		boolean[] bool = {true, false};
		Iterator<Integer> i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < a.length; k++){
			assertEquals(a[k], i.next());
			assertEquals(bool[k], i.hasNext());
		}
		points += 3;
		
		// remove left child in simple tree
		b.insert(14);
		assertTrue(b.remove(4));
		a[0] = 10; a[1] = 14;
		i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < a.length; k++){
			assertEquals(a[k], i.next());
			assertEquals(bool[k], i.hasNext());
		}
		points += 3;
		
		// remove root in simple tree
		b.insert(4);
		assertTrue(b.remove(10));
		a[0] = 4; a[1] = 14;
		i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < a.length; k++){
			assertEquals(a[k], i.next());
			assertEquals(bool[k], i.hasNext());
		}
		points += 3;
		
		// Remove null element
		try {
			b.remove(null);
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)) {
				fail("Did not throw IllegalArgumentException");				
			}
		}
		points += 2;
		
		// Remove leaf from complex tree.
		b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(15);
		b.insert(5);
		b.insert(2);
		b.insert(7);
		b.insert(1);
		b.insert(3);
		b.remove(7);
		assertEquals("[1], [2], [3], [5], [10], [15]", b.toString());
		Integer[] m = {10, 5, 2, 1, 3, 15};
		boolean boo[] = {true, true, true, true, true, false}; 
		i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < m.length; k++){
			assertEquals(m[k], i.next());
			assertEquals(boo[k], i.hasNext());
		}
		points += 5;
		
		// Remove node with 1 child from complex tree.	
		b.remove(5);
		assertEquals("[1], [2], [3], [10], [15]", b.toString());
		Integer[] n = {10, 2, 1, 3, 15};
		boolean boo2[] = {true, true, true, true, false}; 
		i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < n.length; k++){
			assertEquals(n[k], i.next());
			assertEquals(boo2[k], i.hasNext());
		}
		points += 5;
		
		// Remove node with 2 children from complex tree.	
		b.remove(10);
		assertEquals("[1], [2], [3], [15]", b.toString());
		Integer[] p = {3, 2, 1, 15};
		boolean boo3[] = {true, true, true, false}; 
		i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < p.length; k++){
			assertEquals(p[k], i.next());
			assertEquals(boo3[k], i.hasNext());
		}
		points += 2;
		
		//removal of non-existing element
		assertFalse(b.remove(7));
		points += 2;
		

	}
	
	public void testRemoveInPreOrderIterator(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();

		// Testing exception throwing on empty tree.
		Iterator<Integer> i = b.preOrderIterator();
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
		
		b.insert(5);
		b.insert(3);
		b.insert(7);
		i = b.preOrderIterator();
		
		// Testing exception throwing when next() has not been
		// called yet.
		assertTrue(i.hasNext());
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
		
		i.next();
		assertTrue(i.hasNext());
		i.next();
		i.remove();
		assertEquals("[5], [7]", b.toString());
		points += 2;
		
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
		
	}
	
	public void testRemoveInInOrderIterator(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();

		// Testing exception throwing on empty tree.
		Iterator<Integer> i = b.iterator();
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
		
		b.insert(5);
		b.insert(3);
		b.insert(7);
		i = b.iterator();
		
		// Testing exception throwing when next() has not been
		// called yet.
		assertTrue(i.hasNext());
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
		
		i.next();
		assertTrue(i.hasNext());
		i.next();
		i.remove();
		assertEquals("[3], [7]", b.toString());
		points += 2;
		
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
	}
	
	public void testPrintPoints(){
		System.out.println(points);
	}
	
	public static void main(String args[]) {
		junit.swingui.TestRunner.run(Testing_with_Iterator_Remove.class);
	}	
	
}