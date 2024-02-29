import java.util.ArrayList;
import java.util.List;

public class GhostPath {
    List<int[]> path =  new ArrayList<>(); 
    public GhostPath(int indStr_i, int indStr_j, int indEnd_i, int indEnd_j, int[][] matrix){
        MyQueue queue = new MyQueue();
        List<int[]> list2 = new ArrayList<>();
        list2.add(new int[2]);
        list2.get(0)[0] = indStr_i;
        list2.get(0)[1] = indStr_j;
        queue.enqueue(new Triple(indStr_i,indStr_j,list2));
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        while (!queue.empty()){
            int size = queue.size();
            for(int i = 0; i < size; i = i + 1){
                Triple node = queue.dequeue();
                int ind_i = node.i;
                int ind_j = node.j;
                if(visited[ind_i][ind_j]){
                    continue;
                }
                visited[ind_i][ind_j] = true;
                if(ind_i == indEnd_i && ind_j == indEnd_j){
                    path = node.list;
                    return;
                }
                if(ind_i - 1 >= 0 && (matrix[ind_i - 1][ind_j] == 0 || matrix[ind_i - 1][ind_j] == 2)){
                    List<int[]> list = new ArrayList<>();
                    for(int j = 0; j < node.list.size(); j = j + 1){
                        list.add(new int[2]);
                        list.get(list.size() - 1)[0] = node.list.get(j)[0];
                        list.get(list.size() - 1)[1] = node.list.get(j)[1];
                    }
                    list.add(new int[2]);
                    list.get(list.size() - 1)[0] = ind_i - 1;
                    list.get(list.size() - 1)[1] = ind_j;
                    queue.enqueue(new Triple(ind_i - 1,ind_j,list));
                }
                if(ind_i + 1 < matrix.length && (matrix[ind_i + 1][ind_j] == 0 || matrix[ind_i + 1][ind_j] == 2)){
                    List<int[]> list = new ArrayList<>();
                    for(int j = 0; j < node.list.size(); j = j + 1){
                        list.add(new int[2]);
                        list.get(list.size() - 1)[0] = node.list.get(j)[0];
                        list.get(list.size() - 1)[1] = node.list.get(j)[1];
                    }
                    list.add(new int[2]);
                    list.get(list.size() - 1)[0] = ind_i + 1;
                    list.get(list.size() - 1)[1] = ind_j;
                    queue.enqueue(new Triple(ind_i + 1,ind_j,list));
                }
                if(ind_j - 1 >= 0 && (matrix[ind_i][ind_j - 1] == 0 || matrix[ind_i][ind_j - 1] == 2)){
                    List<int[]> list = new ArrayList<>();
                    for(int j = 0; j < node.list.size(); j = j + 1){
                        list.add(new int[2]);
                        list.get(list.size() - 1)[0] = node.list.get(j)[0];
                        list.get(list.size() - 1)[1] = node.list.get(j)[1];
                    }
                    list.add(new int[2]);
                    list.get(list.size() - 1)[0] = ind_i;
                    list.get(list.size() - 1)[1] = ind_j - 1;
                    queue.enqueue(new Triple(ind_i,ind_j - 1,list));
                }
                if(ind_j + 1 < matrix[0].length && (matrix[ind_i][ind_j + 1] == 0 || matrix[ind_i][ind_j + 1] == 2)){
                    List<int[]> list = new ArrayList<>();
                    for(int j = 0; j < node.list.size(); j = j + 1){
                        list.add(new int[2]);
                        list.get(list.size() - 1)[0] = node.list.get(j)[0];
                        list.get(list.size() - 1)[1] = node.list.get(j)[1];
                    }
                    list.add(new int[2]);
                    list.get(list.size() - 1)[0] = ind_i;
                    list.get(list.size() - 1)[1] = ind_j + 1;
                    queue.enqueue(new Triple(ind_i,ind_j + 1,list));
                }
                if(ind_j - 1 < 0){
                    List<int[]> list = new ArrayList<>();
                    for(int j = 0; j < node.list.size(); j = j + 1){
                        list.add(new int[2]);
                        list.get(list.size() - 1)[0] = node.list.get(j)[0];
                        list.get(list.size() - 1)[1] = node.list.get(j)[1];
                    }
                    list.add(new int[2]);
                    list.get(list.size() - 1)[0] = ind_i;
                    list.get(list.size() - 1)[1] = matrix[0].length - 1;
                    queue.enqueue(new Triple(ind_i,matrix[0].length - 1,list));
                }
                else if(ind_j + 1 >= matrix[0].length){
                    List<int[]> list = new ArrayList<>();
                    for(int j = 0; j < node.list.size(); j = j + 1){
                        list.add(new int[2]);
                        list.get(list.size() - 1)[0] = node.list.get(j)[0];
                        list.get(list.size() - 1)[1] = node.list.get(j)[1];
                    }
                    list.add(new int[2]);
                    list.get(list.size() - 1)[0] = ind_i;
                    list.get(list.size() - 1)[1] = 0;
                    queue.enqueue(new Triple(ind_i,0,list));
                }
            }
        }
    }
}
