import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Update extends JPanel {

    private JLabel StudentId;

    private JTextField FirstName;

    private JTextField LastName;

    private JComboBox<String> FacultyCb;

    private JComboBox<String> SemesterCb;

    public Update() {
        initComponent();
    }

    private void initComponent() {
        removeAll();
        repaint();
        revalidate();
        String[] sem = { "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th" };
        String[] faculty = { "BCA", "BBA", "BIM" };
        this.FirstName = new JTextField(30);
        this.LastName = new JTextField(30);
        JLabel faculty1 = new JLabel("Faculty");
        JLabel firstNameL = new JLabel("First Name:");
        JLabel lastNameL = new JLabel("Last Name:");
        this.FacultyCb = new JComboBox<>(faculty);
        this.SemesterCb = new JComboBox<>(sem);
        var updateBtn = new JButton("Update");
        this.StudentId = new JLabel();
        add(this.StudentId);
        firstNameL.setBounds(100, 3, 200, 80);
        firstNameL.setFont(new Font("Verdana", Font.BOLD, 12));
        add(firstNameL);
        this.FirstName.setBounds(150, 58, 200, 30);
        add(this.FirstName);
        lastNameL.setBounds(100, 63, 200, 80);
        lastNameL.setFont(new Font("Verdana", Font.BOLD, 12));
        add(lastNameL);
        this.LastName.setBounds(150, 118, 200, 30);
        add(this.LastName);
        faculty1.setBounds(100, 123, 200, 80);
        faculty1.setFont(new Font("Verdana", Font.BOLD, 12));
        add(faculty1);
        this.FacultyCb.setBounds(150, 183, 200, 30);
        add(this.FacultyCb);
        this.SemesterCb.setBounds(355, 183, 200, 30);
        add(this.SemesterCb);
        updateBtn.setBounds(180, 290, 100, 30);
        add(updateBtn);
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Update.this.UpdateActionPerformed();
            }
        });
        setBackground(Color.white);
        setLayout(null);
    }

    private void UpdateActionPerformed() {
        try {
            checkValidation();
            int x = JOptionPane.showConfirmDialog(new JFrame(), "do you want to update student with id = " + this.StudentId.getText());
            if (x == 0) {
                Student s2 = new Student(Integer.parseInt(this.StudentId.getText()), this.FirstName.getText() + " " + this.LastName.getText(), "" + this.FacultyCb.getSelectedItem() + " " + this.SemesterCb.getSelectedItem());
                DatabaseOperation dbex = new DatabaseOperation();
                dbex.Update(s2);
                initComponent();
            }
        } catch (Exception e) {
            Msg.showMessage(e.getMessage());
        }
    }

    private void checkValidation() throws Exception {
        String strName = this.FirstName.getText().trim();
        String strLName = this.LastName.getText().trim();
        nameValidate(strLName);
        nameValidate(strName);
    }

    private void nameValidate(String s) throws Exception {
        if (s.isEmpty())
            throw new Exception("field is empty");
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(s);
        if (m.find())
            throw new Exception("name cannot contain number and Special character");
        if (s.contains("_"))
            throw new Exception("name cannot contain number and Special character");
        p = Pattern.compile("\\W");
        m = p.matcher(s);
        if (m.find())
            throw new Exception("name cannot contain number and Special character");
    }

    public void setFirstName(String firstName) {
        this.FirstName.setText(firstName);
    }

    public void setLastName(String lastname) {
        this.LastName.setText(lastname);
    }

    public void setFacultyCb(String faculty) {
        this.FacultyCb.setSelectedItem(faculty);
    }

    public void setSemesterCb(String semester) {
        this.SemesterCb.setSelectedItem(semester);
    }

    public void setStudentId(String studentId) {
        this.StudentId.setText(studentId);
    }
}
