package BomberInit;

import javax.swing.*;

public class Button_ex extends JButton {
    public int indexi;
    public int indexj;
    public int state;
    public Button_ex(String var1, Icon var2) {
        this.setModel(new DefaultButtonModel());
        this.init(var1, var2);
        state=0;


    }
}
