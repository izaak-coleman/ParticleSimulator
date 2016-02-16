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
    public MinPriorityQueue() {		
    	heap = new ArrayList<T>();
    	heap.add(null);
	}
    
    public void createHeap(Iterable <Collision> unorderedList){
    	addNewEvents(1, unorderedList);
    	int middle = heap.size() / 2;
    	for(int i = middle; i <= 1; i--){
    		reorderTree(i);			// sort for each parent up to root
    	}
    }
    
	private void addNewEvents(int start, Iterable <Collision> unorderedList){
		int count = 0;
    	for( Collision event : unorderedList) {
			heap.add((T) event);
			System.out.print((T)event);
			count++;
    	}
    	System.out.println(" COUTNSADFKLASJFLKSJADFLKJSDFLKJSDLFKJS");
    	System.out.print(count);
    }
    
    public double timeOfLastEvent(){
    	Comparable<T> lastEvent = heap.get(heap.size()-1);
    	//System.out.println("Last event pulled from timeofLastEvent: ");
    	
    	//System.out.print(((Event) lastEvent).time());
    	return (double) ((Event) lastEvent).time();
    }
    
    public void print(){
    	System.out.println("SOH ===============================================");
    	for(Comparable<T> e: heap){
    		if(e == null){
    			
    		} else {
    		//System.out.print(e);
    		System.out.print(((Event)e).time());
    		System.out.println(" ");
    		}
    	}
    	System.out.print("EOH  +++++++++++++++++++++++++++++++++++++++++++++++++");
    	System.out.println(" ");
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
    	
    	heap.remove(heap.size()-1);			// remove element 	
    	reorderTree(1);						// reorder tree from top down
		return nextEvent;					// return highest priority event
    }
    private void reorderTree(int positionOfMovingNode){
    	// define the index of the parent, and two children
    	int left = positionOfMovingNode * 2;
    	int right = positionOfMovingNode * 2 + 1;
    	int lowestPriorityIndex = positionOfMovingNode;
    	
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
}
