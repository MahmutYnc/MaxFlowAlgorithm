/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxflowgui;

/**
 *
 * @author siebe
 */
import java.util.LinkedList; 
import java.util.Queue; 

public class MinCut {
    

// Java program for finding min-cut in the given graph 
		
	// Returns true if there is a path 
	// from source 's' to sink 't' in residual 
	// graph. Also fills parent[] to store the path 
	private static boolean bfs(int[][] rGraph, int s, int t, int[] parent) { 
		
		// Create a visited array and mark 
		// all vertices as not visited	 
		boolean[] visited = new boolean[rGraph.length]; 
		
		// Create a queue, enqueue source vertex 
		// and mark source vertex as visited	 
		Queue<Integer> q = new LinkedList<Integer>(); 
		q.add(s); 
		visited[s] = true; 
		parent[s] = -1; 
		
		// Standard BFS Loop	 
		while (!q.isEmpty()) { 
			int v = q.poll(); 
			for (int i = 0; i < rGraph.length; i++) { 
				if (rGraph[v][i] > 0 && !visited[i]) { 
					q.offer(i); 
					visited[i] = true; 
					parent[i] = v; 
				} 
			} 
		} 
		
		// If we reached sink in BFS starting 
		// from source, then return true, else false	 
		return (visited[t] == true); 
	} 
	
	// A DFS based function to find all reachable 
	// vertices from s. The function marks visited[i] 
	// as true if i is reachable from s. The initial 
	// values in visited[] must be false. We can also 
	// use BFS to find reachable vertices 
	private static void dfs(int[][] rGraph, int s, boolean[] visited) { 
		visited[s] = true; 
		for (int i = 0; i < rGraph.length; i++) { 
				if (rGraph[s][i] > 0 && !visited[i]) { 
					dfs(rGraph, i, visited); 
				} 
		} 
	} 

	// Prints the minimum s-t cut 
	public static String minCut(int[][] graph, int s, int t) { 
		int u,v; 
		
		// Create a residual graph and fill the residual 
		// graph with given capacities in the original 
		// graph as residual capacities in residual graph 
		// rGraph[i][j] indicates residual capacity of edge i-j 
		int[][] rGraph = new int[graph.length][graph.length]; 
		for (int i = 0; i < graph.length; i++) { 
			for (int j = 0; j < graph.length; j++) { 
				rGraph[i][j] = graph[i][j]; 
			} 
		} 

		// This array is filled by BFS and to store path 
		int[] parent = new int[graph.length]; 
		
		// Augment the flow while tere is path from source to sink	 
		while (bfs(rGraph, s, t, parent)) { 
			
			// Find minimum residual capacity of the edhes 
			// along the path filled by BFS. Or we can say 
			// find the maximum flow through the path found. 
			int pathFlow = Integer.MAX_VALUE;		 
			for (v = t; v != s; v = parent[v]) { 
				u = parent[v]; 
				pathFlow = Math.min(pathFlow, rGraph[u][v]); 
			} 
			
			// update residual capacities of the edges and 
			// reverse edges along the path 
			for (v = t; v != s; v = parent[v]) { 
				u = parent[v]; 
				rGraph[u][v] = rGraph[u][v] - pathFlow; 
				rGraph[v][u] = rGraph[v][u] + pathFlow; 
			} 
		} 
		
		// Flow is maximum now, find vertices reachable from s	 
		boolean[] isVisited = new boolean[graph.length];	 
		dfs(rGraph, s, isVisited); 
		
                
                StringBuilder strB = new StringBuilder();
		// Print all edges that are from a reachable vertex to 
		// non-reachable vertex in the original graph	 
		for (int i = 0; i < graph.length; i++) { 
			for (int j = 0; j < graph.length; j++) { 
				if (graph[i][j] > 0 && isVisited[i] && !isVisited[j]) { 
                                        strB.append("Musluk" +(char)(65 + i) +
                                                " - " +"Musluk" + (char)(65 + j) + "\n");
					//System.out.println(i + " - " + j); 
				} 
			} 
		}
                return strB.toString();
	}  
} 

