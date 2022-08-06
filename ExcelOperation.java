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
    private final XSSFWorkbook Workbook1;
    DatabaseOperation DBEX;
    XSSFSheet Sheet1;
    Vector<Integer> storeIndex;
    XSSFRow student;
    int column;
    public ExcelOperation() {
        storeIndex = new Vector<>();
        Workbook1 = new XSSFWorkbook();
        DBEX = new DatabaseOperation();
    }
    private CellStyle setCellStyle(int size,boolean setBorder){
        Sheet1.setDefaultRowHeight((short) 600);
        CellStyle cStyle = Workbook1.createCellStyle();
        cStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        cStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont font = Workbook1.createFont();
        font.setFontHeight(size);
        font.setBold(true);
        cStyle.setFont(font);
        if(setBorder){
            cStyle.setBorderBottom(BorderStyle.THIN);
            cStyle.setBorderRight(BorderStyle.THIN);
            cStyle.setBorderTop(BorderStyle.THIN);
            cStyle.setBorderLeft(BorderStyle.THIN);
        }
        return cStyle;
    }
    private int getColumn(String room){
        int k;
        RoomData rd = new RoomData();
        k=rd.getColumn(room);
        return k;
    }
    public void writeHeading(String room) {

        column=getColumn(room);
        storeIndex.removeAllElements();
        int rowIndex = 0;
        Sheet1 = Workbook1.createSheet(room);
        CellStyle cStyle = setCellStyle(22,false);
        if (column == 2)
            Sheet1.addMergedRegion(new CellRangeAddress(0, 2, 1, 3));
        else if (column == 3)
            Sheet1.addMergedRegion(new CellRangeAddress(0, 2, 1, 6));
        XSSFRow roomName = Sheet1.createRow(0);
        XSSFCell cell1 = roomName.createCell(1);
        cell1.setCellValue(room.toUpperCase());
        cell1.setCellStyle(cStyle);
        XSSFRow rowName2 = Sheet1.createRow(3);
        for (int i = 1; i <= column; i++) {
            Sheet1.addMergedRegion(new CellRangeAddress(3, 4, rowIndex, rowIndex + 1));
            XSSFCell cell2 = rowName2.createCell(rowIndex);
            storeIndex.add(rowIndex);
            cell2.setCellValue("Column " + i);
            cell2.setCellStyle(cStyle);
            rowIndex += 3;
        }
    }
    public void writeRows( int row) {
        String stdName ;
        String faculty;
        int index;
        for (int i = 6; i < row+6; i++) {
            faculty=" ";
            student=Sheet1.getRow(i);
            if(student==null)
                student = Sheet1.createRow(i);
            index= storeIndex.firstElement();
            for (int k = index; k <= index + 1; k++) {
                XSSFCell name = student.createCell(k);
                stdName = DBEX.SelectName(faculty);
                faculty=DBEX.getFacultyStore();
                name.setCellValue(stdName);
                Sheet1.autoSizeColumn(k,true);
                name.setCellStyle(setCellStyle(14, true));
            }
        }
        storeIndex.removeElementAt(0);
        for (int i = 0; i < getColumnInExcelFile(); i++) {
            Sheet1.setColumnWidth(i,6000);
        }
    }
    private int getColumnInExcelFile()
    {
        XSSFRow r = Sheet1.getRow(6);
        return r.getLastCellNum();
    }
    public void writeFile() {
        FileOutputStream fpb = null;
        File f2;
        Date date = new Date();
        SimpleDateFormat Sdp= new SimpleDateFormat("dd-MMMM-yyyy hh:mm:ss");
        String dateName="SP"+Sdp.format(date).replace(" ","-");
        dateName=dateName.replace(":","-");
        try {
            String pathPart="d://Seatplanner//";
            File dir = new File(pathPart);
            if(!dir.exists()) {
                boolean b=dir.mkdir();
                if(!b)
                {
                    pathPart.replace("d://Seatplanner//","");
                }
            }
            f2 = new File(pathPart+dateName+".xlsx");
            fpb = new FileOutputStream(f2);
            Workbook1.write(fpb);
        } catch (IOException e) {
            Msg.showError("file creation error");
        }
        finally {
            try {
                if(fpb!=null)
                    fpb.close();
            } catch (IOException e) {
                Msg.showError("error");
            }
        }
        Msg.showMessage("Excel file created");
    }
}

