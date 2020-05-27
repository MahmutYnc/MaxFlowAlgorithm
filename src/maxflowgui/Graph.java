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
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
    import org.jgrapht.*;
    import org.jgrapht.alg.*;
    import org.jgrapht.graph.*;
    import java.util.List;
import javax.swing.JFrame;
import org.jgrapht.ext.JGraphXAdapter;


    public class Graph {
        
    public static class MyEdge extends DefaultWeightedEdge {
        @Override
        public String toString() {
            return String.valueOf(getWeight());
        }
    }
        public static void main(String args[]) {

            SimpleDirectedWeightedGraph<String, MyEdge>  graph = 
            new SimpleDirectedWeightedGraph<String, MyEdge>
            (MyEdge.class); 
            
            SimpleDirectedWeightedGraph<String, MyEdge> gi = 
            new SimpleDirectedWeightedGraph<String, MyEdge>(MyEdge.class);
            
            int g[][] =new int[][] {            {0, 16, 13, 0, 0, 0}, 
                                                {0, 0, 10, 12, 0, 0}, 
                                                {0, 4, 0, 0, 14, 0}, 
                                                {0, 0, 9, 0, 0, 20}, 
                                                {0, 0, 0, 7, 0, 4}, 
                                                {0, 0, 0, 0, 0, 0}
                                               }; 
            for (int i = 0; i < g.length; i++) {
                String str = "vertex" + (i+1);
                graph.addVertex(str);
            }
    
             MyEdge[] ede = new MyEdge[10];
             int k = 0;
             for (int i = 0; i < g.length; i++) {
                for (int j = 0; j < g[i].length; j++) {
                    if(g[i][j] != 0){
                        
                        String from = "vertex" + (i+1);
                        String to = "vertex" + (j+1);
                        
                        ede[k] = graph.addEdge(from, to);
                        graph.setEdgeWeight(ede[k], g[i][j] );
                        k++;
                    }
                }
            }

            System.out.println("Shortest path from vertex1 to vertex5:");
           
        JFrame frame = new JFrame("DemoGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        
        JGraphXAdapter<String, MyEdge> graphAdapter = 
                new JGraphXAdapter<String, MyEdge>(graph);

        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        frame.add(new mxGraphComponent(graphAdapter));

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        }
    }