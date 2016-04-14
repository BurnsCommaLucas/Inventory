import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Lucas on 4/3/16.
 */
public class Main {

    public static Integer[] nums;
    public static String[] names;
    public static String[] raws;
    public static String cookPath = "Test/Out/Cooks/"; //     ../../Dropbox/CS 341/
    private static String managePath = "Test/Out/Mgmt/"; //     ../../Dropbox/CS 341/
    public static String inPath = "Test/In/";
    public static String cookExt = ".txt";
    public static String manageExt = ".txt";
    public static String fileName;
    public static Database db;

    public static void main(String[] args) throws FileNotFoundException {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd 'at' HH.mm.ss");

        emptyFolder(cookPath);
        emptyFolder(managePath);

        db = new Database();
        db.init();

        TimerTask task = new DirWatcher(inPath, "txt") {
                protected void onChange(File file, String action) {
                if (action == "add") {
                    names = new String[] {};
                    raws = new String[] {};
                    nums = new Integer[] {};
                    Date d = new Date();
                    fileName = ft.format(d);

                    // First get input
                    System.out.println("huh");
                    try {
                        FileReader fileReader = new FileReader(file);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            raws = (String[]) addElement(raws, line);
                        }
                        fileReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    parse();
                    // Then output
                    try {
                        PrintWriter cookWriter = new PrintWriter(cookPath + fileName + cookExt);
                        for (int i = 0; i < names.length; i++)
                        {
                            cookWriter.println(names[i] + " " + nums[i] + " " + db.getStockChar(names[i], nums[i]));
                            db.updateStock(names[i], db.getStock(names[i]) - nums[i], managePath);
                        }
                        cookWriter.close();
                    } catch (FileNotFoundException e) {
                    }
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, new Date(), 1000);
    }

    public static Object[] addElement(Object[] org, Object value) {
        Object[] result = Arrays.copyOf(org, org.length + 1);
        result[org.length] = value;
        return result;
    }

    public static void parse()
    {
        String temp;
        for (int i = 0; i < raws.length; i++)
        {
            temp = raws[i];
            while (temp.length() > 0)
            {
                if (db.includes(temp.substring(0, temp.indexOf(' '))))
                {
                    String itWorkedLol = temp.substring(0, temp.indexOf(' '));
                    names = (String[]) addElement(names, itWorkedLol);
                    String whatIsHappeneing = temp.replaceAll("[^0-9]", "");
                    nums = (Integer[]) addElement(nums, new Scanner(whatIsHappeneing).nextInt());
                    temp = "";
                    break;
                }
                temp = temp.substring(temp.indexOf(' ') + 1);
            }
        }
    }

    public static void emptyFolder (String path)
    {
        File folder = new File(path);
        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            files[i].delete();
        }
    }
}
