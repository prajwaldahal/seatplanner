import java.io.File;
import java.io.FileFilter;
import java.util.Vector;

public class FileList {
    public Vector<String> list(){
        int i=0;
        Vector<String> stringFile ;
        stringFile = new Vector<>();
        File dir = new File("d://Seatplanner");
        if (!dir.exists()) {
            stringFile.add("no file found");
            return stringFile;
        }
        File[] listFile = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && pathname.toString().toLowerCase().endsWith(".xlsx")&&pathname.toString().toLowerCase().startsWith("d:\\seatplanner\\sp");
            }
        });
        for (File f1:listFile) {
            String path=f1.toString().replace("d:\\Seatplanner\\","");
            stringFile.add(path);
        }
        return stringFile;
    }
}
