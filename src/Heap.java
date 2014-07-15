
import java.util.*;



public class Heap {
	int last;//range 0 to 4999
	int capacity;
	HeapNode lastNode;
	

	List H;//only store name
	List D;//store the value of that node
	
	/**
	 *  Define a Heap and initialize the root
	 *
	 *  
	 **/	
	
	public Heap(){
		//int iniI = 0;
		Integer ini = new Integer(-1);
		H = new ArrayList();
		D = new ArrayList();
		for (int i = 0; i < 5000; i++){
			D.add(ini);
		}

	last = 0;
	capacity = 5000;
	}
	

	
	public int size(){
		return last;
	}
	
	/**
	 *  Insert an Interger into the heap
	 *
	 *  
	 **/
	public Integer removeMax() {
			Integer max =  max();
		
			H.set(0, H.get(size()-1));
			H.remove(size() -1);
			last --;
			bubbledown();
			//System.out.println("the 0 index is " + H.get(0) + "the last index is " + H.get(size()-1));
			return max;

	}
	

	
	
	public boolean contains(int x){
		if (H.contains(x))
			return true;
		else 
			return false;

	}
	
	public void insertBW(Integer name, Integer value){
		//searchUP[value] = index;
		//System.out.println("index: " + index + " value: " +value);
		last ++;
		D.set(name, value);
		H.add(name);
		bubbleup();
	
	}
	
	
	public void update(int index, Integer newValue){
		//System.out.println("deleting " + D.get(index).BW);
		 int temp = H.indexOf(index);
		// H.remove(temp);
		 D.set(index, newValue);
		 swap(temp, size()-1);
		// H.add(index);
		 bubbleup();
	
	}
	

	private void bubbleup(){
		double index2 = size() - 1;
		//System.out.print(index + "" + " index: " + index2);
		while((int) index2 > 0){
			int parentIndex = (int) (Math.ceil(index2 /2) - 1);
			//System.out.println("parentindex: " + parentIndex + "index: " + index2);

			//if (compare(H.get((int) index2), H.get(parentIndex)) > 0 ){ 
			if (compare(D.get((int) H.get((int) index2)), D.get((int) H.get(parentIndex))) > 0 ){ 
			//System.out.println(H.get((int) index2) + " larger than " + H.get(parentIndex) + " so I swap them" );
			swap((int) index2, parentIndex);
			index2 = parentIndex;
		

			}//                  since we use update(), we may "add" value in the mid of heap
			//else if (compare(D.get((int) H.get((int) index2)), D.get((int) H.get(parentIndex))) <= 0 ){
			else{	
			break;//break as long as the last node is okay since we only add at the end
				
			}
	

		}//end while loop
	}
	
	public void bubbledown(){
		int index = 1;
		while (true){
			
			int child = index*2;
			if (child > size() ){
				//System.out.println("I break due to child == size()");
				break;
				}
			if(child + 1 <= size()){
				
				int result = findMax((int)H.get(child-1), (int)H.get(child));
				if(result == 0){
					child = child + 1;
				}else{
					child = child;
				}

			}
			//System.out.println("the larger index is " + (child-1));
			
			if (compare(D.get((int) H.get(index - 1)), D.get((int) H.get(child -1))) > 0)
				break;
			
			//else if (compare(H.get(index-1), H.get(child -1)) <= 0){
			//else if (compare(D.get((int) H.get(index - 1)), D.get((int) H.get(child -1))) <= 0){
			else{	
			//System.out.println(H.get(index - 1) + " less than " + H.get(child -1) + " so I swap them" );
				//System.out.println(D.get((int) H.get(index - 1)) + " less than " + D.get((int) H.get(child -1)) + " so I swap them" );
				swap(index-1, child-1);
				index = child;
			}
		}
			
			

	}
	
	
	private int findMax(int leftChild, int rightChild){
		if (compare(D.get(leftChild), D.get(rightChild)) <= 0)
			//return rightChild;
			return 0;
		else
			//return leftChild;
			return 1;
	}
	
	
	
	private int compare(Object x, Object y){
		return ((Integer) x).compareTo((Integer) y);
	}

	
	/**
     * Swaps two integers i and j
     * @param i
     * @param j
     */

	private void swap(int i, int j){
		Integer temp = (Integer) H.get(i);
		H.set(i, H.get(j));
		H.set(j, temp);

		
	}



	public Integer max(){
			return (Integer) H.get(0);
		
	}
	
    public String toString() {
        String s = "[";
        for (int i = 0; i < size() ; i++) {
            s += H.get(i);
            if (i != last-1)
                s += ",";
        }
        
        s = s + "]";
        
        System.out.println(s);
        
        return s;
    }

	
	public static void main(String[] args){
		Heap testHeap = new Heap();
		//System.out.println("min value is: " +testHeap.min());
		/*
		testHeap.insert(7);
		testHeap.insert(0);
		testHeap.insert(1);		
	
		testHeap.insert(3);
	
		testHeap.insert(5);
		testHeap.insert(6);
		testHeap.insert(2);
		testHeap.insert(4);
		
		*/
		testHeap.insertBW(0, 11);
		testHeap.insertBW(1, 12);
		testHeap.insertBW(2, 13);
		testHeap.insertBW(3, 14);
		testHeap.insertBW(4, 15);
		testHeap.insertBW(5, 16);
		testHeap.insertBW(6, 17);
		testHeap.insertBW(7, 18);
		//System.out.println("contains or not? " + testHeap.contains(i));
		
	
	
		testHeap.toString();
		System.out.println("size: " +testHeap.size());
		
		System.out.println("max value is: " +testHeap.max());
		
		testHeap.removeMax();
		testHeap.toString();
		System.out.println("max value is: " +testHeap.max());

		testHeap.removeMax();
		testHeap.toString();
		System.out.println("max value is: " +testHeap.max());

		testHeap.removeMax();
		testHeap.toString();
		System.out.println("max value is: " +testHeap.max());
		
		testHeap.removeMax();
		testHeap.toString();
		System.out.println("max value is: " +testHeap.max());
		
		testHeap.removeMax();
		testHeap.toString();
		System.out.println("max value is: " +testHeap.max());
		
		testHeap.removeMax();
		testHeap.toString();
		System.out.println("max value is: " +testHeap.max());

		
	}
	
}

