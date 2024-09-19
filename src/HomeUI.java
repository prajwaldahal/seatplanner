import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.List;
import java.util.regex.*;


public class HomeUI extends JFrame {
    private JButton Back;

    private JList<String> jl;

    private JList<String> jlRoom;

    private JButton AddRoomBtn;

    private JButton SelectRoom;

    private JButton SelectFacultybtn;

    private JPanel ContentPanel;

    private JPanel StudentListPanel;

    private JPanel RoomPanel;

    private JPanel SelectFacultyPanel;

    private JPanel PlanSeatPanel;

    private JPanel ColumnPanel;

    private JPanel FileListPanel;

    private JTextField Name;

    private JTextField LName;

    private JTextField Room;

    private JComboBox<String> Faculty;

    private JComboBox<String> Semester;

    private JLabel RoomSelect;

    private JLabel FacultySelect;

    private JTextField StudentId;

    private JTextField ColumnNamejt;

    private JTextField ColumnNamejt2;

    private JTextField ColumnNamejt3;

    private JLabel ColumnName;

    private JLabel ColumnName2;

    private JLabel ColumnName3;

    JComboBox ColumnNo;

    private String strRoom;

    private String sql;

    private DatabaseOperation db;

    private JTable TableM;

    private Container Cp;

    public HomeUI() {
        initComponents();
    }

    public void initComponents() {
        String[] sem = { "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th" };
        String[] faculty = { "BCA", "BBA", "BIM","C.E"};
        String[] seat = { "2", "3" };
        db = new DatabaseOperation();
        JPanel loggedIn = new JPanel(null);
        JPanel buttonPanel = new JPanel();
        this.SelectFacultyPanel = new JPanel();
        JButton fileList = new JButton("Files");
        this.FileListPanel = new JPanel();
        JButton logOut = new JButton("Logout");
        JButton changePassword = new JButton("<html>Change<br>Password</html>");
        this.AddRoomBtn = new JButton("Add");
        JButton AddStudentBtn = new JButton("Add");
        JButton next = new JButton("Continue");
        this.Back = new JButton("Back");
        this.SelectRoom = new JButton("Select");
        this.SelectFacultybtn = new JButton("Select");
        JButton studentList = new JButton("<html>Manage<br>Student</html>");
        JButton manageRoom = new JButton("<html>Manage<br>Room</html>");
        JButton planSeat = new JButton("Plan Seat");
        this.ContentPanel = new JPanel(new CardLayout());
        this.StudentListPanel = new JPanel();
        JPanel AddRoomPanel = new JPanel();
        JPanel addStudentPanel = new JPanel();
        JPanel seatPlanPanel = new JPanel();
        this.PlanSeatPanel = new JPanel();
        this.ColumnPanel = new JPanel(null);
        this.ColumnName = new JLabel("Column1 no.of rows");
        this.ColumnName2 = new JLabel("Column2 no.of rows");
        this.ColumnName3 = new JLabel("Column3 no.of rows");
        this.RoomSelect = new JLabel("Select Room");
        this.FacultySelect = new JLabel("Select Faculty");
        this.ColumnNamejt = new JTextField(30);
        this.ColumnNamejt2 = new JTextField(30);
        this.ColumnNamejt3 = new JTextField(30);
        this.RoomPanel = new JPanel(new CardLayout());
        JPanel roomInnerPanel = new JPanel();
        this.Name = new JTextField(30);
        JLabel nameL = new JLabel("FirstName:");
        this.LName = new JTextField(30);
        JLabel LNameL = new JLabel("LastName:");
        this.Room = new JTextField(30);
        JLabel roomL = new JLabel("RoomName");
        JLabel NoColumnL = new JLabel("Number of Column:");
        JLabel numberPerSeat = new JLabel("Student Per Desk");
        this.Faculty = new JComboBox<>(faculty);
        this.ColumnNo = new JComboBox<>(seat);
        JLabel facultyL = new JLabel("Faculty:");
        this.StudentId = new JTextField(30);
        this.Semester = new JComboBox<>(sem);
        JLabel studentIdL = new JLabel("StudentId:");
        this.Cp = getContentPane();
        this.Cp.setLayout(new CardLayout());
        setTitle("Seat Planner");
        setSize(780, 450);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.SelectFacultybtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeUI.this.facultySelectAction();
            }
        });
        fileList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeUI.this.fileListAction();
            }
        });
        this.SelectRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    HomeUI.this.roomInfoCollect();
                } catch (Exception ex) {
                    Msg.showMessage(ex.getMessage());
                }
            }
        });
        manageRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeUI.this.manageRoomActionPerformed();
            }
        });
        studentList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                HomeUI.this.studentListActionPerformed();
            }
        });
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                HomeUI.this.nextActionPerformed();
            }
        });
        AddStudentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                HomeUI.this.formValidate();
            }
        });
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeUI.this.logOutPasswordAction();
            }
        });
        changePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeUI.this.changePasswordAction();
            }
        });
        this.AddRoomBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeUI.this.nextColumnAction();
            }
        });
        this.Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeUI.this.backAction();
            }
        });
        buttonPanel.add(studentList);
        buttonPanel.add(fileList);
        buttonPanel.add(manageRoom);
        buttonPanel.add(changePassword);
        buttonPanel.add(logOut);
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(0, 0, 160, 360);
        buttonPanel.setBackground(new Color(204, 255, 255));
        loggedIn.add(buttonPanel);
        manageRoom.setFont(new Font("Verdana", Font.BOLD, 14));
        manageRoom.setBounds(2, 105, 155, 35);
        studentList.setFont(manageRoom.getFont());
        studentList.setBounds(2, 60, 155, 35);
        fileList.setFont(manageRoom.getFont());
        fileList.setBounds(2, 150, 155, 35);
        changePassword.setBounds(2, 195, 155, 35);
        changePassword.setFont(manageRoom.getFont());
        logOut.setBounds(2, 240, 155, 35);
        logOut.setFont(manageRoom.getFont());
        this.StudentListPanel.setBackground(Color.white);
        this.StudentListPanel.setLayout(null);
        roomInnerPanel.setLayout(null);
        this.RoomPanel.setBounds(40, 20, 500, 360);
        this.RoomPanel.add(roomInnerPanel, "RIP");
        this.RoomPanel.add(this.ColumnPanel, "CP");
        roomL.setBounds(10, 30, 200, 80);
        roomL.setFont(new Font("Verdana", Font.BOLD, 14));
        roomInnerPanel.add(roomL);
        this.Room.setBounds(30, 85, 200, 30);
        roomInnerPanel.add(this.Room);
        NoColumnL.setBounds(10, 93, 200, 80);
        NoColumnL.setFont(new Font("Verdana", Font.BOLD, 14));
        roomInnerPanel.add(NoColumnL);
        this.ColumnNo.setBounds(30, 145, 200, 30);
        roomInnerPanel.add(this.ColumnNo);
        next.setBounds(350, 300, 95, 30);
        roomInnerPanel.add(next);
        numberPerSeat.setBounds(100, 230, 150, 80);
        numberPerSeat.setFont(new Font("Verdana", Font.BOLD, 12));
        roomInnerPanel.setBackground(Color.white);
        AddRoomPanel.add(this.RoomPanel);
        AddRoomPanel.setBackground(Color.white);
        AddRoomPanel.setLayout(null);
        nameL.setBounds(100, 3, 200, 80);
        nameL.setFont(new Font("Verdana", Font.BOLD, 12));
        addStudentPanel.add(nameL);
        this.Name.setBounds(150, 58, 200, 30);
        addStudentPanel.add(this.Name);
        LNameL.setBounds(100, 63, 200, 80);
        LNameL.setFont(new Font("Verdana", Font.BOLD, 12));
        addStudentPanel.add(LNameL);
        this.LName.setBounds(150, 118, 200, 30);
        addStudentPanel.add(this.LName);
        facultyL.setBounds(100, 123, 200, 80);
        facultyL.setFont(new Font("Verdana", Font.BOLD, 12));
        addStudentPanel.add(facultyL);
        this.Faculty.setBounds(150, 183, 200, 30);
        addStudentPanel.add(this.Faculty);
        this.Faculty.setSelectedIndex(0);
        this.Semester.setBounds(355, 183, 200, 30);
        addStudentPanel.add(this.Semester);
        this.Semester.setSelectedIndex(0);
        studentIdL.setBounds(100, 188, 200, 80);
        studentIdL.setFont(new Font("Verdana", Font.BOLD, 12));
        addStudentPanel.add(studentIdL);
        this.StudentId.setBounds(150, 243, 200, 30);
        addStudentPanel.add(this.StudentId);
        AddStudentBtn.setBounds(180, 290, 100, 30);
        addStudentPanel.add(AddStudentBtn);
        addStudentPanel.setBackground(Color.white);
        addStudentPanel.setLayout(null);
        this.SelectFacultyPanel.setLayout(null);
        this.SelectFacultyPanel.setBackground(Color.white);
        this.ContentPanel.add(addStudentPanel, "ASP");
        this.ContentPanel.add(this.FileListPanel, "FLP");
        this.ContentPanel.add(this.StudentListPanel, "SLP");
        this.ContentPanel.add(AddRoomPanel, "ARP");
        this.ContentPanel.add(this.PlanSeatPanel, "PSP");
        this.ContentPanel.add(this.SelectFacultyPanel, "SFP");
        loggedIn.add(this.ContentPanel);
        this.ContentPanel.setBounds(161, 0, 610, 359);
        seatPlanPanel.add(planSeat);
        seatPlanPanel.setLayout(null);
        loggedIn.add(seatPlanPanel);
        seatPlanPanel.setBounds(0, 360, 770, 50);
        seatPlanPanel.setBackground(new Color(153, 255, 255));
        planSeat.setFont(new Font("Verdana", Font.BOLD, 14));
        planSeat.setBounds(200, 10, 300, 30);
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.PlanSeatPanel.setLayout(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                tempTableDispose();
            }
        });
        planSeat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                HomeUI.this.planSeatActionPerformed();
            }
        });
        final LoginPanel Lp = new LoginPanel();
        this.Cp.add("Lp", Lp);
        this.Cp.add("LL", loggedIn);
        JButton login = Lp.getLoginBtn();
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(Lp);
            }
        });
    }

    private void logOutPasswordAction() {
        int x = JOptionPane.showConfirmDialog(new JFrame(), "do you want to  logout?");
        if (x == 0) {
            CardLayout cardLayout = (CardLayout)this.Cp.getLayout();
            cardLayout.show(this.Cp, "Lp");
        }
    }

    private void changePasswordAction() {
        this.ContentPanel.add("CP", new ChangePassword());
        CardLayout cardLayout = (CardLayout)this.ContentPanel.getLayout();
        cardLayout.show(this.ContentPanel, "CP");
    }

    private void loginActionPerformed(LoginPanel Lp) {
        String userName = Lp.getUserName();
        String pwd = Lp.getPassword();
        Lp.setUserName();
        Lp.setPassword();
        boolean k = db.checkLogin(userName, pwd);
        if (k) {
            CardLayout cardLayout = (CardLayout)this.Cp.getLayout();
            cardLayout.show(this.Cp, "LL");
        } else {
            Msg.showMessage("please check credential");
        }
    }

    private void manageRoomActionPerformed() {
        final ManageRoomPanel manageRoomPanel = new ManageRoomPanel();
        UpdateRoom UR = manageRoomPanel.getUR();
        this.ContentPanel.add(manageRoomPanel, "MRP");
        this.ContentPanel.add(UR, "UR");
        CardLayout cardLayout = (CardLayout)this.ContentPanel.getLayout();
        cardLayout.show(this.ContentPanel, "MRP");
        JButton AddRoom = manageRoomPanel.getAdd();
        JButton Update = manageRoomPanel.getUpdate();
        AddRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeUI.this.AddRoomActionPerformed();
            }
        });
        Update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTable table1 = manageRoomPanel.getTableM();
                if (table1.getSelectedRow() == -1) {
                    Msg.showMessage("please select at least one row");
                    return;
                }
                int x = JOptionPane.showConfirmDialog(new JFrame(), "do you want to update room");
                if (x == 0) {
                    manageRoomPanel.updateActionPerformed();
                    CardLayout cardLayout = (CardLayout)HomeUI.this.ContentPanel.getLayout();
                    cardLayout.show(HomeUI.this.ContentPanel, "UR");
                }
            }
        });
    }

    private void fileListAction() {
        FileList fls = new FileList();
        Vector<String> name = fls.list();
        System.out.println(name);
        this.FileListPanel.setLayout(null);
        this.FileListPanel.removeAll();
        FileListPanel.revalidate();
        FileListPanel.repaint();
        this.FileListPanel.setBackground(Color.white);
        int xaxis = 2;
        int yaxis = 15;
        int height = 30;
        int width = 300;
        int i = 1;
        for (String x : name) {
            JLabel fileName;
            if (x.equals("System Cannot Find Files")){
                 fileName = new JLabel(x);
            }
            else{
                fileName = new JLabel("" + i + "." + x);
            }
            JButton fileButton = new JButton("view");
            fileButton.setActionCommand(x);
            fileName.setFont(new Font("verdana", 1, 14));
            fileName.setFont(new Font("verdana", 1, 14));
            i++;
            fileName.setBounds(xaxis, yaxis, width, height);
            if (!x.equals("System Cannot Find Files")){
                fileButton.setBounds(fileName.getWidth() + 10, yaxis, 70, 30);
                this.FileListPanel.add(fileButton);
            }
            yaxis += 50;
            this.FileListPanel.add(fileName);
            fileButton.addActionListener(this::fileOpen);
        }
        CardLayout cd = (CardLayout)this.ContentPanel.getLayout();
        cd.show(this.ContentPanel, "FLP");
    }
    private void fileOpen(ActionEvent e) {
        String name = e.getActionCommand();
        File f1 = new File("D:\\Seatplanner\\" + name);
        try {
            Desktop.getDesktop().open(f1);
        } catch (IOException ex) {
            Msg.showError("error opening file");
        }
    }

    private void facultySelectAction() {
        try {
            FacultyInfoCollect();
            addBoXItem();
            CardLayout cd = (CardLayout)this.ContentPanel.getLayout();
            cd.show(this.ContentPanel, "PSP");
        } catch (Exception e) {
            Msg.showMessage(e.getMessage());
        }
    }

    private void tempTableDispose() {
        db.deleteTempTable();
    }

    private void roomInfoCollect() throws Exception {
        List<String> RoomName = this.jlRoom.getSelectedValuesList();
        if (RoomName.isEmpty())
            throw new Exception("please select at least one room");
        tempTableDispose();
        db.createTempTable(this.sql);
        try {
            checkError(RoomName);
            roomDataCollect(RoomName);
        } catch (Exception e) {
            Msg.showMessage(e.getMessage());
        }
    }

    private void checkError(List<String> RoomName) throws Exception {
        RoomData rd = new RoomData();
        int totalStudent = this.db.countStudent();
        int totalRoom = rd.totalSeat(RoomName);
        if (totalStudent == -1)
            throw new Exception("error");
        if (totalRoom < totalStudent)
            throw new Exception("seat is less than total student\ntotal Seat=" + totalRoom + "\ntotal student=" + totalStudent);
    }

    private void roomDataCollect(List<String> RoomName) {
        RoomData rd = new RoomData();
        ExcelOperation ex = new ExcelOperation();
        for (String x : RoomName) {
            int column = rd.getColumn(x);
            ex.writeHeading(x);
            for (int i = 1; i <= column; i++) {
                int row = rd.getRow(i, x);
                ex.writeRows(row);
            }
        }
        ex.writeFile();
    }

    private void FacultyInfoCollect() throws Exception {
        List<String> Faculty = this.jl.getSelectedValuesList();
        if (Faculty.isEmpty())
            throw new Exception("please select at least one");
        this.sql = Faculty.get(0);
        Faculty.remove(this.sql);
        this.sql = "('" + this.sql + "'";
        for (String name : Faculty)
            this.sql = this.sql + (",'" + name + "'");
        this.sql = "insert into temp Select * from studentlist where faculty in " + this.sql + ")";
    }

    private void addFacultyItem() {
        this.SelectFacultyPanel.removeAll();
        this.SelectFacultyPanel.repaint();
        DatabaseOperation FacultyChoosen = new DatabaseOperation();
        Vector<String> Faculty = FacultyChoosen.distinctFaculty();
        this.FacultySelect.setFont(new Font("verdana", 1, 16));
        this.FacultySelect.setBounds(50, 10, 200, 30);
        this.SelectFacultyPanel.add(this.FacultySelect);
        this.jl = new JList<>(Faculty);
        this.jl.setBackground(new Color(255, 255, 255));
        this.jl.setFont(new Font("verdana", 1, 14));
        JScrollPane jsp = new JScrollPane(this.jl);
        jsp.setBackground(Color.white);
        jsp.setBounds(10, 40, this.SelectFacultyPanel.getWidth() - 12, this.SelectFacultyPanel.getHeight() - 200);
        this.SelectFacultyPanel.add(jsp);
        this.SelectFacultybtn.setBounds(this.SelectFacultyPanel.getWidth() / 2 - 100, jsp.getHeight() + 100, 120, 30);
        this.SelectFacultyPanel.add(this.SelectFacultybtn);
        this.SelectFacultybtn.setFont(new Font("verdana", 1, 14));
        this.PlanSeatPanel.add(this.SelectRoom);
    }

    private void planSeatActionPerformed() {
        addFacultyItem();
        CardLayout cardLayout = (CardLayout)this.ContentPanel.getLayout();
        cardLayout.show(this.ContentPanel, "SFP");
    }

    private void addBoXItem() {
        RoomData rd = new RoomData();
        Vector<String> roomName = rd.retrieveTableName();
        this.PlanSeatPanel.removeAll();
        this.PlanSeatPanel.repaint();
        this.RoomSelect.setFont(new Font("verdana", 1, 16));
        this.RoomSelect.setBounds(10, 10, 200, 30);
        this.PlanSeatPanel.add(this.RoomSelect);
        this.jlRoom = new JList<>(roomName);
        this.jlRoom.setFont(new Font("verdana", 1, 14));
        this.jlRoom.setBackground(Color.white);
        JScrollPane jsp = new JScrollPane(this.jlRoom);
        jsp.setBounds(10, 40, this.PlanSeatPanel.getWidth() - 12, this.PlanSeatPanel.getHeight() - 200);
        this.SelectRoom.setFont(new Font("verdana", 1, 14));
        this.SelectRoom.setBounds(this.PlanSeatPanel.getWidth() / 2 - 100, jsp.getHeight() + 100, 120, 30);
        this.PlanSeatPanel.add(jsp);
        this.PlanSeatPanel.add(this.SelectRoom);
    }

    private void backAction() {
        CardLayout cardLayout = (CardLayout)this.RoomPanel.getLayout();
        cardLayout.show(this.RoomPanel, "RIP");
    }

    private void nextColumnAction() {
        int[] s1 = new int[3];
        try {
            s1[0] = Integer.parseInt(this.ColumnNamejt.getText().trim());
            this.ColumnNamejt.setText("");
            s1[1] = Integer.parseInt(this.ColumnNamejt2.getText().trim());
            this.ColumnNamejt2.setText("");
            this.ColumnNamejt2.setText("");
            if (this.ColumnNo.getSelectedItem().equals("3")) {
                s1[2] = Integer.parseInt(this.ColumnNamejt3.getText().trim());
                this.ColumnNamejt3.setText("");
            }
            RoomData rd = new RoomData();
            for (int i = 0; i < 3; i++) {
                if (i != 2 || s1[2] != 0)
                    rd.addData(this.strRoom, s1[i], i + 1,s1[2]);
            }
            CardLayout cardLayout = (CardLayout)this.RoomPanel.getLayout();
            cardLayout.show(this.RoomPanel, "RIP");
        } catch (Exception e) {
            Msg.showError("should only contain number");
        }
    }

    private void addStudentActionPerformed() {
        CardLayout cardLayout = (CardLayout)this.ContentPanel.getLayout();
        cardLayout.show(this.ContentPanel, "ASP");
    }

    private void AddRoomActionPerformed() {
        CardLayout cardLayout = (CardLayout)this.ContentPanel.getLayout();
        cardLayout.show(this.ContentPanel, "ARP");
    }

    private void studentListActionPerformed() {
        CardLayout cardLayout = (CardLayout)this.ContentPanel.getLayout();
        cardLayout.show(this.ContentPanel, "SLP");
        showData();
    }

    public void showData() {
        this.StudentListPanel.removeAll();
        this.StudentListPanel.repaint();
        Vector<Student> StudentData = new Vector<>();
        ResultSet r = this.db.displayData();
        while (true) {
            try {
                if (!r.next())
                    break;
                Student s = new Student(r.getInt("studentid"), r.getString("name"), r.getString("faculty"));
                StudentData.add(s);
            } catch (SQLException e) {
                Msg.showError("error");
            }
        }
        String[] ColumnName = { "ID", "Name", "Faculty" };
        DefaultTableModel StudentTable = new DefaultTableModel();
        StudentTable.setColumnIdentifiers(ColumnName);
        Object[] row = new Object[3];
        for (Student s : StudentData) {
            row[0] = s.getStudentid();
            row[1] = s.getName();
            row[2] = s.getFaculty();
            StudentTable.addRow(row);
        }
        this.TableM = new JTable(StudentTable) {
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };
        this.TableM.setFont(new Font("verdana", 1, 12));
        JButton Add = new JButton("Add Student");
        JButton Delete = new JButton("Delete");
        JButton Update = new JButton("Update");
        Delete.setBounds(5, 3, 150, 30);
        Delete.setFont(new Font("verdana", 1, 16));
        Add.setBounds(Delete.getWidth() + 75, 3, 150, 30);
        Add.setFont(Delete.getFont());
        Update.setFont(Delete.getFont());
        Update.setBounds(this.ContentPanel.getWidth() - 160, 3, 150, 30);
        Add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeUI.this.addStudentActionPerformed();
            }
        });
        Delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeUI.this.deleteActionPerformed();
            }
        });
        Update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeUI.this.updateActionPerformed();
            }
        });
        JScrollPane SP = new JScrollPane(this.TableM);
        SP.setBounds(2, 40, this.ContentPanel.getWidth() - 3, this.ContentPanel.getHeight() - 45);
        this.StudentListPanel.add(Delete);
        this.StudentListPanel.add(Update);
        this.StudentListPanel.add(Add);
        this.StudentListPanel.add(SP);
    }

    private void updateActionPerformed() {
        Update UpdatePanel = new Update();
        this.ContentPanel.add(UpdatePanel, "UPD");
        int row = this.TableM.getSelectedRow();
        if (row == -1) {
            Msg.showMessage("please Select row in table");
            return;
        }
        String sid = this.TableM.getModel().getValueAt(row, 0).toString().trim();
        String[] Name = this.TableM.getModel().getValueAt(row, 1).toString().trim().split(" ");
        String firstnamev = Name[0];
        String lastnamev = Name[1];
        String[] Facultyv = this.TableM.getModel().getValueAt(row, 2).toString().trim().split(" ");
        String coursev = Facultyv[0];
        String semesterv = Facultyv[1];
        UpdatePanel.setFirstName(firstnamev);
        UpdatePanel.setLastName(lastnamev);
        UpdatePanel.setFacultyCb(coursev);
        UpdatePanel.setSemesterCb(semesterv);
        UpdatePanel.setStudentId(sid);
        CardLayout cardLayout = (CardLayout)this.ContentPanel.getLayout();
        cardLayout.show(this.ContentPanel, "UPD");
    }

    private void deleteActionPerformed() {
        int row = this.TableM.getSelectedRow();
        if (row == -1) {
            Msg.showMessage("please Select row in table");
            return;
        }
        String value = this.TableM.getModel().getValueAt(row, 0).toString();
        int x = JOptionPane.showConfirmDialog(this, "do you want to delete Student with id " + value);
        if (x == 0) {
            this.db.deleteData(Integer.parseInt(value));
            showData();
            Msg.showMessage("sucessfully deleted");
        }
    }

    private void formValidate() {
        int storeStudentId = 0;
        boolean flag = true;
        try {
            String strName = this.Name.getText().trim();
            String strLName = this.LName.getText().trim();
            nameValidate(strLName);
            nameValidate(strName);
            try {
                storeStudentId = Integer.parseInt(this.StudentId.getText().trim());
            } catch (NumberFormatException e) {
                Msg.showError("Student id should only contain Number");
                this.StudentId.setText("");
                flag = false;
            }
            if (flag) {
                this.db.insertData(storeStudentId, strName + " " + strLName, (String)this.Faculty.getSelectedItem(), (String)this.Semester.getSelectedItem());
                this.Name.setText("");
                this.LName.setText("");
                this.StudentId.setText("");
                this.Faculty.setSelectedIndex(0);
                this.Semester.setSelectedIndex(0);
            }
        } catch (Exception ex) {
            Msg.showError(ex.getMessage());
            this.Name.setText("");
            this.LName.setText("");
        }
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

    private void nextActionPerformed() {
        RoomData rd = new RoomData();
        this.strRoom = this.Room.getText().trim();
        if (this.strRoom.isEmpty()) {
            Msg.showError("field is empty");
            return;
        }
        char[] num = this.strRoom.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : num) {
            if (Character.isDigit(c))
                sb.append(c);
        }
        if (sb.length() == this.strRoom.length()) {
            Msg.showError("Room name shouldnot only contain number");
            return;
        }
        if (rd.checkTable(this.strRoom)) {
            Msg.showError("Room Already Exist");
            return;
        }
        String strColumn = (String)this.ColumnNo.getSelectedItem();
        this.ColumnPanel.setBackground(Color.white);
        assert strColumn != null;
        if (strColumn.equals("2")) {
            this.ColumnPanel.removeAll();
            this.ColumnPanel.repaint();
            this.ColumnPanel.add(this.ColumnName);
            this.ColumnName.setBounds(10, 3, 200, 80);
            this.ColumnName.setFont(new Font("Verdana", 1, 14));
            this.ColumnPanel.add(this.ColumnNamejt);
            this.ColumnNamejt.setBounds(50, 54, 200, 30);
            this.ColumnPanel.add(this.ColumnName2);
            this.ColumnName2.setBounds(10, 60, 200, 80);
            this.ColumnName2.setFont(new Font("Verdana", 1, 14));
            this.ColumnPanel.add(this.ColumnNamejt2);
            this.ColumnNamejt2.setBounds(50, 111, 200, 30);
        } else {
            this.ColumnPanel.removeAll();
            this.ColumnPanel.revalidate();
            this.ColumnPanel.repaint();
            this.ColumnPanel.add(this.ColumnName);
            this.ColumnName.setBounds(10, 3, 200, 80);
            this.ColumnName.setFont(new Font("Verdana", 1, 14));
            this.ColumnPanel.add(this.ColumnNamejt);
            this.ColumnNamejt.setBounds(50, 54, 200, 30);
            this.ColumnPanel.add(this.ColumnName2);
            this.ColumnName2.setBounds(10, 60, 200, 80);
            this.ColumnName2.setFont(new Font("Verdana", 1, 14));
            this.ColumnPanel.add(this.ColumnNamejt2);
            this.ColumnNamejt2.setBounds(50, 111, 200, 30);
            this.ColumnPanel.add(this.ColumnName3);
            this.ColumnName3.setBounds(10, 117, 200, 80);
            this.ColumnName3.setFont(new Font("Verdana", 1, 14));
            this.ColumnPanel.add(this.ColumnNamejt3);
            this.ColumnNamejt3.setBounds(50, 168, 200, 30);
        }
        this.ColumnPanel.add(this.AddRoomBtn);
        this.AddRoomBtn.setBounds(350, 300, 95, 30);
        this.ColumnPanel.add(this.Back);
        this.Back.setBounds(2, 300, 95, 30);
        CardLayout cd = (CardLayout)this.RoomPanel.getLayout();
        cd.show(this.RoomPanel, "CP");
    }

    public static void main(String[] args) {
        new HomeUI();
    }
}
