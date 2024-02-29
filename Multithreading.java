import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException; 

public class Multithreading implements Runnable{
    CellRenderer cellRenderer;
    PacmanGameBoard pacmanGameBoard;
    int[][] matrix;
    JFrame jFrame;
    boolean finish = false;
    public Multithreading(CellRenderer cellRenderer, PacmanGameBoard pacmanGameBoard, int[][] matrix, JFrame jFrame){
        this.cellRenderer = cellRenderer;
        this.pacmanGameBoard = pacmanGameBoard;
        this.matrix = matrix;
        this.jFrame = jFrame;
    }
    @Override
    public void run(){
        int count = 0;
        while(true) {
            cellRenderer.move = true;
            pacmanGameBoard.repaint();
            try {
                Thread.sleep(125);
            } catch (InterruptedException e) {
            }
            if(count == 2){
                count = 0;
                cellRenderer.ghostMovement1 = true;
                cellRenderer.ghostMovement2 = true;
                cellRenderer.ghostMovement3 = true;
                cellRenderer.ghostMovement4 = true;
                cellRenderer.moveAutomatically = true;
                cellRenderer.pacman.setY(cellRenderer.pacman.YY());
                cellRenderer.pacman.setX(cellRenderer.pacman.XX());
            }else{
                cellRenderer.moveAutomatically = false;
                cellRenderer.pacman.xANDy();
            }
            boolean doNotStop = true;
            boolean stoppp = false;
            if(cellRenderer.stop2 && (cellRenderer.stop || cellRenderer.lives < 1)){
                String message = "Game Over";
                JOptionPane.showMessageDialog(null, message);
                break;
            }else if(cellRenderer.stop2 && cellRenderer.lives >= 1){
                cellRenderer.stop2 = false;
                stoppp = true;
            }else {
                for (int i = 0; i < matrix.length; i = i + 1) {
                    for (int j = 0; j < matrix[i].length; j = j + 1) {
                        if (matrix[i][j] == 0) {
                            doNotStop = false;
                            break;
                        }
                    }
                    if (!doNotStop) {
                        break;
                    }
                }
            }
            if(doNotStop){
                int firstI = -1;
                int firstJ = -1;
                int lastI = -1;
                int lastJ = -1;
                for(int i = 0; i < matrix.length; i = i + 1){
                    for(int j = 0; j < matrix[i].length; j = j + 1){
                        if(matrix[i][j] == 2){
                            if(cellRenderer.lives < 1) {
                                matrix[i][j] = 0;
                            }
                            if(firstI == -1) {
                                firstI = i;
                                firstJ = j;
                            }
                            lastI = i;
                            lastJ = j;
                        }
                    }
                }
                if(stoppp) {
                    cellRenderer.lives--;
                }
                cellRenderer.currX = firstJ;
                cellRenderer.currY = firstI;
                cellRenderer.pacman.xANDy();
                cellRenderer.pacman.xxANDyy();
                for(int i = 0; i < cellRenderer.ghosts.length; i = i + 1){
                    cellRenderer.ghosts[i] = new Ghost(matrix,-1,-1);
                }
                cellRenderer.ind1 = 0;
                cellRenderer.ind2 = 0;
                cellRenderer.ind3 = 0;
                cellRenderer.ind4 = 0;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }
            count++;
        }
        /*if(cellRenderer.stop) {
            jFrame.dispose();
            cellRenderer.stop = false;
            finish = true;
            cellRenderer.pacman.setControl(0);
            cellRenderer.pacman.setQ(0);
            cellRenderer.pacman.setShift(0);
            PacmanGameWindow pacmanGameWindow = new PacmanGameWindow();
            pacmanGameWindow.gameWindow(jFrame.getWidth(),jFrame.getHeight());
        }*/
        if(cellRenderer.stop2){
            finish = true;
            cellRenderer.pacman.setControl(0);
            cellRenderer.pacman.setQ(0);
            cellRenderer.pacman.setShift(0);
            cellRenderer.stop2 = false;
            jFrame.dispose();
            JFrame jFrame1 = new JFrame();
            JPanel jPanel = new JPanel();
            JTextArea name = new JTextArea("Enter name:");
            name.setBackground(jPanel.getBackground());
            name.setEditable(false);
            jPanel.add(name);
            JTextField field1 = new JTextField("",10);
            field1.setBackground(jPanel.getBackground());
            jPanel.add(field1);
            JButton newGame = new JButton("OK");
            newGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name1 = field1.getText();
                    jFrame1.setVisible(false);
                    try {
                        BufferedWriter file = new BufferedWriter(new FileWriter("HighScores.txt",true));
                        file.write(name1 + ": " + cellRenderer.points);
                        file.newLine();
                        file.close();
                    } catch (IOException ex) {
                    }
                    PacmanGameWindow pacmanGameWindow = new PacmanGameWindow();
                    pacmanGameWindow.gameWindow(jFrame.getWidth(),jFrame.getHeight());
                }
            });
            jPanel.add(newGame);
            jFrame1.add(jPanel);
            jFrame1.setSize(300,190);
            jFrame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jFrame1.setLocationRelativeTo(null);
            jFrame1.setVisible(true);
        }
    }
}
