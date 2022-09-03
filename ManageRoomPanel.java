import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ManageRoomPanel extends JPanel {
    private final RoomData rd = new RoomData();

    private UpdateRoom UR = new UpdateRoom();

    private JTable TableM;

    private JButton Add;

    private JButton Update;

    private DefaultTableModel RoomTable;

    public UpdateRoom getUR() {
        return this.UR;
    }

    public JTable getTableM() {
        return this.TableM;
    }

    public JButton getUpdate() {
        return this.Update;
    }

    public ManageRoomPanel() {
        initComponent();
    }

    private void initComponent() {
        removeAll();
        revalidate();
        repaint();
        Vector<Room> vector = RoomData();
        String[] ColumnName = { "Name", "No.of Column", "total row1", "total row2", "total row3" };
        this.RoomTable = new DefaultTableModel();
        this.RoomTable.setColumnIdentifiers((Object[])ColumnName);
        Object[] row = new Object[5];
        for (Room r : vector) {
            row[0] = r.getRoomName();
            row[1] = Integer.valueOf(r.getColumn());
            row[2] = Integer.valueOf(r.getRow());
            row[3] = Integer.valueOf(r.getRow2());
            row[4] = Integer.valueOf(r.getRow3());
            this.RoomTable.addRow(row);
        }
        this.TableM = new JTable(this.RoomTable) {
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };
        this.TableM.setFont(new Font("verdana", 1, 12));
        this.TableM.setBackground(Color.white);
        this.Add = new JButton("Add Room");
        JButton Delete = new JButton("Delete");
        this.Update = new JButton("Update");
        Delete.setBounds(5, 3, 150, 30);
        Delete.setFont(new Font("verdana", 1, 16));
        this.Add.setBounds(Delete.getWidth() + 75, 3, 150, 30);
        this.Add.setFont(Delete.getFont());
        this.Update.setFont(Delete.getFont());
        this.Update.setBounds(this.Add.getWidth() + 300, 3, 150, 30);
        Delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManageRoomPanel.this.deleteActionPerformed();
            }
        });
        setBackground(Color.white);
        setLayout((LayoutManager)null);
        JScrollPane SP = new JScrollPane(this.TableM, 20, 30);
        SP.setBounds(2, 40, this.Update.getX() + this.Update.getWidth(), 357);
        SP.getViewport().setBackground(Color.white);
        add(Delete);
        add(this.Update);
        add(this.Add);
        add(SP);
    }

    private Vector<Room> RoomData() {
        Vector<Room> vector = new Vector<>();
        int[] row = new int[3];
        Vector<String> TableName = this.rd.retrieveTableName();
        for (String s : TableName) {
            int column = this.rd.getColumn(s);
            if (column == 2)
                row[2] = 0;
            for (int i = 1; i <= column; i++)
                row[i - 1] = this.rd.getRow(i, s);
            Room r = new Room(s, column, row[0], row[1], row[2]);
            vector.add(r);
        }
        return vector;
    }

    public void updateActionPerformed() {
        int row = this.TableM.getSelectedRow();
        String roomName = this.TableM.getModel().getValueAt(row, 0).toString();
        String column = this.TableM.getModel().getValueAt(row, 1).toString();
        String row1 = this.TableM.getModel().getValueAt(row, 2).toString();
        String row2 = this.TableM.getModel().getValueAt(row, 3).toString();
        String row3 = this.TableM.getModel().getValueAt(row, 4).toString();
        System.out.println(row2 + " " + row2);
        this.UR.setRoomName(roomName);
        this.UR.setColumnNo(column);
        this.UR.setRow1(row1);
        this.UR.setRow2(row2);
        this.UR.setRow3(row3);
    }

    private void deleteActionPerformed() {
        int row = this.TableM.getSelectedRow();
        if (row == -1) {
            Msg.showMessage("Please Select the row");
            return;
        }
        String Name = this.TableM.getModel().getValueAt(row, 0).toString().trim();
        this.rd.deleteRoom(Name);
        initComponent();
    }

    public JButton getAdd() {
        return this.Add;
    }
}
