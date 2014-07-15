import java.util.Arrays;


public class Finaltest{
	private final Node[] nodelist;
	private int BW = 0;
	
	private int[] sourceNum;
	private int[] DestiNum;
	
	private static Graph sparse;
	private static Graph dense;
	
	private Heap heap;

	
	
	public Finaltest(String a){
		nodelist = new Node[5000];
		heap = new Heap(); 
		sourceNum = new int[]{113, 4012, 345, 1, 50};
		DestiNum = new int[]{3501,2145,3301,4999,2500};
		int countEdge = 0;
		
		
		if (a.equals("sparse") ){
		sparse = new Graph();
		sparse.Sparse();
		for (int i = 0; i < 5000; i ++){
	
			nodelist[i]  = new Node();
			nodelist[i].name = i;
			nodelist[i].Dad = null;
			nodelist[i].BW = -1;//-1 means the node is unseen since we dont have negative bandwidth
			nodelist[i].edges =  sparse.EdgeInsparse(i);	
			nodelist[i].NumOfedges = nodelist[i].edges.size();
		//	if(nodelist[i].NumOfedges != 6){
			//System.out.println(nodelist[i].NumOfedges);
			//}

		}

	
		}//end if 
		
		if (a.equals("dense") ){
			dense = new Graph();
			dense.Dense();
			for (int i = 0; i < 5000; i ++){
				nodelist[i]  = new Node();
				nodelist[i].name = i;
				nodelist[i].Dad = null;
				nodelist[i].BW = -1;//-1 means the node is unseen since we dont have negative bandwidth
				nodelist[i].edges =  dense.EdgeIndense(i);		
				nodelist[i].NumOfedges = nodelist[i].edges.size();
				
			}
			
	
			}//end if 
			
		
		//nodelist[sourceNum].BW = 0;//the bandwidth of path from s to s is 0

	}
	


	public void Sreset(){
		for (int i = 0; i < 5000; i ++){
			nodelist[i].name = i;
			nodelist[i].Dad = null;
			nodelist[i].BW = -1;
			nodelist[i].edges =  sparse.EdgeInsparse(i);	
			nodelist[i].NumOfedges = nodelist[i].edges.size();
			
		}
	}
	
	public void Dreset(){
		for (int i = 0; i < 5000; i ++){
			nodelist[i].name = i;
			nodelist[i].Dad = null;
			nodelist[i].BW = -1;
			nodelist[i].edges =  dense.EdgeIndense(i);	
			nodelist[i].NumOfedges = nodelist[i].edges.size();
			
		}
	}
	

	
	public void testDijNo(int Order, int sourceNum, int DestiNum, Node[] nodelist){
	    long startTime5 = System.nanoTime();
		Dijstra_without_heap dijNoheap = new Dijstra_without_heap(sourceNum, DestiNum, nodelist);
		dijNoheap.dijstra();
		dijNoheap.calpath();
	    long endTime5 = System.nanoTime();
	    long duration5 = endTime5 - startTime5;
		//System.out.println(Order + " running of DijWH cost: " + duration5/1000000.0 + " ms");
		
	}
	
	public void testDijyes(int Order, int sourceNum, int DestiNum, Node[] nodelist){
	Dijstra_with_heap dijwithheap = new Dijstra_with_heap(sourceNum, DestiNum, nodelist);
	dijwithheap.NEWdijstra();
    long startTime5 = System.nanoTime();
	dijwithheap.calpath();
    long endTime5 = System.nanoTime();
    long duration5 = endTime5 - startTime5;
	//System.out.println(Order + " running of DijH cost: " + duration5/1000000.0 + " ms");
	}
	
	
	

	//public void testKruskal(String a){
	public void testKruskal(int sourceNum1, int DestiNum1, Node[] nodelist, String a){
	    long startTime5 = System.nanoTime();
		
		Kruskal kurskal = new Kruskal(sourceNum1, DestiNum1, nodelist);
		
		kurskal.update();

		kurskal.calpath();
		long endtTime5 = System.nanoTime();
		long duration5 = endtTime5 - startTime5;

	 //  System.out.println("1 running of kruskal costs: " + duration5/1000000.0 + " ms");
	    
	   if( a.equals("sparse")){
		   Sreset();
	   }
	   else if( a.equals("sparse")){
		   Dreset();
	   }
		
		for ( int i = 0; i < 4; i++){
			  long startTime6 = System.nanoTime();
			  Kruskal kurskal2 = new Kruskal(sourceNum[i+1], DestiNum[i+1], nodelist);
			    kurskal2.update2( kurskal.T);
			    kurskal2.calpath();
			    long endTime6 = System.nanoTime();
			    long duration6 = endTime6 - startTime6;
			    if( a.equals("sparse")){
					   Sreset();
				   }
				else if( a.equals("sparse")){
					   Dreset();
				}
		    //	System.out.println(i+2 + " running of kruskal costs: " + duration6/1000000.0 + " ms");
		}
	  
    	
    	
	}
	
	


	
	public static void main(String[] args) throws CloneNotSupportedException, InterruptedException  {
		Finaltest test = new Finaltest("sparse");
		 Thread.sleep(4000);
		 
		for(int i = 0; i < 5; i ++){
			test.testDijNo((i+1), test.sourceNum[i], test.DestiNum[i], test.nodelist);
			test.Sreset();
		}

    	System.out.println("");
    	
		for(int i = 0; i < 5; i ++){
			test.testDijyes((i+1), test.sourceNum[i], test.DestiNum[i], test.nodelist);
			test.Sreset();
		}

    	System.out.println("");
    	
    	  test.testKruskal(test.sourceNum[0], test.DestiNum[0], test.nodelist, "sparse");
    	
 
	  
	
	    System.out.println("--------------------------------------------------------------------------------done on sparse, begin dense--------------------------------------");
		Finaltest test2 = new Finaltest("dense");
	    System.out.println("");
	    
	  Thread.sleep(4000);
	    
	    for(int i = 0; i < 5; i ++){
			test2.testDijNo((i+1), test2.sourceNum[i], test2.DestiNum[i], test2.nodelist);
			test2.Dreset();
		}

    	System.out.println("");
    	
		for(int i = 0; i < 5; i ++){
			test2.testDijyes((i+1), test2.sourceNum[i], test2.DestiNum[i], test2.nodelist);
			test2.Dreset();
		}

    	System.out.println("");
    	
    	  test2.testKruskal(test2.sourceNum[0], test2.DestiNum[0], test2.nodelist, "dense");
	}
	
}
