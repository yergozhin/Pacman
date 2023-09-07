import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PacmanGameBoard extends JTable{
    PacmanBoardModel pacmanBoardModel;
    JFrame jFrame;
    public PacmanGameBoard(PacmanBoardModel pacmanBoardModel, JFrame jFrame){
        super(pacmanBoardModel);
        this.pacmanBoardModel = pacmanBoardModel;
        this.jFrame = jFrame;
    }
    public void board(){
        for(int i = 0; i < getColumnCount(); i = i + 1){
            TableColumn column = getColumnModel().getColumn(i);
            column.setPreferredWidth(jFrame.getWidth()/getColumnCount());
        }
        setRowHeight(jFrame.getHeight()/(getRowCount() + 1));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                for(int i = 0; i < getColumnCount(); i = i + 1){
                    TableColumn column = getColumnModel().getColumn(i);
                    column.setMinWidth(jFrame.getWidth()/getColumnCount());
                }
                setRowHeight(jFrame.getHeight()/(getRowCount() + 1));
            }
        });
        setShowGrid(false);
        setBackground(Color.black);
    }
}
