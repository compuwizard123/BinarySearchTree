import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A BinarySearchTree implementation class
 * @author risdenkj
 * 
 */

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
	private BinaryNode root;
	private int modCount = 0;
	
	/**
	 * Constructs a BinarySearchTree
	 * Sets the root to null
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Checks if the BinarySearchTree has no nodes
	 * 
	 * @return true if the BinarySearchTree has no nodes; false if has nodes
	 */
	public boolean isEmpty() {
		return root == null ? true : false;
	}
	
	/**
	 * Default iterator method returns the nodes in order
	 * 
	 * @return an iterator to traverse the nodes in order 
	 */
	public Iterator<T> iterator() {
		return new inOrderTreeIterator(root);
	}
	
	/**
	 * Iterator that returns the nodes in preorder
	 * 
	 * @return an iterator to traverse the nodes in preorder
	 */
	public Iterator<T> preOrderIterator() {
		return new preOrderTreeIterator(root);
	}
	
	/**
	 * Method that returns an ArrayList representation of the BinarySearchTree
	 * 
	 * @return ArrayList with the nodes in order
	 */
	public ArrayList<T> toArrayList() {
		if(root == null) { 
			return new ArrayList<T>();
		}
		return root.toArrayList(new ArrayList<T>());
	}
	
	/**
	 * Method that returns an Array representation of the BinarySearchTree
	 * 
	 * @return Array with the nodes in order
	 */
	public Object[] toArray() {
		return toArrayList().toArray();
	}

	/**
	 * Method to determine the height of the BinarySearchTree
	 * 
	 * @return height of the BinarySearchTree; -1 if BinarySearchTree is empty
	 */
	public int height(){
		return !isEmpty() ? root.height() : -1;
	}
	
	/**
	 * Method that returns a String representation of the BinarySearchTree
	 * 
	 * @return string in [element, element] format with the BinarySearchTree BinaryNodes in order 
	 */
	public String toString() {
		String temp = "";
		if(root == null) {
			return temp;
		}
		Iterator<T> i = iterator();
		while(i.hasNext()) {
			temp += "[" + i.next() + "]";
			if(i.hasNext()) {
				temp += ", ";
			}
		}
		return temp;
	}
	
	/**
	 * Method to determine the size of the BinarySearchTree
	 * 
	 * @return size of the BinarySearchTree; 0 if BinarySearchTree is empty
	 */
	public int size() {
		return !isEmpty() ? root.size() : 0;
	}
	
	/**
	 * Returns a boolean value representing whether the tree was modified
	 * or not. The item argument must be of the same type that was used 
	 * when initiating the BinarySearchTree class.
	 *
	 * @param item	the item to be inserted into the BinarySearchTree
	 * @return      true if the tree was modified, false if not
	 * @exception	IllegalArgumentException if item is null
	 */
	public boolean insert(T item) {
		if(item == null) {
			throw new IllegalArgumentException();
		}
		if(root != null) {
			return root.insert(item);
		} else {
			root = new BinaryNode(item);
			modCount++;
			return true;
		} 
	}
	
	/**
	 * TODO JavaDoc
	 * 
	 * @param item	
	 * @return	
	 */
	public boolean remove(T item) {
		modWrapper mod = new modWrapper();
		if(item == null) {
			throw new IllegalArgumentException();
		}
		if(root != null) {
			root = root.remove(item, mod);
		}
		return mod.getValue();
	}
	
	/**
	 * A BinaryNode Implementation Class
	 * @author risdenkj
	 * 
	 */
	private class BinaryNode {
		private T element;
		private BinaryNode left,right;
		
		/**
		 * Constructs a BinaryNode
		 * Sets the left and right children to null
		 * 
		 * @param initelement The element that becomes the BinaryNode
		 */
		public BinaryNode(T initelement) {
			element = initelement;
			left = null;
			right = null;
		}
		
		/**
		 * Returns the string representation of the current BinaryNode
		 * 
		 * @return string of the current BinaryNode
		 */
		public String toString() {
			return element.toString();
		}
		
		/**
		 * Recursive method that returns an ArrayList of the BinaryNode and its children
		 * 
		 * @param list the ArrayList that elements should be added onto
		 * @return ArrayList of the BinaryNode and its children
		 */
		public ArrayList<T> toArrayList(ArrayList<T> list) {
			if(left != null) {
				left.toArrayList(list);
			}
			list.add(element);
			if(right != null) {
				right.toArrayList(list);
			}
			return list;
		}
		
		/**
		 * Method that determines the height of the BinaryNode
		 * 
		 * @return height of the BinaryNode
		 */
		public int height() {
			int leftheight = 0, rightheight = 0;
			if(left != null) {
				leftheight = 1 + left.height();
			}
			if(right != null) {
				rightheight = 1 + right.height();
			}
			if(leftheight > rightheight) {
				return leftheight;
			} else { 
				return rightheight;
			}
		}
		
		/**
		 * Method that determines the size of the BinaryNode 
		 * 
		 * @return size of the BinaryNode
		 */
		public int size() {
			int size = 1;
			if(left != null) {
				size += left.size();
			}
			if(right != null) {
				size += right.size();
			}
			return size;
		}
		
		/**
		 * Inserts the provided element as a child to the BinaryNode
		 * The item becomes a left child if less than current BinaryNode
		 * The item becomes a right child if greater than current BinaryNode
		 * If the insert is successful sets the modification boolean flag to true
		 * 
		 * @param item item to be inserted as a child to the BinaryNode
		 * @return true if insert successful; false if not
		 */
		public boolean insert(T item) {
			if(element.compareTo(item) < 0) {
				if(right != null) {
					return right.insert(item);
				} else {
					right = new BinaryNode(item);
					modCount++;
					return true;
				}
			} else if(element.compareTo(item) > 0){
				if(left != null) {
					return left.insert(item);
				} else {
					left = new BinaryNode(item);
					modCount++;
					return true;
				}
			} else {
				return false;
			}
		}
		
		/**
		 * TODO JavaDoc
		 * 
		 * @param item
		 * @param mod
		 * @return
		 */
		public BinaryNode remove(T item, modWrapper mod) {
			if(left == null && right == null) {
				if(item.compareTo(element) != 0) {
					throw new NoSuchElementException();
				}
				mod.setTrue();
				return null;
			} else if(right == null) {
				if(item.compareTo(element) < 0) {
					left = left.remove(item, mod);
				} else if(item.compareTo(element) > 0) {
					throw new NoSuchElementException();
				}
				mod.setTrue();
				return left;
			} else if(left == null) {
				if(item.compareTo(element) > 0) {
					right = right.remove(item, mod);
				} else if(item.compareTo(element) < 0) {
					throw new NoSuchElementException();
				}
				mod.setTrue();
				return right;
			} else {
				if(item.compareTo(element) > 0) {
					right = right.remove(item,mod);
				} else if(item.compareTo(element) < 0) {
					left = left.remove(item, mod);
				} else {
					T temp = element;
					BinaryNode largestChildNode = findLargestChild(left);
					element = largestChildNode.element;
					largestChildNode.element = temp;
					left = left.remove(temp, mod);
				}
				mod.setTrue();
				return this;
			}
		}
		
		/**
		 * TODO JavaDoc
		 * 
		 * @param node
		 * @return
		 */
		public BinaryNode findLargestChild(BinaryNode node) {
			while(node.right != null) {
				node = node.right;
			}
			return node;
		}
	}
	
	/**
	 * Creates a wrapper for the mod boolean
	 * @author risdenkj
	 * TODO JavaDoc
	 */
	private class modWrapper {
		private boolean mod = false;

		public void setTrue() {
			this.mod = true;
		}
		
		public void setFalse() {
			this.mod = false;
		}

		public boolean getValue() {
			return mod;
		}
	}
	
	/**
	 * A preorder BinarySearchTree iterator implementation class
	 * @author risdenkj
	 * 
	 */
	private class preOrderTreeIterator implements Iterator<T> {
		private Stack<BinaryNode> list = new Stack<BinaryNode>();
		private int mod;
		
		/**
		 * Constructs a preOrderTreeIterator
		 * Sets the modification boolean flag to false
		 * 
		 * @param node BinaryNode to start the iterator from
		 */
		public preOrderTreeIterator(BinaryNode node) {
			if(node != null) {
				list.push(node);
				this.mod = modCount;
			}
		}
		
		/**
		 * Checks if there is another element in the BinarySearchTree that hasn't been accessed
		 * 
		 * @return true if there is another element to return; false if not
		 */
		public boolean hasNext() {
			if(!list.empty()) {
				return true;
			}
			return false;
		}

		/**
		 * Method that returns the next BinaryNode element from the BinarySearchTree
		 * 
		 * @return BinaryNode element in the BinarySearchTree
		 * @exception ConcurrentModificationException if the BinarySearchTree was modified after initializing the iterator
		 * @exception NoSuchElementException if there are no more elements to return
		 */
		public T next() {
			if(this.mod != modCount) {
				throw new ConcurrentModificationException();
			}
			BinaryNode item = null;
			
			if(!list.empty()) {
				item = list.pop();
			} else {
				throw new NoSuchElementException();
			}
			
			if(item.right != null) {
				list.push(item.right);
			}
			if(item.left != null) {
				list.push(item.left);
			}
			return item.element;
		}
		
		/**
		 * Removes an element from the BinarySearchTree
		 * 
		 * @exception UnsupportedOperationException
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	/**
	 * An in order BinarySearchTree iterator implementation class
	 * @author risdenkj
	 * 
	 */
	private class inOrderTreeIterator implements Iterator<T> {
		private Stack<BinaryNode> list = new Stack<BinaryNode>();
		private int mod;
		
		/**
		 * Constructs an inOrderTreeIterator
		 * Sets the modification boolean flag to false
		 * 
		 * @param node BinaryNode to start the iterator from
		 */
		public inOrderTreeIterator(BinaryNode node) {
			this.mod = modCount;
			checkLeft(node);
		}
		
		/**
		 * Checks if there is another element in the BinarySearchTree that hasn't been accessed
		 * 
		 * @return true if there is another element to return; false if not
		 */
		public boolean hasNext() {
			if(!list.empty()) {
				return true;
			}
			return false;
		}
		
		/**
		 * Method that returns the next BinaryNode element from the BinarySearchTree
		 * 
		 * @return BinaryNode element in the BinarySearchTree
		 * @exception ConcurrentModificationException if the BinarySearchTree was modified after initializing the iterator
		 * @exception NoSuchElementException if there are no more elements to return
		 */
		public T next() {
			if(this.mod != modCount) {
				throw new ConcurrentModificationException();
			}
			BinaryNode item = null;
			if(list.empty()) {
				throw new NoSuchElementException();
			}
			item = list.pop();
			checkLeft(item.right);
			return item.element;
		}

		/**
		 * Checks if the provided BinaryNode has a left child
		 * 
		 * @param node node to to check if it has a left child
		 */
		public void checkLeft(BinaryNode node) {
			while(node != null) {
				list.push(node);
				node = node.left;
			}
		}
		
		/**
		 * Removes an element from the BinarySearchTree
		 * 
		 * @exception UnsupportedOperationException
		 */
		public void remove() {
			throw new UnsupportedOperationException();		
		}
	}
}
