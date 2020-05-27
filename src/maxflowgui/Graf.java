/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxflowgui;

import javax.swing.JButton;

/**
 *
 * @author siebe
 */
public class Graf extends javax.swing.JFrame {

    /**
     * Creates new form Graf
     */
    private int nodeCount;
    private String[] nodeNames;
    private String source, sink;
    private int[][] matrix;
    
    //My attributes
    boolean[] isVisited;
    final static int startX = 40, startY = 250, addX = 100, addY = 100, srcY = 300;
    final static int WIDTH = 40, HEIGHT = 30;
    
    //Yorum Satırı olmalı
    
    public Graf() {
        
        initComponents();
        
    }
    
    public Graf(int nodeCount, String[] nodeNames, int[][] connectionMatrix) {
        this.nodeCount = nodeCount;
        this.nodeNames = nodeNames;
        this.matrix = connectionMatrix;
        
        initComponents();
        
 
        
        createNodes(connectionMatrix, nodeNames);
    }
    private JButton buttons[];
    
   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(230, 230, 250));
        jPanel1.setMinimumSize(new java.awt.Dimension(904, 640));
        jPanel1.setPreferredSize(new java.awt.Dimension(904, 640));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 904, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Graf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Graf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Graf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Graf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
       
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //To Delete later 
                int s = 3;
                String[] arr = {"A", "B", "C"};
                // using for loop
                int[][] board = new int[3][3];
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        board[i][j] = i + j;
                    }
                }
                   int graph[][] =new int[][] { {0, 16, 13, 0, 0, 0}, 
                                                {0, 0, 10, 12, 0, 0}, 
                                                {0, 4, 0, 0, 14, 0}, 
                                                {0, 0, 9, 0, 0, 20}, 
                                                {0, 0, 0, 7, 0, 4}, 
                                                {0, 0, 0, 0, 0, 0}
                                               }; 
                
                
               
                new Graf(s, arr, graph).setVisible(true);
            }
        });
    }
    
    public void twoDtoGraph(int[][] graph) {
        
    }
    
    public void createNodes(int[][] graph, String[] str) {
        //remove later
        buttons = new JButton[graph.length];
        isVisited = new boolean[graph.length];
        
        int currentX = 40, currentY = 250;
        
        System.out.println("graph length : "+ graph.length);
        
        //source Node 
        JButton btn = new JButton( 0 + ".");
        btn.setEnabled(false);
        btn.setBounds(startX, srcY, WIDTH, HEIGHT);
        buttons[0] = btn;
        jPanel1.add(btn);
        isVisited[0] = true;
        
        //nodes between source and sink       
        for (int i = 0; i < graph.length; i++) {            
            for (int j = 0; j < graph[i].length -1; j++) {
                
                if(!isVisited[j] && graph[i][j] != 0 && (j%2 != 0)){
                    btn = new JButton( j + ".");
                    btn.setEnabled(false);
                    btn.setBounds(currentX + addX, currentY, WIDTH, HEIGHT);
                    buttons[i] = btn;
                    jPanel1.add(btn);
                    isVisited[j] = true;
                    currentY += addY;
                    
                    //problemmy part is here 
                    btn = new JButton( j+1 + ".");
                    btn.setEnabled(false);
                    btn.setBounds(currentX + addX, currentY, WIDTH, HEIGHT);
                    buttons[i+1] = btn;
                    jPanel1.add(btn);
                    isVisited[j+1] = true;
                    currentY += addY;
                }                
                
            }
            currentY = startY;
            currentX += addX;
            
        }
        //SiNK NODE
        btn = new JButton( graph.length-1 + ".");
        btn.setEnabled(false);
        btn.setBounds((graph.length / 2 ) * 100 + 40 , srcY, WIDTH, HEIGHT);
        buttons[graph.length - 1] = btn;
        jPanel1.add(btn);
        isVisited[graph.length-1] = true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
