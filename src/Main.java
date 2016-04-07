import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Lucas on 4/3/16.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd 'at' HH.mm.ss");
        Date d = new Date();

        String name = ft.format(d);
        String outPath = "Test/Out/"; //     ../../Dropbox/CS 341/
        String inPath = "Test/In/";
        String ext = ".txt";

        File outFolder = new File(outPath);
        File[] outFiles = outFolder.listFiles();

        Database db = new Database();
        db.init();

        System.out.println(ft.format(d));

        File inFolder = new File(outPath);
        File[] inFiles = inFolder.listFiles();

        TimerTask task = new DirWatcher(inPath, "txt") {
                protected void onChange(File file, String action) {
                if (action == "add") {
                    // Input first
                    String[] foods = null;
                    int[] nums = null;

                    try {
                        FileReader fileReader = new FileReader(file);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        StringBuffer stringBuffer = new StringBuffer();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuffer.append(line);
                            stringBuffer.append("\n");
                        }
                        fileReader.close();
                        System.out.println("Contents of file:");
                        System.out.println(stringBuffer.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // Then output
                    try {
                        PrintWriter writer = new PrintWriter(outPath + name + ext);
                        db.printMap(writer, foods, nums);
                        writer.close();
                    } catch (FileNotFoundException e) {
                    }
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, new Date(), 1000);
    }
}
