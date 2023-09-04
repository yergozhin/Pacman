import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellRenderer extends DefaultTableCellRenderer {
    int[][] matrix;
    public int currX = 1;
    public int currY = 1;
    public boolean mouthOpen = true;
    PacmanMove pacman;
    public boolean move = true;
    public boolean moveAutomatically = false;
    Ghost[] ghosts;
    int ind1;
    int ind2;
    int ind3;
    int ind4;
    boolean ghostMovement1 = false;
    boolean ghostMovement2 = false;
    boolean ghostMovement3 = false;
    boolean ghostMovement4 = false;
    boolean stop = false;
    boolean stop2 = false;
    public int points = 0;
    int lives = 3;
    public CellRenderer(int[][] matrix){
        for(int i = 0; i < matrix.length; i = i + 1){
            boolean check = false;
            for(int j = 0; j < matrix[i].length; j = j + 1){
                if(matrix[i][j] == 0){
                    currX = j;
                    currY = i;
                    check = true;
                    break;
                }
            }
            if(check){
                break;
            }
        }
        ghosts = new Ghost[4];
        for(int i = 0; i < ghosts.length; i = i + 1){
            ghosts[i] = new Ghost(matrix,-1,-1);
        }
        ind1 = 0;
        ind2 = 0;
        ind3 = 0;
        ind4 = 0;
    }
    public void cell(int[][] matrix, PacmanMove pacman){
        this.matrix = matrix;
        this.pacman = pacman;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (move && (currX + pacman.X() < 0 || currX + pacman.X() >= matrix[currY].length)) {
                    if (currX + pacman.X() < 0) {
                        currX = matrix[currY].length - 1;
                    } else if (currX + pacman.X() >= matrix[currY].length) {
                        currX = 0;
                    }
                    move = false;
                }else if(move && matrix[currY + pacman.Y()][currX + pacman.X()] == 1){
                }else if(move){
                    currY = currY + pacman.Y();
                    currX = currX + pacman.X();
                    move = false;
                }
                if(row < matrix.length && column < matrix[0].length) {
                    if (matrix[row][column] == 1) {
                        g.setColor(Color.BLUE);
                        g.fillRect(column - table.getColumnCount(), row - table.getRowCount(), getWidth() + table.getColumnCount(), getHeight() + table.getRowCount());
                    } else if (row == currY && column == currX) {
                        if (matrix[row][column] == 0) {
                            points += 10;
                        }
                        matrix[row][column] = 2;
                        int diameter = (int) (Math.min(getWidth(), getHeight()) - (Math.min(getWidth(), getHeight()) * 0.1));
                        g.setColor(Color.YELLOW);
                        g.fillOval((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter);
                        g.setColor(Color.BLACK);
                        if (mouthOpen) {
                            if (pacman.XX() == -1) {
                                g.fillArc((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter, 150, 60);
                            } else if (pacman.XX() == 1) {
                                g.fillArc((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter, 330, 60);
                            } else if (pacman.YY() == -1) {
                                g.fillArc((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter, 60, 60);
                            } else if (pacman.YY() == 1) {
                                g.fillArc((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter, 240, 60);
                            }
                            mouthOpen = false;
                        } else {
                            mouthOpen = true;
                        }
                        move = false;
                        pacman.xANDy();
                    }
                    if (ind1 < ghosts[0].path.size() && ghostMovement1 && row == ghosts[0].path.get(ind1)[0] && column == ghosts[0].path.get(ind1)[1]) {
                        if (row == currY && column == currX) {
                            stop2 = true;
                        }
                        int diameter = (int) (Math.min(getWidth(), getHeight()) - (Math.min(getWidth(), getHeight()) * 0.1));
                        g.setColor(Color.RED);
                        g.fillOval((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter);
                        ghostMovement1 = false;
                        if (ind1 + 1 != ghosts[0].path.size()) {
                            ind1++;
                        } else {
                            ghosts[0] = new Ghost(matrix, row, column);
                            ind1 = 0;
                        }
                    }
                    if (ind2 < ghosts[1].path.size() && ghostMovement2 && row == ghosts[1].path.get(ind2)[0] && column == ghosts[1].path.get(ind2)[1]) {
                        if (row == currY && column == currX) {
                            stop2 = true;
                        }
                        int diameter = (int) (Math.min(getWidth(), getHeight()) - (Math.min(getWidth(), getHeight()) * 0.1));
                        g.setColor(Color.GRAY);
                        g.fillOval((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter);
                        ghostMovement2 = false;
                        if (ind2 + 1 != ghosts[1].path.size()) {
                            ind2++;
                        } else {
                            ghosts[1] = new Ghost(matrix, row, column);
                            ind2 = 0;
                        }
                    }
                    if (ind3 < ghosts[2].path.size() && ghostMovement3 && row == ghosts[2].path.get(ind3)[0] && column == ghosts[2].path.get(ind3)[1]) {
                        if (row == currY && column == currX) {
                            stop2 = true;
                        }
                        int diameter = (int) (Math.min(getWidth(), getHeight()) - (Math.min(getWidth(), getHeight()) * 0.1));
                        g.setColor(Color.PINK);
                        g.fillOval((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter);
                        ghostMovement3 = false;
                        if (ind3 + 1 != ghosts[2].path.size()) {
                            ind3++;
                        } else {
                            ghosts[2] = new Ghost(matrix, row, column);
                            ind3 = 0;
                        }
                    }
                    if (ind4 < ghosts[3].path.size() && ghostMovement4 && row == ghosts[3].path.get(ind4)[0] && column == ghosts[3].path.get(ind4)[1]) {
                        if (row == currY && column == currX) {
                            stop2 = true;
                        }
                        int diameter = (int) (Math.min(getWidth(), getHeight()) - (Math.min(getWidth(), getHeight()) * 0.1));
                        g.setColor(Color.GREEN);
                        g.fillOval((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter);
                        ghostMovement4 = false;
                        if (ind4 + 1 != ghosts[3].path.size()) {
                            ind4++;
                        } else {
                            ghosts[3] = new Ghost(matrix, row, column);
                            ind4 = 0;
                        }
                    }
                    if (matrix[row][column] == 0) {
                        int diameter = (int) (Math.min(getWidth(), getHeight()) - (Math.min(getWidth(), getHeight()) * 0.6));
                        g.setColor(Color.WHITE);
                        g.fillOval((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter);
                    }
                    if (pacman.getControl() == 1 && pacman.getQ() == 1 && pacman.getShift() == 1) {
                        stop2 = true;
                        stop = true;
                    }
                }
                if(lives >= 1 && row == matrix.length && column == 0){
                    int diameter = (int) (Math.min(getWidth(), getHeight()) - (Math.min(getWidth(), getHeight())*0.1));
                    g.setColor(Color.YELLOW);
                    g.fillOval((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter);
                    g.setColor(Color.BLACK);
                    g.fillArc((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter, 330, 60);
                }
                if(lives >= 2 && row == matrix.length && column == 1){
                    int diameter = (int) (Math.min(getWidth(), getHeight()) - (Math.min(getWidth(), getHeight())*0.1));
                    g.setColor(Color.YELLOW);
                    g.fillOval((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter);
                    g.setColor(Color.BLACK);
                    g.fillArc((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter, 330, 60);
                }
                if(lives >= 3 && row == matrix.length && column == 2){
                    int diameter = (int) (Math.min(getWidth(), getHeight()) - (Math.min(getWidth(), getHeight())*0.1));
                    g.setColor(Color.YELLOW);
                    g.fillOval((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter);
                    g.setColor(Color.BLACK);
                    g.fillArc((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter, 330, 60);
                }
                if(row == matrix.length && column == matrix[0].length/2){
                    int diameter = (int) (Math.min(getWidth(), getHeight()) - (Math.min(getWidth(), getHeight())*0.1));
                    g.setFont(new Font("", Font.BOLD, getWidth()/5));
                    g.setColor(Color.white);
                    g.drawString(Integer.toString(points),0,getHeight());
                }
                if(row == matrix.length && column == matrix[0].length/2 - 1){
                    int diameter = (int) (Math.min(getWidth(), getHeight()) - (Math.min(getWidth(), getHeight())*0.1));
                    g.setFont(new Font("", Font.BOLD, getWidth()/5));
                    g.setColor(Color.white);
                    g.drawString("Score: ",0,getHeight());
                }
            }
        };
        panel.setBackground(Color.black);
        return panel;
    }
}
