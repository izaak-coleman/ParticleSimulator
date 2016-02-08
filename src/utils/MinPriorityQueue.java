package utils;

import java.util.ArrayList;

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
    	heap.add(null);		// discard 0th position from heap
        // Create a dynamic array
				// parent of a[n] is a[n/2]
				// children of a[n] are a[2*n+1]
	}
    
    public void createHeap(List<T> unorderedList){
    	addNewEvents(1, unorderedList);
    	int middle = heap.size() / 2;
    	for(int i = middle; i <= 1; i--){
    		reorderTree(i);			// sort for each parent up to root
    	}
    }
    
    private void addNewEvents(int start, List<T> unorderedList){
    	for( T event : unorderedList) {
    		heap.add(event);
    	}
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
    	int currentIndex = heap.size();
    	int parIndex = currentIndex/2;
    	while(heap.get(parIndex).compareTo(heap.get(currentIndex)) == 1 && currentIndex > 1){
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
    	
    	heap.set(1, heap.get(heap.size()));	// set the rightmost leaf to root
    	heap.remove(heap.size());			// remove element
    	
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
    	int left = positionOfMovingNode * 2;
    	int right = positionOfMovingNode * 2 + 1;
    	int lowestPriorityIndex = positionOfMovingNode;
    	
    	
    	// Get the the data elements
    	T movingNode = heap.get(positionOfMovingNode);
    	// get movingNodes left child
    	T leftChild = heap.get(left);
    	// get moving nodes right child
    	T rightChild = heap.get(right);
    	
    	T lowestPriority;		// out of moving, left and right
    	
    	
    	if (left <= heap.size() && leftChild.compareTo(movingNode) == -1) {
    		lowestPriorityIndex = left;
    	}
    
    	// Update priority
    	lowestPriority = heap.get(lowestPriorityIndex);
    	
    	if (right <= heap.size() && rightChild.compareTo(lowestPriority) == -1) {
    		lowestPriorityIndex = right;
    	}
    	// Update Priority
    	lowestPriority = heap.get(lowestPriorityIndex);
    	
    	if(lowestPriority != movingNode){
    		
    		// Swap elements
    		// After swap, the highest priority child will be in the parent position
    		T store = movingNode;
    		heap.set(positionOfMovingNode, lowestPriority);
    		heap.set(lowestPriorityIndex, store);
    		reorderTree(lowestPriorityIndex);
    	}
    	
    	
    	
//    	if (movingNode.time() > leftChild.time()) {
//    		int newLocation = positionOfMovingNode * 2;
//    		swap(movingNode, leftChild);
//    		reorderTree(newLocation);
//    	}
//    	else (movingNode.time() > rightChild.time()) {
//    		int newLocation = (positionOfMovingNode * 2) + 1;
//    		swap(movingNode, rightChild);
//    		reorderTree(newLocation);
//    	}
//    	
//    	
//    	
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
