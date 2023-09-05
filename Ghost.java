import java.util.ArrayList;
import java.util.List;

public class Ghost {
    List<int[]> path =  new ArrayList<>();
    int start_i;
    int start_j;
    int end_i;
    int end_j;
    public Ghost(int[][] matrix, int continue_i, int continue_j){
        boolean zero = false;
        if(continue_i == -1 && continue_j == -1) {
            for (int i = matrix.length - 1; i >= 0; i = i - 1) {
                for (int j = matrix[i].length - 1; j >= 0; j = j - 1) {
                    if (matrix[i][j] == 0 || matrix[i][j] == 2) {
                        zero = true;
                        start_i = i;
                        start_j = j;
                        break;
                    }
                }
                if(zero){
                    break;
                }
            }
        }else{
            start_i = continue_i;
            start_j = continue_j;
        }
        zero = false;
        while(!zero){
            int i = (int)(Math.random()* matrix.length);
            int j = (int)(Math.random()* matrix[0].length);
            end_i = i;
            end_j = j;
            if(matrix[i][j] == 0 || matrix[i][j] == 2){
                zero = true;
            }
        }
        GhostPath ghostPath = new GhostPath(start_i,start_j,end_i,end_j,matrix);
        path = ghostPath.path;
    }
}
