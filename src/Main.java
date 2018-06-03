
import Schema.Data;
import ToolBox.FileManager;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {               
        
        // 1) Get Data
        ArrayList<Data>  posibilities;
        ArrayList<Data>  weights;
        
        posibilities = FileManager.getDataSet(
                "/home/zexes/NetBeansProjects/Data/"+
                "MP.txt"
        );
        int nb_criteres = posibilities.get(0).getNb_c();
        int nb_possibilities = Data.obj_count;
        
        Data.obj_count=0;
        weights = FileManager.getDataSet(
                "/home/zexes/NetBeansProjects/Data/"+
                "POIDS.txt"
        );
        
        
        // 2) Matrice de Performances
        String mp[][] = new String[nb_possibilities][nb_criteres];    
        for (int i = 0; i < nb_possibilities; i++) {
            for (int j = 1; j < nb_criteres; j++) {
                mp[i][j] = posibilities.get(i).getC(j);
            }
        }
        
        String[] vw = new String[nb_criteres];
        for (int i = 0; i < nb_criteres; i++) {
            vw[i]= weights.get(i).getC(1);
        }
        
        
        System.out.println("\n[MP]");
        for (int i = 0; i < nb_possibilities; i++) {
            for (int j = 1; j < nb_criteres; j++) {
                System.out.print(mp[i][j]+" | ");
            }
            System.out.println("");
        }
        
        
        // 3) Matrice de Concordance
        String[][] mc = concordance(mp, nb_possibilities, vw);
        
        System.out.println("\n[MC]");
        for (int i = 0; i < nb_possibilities; i++) {
            for (int j = 0; j < nb_possibilities; j++) {
                if(!mc[i][j].equals("-")){
                    DecimalFormat df2 = new DecimalFormat("#.####");
                    System.out.print(df2.format(Double.valueOf(mc[i][j])) +" | ");
                }
                else
                    System.out.print(mc[i][j]+" | ");
            }
            System.out.println("");
        }
        
        
        // 4) Matrice de Surclassement
        double seuil_concord = 0.6;
        String[][] ms = mc;
                    
        for (int i = 0; i < nb_possibilities; i++) {
            for (int j = 0; j < nb_possibilities; j++) {
                if(!mc[i][j].equals("-")){
                    if(Double.valueOf(mc[i][j]) > seuil_concord )
                        ms[i][j] = "1";
                    else
                        ms[i][j] = "0";
                }
            }   
        }
        
        System.out.println("\n[MS]");
        for (int i = 0; i < nb_possibilities; i++) {
            for (int j = 0; j < nb_possibilities; j++) {
                if(!ms[i][j].equals("-")){
                    DecimalFormat df2 = new DecimalFormat("#.####");
                    System.out.print(df2.format(Double.valueOf(ms[i][j])) +" | ");
                }
                else
                    System.out.print(ms[i][j]+" | ");
            }
            System.out.println("");
        }
        
        
        System.out.println("\n[Max]\n"+max_row_matrix(ms));
        
        
        
        // 5) A'
        ArrayList<Integer> indexesV = indexes(ms, max_row_matrix(ms));
        
        System.out.println("\n[A']");
        for(int e : indexesV){
            System.out.print(posibilities.get(e)+ " | ");
            for (int j = 1; j < nb_criteres; j++) {
                System.out.print(mp[e][j]+" | ");
            }
             System.out.println("");       
        }
        

        
        
    }
    
    public static String[][] concordance (String[][] mp, int n, String[] vw){
        String mc[][] = new String[n][n];    
        
        
        double weight_sum = 0;
        for (int i = 0; i < vw.length; i++) {
            weight_sum += Double.valueOf(vw[i]);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n ; j++) {
                if(i==j)
                    mc[i][j] ="-";
                else
                    mc[i][j] = String.valueOf( sum_weight(mp, vw, i, j)/ weight_sum );
            }
        }
        
        
        return mc;
    }
    
    public static double sum_weight(String[][] mp, String[] vw, int i, int j){
        double sum = 0;
            
        for (int k = 0; k < vw.length-1; k++) {
            if(Double.valueOf(mp[i][k+1])>=Double.valueOf(mp[j][k+1]))
               sum+= Double.valueOf(vw[k]);
        }
        
        return sum;
    }
    
    public static int max_row_matrix(String[][] ms){
        int max = 0;
        int sum;
        for (int i = 0; i < ms.length; i++) {
            sum =0;
            for (int j = 0; j < ms.length ; j++) {
                if(!ms[i][j].equals("-")){
                    sum += Integer.valueOf(ms[i][j]);
                }
                
            }
            if (sum>max)
                max = sum;
        }
        
        return max;
    }
    
    public static ArrayList<Integer> indexes(String[][] ms, int max_row){
        ArrayList<Integer> v = new ArrayList();
        int sum;
        
        for (int i = 0; i < ms.length; i++) {
            sum = 0;
            for (int j = 0; j < ms.length ; j++) {
                if(!ms[i][j].equals("-")){
                    sum += Integer.valueOf(ms[i][j]);
                }
                
            }
            if (sum == max_row)
                v.add(i);
        }
        
        return v;
    }
}
