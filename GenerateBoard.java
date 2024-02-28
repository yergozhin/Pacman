public class GenerateBoard {
    public GenerateBoard(){
 
    }
    public void generateBoard(int[][] matrix, int i, int j, int[][] visited){
        if(i == 0 || j == 0 || i == matrix.length - 1 || j == matrix[i].length - 1 || matrix[i][j] == 0 || visited[i][j] == 1){
            return;
        }
        matrix[i][j] = 0;
        visited[i][j] = 1;
        if((Math.random()*3) <= 2) {
            generateBoard(matrix, i + 1, j,visited);
        }else{
            if(i + 1 < visited.length){
                visited[i + 1][j] = 1;
            }
        }
        if((Math.random()*3) <= 2) {
            generateBoard(matrix, i - 1, j,visited);
        }else{
            if(i - 1 >= 0){
                visited[i - 1][j] = 1;
            }
        }
        if((Math.random()*3) <= 2) {
            generateBoard(matrix, i, j + 1,visited);
        }else{
            if(j + 1 < visited[0].length){
                visited[i][j + 1] = 1;
            }
        }
        if((Math.random()*3) <= 2) {
            generateBoard(matrix, i, j - 1,visited);
        }else{
            if(j - 1 >= 0){
                visited[i][j - 1] = 1;
            }
        }
    }
}
