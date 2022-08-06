import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.Vector;

public class ManageRoomPanel extends JPanel {
    private final RoomData rd=new RoomData();
    private JTable TableM;
    private DefaultTableModel RoomTable;
    public ManageRoomPanel() {
        initComponent();
    }
    private void initComponent() {
        removeAll();
        revalidate();
        repaint();
        Vector<Room> RoomData= RoomData();
        String [] ColumnName = {"Name","No.of Column","total row1","total row2","total row3"};
        RoomTable = new DefaultTableModel();
        RoomTable.setColumnIdentifiers(ColumnName);
        Object[] row=new Object[5];

        for(Room r:RoomData)
        {
            row[0]=r.getRoomName();
            row[1]=r.getColumn();
            row[2]=r.getRow();
            row[3]=r.getRow2();
            row[4]=r.getRow3();
            RoomTable.addRow(row);
        }
        TableM=new JTable(RoomTable){
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };
        TableM.setFont(new Font("verdana",1,12));
        TableM.setBackground(Color.white);
        JButton Add = new JButton("Add Room");
        JButton Delete = new JButton("Delete");
        JButton Update = new JButton("Update");
        Delete.setBounds(5,3,150,30);
        Delete.setFont(new Font("verdana",1,16));
        Add.setBounds(Delete.getWidth()+75,3,150,30);
        Add.setFont(Delete.getFont());
        Update.setFont(Delete.getFont());
        Update.setBounds(Add.getWidth()+300, 3, 150,30);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudentActionPerformed();
            }
        });
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
        setBackground(Color.white);
        setLayout(null);
        JScrollPane SP=new JScrollPane(TableM,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        SP.setBounds(2, 40,Update.getX()+Update.getWidth(),357);
        SP.getViewport().setBackground(Color.white);
        add(Delete);
        add(Update);
        add(Add);
        add(SP);
    }
    private Vector<Room> RoomData() {
        Vector<Room> RoomData = new Vector<>();
        Room r;
        Vector <String> TableName;
        int i;
        int [] row = new int[3];
        TableName=rd.retrieveTableName();
        for (String s:TableName) {
            int column=rd.getColumn(s);
            if(column==2)
                row[2]=0;
            for (i = 1; i <= column ; i++) {
                row[i-1]=rd.getRow(i,s);
            }
            r= new Room(s,column,row[0],row[1],row[2]);
            RoomData.add(r);
        }
        return RoomData;
    }

    private void updateActionPerformed() {
    }

    private void deleteActionPerformed() {
        int row = TableM.getSelectedRow();
        if(row==-1)
        {
            Msg.showMessage("Please Select the row");
            return;
        }
        String Name= TableM.getModel().getValueAt(row,0).toString().trim();
        rd.deleteRoom(Name);
        initComponent();
    }

    private void addStudentActionPerformed() {
    }
}
