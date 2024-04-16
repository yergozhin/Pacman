import javax.swing.table.AbstractTableModel;
import java.io.*;

import static java.lang.Character.isDigit; 

class SerializableModel extends AbstractTableModel implements Serializable{
    Object[][] objects;
    String[] strings;
    public SerializableModel(){
        File file = new File("HighScores.txt");
        int rows = 0;
        if(file.exists()){
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                int count = 0;
                while (bufferedReader.readLine() != null) {
                    count++;
                }
                rows = count;

        } catch (IOException e) {

            }
        }else{
            rows = 0;
        }
        Object[][] objects = new Object[rows][3];
        String[][] nicknamesAndPoints = new String[rows][2];
        if(file.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line;
                int i = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    String name = "";
                    String points = "";
                    boolean nameFirst = true;
                    for(int j = 0; j < line.length(); j = j + 1){
                        if(line.charAt(j) != ':' && nameFirst){
                            name += line.charAt(j);
                        }else{
                            nameFirst = false;
                        }
                        if(!nameFirst && isDigit(line.charAt(j))){
                            points += line.charAt(j);
                        }
                    }
                    nicknamesAndPoints[i][0] = name;
                    nicknamesAndPoints[i][1] = points;
                    //System.out.println(line);
                    i = i + 1;
                }
            } catch (IOException e) {

            }
        }
        for(int i = 0; i < nicknamesAndPoints.length; i = i + 1){
            for(int j = i + 1; j < nicknamesAndPoints.length; j = j + 1){
                if(Integer.parseInt(nicknamesAndPoints[i][1]) <= Integer.parseInt(nicknamesAndPoints[j][1])){
                    String temp = nicknamesAndPoints[i][0];
                    String temp1 = nicknamesAndPoints[i][1];
                    nicknamesAndPoints[i][0] = nicknamesAndPoints[j][0];
                    nicknamesAndPoints[i][1] = nicknamesAndPoints[j][1];
                    nicknamesAndPoints[j][0] = temp;
                    nicknamesAndPoints[j][1] = temp1;
                }
            }
        }
        for(int i = 0; i < objects.length; i = i + 1){
            for(int j = 0; j < objects[i].length; j = j + 1){
                objects[i][j] = "";
                if(j == 0){
                    int k = i + 1;
                    String s = Integer.toString(k);
                    objects[i][j] = s;
                }
                if(j == 1){
                    objects[i][j] = nicknamesAndPoints[i][0];
                }
                if(j == 2){
                    objects[i][j] = nicknamesAndPoints[i][1];
                }
            }
        }
        String[] strings = new String[3];
        strings[0] = "Rating:";
        strings[1] = "Nickname:";
        strings[2] = "Points:";
        this.objects = objects;
        this.strings = strings;
    }

    @Override
    public int getRowCount() {
        return objects.length;
    }

    @Override
    public int getColumnCount() {
        return strings.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return objects[rowIndex][columnIndex];
    }
    @Override
    public String getColumnName(int columnIndex) {
        return strings[columnIndex];
    }
}
