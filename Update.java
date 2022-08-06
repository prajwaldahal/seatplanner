import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Update extends JPanel {
    private JLabel FirstNameL;
    private JLabel StudentId;
    private JLabel LastNameL;
    private JLabel Faculty;
    private JButton UpdateBtn;
    private JTextField FirstName;
    private JTextField LastName;
    private JComboBox<String> FacultyCb;
    private JComboBox<String> SemesterCb;

    public Update() {
        initComponent();
    }

    private void initComponent() {
        String[] sem = {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th"};
        String[] faculty = {"BCA", "BBA", "BIM"};
        FirstName = new JTextField(30);
        LastName=new JTextField(30);
        Faculty=new JLabel("Faculty");
        FirstNameL = new JLabel("First Name:");
        LastNameL = new JLabel("Last Name:");
        FacultyCb= new JComboBox<>(faculty);
        SemesterCb= new JComboBox<>(sem);
        UpdateBtn=new JButton("Update");
        StudentId=new JLabel();
        add(StudentId);
        FirstNameL.setBounds(100, 3, 200, 80);
        FirstNameL.setFont(new Font("Verdana", 1, 12));
        add(FirstNameL);

        FirstName.setBounds(150, 58, 200, 30);
        add(FirstName);

        LastNameL.setBounds(100, 63, 200, 80);
        LastNameL.setFont(new Font("Verdana", 1, 12));
        add(LastNameL);

        LastName.setBounds(150, 118, 200, 30);
        add(LastName);

        Faculty.setBounds(100, 123, 200, 80);
        Faculty.setFont(new Font("Verdana", 1, 12));
        add(Faculty);

        FacultyCb.setBounds(150, 183, 200, 30);
        add(FacultyCb);

        SemesterCb.setBounds(355, 183, 200, 30);
        add(SemesterCb);

        UpdateBtn.setBounds(180, 290, 100, 30);
        add(UpdateBtn);
        UpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateActionPerformed();
            }
        });

        setBackground(Color.white);
        setLayout(null);
    }

    private void UpdateActionPerformed(){
        try {
            checkValidation();
            int x = JOptionPane.showConfirmDialog(new JFrame(),"do you want to update student with id = "+StudentId.getText());
            if(x==0){
                Student s2 = new Student(parseInt(StudentId.getText()),FirstName.getText()+" "+LastName.getText(),FacultyCb.getSelectedItem()+" "+SemesterCb.getSelectedItem());
                DatabaseOperation dbex = new DatabaseOperation();
                dbex.Update(s2);
            }
        } catch (Exception e) {
            Msg.showMessage(e.getMessage());
        }
    }
    private void checkValidation() throws Exception {
        String strName,strLName;

            strName= FirstName.getText().trim();
            strLName= LastName.getText().trim();
            nameValidate(strLName);
            nameValidate(strName);
    }
    private void nameValidate( String s ) throws Exception
    {
        if(s.isEmpty())
            throw new Exception("field is empty");
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher( s );
        if(m.find())
            throw new Exception("name cannot contain number and Special character");
        else if(s.contains("_"))
            throw new Exception("name cannot contain number and Special character");
        else
        {
            p = Pattern.compile("\\W");
            m = p.matcher( s );
            if(m.find())
            {
                throw new Exception("name cannot contain number and Special character");
            }
        }
    }
    public void setFirstName(String firstName) {
        FirstName.setText(firstName);
    }

    public void setLastName(String lastname) {
        LastName.setText(lastname);
    }

    public void setFacultyCb(String faculty) {
        FacultyCb.setSelectedItem(faculty);
    }

    public void setSemesterCb(String semester) {
        SemesterCb.setSelectedItem(semester);
    }
    public void setStudentId(String studentId) {
        StudentId.setText(studentId);
    }
}
