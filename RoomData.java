import java.sql.*;
import java.util.List;
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
    public void deleteRoom(String roomName)
    {
        try {
            Statement st = c.createStatement();
            st.executeUpdate("Drop table "+roomName);
        } catch (SQLException e) {
            Msg.showError("error");
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
            Msg.showError("error");
        }
        return k;
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
    public void addData(String roomName,int row,int col)  {
        try {

            Statement stmt = c.createStatement();
            if (!checkTable(roomName))
                stmt.execute("create table if not exist" + roomName + "(column_name int , row_no int)");
            else {
                throw new RoomAlreadyExistException("Room Already Exist");
            }

            String s ="insert into " + roomName + " values (?,?)";
            PreparedStatement pst = c.prepareStatement(s);
            pst.setInt(1,  col);
            pst.setInt(2,row);
            pst.executeUpdate();
        } catch (SQLException e) {
            Msg.showError("insertion error");
        } catch (RoomAlreadyExistException e) {
            Msg.showError(e.getMessage());
        }
    }
    public Vector<String> retrieveTableName() {
        Vector<String> roomName=new Vector<>();
        try {
            Statement stmt = c.createStatement();
            ResultSet r = stmt.executeQuery("SELECT Table_name as TablesName from information_schema.tables where table_schema = 'roomdata'");
            while(true)
            {
                if(r.next()){
                    roomName.add(r.getString("TablesName"));
                }
                else{
                    break;
                }
            }
        } catch (SQLException e) {
            Msg.showError("operation error");
            e.printStackTrace();
        }
        return roomName;
    }
    public int totalSeat(List<String> roomName)
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
