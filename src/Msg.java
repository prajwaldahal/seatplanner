import javax.swing.*;
public class Msg {
     public static void showError(String errormsg)
     {
         JOptionPane.showMessageDialog(new JFrame(),errormsg,"Error!",JOptionPane.ERROR_MESSAGE);
     }
     public static void showMessage(String msg)
     {
         JOptionPane.showMessageDialog(new JFrame(),msg,"Message",JOptionPane.INFORMATION_MESSAGE);
     }
}
