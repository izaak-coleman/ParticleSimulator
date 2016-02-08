package utils;

import java.util.ArrayList;

public class MinPriorityQueue<T extends Comparable<T>> {
		
		/** Implementing dynamic array as binary heap, not given 
		 **/
		// private static final int INITIAL_CAPACITY = 1;
		private ArrayList<Comparable<T>> heap = new ArrayList<Comparable<T>>;
		//private int size;

    /**
     * Creates an empty queue.
     */
    public MinPriorityQueue() {
        // Create a dynamic array
				// parent of a[n] is a[n/2]
				// children of a[n] are a[2*n+1]
		}

    /**
     * Returns the number of elements currently in the queue.
     */
    public int size() {
        return heap.length();
    }
    
    /**
     * Adds elem to the queue.
     */
    public void add(T elem) {
				// node is of time [event(time)]
				// queue[size+1] = elem
				// current = queue[size+1]
				// parent = queue[2*(size+1)]
				// while(elem.T > parent.time){
				//			switch(current, parent)
				// }
				// while time is greater than the time of the node's parents
				// swap T with its parent 	
    }

    /**
     * Removes, and returns, the element at the front of the queue.
     */
    public T remove() {
				// index = 1
        // nextEvent = heap[1]
				// reorganize tree
				// heap[1] = heap[heap.size()]
				// heap.remove(heap.size())
				// movingLeaf = heap[1]
				// while leaf is greater than child 
				// while (movingLeaf.time() > heap[2*index] ){ 
							
			//	}
			//  while (movingLeaf.time() > heap[2*index+1] {}
			return nextEvent;
    }

    /**
     * Returns true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

		/** Functions to manage dynamic array, Added by ZVIC
				Consider adding private booleanisTooBig or isFull	
	 	

		private boolean isFull(){
			return size == heap.length();
		}

		private void increaseCapacity(){
		}
		**/
}
