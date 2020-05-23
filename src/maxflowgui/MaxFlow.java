/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxflowgui;

/**
 *
 * @author Mahmut
 */

// Java program for implementation of Ford Fulkerson algorithm
import java.util.*;
        import java.lang.*;
        import java.io.*;
        import java.util.LinkedList;

public class MaxFlow{

	static final int V = 6; //Number of vertices in graph 

	/* Returns true if there is a path from source 's' to sink 
	't' in residual graph. Also fills parent[] to store the 
	path */
	boolean bfs(int rGraph[][], int s, int t, int parent[]) 
	{ 
		// Create a visited array and mark all vertices as not 
		// visited 
		boolean visited[] = new boolean[V]; 
		for(int i=0; i<V; ++i) 
			visited[i]=false; 

		// Create a queue, enqueue source vertex and mark 
		// source vertex as visited 
		LinkedList<Integer> queue = new LinkedList<Integer>(); 
		queue.add(s); 
		visited[s] = true; 
		parent[s]=-1; 

		// Standard BFS Loop 
		while (queue.size()!=0) 
		{ 
			int u = queue.poll(); 

			for (int v=0; v<V; v++) 
			{ 
				if (visited[v]==false && rGraph[u][v] > 0) 
				{ 
					queue.add(v); 
					parent[v] = u; 
					visited[v] = true; 
				} 
			} 
		} 

		// If we reached sink in BFS starting from source, then 
		// return true, else false 
		return (visited[t] == true); 
	} 

	// Returns tne maximum flow from s to t in the given graph 
	int fordFulkerson(int graph[][], int s, int t) 
	{ 
		int u, v; 

		// Create a residual graph and fill the residual graph 
		// with given capacities in the original graph as 
		// residual capacities in residual graph 

		// Residual graph where rGraph[i][j] indicates 
		// residual capacity of edge from i to j (if there 
		// is an edge. If rGraph[i][j] is 0, then there is 
		// not) 
		int rGraph[][] = new int[V][V]; 

		for (u = 0; u < V; u++) 
			for (v = 0; v < V; v++) 
				rGraph[u][v] = graph[u][v]; 

		// This array is filled by BFS and to store path 
		int parent[] = new int[V]; 

		int max_flow = 0; // There is no flow initially 

		// Augment the flow while tere is path from source 
		// to sink 
		while (bfs(rGraph, s, t, parent)) 
		{ 
			// Find minimum residual capacity of the edhes 
			// along the path filled by BFS. Or we can say 
			// find the maximum flow through the path found. 
			int path_flow = Integer.MAX_VALUE; 
			for (v=t; v!=s; v=parent[v]) 
			{ 
				u = parent[v]; 
				path_flow = Math.min(path_flow, rGraph[u][v]); 
			} 

			// update residual capacities of the edges and 
			// reverse edges along the path 
			for (v=t; v != s; v=parent[v]) 
			{ 
				u = parent[v]; 
				rGraph[u][v] -= path_flow; 
				rGraph[v][u] += path_flow; 
			} 

			// Add path flow to overall flow 
			max_flow += path_flow; 
		} 

		// Return the overall flow 
		return max_flow; 
	} 

    //Driver program to test above functions
    public static int[][] findGraph (String string)
    {
        int[][] temp;
        String info = "Musluk A --> Kaynak musluk\r\n" +
                "B - 11\r\n" +
                "C - 12\r\n" +
                "*\r\n" +
                "Musluk B \r\n" +
                "D - 12\r\n" +
                "*\r\n" +
                "Musluk C \r\n" +
                "B - 1\r\n" +
                "E - 11\r\n" +
                "*\r\n" +
                "Musluk D \r\n" +
                "F - 19\r\n" +
                "*\r\n" +
                "Musluk E \r\n" +
                "D - 7\r\n" +
                "F - 5\r\n" +
                "*\r\n" +
                "Musluk F \r\n" +
                "--> Son musluk\r\n" +
                "*\r\n" +
                "";

        String nodes[] = info.split("\\*");
        String[][] lines = new String[nodes.length][];
        for(int i = 0; i< nodes.length; i++) {
            nodes[i] = nodes[i] .replaceAll("(?m)^[ \t]*\r?\n", "");
            lines[i] = nodes[i].split("\\n");
        }

        for(int i = 0; i< nodes.length ; i++) {
            for(int j = 0; j< lines[i].length; j++) {
                System.out.print(lines[i][j]);
            }
        }

        temp = new int[nodes.length -1][nodes.length -1];
        // Fill each row with 0
        for(int i = 0; i< temp.length ; i++) {
            for(int j = 0; j< temp[i].length; j++) {
                temp[i][j] = 0;
            }

        }

        //kaynak m� diye kontrol et
        //de�ilse ba�lant�lar�n� al
        boolean isSrc = false;
        boolean isSnk = false;

        System.out.println("temp boyutu : "+ (nodes.length -1));
        int[] tempAry = new int[nodes.length -1];
        Arrays.fill(tempAry, new Integer(0));


        int[] srcAry = new int[nodes.length -1];
        Arrays.fill(srcAry, new Integer(0));

        for(int j = 0; j< srcAry.length; j++) {
            System.out.println(srcAry[j]);
        }

        char src = 'D';
        char toWhere;
        String str = "";

        for(int i = 0; i< nodes.length -2; i++) {
            //kaynak musluk durumu
            if (lines[i][0].contains("Kaynak")) {

                //find source index
                src = lines[i][0].charAt(7);
                int srcNmb = src -65;
                System.out.println("kaynak node : "+srcNmb);

                for(int j = 1; j< lines[i].length; j++) {

                    //Find where to connect
                    str = lines[i][j];
                    toWhere = str.charAt(0);
                    int chrNmb = toWhere -65;

                    //connection capacity
                    str = str.replaceAll("\\D+","");

                    System.out.println(chrNmb + "--" + str);

                    srcAry[chrNmb] = Integer.parseInt(str);
                }
                temp[0] = srcAry;
            }
            //Son musluk durumu
			/*else if (lines[i][1].contains("Son")) {

				//find source index
				src = lines[i][0].charAt(7);
				int snkNmb = src -65;
				System.out.println("Son node : "+snkNmb);

				int[] lst = new int[nodes.length -1];
				Arrays.fill(lst, new Integer(0));

				temp[temp.length -1] = lst;
			}*/
            else {
                //find source index
                src = lines[i][0].charAt(7);
                int srcNmb = src -65;
                Arrays.fill(tempAry, 0);
                System.out.println("kaynak node : "+srcNmb);
                for(int j = 1; j< lines[i].length; j++) {

                    //Find where to connect
                    str = lines[i][j];
                    toWhere = str.charAt(0);
                    int chrNmb = toWhere -65;

                    //connection capacity
                    str = str.replaceAll("\\D+","");

                    System.out.println(chrNmb + "--" + str);

                    if (!str.isEmpty()) {
                        tempAry[chrNmb] = Integer.parseInt(str);
                    }

                }
                System.out.println(i);
                for (int j = 0; j < tempAry.length; j++) {
                    System.out.print(tempAry[j] + "\t");
                    temp[i][j] = tempAry[j];
                }
                System.out.println();
                //temp[i] = tempAry;
            }


        }

        System.out.println("Son durum: \n -----------------------------------------------");
        for(int i = 0; i< temp.length ; i++) {
            for(int j = 0; j< temp[i].length; j++) {
                System.out.print(temp[i][j] + "\t");
            }
            System.out.println();
        }

    return temp;
    }

    public static void main(String[] args) {
        int[][] graph = findGraph("hahaha");
        System.out.println(Arrays.deepToString(graph).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        System.out.println(Arrays.deepToString(graph));
        
        MaxFlow m = new MaxFlow(); 

	System.out.println("The maximum possible flow is " + 
						m.fordFulkerson(graph, 0, 5)); 


    }
}
