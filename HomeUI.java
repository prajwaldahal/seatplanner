import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class HomeUI extends JFrame {
    private JButton Back;
    private JList<String> jl,jlRoom;
    private JButton NextColumn;
    private JButton AddRoom;
    private JButton SelectFacultybtn;
    private JPanel ContentPanel;
    private JPanel StudentListPanel;
    private JPanel RoomPanel;
    private JPanel SelectFacultyPanel;
    private JPanel PlanSeatPanel;
    private JPanel ColumnPanel;
    private JPanel FileListPanel;
    private JPanel addStudentPanel;
    private JTextField Name,LName,Room;
    private JComboBox<String> Faculty;
    private JComboBox<String> Semester;
    private JLabel RoomSelect;
    private JLabel FacultySelect;
    private JTextField StudentId,ColumnNamejt,ColumnNamejt2,ColumnNamejt3;
    private JLabel ColumnName;
    private JLabel ColumnName2;
    private JLabel ColumnName3;
    private String storeFaculty,storeSemester,storeSeat,strRoom,sql;
    private DatabaseOperation db;
    private JTable TableM;
    public HomeUI()
    {
        initComponents();
    }
    public void initComponents()
    {
        String[] sem = {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th"};
        String[] faculty = {"BCA", "BBA", "BIM"};
        String[] seat = {"2", "3"};
        db = new DatabaseOperation();
        JPanel buttonPanel = new JPanel();
        SelectFacultyPanel = new JPanel();
        JButton addStudent = new JButton("Add Student");
        JButton fileList = new JButton("Files");
        FileListPanel = new JPanel();
        NextColumn = new JButton("Add");
        JButton add = new JButton("Add");
        JButton next = new JButton("Continue");
        Back = new JButton("Back");
        AddRoom = new JButton("Select");
        SelectFacultybtn = new JButton("Select");
        JButton studentList = new JButton("Manage Student");
        JButton manageRoom = new JButton("Add Room");
        JButton planSeat = new JButton("Plan Seat");
        ContentPanel = new JPanel(new CardLayout());
        StudentListPanel = new JPanel();
        JPanel manageRoomPanel = new JPanel();
        addStudentPanel = new JPanel();
        JPanel seatPlanPanel = new JPanel();
        PlanSeatPanel = new JPanel();
        ColumnPanel = new JPanel(null);
        ColumnName=new JLabel("Column1 no.of rows");
        ColumnName2=new JLabel("Column2 no.of rows");
        ColumnName3=new JLabel("Column3 no.of rows");
        RoomSelect=new JLabel("Select Room");
        FacultySelect=new JLabel("Select Faculty");
        ColumnNamejt=new JTextField(30);
        ColumnNamejt2=new JTextField(30);
        ColumnNamejt3=new JTextField(30);
        RoomPanel = new JPanel(new CardLayout());
        JPanel roomInnerPanel = new JPanel();
        Name = new JTextField(30);
        JLabel nameL = new JLabel("FirstName:");
        LName = new JTextField(30);
        JLabel LNameL = new JLabel("LastName:");
        Room = new JTextField(30);
        JLabel roomL = new JLabel("RoomName");
        JLabel numberDesk = new JLabel("Number of Column:");
        JLabel numberPerSeat = new JLabel("Student Per Desk");
        Faculty = new JComboBox(faculty);
        JComboBox seatcb = new JComboBox(seat);
        JLabel facultyL = new JLabel("Faculty:");
        StudentId = new JTextField(30);
        Semester = new JComboBox(sem);
        JLabel studentIdL = new JLabel("StudentId:");
        setTitle("Seat Planner");
        setSize(780, 450);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        SelectFacultybtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facultySelectAction();
            }
        });
        fileList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileListAction();
            }
        });
        AddRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    roomInfoCollect();
                } catch (Exception ex) {
                    Msg.showMessage(ex.getMessage());
                }
            }
        });
        Faculty.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e)
            {
                storeFaculty(e);
            }
        });
        Semester.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e)
            {
                storeSemester(e);
            }
        });
        seatcb.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e)
            {
                storeSeat(e);
            }
        });
        studentList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                studentListActionPerformed();
            }
        });
        manageRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                manageRoomActionPerformed();
            }
        });
        addStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                addStudentActionPerformed();
            }
        });
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                nextActionPerformed();
            }
        });
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                formValidate();
            }
        });
        NextColumn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               nextColumnAction();
            }
        });
        Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               backAction();
            }
        });
        buttonPanel.add(addStudent);
        buttonPanel.add(manageRoom);
        buttonPanel.add(studentList);
        buttonPanel.add(fileList);
        buttonPanel.setLayout(null);
        add(buttonPanel);
        buttonPanel.setBounds(0, 0, 160, 360);
        buttonPanel.setBackground(new Color(204, 255, 255));

        addStudent.setFont(new Font("Verdana", 1, 12));
        addStudent.setBounds(20, 100, 130, 30);

        manageRoom.setFont(new Font("Verdana", 1, 12));
        manageRoom.setBounds(20, 140, 130, 30);

        studentList.setFont(new Font("Verdana", 1, 12));
        studentList.setBounds(20, 180, 130, 30);

        fileList.setFont(new Font("Verdana", 1, 12));
        fileList.setBounds(20, 220, 130, 30);
        StudentListPanel.setBackground(Color.white);
        StudentListPanel.setLayout(null);

        roomInnerPanel.setLayout(null);
        RoomPanel.setBounds(40, 20, 500, 360);
        RoomPanel.add(roomInnerPanel,"RIP");
        RoomPanel.add(ColumnPanel,"CP");

        roomL.setBounds(10, 30, 200, 80);
        roomL.setFont(new Font("Verdana", 1, 14));
        roomInnerPanel.add(roomL);

        Room.setBounds(30, 85, 200, 30);
        roomInnerPanel.add(Room);

        numberDesk.setBounds(10, 93, 200, 80);
        numberDesk.setFont(new Font("Verdana", 1, 14));
        roomInnerPanel.add(numberDesk);

        seatcb.setBounds(30, 145, 200, 30);
        roomInnerPanel.add(seatcb);
        storeSeat= (String) seatcb.getSelectedItem();

        next.setBounds(350, 300, 95, 30);
        roomInnerPanel.add(next);
        
        numberPerSeat.setBounds(100,230,150,80);
        numberPerSeat.setFont(new Font("Verdana", 1, 12));
        roomInnerPanel.setBackground(Color.white);
        manageRoomPanel.add(RoomPanel);

        manageRoomPanel.setBackground(Color.white);
        manageRoomPanel.setLayout(null);

        nameL.setBounds(100, 3, 200, 80);
        nameL.setFont(new Font("Verdana", 1, 12));
        addStudentPanel.add(nameL);

        Name.setBounds(150, 58, 200, 30);
        addStudentPanel.add(Name);

        LNameL.setBounds(100, 63, 200, 80);
        LNameL.setFont(new Font("Verdana", 1, 12));
        addStudentPanel.add(LNameL);

        LName.setBounds(150, 118, 200, 30);
        addStudentPanel.add(LName);

        facultyL.setBounds(100, 123, 200, 80);
        facultyL.setFont(new Font("Verdana", 1, 12));
        addStudentPanel.add(facultyL);

        Faculty.setBounds(150, 183, 200, 30);
        addStudentPanel.add(Faculty);
        Faculty.setSelectedIndex(0);
        storeFaculty= (String) Faculty.getSelectedItem();

        Semester.setBounds(355, 183, 200, 30);
        addStudentPanel.add(Semester);
        Semester.setSelectedIndex(0);
        storeSemester= (String) Semester.getSelectedItem();

        studentIdL.setBounds(100, 188, 200, 80);
        studentIdL.setFont(new Font("Verdana", 1, 12));
        addStudentPanel.add(studentIdL);

        StudentId.setBounds(150, 243, 200, 30);
        addStudentPanel.add(StudentId);

        add.setBounds(180, 290, 100, 30);
        addStudentPanel.add(add);

        addStudentPanel.setBackground(Color.white);
        addStudentPanel.setLayout(null);

        SelectFacultyPanel.setLayout(null);
        SelectFacultyPanel.setBackground(Color.white);
        ContentPanel.add(addStudentPanel, "ASP");
        ContentPanel.add(StudentListPanel, "SLP");
        ContentPanel.add(manageRoomPanel, "MRP");
        ContentPanel.add(PlanSeatPanel,"PSP");
        ContentPanel.add(SelectFacultyPanel,"SFP");
        ContentPanel.add(FileListPanel,"FLP");
        add(ContentPanel);
        ContentPanel.setBounds(161, 0, 610, 360);
        seatPlanPanel.add(planSeat);
        seatPlanPanel.setLayout(null);
        add(seatPlanPanel);
        seatPlanPanel.setBounds(0, 360, 770, 50);
        seatPlanPanel.setBackground(new Color(153, 255, 255));

        planSeat.setFont(new Font("Verdana", 1, 14));
        planSeat.setBounds(200, 10, 300, 30);

        PlanSeatPanel.setLayout(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                tempTableDispose();
            }
        });
        planSeat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                planSeatActionPerformed();
            }
        });
    }
    private void fileListAction() {
        Vector<String> name;
        FileList fls = new FileList();
        name =fls.list();
        FileListPanel.setLayout(null);
        FileListPanel.removeAll();
        FileListPanel.setBackground(Color.white);
        int xaxis=2;
        int yaxis=15;
        int height=30;
        int width =300;
        int i=1;
        for (String x:name ) {
            JLabel fileName = new JLabel(i+". "+x);
            JButton fileButton = new JButton("view");
            fileButton.setActionCommand(x);
            fileName.setFont(new Font("verdana",1,14));
            fileName.setFont(new Font("verdana",1,14));
            i++;
            fileName.setBounds(xaxis,yaxis,width,height);
            fileButton.setBounds(fileName.getWidth()+10,yaxis,70,30);
            yaxis+=50;
            FileListPanel.add(fileName);
            FileListPanel.add(fileButton);
            fileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fileOpen(e);
                }
            });
        }
        CardLayout cd = (CardLayout) ContentPanel.getLayout();
        cd.show(ContentPanel,"FLP");
    }

    private void fileOpen(ActionEvent e) {
        String name= e.getActionCommand();
        File f1 = new File("D:\\Seatplanner\\"+name);
        try {
            Desktop.getDesktop().open(f1);
        } catch (IOException ex) {
            Msg.showError("error opening file");
        }
    }

    private void facultySelectAction(){
        try {
            FacultyInfoCollect();
            addBoXItem();
            CardLayout cd = (CardLayout) ContentPanel.getLayout();
            cd.show(ContentPanel,"PSP");
        } catch (Exception e) {
           Msg.showMessage(e.getMessage());
        }
    }
    private void tempTableDispose() {
        db.deleteTempTable();
    }
    private void roomInfoCollect() throws Exception {
        List<String> RoomName = jlRoom.getSelectedValuesList();
        if(RoomName.isEmpty()){
            throw new Exception("please select at least one room");
        }
        tempTableDispose();
        db.createTempTable(sql);
        try {
            checkError(RoomName);
            roomDataCollect(RoomName);
        } catch (Exception e) {
            Msg.showMessage(e.getMessage());
        }
    }
    private void checkError(List<String> RoomName) throws Exception {
        RoomData rd = new RoomData();
        int totalStudent=db.countStudent();
        int totalRoom=rd.totalSeat(RoomName);
        if(totalStudent==-1)
            throw new Exception("error");
        if(totalRoom<totalStudent)
        {
            throw new Exception("seat is less than total student\ntotal Seat="+totalRoom+"\ntotal student="+totalStudent);
        }
    }
    private void roomDataCollect(List<String> RoomName)
    {
        int column,row;
        RoomData rd = new RoomData();
        ExcelOperation ex = new ExcelOperation();
        for(String x:RoomName)
        {
            column=rd.getColumn(x);
            ex.writeHeading(x);
            for (int i = 1; i<=column; i++) {
                row=rd.getRow(i,x);
                ex.createRows(row);
            }
        }
        ex.writeFile();
    }
    private void FacultyInfoCollect() throws Exception {
        List<String> Faculty = jl.getSelectedValuesList();
        if(Faculty.isEmpty()) {
            throw new Exception("please select at least one");
        }
        sql=Faculty.get(0);
        Faculty.remove(sql);
        sql="("+"'"+sql+"'";
        for(String x:Faculty){
            sql=sql+","+"'"+x+"'";
        }
        sql="insert into temp Select * from studentlist where faculty in "+sql+")" ;
    }
    private void addFacultyItem(){
        SelectFacultyPanel.removeAll();
        SelectFacultyPanel.repaint();
        DatabaseOperation FacultyChoosen = new DatabaseOperation();
        Vector <String> Faculty=FacultyChoosen.distinctFaculty();

        FacultySelect.setFont(new Font("verdana",1,16));
        FacultySelect.setBounds(50, 10, 200, 30);
        SelectFacultyPanel.add(FacultySelect);
        jl= new JList(Faculty);
        jl.setBackground(new Color(255,255,255));
        jl.setFont(new Font("verdana",1,14));
        JScrollPane jsp = new JScrollPane(jl);
        jsp.setBackground(Color.white);
        jsp.setBounds(10,40,SelectFacultyPanel.getWidth()-12,SelectFacultyPanel.getHeight()-200);
        SelectFacultyPanel.add(jsp);
        SelectFacultybtn.setBounds(SelectFacultyPanel.getWidth()/2-100,jsp.getHeight()+100,120,30);
        SelectFacultyPanel.add(SelectFacultybtn);
        SelectFacultybtn.setFont(new Font("verdana",1,14));
        PlanSeatPanel.add(AddRoom);
    }
    private void planSeatActionPerformed()
    {
        addFacultyItem();
        CardLayout cardLayout = (CardLayout)    ContentPanel.getLayout();
        cardLayout.show(ContentPanel, "SFP");
    }
    private void addBoXItem()
    {
        Vector <String> roomName;
        RoomData rd = new RoomData();
        roomName=rd.retrieveTableName();
        PlanSeatPanel.removeAll();
        PlanSeatPanel.repaint();
        RoomSelect.setFont(new Font("verdana",1,16));
        RoomSelect.setBounds(10, 10, 200, 30);
        PlanSeatPanel.add(RoomSelect);
        jlRoom=new JList<>(roomName);
        jlRoom.setFont(new Font("verdana",1,14));
        jlRoom.setBackground(Color.white);
        JScrollPane jsp = new JScrollPane(jlRoom);
        jsp.setBounds(10,40,PlanSeatPanel.getWidth()-12,PlanSeatPanel.getHeight()-200);
        AddRoom.setFont(new Font("verdana",1,14));
        AddRoom.setBounds(PlanSeatPanel.getWidth()/2-100,jsp.getHeight()+100,120,30);
        PlanSeatPanel.add(jsp);
        PlanSeatPanel.add(AddRoom);
    }
    private void backAction() {
        CardLayout cardLayout = (CardLayout) RoomPanel.getLayout();
        cardLayout.show(RoomPanel, "RIP");
    }
    private void nextColumnAction() {
        int s1[]=new int[3];
        s1[2]=0;
        try {
            s1[0] = parseInt(ColumnNamejt.getText().trim());
            ColumnNamejt.setText("");
            s1[1] = parseInt(ColumnNamejt2.getText().trim());
            ColumnNamejt2.setText("");
            ColumnNamejt2.setText("");
            if (storeSeat.equals("3")) {
                s1[2] = parseInt(ColumnNamejt3.getText().trim());
                ColumnNamejt3.setText("");
            }
            RoomData rd = new RoomData();
            for (int i = 0; i < 3; i++) {
                if(i==2&&s1[2]==0)
                    continue;
                rd.addData(strRoom,s1[i],i+1);
            }
            CardLayout cardLayout = (CardLayout) RoomPanel.getLayout();
            cardLayout.show(RoomPanel, "RIP");
        } catch (Exception e) {
            Msg.showError("should only contain number");
        }
    }
    private void addStudentActionPerformed()
    {
        CardLayout cardLayout = (CardLayout) ContentPanel.getLayout();
        cardLayout.show(ContentPanel, "ASP");
    }
    private void manageRoomActionPerformed()
    {
        CardLayout cardLayout = (CardLayout) ContentPanel.getLayout();
        cardLayout.show(ContentPanel, "MRP");
    }

    private void studentListActionPerformed()
    {
        CardLayout cardLayout = (CardLayout) ContentPanel.getLayout();
        cardLayout.show(ContentPanel, "SLP");
        showData();
    }
    public void showData(){
        StudentListPanel.removeAll();
        StudentListPanel.repaint();
        Vector<Student> StudentData= new Vector<>();
        ResultSet r= db.displayData();
        while(true)
        {
            try {
                if (!r.next())
                    break;
                Student s=new Student(r.getInt("studentid"),r.getString("name"),r.getString("faculty"));
                StudentData.add(s);
            } catch (SQLException e) {
               Msg.showError("error");
            }
        }
        String [] ColumnName = {"ID","Name","Faculty"};
        DefaultTableModel StudentTable = new DefaultTableModel();
        StudentTable.setColumnIdentifiers(ColumnName);
        Object[] row=new Object[3];
        for(Student s:StudentData)
        {
            row[0]=s.getStudentid();
            row[1]=s.getName();
            row[2]=s.getFaculty();
            StudentTable.addRow(row);
        }
        TableM=new JTable(StudentTable){
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };
        TableM.setFont(new Font("verdana",1,12));
        JButton Delete = new JButton("Delete");
        JButton Update = new JButton("Update");
        Delete.setFont(new Font("verdana",1,16));
        Update.setFont(Delete.getFont());
        Update.setBounds(ContentPanel.getWidth()-160, 3, 150,30);
        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteActionPerformed();
            }
        });
        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateActionPerformed();
            }
        });
        JScrollPane SP=new JScrollPane(TableM);
        SP.setBounds(2, 40,ContentPanel.getWidth()-3,ContentPanel.getHeight()-45);
        Delete.setBounds(2,3,150,30);
        StudentListPanel.add(Delete);
        StudentListPanel.add(Update);
        StudentListPanel.add(SP);
    }

    private void updateActionPerformed() {
        Update UpdatePanel = new Update();
        ContentPanel.add(UpdatePanel,"UPD");
        int row = TableM.getSelectedRow();
        if(row==-1){
            Msg.showMessage("please Select row in table");
            return;
        }
        String sid = TableM.getModel().getValueAt(row,0).toString().trim();
        String [] Name=TableM.getModel().getValueAt(row,1).toString().trim().split(" ");
        String firstnamev=Name[0];
        String lastnamev=Name[1];
        String [] Facultyv=TableM.getModel().getValueAt(row,2).toString().trim().split(" ");
        String coursev = Facultyv[0];
        String semesterv = Facultyv[1];
        System.out.println(coursev+" "+semesterv);
        UpdatePanel.setFirstName(firstnamev);
        UpdatePanel.setLastName(lastnamev);
        UpdatePanel.setFacultyCb(coursev);
        UpdatePanel.setSemesterCb(semesterv);
        UpdatePanel.setStudentId(sid);
        CardLayout cardLayout = (CardLayout) ContentPanel.getLayout();
        cardLayout.show(ContentPanel, "UPD");
    }

    private void deleteActionPerformed(){
        int row =TableM.getSelectedRow();
        if(row==-1){
            Msg.showMessage("please Select row in table");
            return;
        }
        String value=TableM.getModel().getValueAt(row,0).toString();
        int x=JOptionPane.showConfirmDialog(this,"do you want to delete Student with id "+value);
        if(x == JOptionPane.YES_OPTION)
        {
            db.deleteData(Integer.parseInt(value));
            showData();
            Msg.showMessage("sucessfully deleted");
        }
    }
    private void storeFaculty(ItemEvent e)
    {
        storeFaculty=(String) e.getItem();
    }
    private void storeSemester(ItemEvent e)
    {
        storeSemester=(String) e.getItem();
    }
    private void storeSeat(ItemEvent e)
    {
        storeSeat=(String) e.getItem();
    }
    private void formValidate()
    {
        int storeStudentId=0;
        boolean flag=true;
        String strName = null,strLName=null;

        try {
            strName= Name.getText().trim();
            strLName= LName.getText().trim();
            nameValidate(strLName);
            nameValidate(strName);
            try{
                storeStudentId=parseInt(StudentId.getText().trim());
            }catch(NumberFormatException e){
              Msg.showError("Student id should only contain Number");
              StudentId.setText("");
              flag=false;
            }
            if(flag){
                db.insertData(storeStudentId,strName+" "+strLName,storeFaculty,storeSemester);
                Name.setText("");
                LName.setText("");
                StudentId.setText("");
                Faculty.setSelectedIndex(0);
                Semester.setSelectedIndex(0);
            }
        } catch (Exception ex) {
            Msg.showError(ex.getMessage());
            Name.setText("");
            LName.setText("");
        }
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
    private void nextActionPerformed() {
        try {
            strRoom = Room.getText().trim();
            if (strRoom.isEmpty())
            {
                throw new RoomNameValidateException();
            }
            int strColumn = Integer.parseInt(storeSeat);
            try {
                RoomdataEntry();
                ColumnPanel.setBackground(Color.white);
                if (strColumn == 2) {
                    ColumnPanel.removeAll();
                    ColumnPanel.repaint();
                    ColumnPanel.add(ColumnName);
                    ColumnName.setBounds(10, 3, 200, 80);
                    ColumnName.setFont(new Font("Verdana", 1, 14));
                    ColumnPanel.add(ColumnNamejt);
                    ColumnNamejt.setBounds(50, 54, 200, 30);
                    ColumnPanel.add(ColumnName2);
                    ColumnName2.setBounds(10, 60, 200, 80);
                    ColumnName2.setFont(new Font("Verdana", 1, 14));
                    ColumnPanel.add(ColumnNamejt2);
                    ColumnNamejt2.setBounds(50, 111, 200, 30);
                } else {
                    ColumnPanel.add(ColumnName);
                    ColumnName.setBounds(10, 3, 200, 80);
                    ColumnName.setFont(new Font("Verdana", 1, 14));
                    ColumnPanel.add(ColumnNamejt);
                    ColumnNamejt.setBounds(50, 54, 200, 30);
                    ColumnPanel.add(ColumnName2);
                    ColumnName2.setBounds(10, 60, 200, 80);
                    ColumnName2.setFont(new Font("Verdana", 1, 14));
                    ColumnPanel.add(ColumnNamejt2);
                    ColumnNamejt2.setBounds(50, 111, 200, 30);
                    ColumnPanel.add(ColumnName3);
                    ColumnName3.setBounds(10, 117, 200, 80);
                    ColumnName3.setFont(new Font("Verdana", 1, 14));
                    ColumnPanel.add(ColumnNamejt3);
                    ColumnNamejt3.setBounds(50, 168, 200, 30);
                }
                ColumnPanel.add(NextColumn);
                NextColumn.setBounds(350, 300, 95, 30);
                ColumnPanel.add(Back);
                Back.setBounds(2, 300, 95, 30);
                CardLayout cd = (CardLayout) RoomPanel.getLayout();
                cd.show(RoomPanel,"CP");
            }catch (RoomAlreadyExistException | SQLException e){
                Msg.showMessage("room Already Exist or room name should not only be number");
                Room.setText("");
            }
        } catch (RoomNameValidateException ex) {
            Room.setText("");
            Msg.showMessage("submit valid information");
        }
    }
    private void RoomdataEntry() throws RoomAlreadyExistException, SQLException {
        RoomData rd = new RoomData();
        rd.addRoom(strRoom);
    }
    public static void main(String[] args) {
            new HomeUI();
    }
}
