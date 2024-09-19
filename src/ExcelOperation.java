import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
public class ExcelOperation {
    Vector<Integer> storeIndex = new Vector<>();

    private final XSSFWorkbook Workbook1 = new XSSFWorkbook();

    DatabaseOperation DBEX = new DatabaseOperation();

    XSSFSheet Sheet1;

    XSSFRow student;

    int column;

    private CellStyle setCellStyle(int size, boolean setBorder) {
        this.Sheet1.setDefaultRowHeight((short)600);
        XSSFCellStyle xSSFCellStyle = this.Workbook1.createCellStyle();
        xSSFCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        xSSFCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont font = this.Workbook1.createFont();
        font.setFontHeight(size);
        font.setBold(true);
        xSSFCellStyle.setFont(font);
        if (setBorder) {
            xSSFCellStyle.setBorderBottom(BorderStyle.THIN);
            xSSFCellStyle.setBorderRight(BorderStyle.THIN);
            xSSFCellStyle.setBorderTop(BorderStyle.THIN);
            xSSFCellStyle.setBorderLeft(BorderStyle.THIN);
        }
        return xSSFCellStyle;
    }

    private int getColumn(String room) {
        RoomData rd = new RoomData();
        return rd.getColumn(room);
    }

    public void writeHeading(String room) {
        this.column = getColumn(room);
        this.storeIndex.removeAllElements();
        int rowIndex = 0;
        this.Sheet1 = this.Workbook1.createSheet(room);
        CellStyle cStyle = setCellStyle(22, false);
        if (this.column == 2) {
            this.Sheet1.addMergedRegion(new CellRangeAddress(0, 2, 1, 3));
        } else if (this.column == 3) {
            this.Sheet1.addMergedRegion(new CellRangeAddress(0, 2, 1, 6));
        }
        XSSFRow roomName = this.Sheet1.createRow(0);
        XSSFCell cell1 = roomName.createCell(1);
        cell1.setCellValue(room.toUpperCase());
        cell1.setCellStyle(cStyle);
        XSSFRow rowName2 = this.Sheet1.createRow(3);
        for (int i = 1; i <= this.column; i++) {
            this.Sheet1.addMergedRegion(new CellRangeAddress(3, 4, rowIndex, rowIndex + 1));
            XSSFCell cell2 = rowName2.createCell(rowIndex);
            this.storeIndex.add(rowIndex);
            cell2.setCellValue("Column " + i);
            cell2.setCellStyle(cStyle);
            rowIndex += 3;
        }
    }

    public void writeRows(int row) {
        int i;
        for (i = 6; i < row + 6; i++) {
            String faculty = " ";
            this.student = this.Sheet1.getRow(i);
            if (this.student == null)
                this.student = this.Sheet1.createRow(i);
            int index = this.storeIndex.firstElement();
            for (int k = index; k <= index + 1; k++) {
                XSSFCell name = student.createCell(k);
                String stdName = DBEX.SelectName(faculty);
                faculty = DBEX.getFacultyStore();
                name.setCellValue(stdName);
                this.Sheet1.autoSizeColumn(k, true);
                name.setCellStyle(setCellStyle(14, true));
            }
        }
        this.storeIndex.removeElementAt(0);
        for (i = 0; i < getColumnInExcelFile(); i++)
            this.Sheet1.setColumnWidth(i, 6000);
    }

    private int getColumnInExcelFile() {
        XSSFRow r = this.Sheet1.getRow(6);
        return r.getLastCellNum();
    }

    public void writeFile() {
        FileOutputStream fpb = null;
        Date date = new Date();
        SimpleDateFormat Sdp = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String dateName = "SP" + Sdp.format(date);
        try {
            String pathPart = "d://Seatplanner//";
            File dir = new File(pathPart);
            if (!dir.exists()) {
                boolean b = dir.mkdir();
                if (!b)
                   pathPart=pathPart.replace("d://Seatplanner//", "");
            }
            File f2 = new File(pathPart + dateName + ".xlsx");
            fpb = new FileOutputStream(f2);
            this.Workbook1.write(fpb);
            Msg.showMessage("Excel file created");
        } catch (IOException e) {
            Msg.showError("file creation error");
        } finally {
            try {
                if (fpb != null)
                    fpb.close();
            } catch (IOException e) {
                Msg.showError("error");
            }
        }
    }
}
