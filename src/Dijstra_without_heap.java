import java.util.ArrayList;


public class Dijstra_without_heap {
	private int BW = 0;
	private Node[] nodelist;
	private Node[] test;
	private int sourceNumber;
	private int DestiNumber;
	private ArrayList<Node> fringelist;
	private Heap heap;
	
	public Dijstra_without_heap(int a, int b, Node[] c){//a = source, b = desti
		nodelist = new Node[5000];
		//nodelist = c;
		System.arraycopy(c, 0, nodelist, 0, c.length);
		heap = new Heap(); 
		fringelist = new ArrayList<Node>();
		nodelist[a].BW = 1000000000;
		sourceNumber = a; //range 0 to 5000
		DestiNumber = b;//range 0 to 5000
	}
	
	public void dijstra(){
		Edge temp;
		int nodeNum;
		Node sourceNode = nodelist[sourceNumber];
		
		for (int i = 0; i < sourceNode.NumOfedges; i++){
				temp = sourceNode.edges.get(i);
				nodeNum = temp.endPointW();
				nodelist[nodeNum].Dad = sourceNode;
				nodelist[nodeNum].BW = temp.weight();
				fringelist.add(nodelist[nodeNum]);
		}
	
		
		while (fringelist.size() != 0){
		
			Node Maxfringe = removeMaxfringe();
				for ( int j = 0; j <Maxfringe.NumOfedges; j++){
					
					int tempNodeNum = Maxfringe.edges.get(j).endPointW();
						if (nodelist[tempNodeNum].BW == -1){//when node is unseen
							
							nodelist[tempNodeNum].Dad = Maxfringe;
		
							nodelist[tempNodeNum].BW = returnMin(Maxfringe.BW, Maxfringe.edges.get(j).weight());
							fringelist.add(nodelist[tempNodeNum]);
			
						}//end case1 if
						else if (fringelist.contains(nodelist[tempNodeNum]) && nodelist[tempNodeNum].BW < returnMin(Maxfringe.BW, Maxfringe.edges.get(j).weight())){
						
							 fringelist.remove(nodelist[tempNodeNum]);
							 nodelist[tempNodeNum].Dad = Maxfringe;
							 nodelist[tempNodeNum].BW = returnMin(Maxfringe.BW, Maxfringe.edges.get(j).weight());
							 fringelist.add( nodelist[tempNodeNum]);
						}//end case2 if 
				}//end for loop
		
		}//end while loop
			System.out.println("Done Dijstra algorithm without heap on the calculation from source node " + sourceNumber + " to destination node " + DestiNumber + " !");
		
	}
	
	public int calpath(){
		int numofnodes = 0;
		Node temp = nodelist[DestiNumber];
		int totallength = temp.BW;
		while(temp.name != sourceNumber){
			if (totallength > temp.BW){
			totallength = temp.BW;}
			temp = temp.Dad;	
			numofnodes++;
		}
		System.out.println("The Bandwidth is " + totallength + ", through " + numofnodes + " nodes" );
		return totallength;
	}
	
	public int returnMin(int a, int b){

		if (compare(a, b) < 0)
			return a;
		else 
			return b;
	}
	
	public Node removeMaxfringe(){
		Node maxNode;
		maxNode = fringelist.get(0);
		int markNum = 0;
		for ( int i = 0; i < fringelist.size(); i++){
			if (compare(maxNode.BW, fringelist.get(i).BW) <= 0){//update maxNode
				maxNode = fringelist.get(i);
				markNum = i;
			}
		}
		
		fringelist.remove(markNum);
		return maxNode;
	}
	
	public int compare(Object x, Object y){
		return ((Integer) x).compareTo((Integer) y);
	}
	
	
	
}
