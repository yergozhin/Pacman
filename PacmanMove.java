import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
public class PacmanMove implements KeyListener {
    public  int x = 0;
    public  int y = 0;
    public  int xx = 0;
    public  int yy = 0;
    public  int control = 0;
    public  int shift = 0;
    public  int q = 0;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        x = 0;
        y = 0;
        xx = 0;
        yy = 0;
        int arrow = e.getKeyCode();
        if(arrow == KeyEvent.VK_UP){
            y = -1;
            yy = -1;
            control = 0;
            shift = 0;
            q = 0;
        }else if(arrow == KeyEvent.VK_DOWN){
            y = 1;
            yy = 1;
            control = 0;
            shift = 0;
            q = 0;
        }else if(arrow == KeyEvent.VK_LEFT){
            x = -1;
            xx = -1;
            control = 0;
            shift = 0;
            q = 0;
        }else if(arrow == KeyEvent.VK_RIGHT){
            x = 1;
            xx = 1;
            control = 0;
            shift = 0;
            q = 0;
        }
        int contrShftQ = e.getKeyCode();
        if(contrShftQ == KeyEvent.VK_CONTROL){
            control = 1;
        }
        else if(contrShftQ == KeyEvent.VK_SHIFT){
            shift = 1;
        }
        else if(contrShftQ == KeyEvent.VK_Q){
            q = 1;
        }else{
            control = 0;
            shift = 0;
            q = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public int X(){
        return x;
    }
    public int Y(){
        return y;
    }
    public int XX(){
        return xx;
    }
    public int YY(){
        return yy;
    }
    public void xANDy(){
        x = 0;
        y = 0;
    }
    public void xxANDyy(){
        xx = 0;
        yy = 0;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getQ() {
        return q;
    }

    public int getControl() {
        return control;
    }

    public int getShift() {
        return shift;
    }

    public  void setQ(int q) {
        this.q = q;
    }

    public  void setControl(int control) {
        this.control = control;
    }

    public  void setShift(int shift) {
        this.shift = shift;
    }
}
