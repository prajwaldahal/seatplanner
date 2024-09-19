import java.io.*;
import java.util.Vector;

public class FileList {
    private static final String BASE_DIR_NAME = "Seatplanner";
    private static final String CONFIG_FILE = "/src/config/lastUsedDirectory.txt";
    private File lastUsedDirectory;

    public FileList() {
        loadLastUsedDirectory();
    }

    public Vector<String> list() {
        Vector<String> stringFile = new Vector<>();
        File dir = getOrCreateDirectory();

        if (dir == null) {
            stringFile.add("System Cannot Find Files");
            return stringFile;
        }

        lastUsedDirectory = dir;
        saveLastUsedDirectory();

        File[] listFile = dir.listFiles(pathname ->
                pathname.isFile() && pathname.toString().toLowerCase().endsWith(".xlsx") && !pathname.toString().toLowerCase().contains("~$")
        );

        if (listFile != null && listFile.length > 0) {
            for (File f1 : listFile) {
                String path = f1.toString().replace(dir.getAbsolutePath() + "\\", "");
                stringFile.add(path);
            }
        } else {
            stringFile.add("System Cannot Find Files");
        }

        return stringFile;
    }

    private File getOrCreateDirectory() {
        if (lastUsedDirectory != null && lastUsedDirectory.exists()) {
            return lastUsedDirectory;
        }

        String[] drives = {"D:", "E:", "F:", "G:", "H:"};
        File dir = null;

        for (String drive : drives) {
            dir = new File(drive + "\\" + BASE_DIR_NAME);
            if (dir.exists() || dir.mkdirs()) {
                return dir;
            }
        }

        return null;
    }

    private void loadLastUsedDirectory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            String path = reader.readLine();
            if (path != null) {
                lastUsedDirectory = new File(path);
                if (!lastUsedDirectory.exists()) {
                    lastUsedDirectory = null;
                }
            }
        } catch (IOException e) {
            lastUsedDirectory = null;
        }
    }

    private void saveLastUsedDirectory() {
        if (lastUsedDirectory != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE))) {
                writer.write(lastUsedDirectory.getAbsolutePath());
            } catch (IOException e) {
            }
        }
    }
}
