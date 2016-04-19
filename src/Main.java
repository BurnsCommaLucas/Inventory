import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.HashMap;
import static java.lang.Character.isDigit;

/**
 * FOR THE LOVE OF GOD RUN THIS ON A MAC, I HAVE NO IDEA IF IT WORKS ON PC
 */
public class Main {

    public static Integer[][] nums;
    public static String[] raws;
    public static String cookPath = "Test/Out/Cooks/"; //     MAKE THIS THE COOK FOLDER
    public static String managePath = "Test/Out/Mgmt/"; //     MAKE THIS THE MANAGEMENT FOLDER
    public static String inPath = "Test/In/";
    public static String cookExt = ".txt";
    public static String fileName;
    public static Database db;
    public static HashMap<String, String[]> meals;
    public static String[] headers;
    public static int tableNumber;

    public static void main(String[] args) throws FileNotFoundException {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd 'at' HH.mm.ss");

        emptyFolder(cookPath);
        emptyFolder(managePath);

        db = new Database();
        db.init();

        TimerTask task = new DirWatcher(inPath, "txt") {
                protected void onChange(File file, String action) {
                if (action == "add") {
                    raws = new String[] {};
                    Date d = new Date();
                    fileName = ft.format(d);
                    meals = new HashMap<String, String[]>();
                    headers = new String[] {};

                    // First get input
                    System.out.println("huh");
                    try {
                        FileReader fileReader = new FileReader(file);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String line;
                        String header = "";
                        while ((line = bufferedReader.readLine()) != null) {
                            if (line.equals(""))
                                continue;
                            char c = line.charAt(0);
                            if (c == '/')
                            {
                                header = line;
                                meals.put(header, new String[] {});
                                headers = (String[]) addElement(headers, header);
                            }
                            else if (isDigit(c))
                            {
                                tableNumber = c;
                            }
                            else
                            {
                                raws = (String[]) addElement(raws, line);
                                meals.replace(header, (String[]) addElement(meals.get(header), line));
                            }
                        }
                        fileReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    nums = new Integer[headers.length][];
                    for (int i = 0; i < headers.length; i++)
                    {
                        nums[i] = new Integer[]{};
                    }

                    parse();
                    // Then output
                    try {
                        PrintWriter cookWriter = new PrintWriter(cookPath + fileName + cookExt);
                        cookWriter.println("" + tableNumber);
                        for (int i = 0; i < headers.length; i++)
                        {
                            boolean canMake = true;
                            for (int j = 0; j < meals.get(headers[i]).length; j++)
                            {
                                if (db.getStockChar(meals.get(headers[i])[j], nums[i][j]) != 'y')
                                {
                                    cookWriter.println(headers[i] + " n");
                                    canMake = false;
                                    break;
                                }
                            }
                            if (canMake)
                            {
                                cookWriter.println(headers[i] + " y");
                            }

                            for (int j = 0; j < meals.get(headers[i]).length; j++)
                            {
                                String lineTemp = meals.get(headers[i])[j];
                                db.updateStock(lineTemp, db.getStock(lineTemp) - nums[i][j], managePath);
                                cookWriter.println(lineTemp + " " + nums[i][j]);
                            }
                            cookWriter.println();
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
        String lineTemp;
        // Iterate through headers (MEAL KEYS)
        for (int i = 0; i < headers.length; i++)
        {
            String[] noNumbers = new String[] {};
            // Iterate through meal requirements
            for (int j = 0; j < meals.get(headers[i]).length; j++)
            {
                lineTemp = meals.get(headers[i])[j];
                while (lineTemp.length() > 0)
                {
                    if (db.includes(lineTemp.substring(0, lineTemp.indexOf(' '))))
                    {
                        String itWorkedLol = lineTemp.substring(0, lineTemp.indexOf(' '));
                        noNumbers = (String[]) addElement(noNumbers, itWorkedLol);
                        String whatIsHappeneing = lineTemp.replaceAll("[^0-9]", "");
                        nums[i] = (Integer[]) addElement(nums[i], new Scanner(whatIsHappeneing).nextInt());
                        lineTemp = "";
                        break;
                    }
                    lineTemp = lineTemp.substring(lineTemp.indexOf(' ') + 1);
                }
            }
            meals.replace(headers[i], noNumbers);
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
