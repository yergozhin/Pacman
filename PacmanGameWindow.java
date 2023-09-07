import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

public class PacmanGameWindow extends JFrame {
    public void gameWindow(int width, int height){
        setSize(width,height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameMenu();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void gameMenu(){
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame jFrame = new JFrame();
                JPanel jPanel = new JPanel();
                JTextArea columns = new JTextArea("Columns:");
                columns.setBackground(jPanel.getBackground());
                columns.setEditable(false);
                jPanel.add(columns);
                JTextField field1 = new JTextField("10",2);
                field1.setBackground(jPanel.getBackground());
                jPanel.add(field1);
                JTextArea rows = new JTextArea("Rows:");
                rows.setBackground(jPanel.getBackground());
                rows.setEditable(false);
                jPanel.add(rows);
                JTextField field2 = new JTextField("10",2);
                field2.setBackground(jPanel.getBackground());
                jPanel.add(field2);
                jPanel.setLayout(new GridBagLayout());
                GridBagConstraints gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.fill = 0;

                JButton button = new JButton("Play");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String column = field1.getText();
                        String row = field2.getText();
                        boolean number = false;
                        try {
                            Integer.parseInt(column);
                            Integer.parseInt(row);
                            number = true;
                        } catch (NumberFormatException e2) {
                        }
                        if(number && Integer.parseInt(column) >= 10 && Integer.parseInt(column) <= 100 && Integer.parseInt(row) >= 10 && Integer.parseInt(row) <= 100){
                            jFrame.setVisible(false);
                            Object[][] objects = new Object[Integer.parseInt(row) + 1][Integer.parseInt(column)];
                            int[][] matrix = new int[Integer.parseInt(row)][Integer.parseInt(column)];
                            for(int i = 0; i < objects.length; i = i + 1){
                                for(int j = 0; j < objects[i].length; j = j + 1){
                                    objects[i][j] = null;
                                    if(i < matrix.length) {
                                        matrix[i][j] = 1;
                                    }
                                }
                            }
                            GenerateBoard generateBoard = new GenerateBoard();
                            int[][] visited = new int[matrix.length][matrix[0].length];
                            generateBoard.generateBoard(matrix,(matrix.length/2),(matrix[0].length/2),visited);
                            for(int i = (matrix.length/2) - 1; i < (matrix.length/2) + 2; i = i + 1){
                                for(int j = 0; j < matrix[0].length; j = j + 1){
                                    matrix[i][j] = 0;
                                }
                            }
                            PacmanBoardModel pacmanBoardModel = new PacmanBoardModel(Integer.parseInt(row) + 1,Integer.parseInt(column),objects);
                            JFrame jFrame = new JFrame();
                            jFrame.setSize(getWidth(),getHeight());
                            PacmanGameBoard pacmanGameBoard = new PacmanGameBoard(pacmanBoardModel,jFrame);
                            pacmanGameBoard.board();
                            PacmanMove pacman = new PacmanMove();
                            pacmanGameBoard.addKeyListener(pacman);
                            CellRenderer cellRenderer = new CellRenderer(matrix);
                            cellRenderer.cell(matrix,pacman);
                            for(int j = 0; j < objects[0].length; j = j + 1){
                                pacmanGameBoard.getColumnModel().getColumn(j).setCellRenderer(cellRenderer);
                            }
                            jFrame.getContentPane().add(new JScrollPane(pacmanGameBoard),BorderLayout.CENTER);
                            jFrame.add(pacmanGameBoard);
                            Multithreading multithreading = new Multithreading(cellRenderer,pacmanGameBoard,matrix,jFrame);
                            Thread thread = new Thread(multithreading);
                            thread.start();
                            jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                            jFrame.setLocationRelativeTo(null);
                            jFrame.setVisible(true);
                            if(multithreading.finish){
                                jFrame.dispose();
                                dispose();
                            }
                        }
                    }
                });
                jPanel.add(button);
                jFrame.add(jPanel);
                jFrame.setSize(getWidth(),getHeight());
                jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                jFrame.setLocationRelativeTo(null);
                jFrame.setVisible(true);
            }
        });
        JButton highScores = new JButton("High Scores");
        highScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JTable jTable = new JTable();
                SerializableModel serializableModel = new SerializableModel();
                jTable.setModel(serializableModel);
                JFrame jFrame = new JFrame();
                jFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        PacmanGameWindow pacmanGameWindow = new PacmanGameWindow();
                        pacmanGameWindow.gameWindow(getWidth(),getHeight());
                    }
                });
                JScrollPane jScrollPane = new JScrollPane(jTable);
                jFrame.add(jScrollPane);
                jFrame.setSize(500,700);
                jFrame.setVisible(true);
                jFrame.setLocationRelativeTo(null);
            }
        });
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        JPanel jPanel = new JPanel();
        jPanel.add(newGame);
        jPanel.add(highScores);
        jPanel.add(exit);
        jPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = (int) CENTER_ALIGNMENT;
        add(jPanel);
    }
}

