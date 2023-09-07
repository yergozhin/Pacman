import javax.swing.table.AbstractTableModel;

public class PacmanBoardModel extends AbstractTableModel {
    int rows;
    int columns;
    Object[][] objects;
    public PacmanBoardModel(int rows, int columns,Object[][] objects){
        this.rows = rows;
        this.columns = columns;
        this.objects = objects;
    }
    @Override
    public int getRowCount() {
        return rows;
    }

    @Override
    public int getColumnCount() {
        return columns;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return objects[rowIndex][columnIndex];
    }
}
