package utils;

import java.util.ArrayList;

import simulation.Collision;
import simulation.Event;

public class MinPriorityQueue<T extends Comparable<T>> {
		
		/** Implementing dynamic array as binary heap, not given 
		 **/
		private ArrayList<T> heap;

    /**
     * Creates an empty queue.
     */
    public MinPriorityQueue() {		// overload this constructor??
    	heap = new ArrayList<T>();
    	heap.add(null);
    	// discard 0th position from heap
        // Create a dynamic array
				// parent of a[n] is a[n/2]
				// children of a[n] are a[2*n+1]
	}
    
    public void createHeap(Iterable <Collision> unorderedList){
    	addNewEvents(1, unorderedList);
    	int middle = heap.size() / 2;
    	for(int i = middle; i <= 1; i--){
    		reorderTree(i);			// sort for each parent up to root
    	}
    }
    
	private void addNewEvents(int start, Iterable <Collision> unorderedList){
    	for( Collision event : unorderedList) {
    		heap.add((T) event);
    	}
    }
    
    public double timeOfLastEvent(){
    	Comparable<T> lastEvent = heap.get(heap.size()-1);
    	return (double) ((Event) lastEvent).time();
    }

    /**
     * Returns the number of elements currently in the queue.
     */
    public int size() {
        return heap.size();
    }
    
    
    /**
     * Adds elem to the queue.
     */
    public void add(T elem) {
    	heap.add(elem);
    	int currentIndex = heap.size()-1;
    	int parIndex;
    	if(currentIndex == 1){
    		parIndex = currentIndex;
    	} else {
    		parIndex = currentIndex/2;
    	}
    	//System.out.print(currentIndex);
    	//System.out.print(parIndex);
    	while(currentIndex > 1 && heap.get(parIndex).compareTo(heap.get(currentIndex)) == 1){
    		T parent = heap.get(parIndex);
    		heap.set(parIndex, heap.get(currentIndex));
    		heap.set(currentIndex, parent);
    		currentIndex = parIndex;
    		parIndex = currentIndex/2;
    	}
    }
    
    

    /**
     * Removes, and returns, the element at the front of the queue.
     */
    public T remove() {
    	
    	T nextEvent = heap.get(1);		// store the next event
    	
    	heap.set(1, heap.get(heap.size()-1));	// set the rightmost leaf to root
    	System.out.println("Removed Element");
    	heap.remove(heap.size()-1);			// remove element
    	
    	reorderTree(1);						// reorder tree from top down
		return nextEvent;					// return highest priority event
		
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
    }
    private void reorderTree(int positionOfMovingNode){
    	// define the index of the parent, and two children
    	// System.out.print(" Passed in ");
    	// System.out.print(positionOfMovingNode);
    	int left = positionOfMovingNode * 2;
    	int right = positionOfMovingNode * 2 + 1;
    	int lowestPriorityIndex = positionOfMovingNode;
    	/**
    	System.out.print(" Left is  ");
    	System.out.print(left);
    	System.out.print(" Right is ");
    	System.out.print(right);
    	System.out.print(" LPI is ");
    	System.out.print(lowestPriorityIndex);
    	**/
    	
    	// Get the the data elements
    	T movingNode = heap.get(positionOfMovingNode);
    	// get movingNodes left child
    	T lowestPriority = heap.get(positionOfMovingNode);		// out of moving, left and right
    	
    	if (left < heap.size()) {
    		T leftChild = heap.get(left);
    		if (leftChild.compareTo(movingNode) == -1) {
    			lowestPriorityIndex = left;
    			lowestPriority = heap.get(lowestPriorityIndex);
    		}
    	}
    	
    	if (right < heap.size()) {
    		// get moving nodes right child
    		T rightChild = heap.get(right);
    		if (rightChild.compareTo(lowestPriority) == -1) {
    			lowestPriorityIndex = right;
    			lowestPriority = heap.get(lowestPriorityIndex);
    		}
    	}
    	
    	if(lowestPriority != movingNode){
    		
    		// Swap elements
    		// After swap, the highest priority child will be in the parent position
    		T store = movingNode;
    		heap.set(positionOfMovingNode, lowestPriority);
    		heap.set(lowestPriorityIndex, store);
    		reorderTree(lowestPriorityIndex);
    	}
    }

    /**
     * Returns true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() <= 1;
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
