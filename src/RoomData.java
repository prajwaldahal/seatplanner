import java.sql.*;
import java.util.List;
import java.util.Vector;
public class RoomData {
    Connection c;
    public RoomData() {
        try {
            this.c = DriverManager.getConnection("jdbc:mysql://localhost:3306/roomdata", "root", "");
        } catch (Exception e) {
            Msg.showError("connection error");
        }
    }

    public void deleteRoom(String roomName) {
        try {
            Statement st = this.c.createStatement();
            st.executeUpdate("Drop table " + roomName);
        } catch (SQLException e) {
            Msg.showError("error");
        }
    }

    public boolean checkTable(String roomName) {
        boolean k = false;
        try {
            Statement st = this.c.createStatement();
            ResultSet r = st.executeQuery("SHOW TABLES LIKE '" + roomName + "'");
            if (r.next())
                k = true;
        } catch (SQLException e) {
            Msg.showError("error");
        }
        return k;
    }

    public int getColumn(String roomname) {
        int k = -1;
        try {
            Statement st = this.c.createStatement();
            ResultSet r = st.executeQuery("Select max(column_name) as total from " + roomname);
            if (r.next())
                k = r.getInt("total");
        } catch (SQLException e) {
            Msg.showError("error");
        }
        return k;
    }

    public int getRow(int column, String roomName) {
        int k = -1;
        try {
            Statement st = this.c.createStatement();
            ResultSet r = st.executeQuery("Select row_no from " + roomName + " where column_name=" + column);
            if (r.next())
                k = r.getInt("row_no");
        } catch (SQLException e) {
            Msg.showError("error");
        }
        return k;
    }

    public void addData(String roomName, int row, int col,int value) {
        try {
            Statement stmt = this.c.createStatement();
            stmt.execute("create table if not exists " + roomName + "(column_name int , row_no int)");
            String s = "insert into " + roomName + " values (?,?)";
            PreparedStatement pst = this.c.prepareStatement(s);
            pst.setInt(1, col);
            pst.setInt(2, row);
            pst.executeUpdate();
            if((col==2 && value==0) || (col==3&& value!=0))
                Msg.showMessage("Room Added");
        } catch (SQLException e) {
            Msg.showError("insertion error \n");
        }
    }

    public void updateData(Room r) {
        int total = r.getColumn();
        int value = 0;
        for (int i = 1; i <= total; i++) {
            PreparedStatement pst;
            try {
                switch (i) {
                    case 1 -> value = r.getRow();
                    case 2 -> value = r.getRow2();
                    case 3 -> value = r.getRow3();
                }
                pst = this.c.prepareStatement("update " + r.getRoomName() + " set row_no =? where column_name =?");
                pst.setInt(1, value);
                pst.setInt(2, i);
                pst.executeUpdate();
            } catch (SQLException e) {
                Msg.showError("error");
            }
        }
    }

    public Vector<String> retrieveTableName() {
        Vector<String> roomName = new Vector<>();
        try {
            Statement stmt = this.c.createStatement();
            ResultSet r = stmt.executeQuery("SELECT Table_name as TablesName from information_schema.tables where table_schema = 'roomdata'");
            while (r.next())
                roomName.add(r.getString("TablesName"));
        } catch (SQLException e) {
            Msg.showError("operation error");
        }
        return roomName;
    }

    public int totalSeat(List<String> roomName) {
        int totalSeatv = 0;
        for (String name : roomName) {
            int x = getColumn(name);
            for (int i = 1; i <= x; i++) {
                int y = getRow(i, name);
                totalSeatv += y;
            }
        }
        return totalSeatv;
    }
}
