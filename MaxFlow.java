
// Java program for implementation of Ford Fulkerson algorithm
import java.util.*;
        import java.lang.*;
        import java.io.*;
        import java.util.LinkedList;

public class MaxFlow{


    // Driver program to test above functions
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
    }
}
