import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
    private JLabel top;

    private JLabel userNameL;

    private JButton loginBtn;

    private JTextField password;

    private JTextField userName;

    private JLabel PasswordL;

    public LoginPanel() {
        initComponent();
    }

    public JButton getLoginBtn() {
        return this.loginBtn;
    }

    public String getPassword() {
        return this.password.getText();
    }

    public String getUserName() {
        return this.userName.getText();
    }

    private void initComponent() {
        removeAll();
        revalidate();
        repaint();
        this.loginBtn = new JButton("Login");
        this.userNameL = new JLabel("Username");
        this.PasswordL = new JLabel("Password");
        this.userName = new JTextField(30);
        this.password = new JTextField(30);
        this.top = new JLabel("Login Here");
        this.top.setBounds(300, 2, 200, 80);
        this.top.setFont(new Font("Verdana", 2, 20));
        this.userNameL.setBounds(235, 60, 200, 80);
        this.userNameL.setFont(new Font("Verdana", 1, 15));
        this.userName.setBounds(this.userNameL.getX() + 50, this.userNameL.getY() + 60, 200, 30);
        this.PasswordL.setBounds(this.userNameL.getX(), this.userName.getY() + 5, 200, 80);
        this.PasswordL.setFont(new Font("Verdana", 1, 15));
        this.password.setBounds(this.userName.getX(), this.PasswordL.getY() + 60, 200, 30);
        this.loginBtn.setBounds(this.password.getX() + 40, this.password.getY() + 40, 100, 40);
        this.loginBtn.setFont(this.userNameL.getFont());
        add(this.top);
        add(this.userNameL);
        add(this.userName);
        add(this.PasswordL);
        add(this.password);
        add(this.loginBtn);
        setBackground(new Color(250, 250, 250));
        setLayout((LayoutManager)null);
    }

    public void setUserName() {
        this.userName.setText("");
    }

    public void setPassword() {
        this.password.setText("");
    }
}
