import java.sql.*;
import java.util.Vector;
public class DatabaseOperation {
    private Connection c;


    private String facultyStore = "";

    public DatabaseOperation() {
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3307/seatplanner", "root", "");
        } catch (Exception e) {
            Msg.showError("connection error");
            System.exit(1);
        }
    }

    public void insertData(int id, String name, String faculty, String semester) {
        try {
            PreparedStatement ps = c.prepareStatement("insert into studentlist values(?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, faculty + " " + semester);
            try {
                ps.executeUpdate();
            } catch (SQLIntegrityConstraintViolationException error) {
                Msg.showError("student id already used");
            }
        } catch (SQLException se) {
            Msg.showError("operation error");
        }
    }

    public void createTempTable(String sqlQuery) {
        try {
            Statement s = c.createStatement();
            s.executeUpdate("drop table if exists temp");
            s.executeUpdate("Create table temp(id int primary key AUTO_INCREMENT,name varchar(50),faculty varchar(30))");
            s.executeUpdate(sqlQuery);
        } catch (SQLException se) {
            Msg.showError("error");
        }
    }

    public String SelectName(String faculty) {
        ResultSet StudentName;
        try {
            Statement s = c.createStatement();
            String s1 = "Select * from temp where not faculty=" + "'" +faculty+ "'" + " order by rand() limit 1";
            StudentName = s.executeQuery(s1);
            if (StudentName.next()) {
                String name = StudentName.getString("name");
                facultyStore = StudentName.getString("faculty");
                String deleteStmt = "delete from temp where  id=" + StudentName.getInt("id");
                s.executeUpdate(deleteStmt);
                return name;
            }
        } catch (SQLException e) {
            Msg.showError("unable to retirive data");
        }
        return "";
    }

    public int countStudent() {
        ResultSet total;
        Statement s;
        int value = -1;
        try {
            s = c.createStatement();
            String s1 = "Select count(*) as totalStudent from temp";
            total = s.executeQuery(s1);
            if(total.next())
                value = total.getInt("totalStudent");
        } catch (Exception e) {
                return value;
        }
        return value;
    }

    public void deleteTempTable() {
        Statement s;
        try {
            s = c.createStatement();
            s.executeUpdate("drop table if exists temp");
        } catch (Exception e) {
            Msg.showError("error");
            System.exit(1);
        }
    }

    public ResultSet displayData() {
        try {
            Statement s = c.createStatement();
            return s.executeQuery("Select * From studentlist");
        } catch (Exception se) {
            Msg.showError("operation error");
            System.exit(1);
        }
        return null;
    }
    public Vector<String> distinctFaculty() {
        Vector<String> FacultyList = new Vector<>();
        try {
            Statement FacultySt = c.createStatement();
            ResultSet r2 = FacultySt.executeQuery("select distinct faculty  from studentList order by faculty");
            while (r2.next()) {
                FacultyList.add(r2.getString("faculty"));
            }
        } catch (Exception e) {
            Msg.showError("operation error");
            System.exit(1);
        }
        return FacultyList;
    }

    public void deleteData(int sid) {
        try {
            Statement deleteSt = c.createStatement();
            deleteSt.executeUpdate("delete from studentlist where studentid=" + sid);
        } catch (Exception e) {
            Msg.showError("operation error");
            System.exit(1);
        }
    }
    public void Update(Student s){
        try {
            PreparedStatement pst = c.prepareStatement("update studentlist set name=?,faculty=? where studentid=?");
            pst.setString(1,s.getName());
            pst.setString(2,s.getFaculty());
            pst.setInt(3,s.getStudentid());
            pst.executeUpdate();
        } catch (SQLException e) {
            Msg .showError("cant update");
        }
    }
    public String getFacultyStore() {
        return facultyStore;
    }
}
