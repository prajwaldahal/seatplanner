import java.sql.*;
import java.util.Vector;

public class DatabaseOperation {
    private Connection c;

    private String facultyStore = "";

    public DatabaseOperation() {
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/seatplanner", "root", "");
        } catch (Exception e) {
            Msg.showError("connection error");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void insertData(int id, String name, String faculty, String semester) {
        try {
            PreparedStatement ps = this.c.prepareStatement("insert into studentlist values(?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, faculty + " " + semester);
            try {
                ps.executeUpdate();
                Msg.showMessage("student Added");
            } catch (SQLIntegrityConstraintViolationException error) {
                Msg.showError("student id already used");
            }
        } catch (SQLException se) {
            Msg.showError("operation error");
            System.out.println(se);
        }
    }

    public void createTempTable(String sqlQuery) {
        try {
            Statement s = this.c.createStatement();
            s.executeUpdate("drop table if exists temp");
            s.executeUpdate("Create table temp(id int primary key AUTO_INCREMENT,name varchar(50),faculty varchar(30))");
            s.executeUpdate(sqlQuery);
        } catch (SQLException se) {
            Msg.showError("error");
        }
    }

    public String SelectName(String faculty) {
        try {
            Statement s = this.c.createStatement();
            String s1 = "Select * from temp where not faculty='" + faculty + "' order by rand() limit 1";
            ResultSet StudentName = s.executeQuery(s1);
            if (StudentName.next()) {
                String name = StudentName.getString("name");
                this.facultyStore = StudentName.getString("faculty");
                String deleteStmt = "delete from temp where  id=" + StudentName.getInt("id");
                s.executeUpdate(deleteStmt);
                return name;
            }
        } catch (SQLException e) {
            Msg.showError("Error");
        }
        return "";
    }

    public int countStudent() {
        int value = -1;
        try {
            Statement s = this.c.createStatement();
            String s1 = "Select count(*) as totalStudent from temp";
            ResultSet total = s.executeQuery(s1);
            if (total.next())
                value = total.getInt("totalStudent");
        } catch (Exception e) {
            return value;
        }
        return value;
    }

    public void deleteTempTable() {
        try {
            Statement s = this.c.createStatement();
            s.executeUpdate("drop table if exists temp");
        } catch (Exception e) {
            Msg.showError("error");
            System.exit(1);
        }
    }

    public ResultSet displayData() {
        try {
            Statement s = this.c.createStatement();
            return s.executeQuery("Select * From studentlist");
        } catch (Exception se) {
            Msg.showError("operation error");
            System.exit(1);
            return null;
        }
    }

    public boolean checkLogin(String username, String password) {
        boolean k = false;
        try {
            PreparedStatement St = this.c.prepareStatement("select uname from admin where uname=? AND password=?");
            St.setString(1, username);
            St.setString(2, password);
            ResultSet r = St.executeQuery();
            if (r.next())
                k = true;
        } catch (Exception e) {
            Msg.showError("error");
        }
        return k;
    }

    public void changePassword(String username, String oldpwd, String newpwd, String cpwd) {
        if (cpwd.equals(newpwd)) {
            try {
                PreparedStatement St = this.c.prepareStatement("select uname from admin where uname=? AND password=?");
                St.setString(1, username);
                St.setString(2, oldpwd);
                ResultSet r = St.executeQuery();
                if (r.next()) {
                    PreparedStatement St2 = this.c.prepareStatement("update  admin set password=? where uname=?");
                    St2.setString(2, username);
                    St2.setString(1, newpwd);
                    if (!St2.execute())
                        Msg.showMessage("passsword changed");
                } else {
                    Msg.showMessage("username and password cannot find");
                }
            } catch (Exception e) {
                Msg.showError("error");
            }
        } else {
            Msg.showMessage("new password and confirm passsword doesnot match");
        }
    }

    public Vector<String> distinctFaculty() {
        Vector<String> FacultyList = new Vector<>();
        try {
            Statement FacultySt = this.c.createStatement();
            ResultSet r2 = FacultySt.executeQuery("select distinct faculty  from studentList order by faculty");
            while (r2.next())
                FacultyList.add(r2.getString("faculty"));
        } catch (Exception e) {
            Msg.showError(" error");
        }
        return FacultyList;
    }

    public void deleteData(int sid) {
        try {
            Statement deleteSt = this.c.createStatement();
            deleteSt.executeUpdate("delete from studentlist where studentid=" + sid);
        } catch (Exception e) {
            Msg.showError("error");
        }
    }

    public void Update(Student s) {
        try {
            PreparedStatement pst = this.c.prepareStatement("update studentlist set name=?,faculty=? where studentid=?");
            pst.setString(1, s.getName());
            pst.setString(2, s.getFaculty());
            pst.setInt(3, s.getStudentid());
            pst.executeUpdate();
            Msg.showMessage("student record Updated");
        } catch (SQLException e) {
            Msg.showError("cant update");
        }
    }

    public String getFacultyStore() {
        return this.facultyStore;
    }
}
