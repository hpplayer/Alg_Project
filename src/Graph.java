

import java.io.FileNotFoundException;
import java.util.*;



public class Graph {
    private int node = 5000;
    public Edge[][] sparseMatrix;
    public Edge[][] denseMatrix;
    private  ArrayList<Edge> edgeList;
    private int sparseE = 15000;
    private int denseE = (int) (0.2*5000*5000/2);
    private Node[] nodelist = new Node[5000];

    //List<Integer> denseG;
    public void Sparse() {
    	sparseMatrix = new Edge[5000][5000];
    	for (int i = 0; i < 5000; i ++){
    		nodelist[i]  = new Node();
    		nodelist[i].name = i;
    	}
    		for ( int i = 0; i < sparseE; i++ ){
    			for (int v = 0; v < 5000; v++){
    				if (nodelist[v].density < 6){
    					int u = (int)(Math.random()*5000 );
    					while( v == u || sparseMatrix[v][u] != null){
    						u = (int)(Math.random()*5000 );
    					}
    					if(nodelist[u].density < 6){
    						int weight = (int)(Math.random()*5000 +1 );	
    						Edge temp = new Edge(v, u, weight);
    						sparseMatrix[v][u] = temp;
    						Edge temp2 = new Edge(u, v, weight);
    						sparseMatrix[u][v] = temp2;
    						nodelist[v].density ++;
    						nodelist[u].density ++;
    					}
    				}
    			}
    		}
    		 
	}//end sparse
    	     	
    
    public ArrayList<Edge> EdgeInsparse(int i){
    	edgeList = new ArrayList<Edge>();
    	for(int j = 0; j < 5000; j++){
    		if (sparseMatrix[i][j] != null){
    			edgeList.add(sparseMatrix[i][j]);
    		}
    		    	
    	}
    	return edgeList;
    		
    }
    
    public ArrayList<Edge> EdgeIndense(int i){
    	edgeList = new ArrayList<Edge>();
    	for(int j = 0; j < 5000; j++){
    		if (denseMatrix[i][j] != null){
    			edgeList.add(denseMatrix[i][j]);
    		}
    		    	
    	}
    	return edgeList;
    		
    }
    
    public void Dense() {
 
    	    	denseMatrix = new Edge[5000][5000];
    	    	for (int i = 0; i < 5000; i ++){
    	    		nodelist[i]  = new Node();
    	    		nodelist[i].name = i;
    	    	}
    	    		for ( int i = 0; i < denseE; i++ ){
    	    			for (int v = 0; v < 5000; v++){
    	    				if (nodelist[v].density < 1000){
    	    					int u = (int)(Math.random()*5000 );
    	    					while( v == u || denseMatrix[v][u] != null){
    	    						u = (int)(Math.random()*5000 );
    	    					
    	    					}
    	    					if(nodelist[u].density < 1000){
    	    						int weight = (int)(Math.random()*5000 +1 );	
    	    						Edge temp = new Edge(v, u, weight);
    	    						denseMatrix[v][u] = temp;
    	    						Edge temp2 = new Edge(u, v, weight);
    	    						denseMatrix[u][v] = temp2;
    	    						nodelist[v].density ++;
    	    						nodelist[u].density ++;
    	    					}
    	    				}
    	    			}
    	    		}
    	    		 
    		}//end sparse
    
    public int randomGenerator(){
        int random = (int)(Math.random()*5000);
        return random;
    }
   
    public static void main(String[] args) {
        Graph test = new Graph();

        
        long startTime = System.nanoTime();
       test.Sparse();

    	long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("Creation of sparse graph costs: " + duration/1000000000.0 + " seconds");
        
		
	    long startTime2 = System.nanoTime();

	      test.Dense();

    	long endTime2 = System.nanoTime();
		long duration2 = (endTime2 - startTime2);
		System.out.println("Creation of dense graph costs: " + duration2/1000000000.0 + " seconds");
        
    
    }
}