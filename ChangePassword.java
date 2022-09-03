import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePassword extends JPanel {

    private JTextField confirmPassword;

    private JTextField oldPassword;

    private JTextField newPassword;

    private JTextField userName;

    private JLabel PasswordL;

    private JLabel oldPasswordL;

    private JLabel newPasswordL;

    public ChangePassword() {
        initComponent();
    }

    private void initComponent() {
        JButton done = new JButton("Done");
        JLabel userNameL = new JLabel("Username");
        this.PasswordL = new JLabel("Confirm Password");
        this.userName = new JTextField(30);
        this.confirmPassword = new JTextField(30);
        this.oldPassword = new JTextField(30);
        this.newPassword = new JTextField(30);
        this.oldPasswordL = new JLabel("Old password");
        this.newPasswordL = new JLabel("New Password");
        userNameL.setBounds(100, getHeight() / 2, 200, 80);
        userNameL.setFont(new Font("Verdana", 1, 15));
        this.userName.setBounds(userNameL.getX() + 50, userNameL.getY() + 60, 200, 30);
        this.oldPasswordL.setBounds(userNameL.getX(), this.userName.getY() + 5, 200, 80);
        this.oldPasswordL.setFont(new Font("Verdana", 1, 15));
        this.oldPassword.setBounds(this.userName.getX(), this.oldPasswordL.getY() + 60, 200, 30);
        this.newPasswordL.setBounds(userNameL.getX(), this.oldPassword.getY() + 5, 200, 80);
        this.newPasswordL.setFont(new Font("Verdana", 1, 15));
        this.newPassword.setBounds(this.userName.getX(), this.newPasswordL.getY() + 60, 200, 30);
        this.PasswordL.setBounds(userNameL.getX(), this.newPassword.getY() + 5, 200, 80);
        this.PasswordL.setFont(new Font("Verdana", 1, 15));
        this.confirmPassword.setBounds(this.userName.getX(), this.PasswordL.getY() + 60, 200, 30);
        done.setBounds(this.confirmPassword.getX() + 40, this.confirmPassword.getY() + 40, 100, 40);
        done.setFont(userNameL.getFont());
        done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DatabaseOperation db = new DatabaseOperation();
                String username = ChangePassword.this.userName.getText();
                String oldpwd = ChangePassword.this.oldPassword.getText();
                String newpwd = ChangePassword.this.newPassword.getText();
                String cpwd = ChangePassword.this.confirmPassword.getText();
                db.changePassword(username, oldpwd, newpwd, cpwd);
                ChangePassword.this.userName.setText("");
                ChangePassword.this.oldPassword.setText("");
                ChangePassword.this.newPassword.setText("");
                ChangePassword.this.confirmPassword.setText("");
            }
        });
        add(userNameL);
        add(this.userName);
        add(this.oldPasswordL);
        add(this.oldPassword);
        add(this.newPasswordL);
        add(this.newPassword);
        add(this.PasswordL);
        add(this.confirmPassword);
        add(done);
        setBackground(Color.white);
        setLayout(null);
    }
}
