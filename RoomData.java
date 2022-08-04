import java.sql.*;
import java.util.Vector;
public class RoomData {
    Connection c;
    public RoomData() {
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3307/roomdata", "root", "");
        } catch (Exception e) {
            Msg.showError("connection error");
        }
    }
    public boolean checkTable(String roomName){
        boolean k=false;
        try {
            Statement st = c.createStatement();
            ResultSet r= st.executeQuery("SHOW TABLES LIKE "+"'"+roomName+"'");
            if(r.next())
                k=true;
        } catch (SQLException e) {
            Msg.showError(e.toString());
        }
        return k;
    }
    public void addRoom(String roomName) throws RoomAlreadyExistException,SQLException {
        Statement stmt = c.createStatement();
        if (!checkTable(roomName))
            stmt.execute("create table " + roomName + "(column_name int , row_no int)");
        else {
            throw new RoomAlreadyExistException();
        }
    }
    public int getColumn(String roomname)
    {
        int k=-1;
        Statement st;
        try {
            st = c.createStatement();
            ResultSet r =st.executeQuery("Select max(column_name) as total from "+roomname);
            if(r.next()){
                k=r.getInt("total");
            }
        } catch (SQLException e) {
            Msg.showError("error");
            e.printStackTrace();
        }
        return k;
    }
    public int getRow(int column,String roomName)
    {
        int k=-1;
        Statement st;
        try {
            st = c.createStatement();
            ResultSet r =st.executeQuery("Select row_no from "+roomName+" where column_name="+column);
            if(r.next()){
                k=r.getInt("row_no");
            }
        } catch (SQLException e) {
            Msg.showError("error");
            e.printStackTrace();
        }
        return k;
    }
    public void addData(String roomName,int row,int col) {
        PreparedStatement pst;
        try {
            String s ="insert into " + roomName + " values (?,?)";
            pst = c.prepareStatement(s);
            pst.setInt(1,  col);
            pst.setInt(2,row);
            pst.executeUpdate();
        } catch (SQLException e) {
            Msg.showError("insertion error");
        }
    }
    public Vector<String> retrieveTableName() {
        Vector<String> roomName=new Vector<>();
        int count=0;
        try {
            Statement stmt = c.createStatement();
            ResultSet r = stmt.executeQuery("SELECT Table_name as TablesName from information_schema.tables where table_schema = 'roomdata'");
            while(true)
            {
                if(r.next()){
                    count++;
                    roomName.add(r.getString("TablesName"));
                }
                else{
                    if(count==0)
                    {
                        Msg.showMessage("seat is less than number of student");
                    }
                    break;
                }
            }
        } catch (SQLException e) {
            Msg.showError("operation error");
        }
        return roomName;
    }
    public int totalSeat(Vector<String> roomName)
    {
        int totalSeatv=0;
        for (String name :roomName) {
            int x=getColumn(name);
            for (int i = 1; i <= x; i++) {
                int y = getRow(i,name);
                totalSeatv+=y;
            }
        }
        return totalSeatv;
    }
}
