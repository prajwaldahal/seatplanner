import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateRoom extends JPanel {
    JButton Update;

    JLabel RoomNameL;

    JLabel ColumnNoL;

    JLabel Row1L;

    JLabel Row2L;

    JLabel Row3L;

    JTextField RoomName;

    JTextField ColumnNo;

    JTextField Row1;

    JTextField Row2;

    JTextField Row3;

    public void setRoomName(String roomName) {
        this.RoomName.setText(roomName);
    }

    public void setColumnNo(String columnNo) {
        this.ColumnNo.setText(columnNo);
        columnNocheck();
    }

    public void setRow1(String row1) {
        this.Row1.setText(row1);
    }

    public void setRow2(String row2) {
        this.Row2.setText(row2);
    }

    public void setRow3(String row3) {
        this.Row3.setText(row3);
    }

    public UpdateRoom() {
        initComponent();
    }

    private void initComponent() {
        removeAll();
        repaint();
        revalidate();
        this.Update = new JButton("Update");
        this.RoomNameL = new JLabel("Room Name");
        this.ColumnNoL = new JLabel("No.of Column");
        this.ColumnNo = new JTextField();
        this.ColumnNo.setEditable(false);
        this.Row1L = new JLabel("Row1 no:");
        this.Row2L = new JLabel("Row2 no:");
        this.Row3L = new JLabel("Row3 no:");
        this.RoomName = new JTextField(30);
        this.RoomName.setEditable(false);
        this.Row1 = new JTextField(30);
        this.Row2 = new JTextField(30);
        this.Row3 = new JTextField(30);
        this.RoomNameL.setBounds(100, -10, 200, 80);
        this.RoomNameL.setFont(new Font("Verdana", Font.BOLD, 12));
        add(this.RoomNameL);
        this.RoomName.setBounds(150, 43, 200, 30);
        add(this.RoomName);
        this.ColumnNoL.setBounds(100, 48, 200, 80);
        this.ColumnNoL.setFont(new Font("Verdana", Font.BOLD, 12));
        add(this.ColumnNoL);
        this.ColumnNo.setBounds(150, 103, 200, 30);
        add(this.ColumnNo);
        this.Row1L.setBounds(100, 108, 200, 80);
        this.Row1L.setFont(new Font("Verdana", Font.BOLD, 12));
        add(this.Row1L);
        this.Row1.setBounds(150, 168, 200, 30);
        add(this.Row1);
        this.Row2L.setBounds(100, 173, 200, 80);
        this.Row2L.setFont(new Font("Verdana", Font.BOLD, 12));
        add(this.Row2L);
        this.Row2.setBounds(150, 232, 200, 30);
        add(this.Row2);
        this.Row2L.setBounds(100, 172, 200, 80);
        this.Row2L.setFont(new Font("Verdana", Font.BOLD, 12));
        add(this.Row2L);
        this.Row2.setBounds(150, 232, 200, 30);
        add(this.Row2);
        this.Row3L.setBounds(100, 237, 200, 80);
        this.Row3L.setFont(new Font("Verdana", Font.BOLD, 12));
        add(this.Row3L);
        this.Row3.setBounds(150, 285, 200, 30);
        add(this.Row3);
        this.Update.setBounds(180, 325, 100, 30);
        add(this.Update);
        this.Update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateRoom.this.updateActionPerformed();
            }
        });
        setLayout(null);
        setBackground(Color.white);
    }

    private void updateActionPerformed() {
        String roomName = this.RoomName.getText();
        int col = Integer.parseInt(this.ColumnNo.getText());
        int row1 = Integer.parseInt(this.Row1.getText());
        int row2 = Integer.parseInt(this.Row2.getText());
        int row3 = Integer.parseInt(this.Row3.getText());
        Room r = new Room(roomName, col, row1, row2, row3);
        RoomData rd = new RoomData();
        rd.updateData(r);
        Msg.showMessage("room updated");
        initComponent();
    }

    private void columnNocheck() {
        String storeColumn = this.ColumnNo.getText();
        if (storeColumn.equals("2")) {
            this.Row3.setText("0");
            this.Row3.setEditable(false);
        } else {
            this.Row3.setEditable(true);
        }
    }
}
