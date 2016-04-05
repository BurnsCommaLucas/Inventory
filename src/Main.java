import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        File outFolder = new File(outPath);
        File[] outFiles = outFolder.listFiles();

        Database db = new Database();
        db.init();

        System.out.println(ft.format(d));

        if (outFiles != null)
        {
            for (File f : outFiles)
            {
                f.delete();
            }
        }

        boolean bool = true;
        readStart:  // Needed a statement for the goto statement
        while (bool)
        {
            bool = false;
        }
        bool = true;

        File inFolder = new File(inPath);
        File[] inFiles = inFolder.listFiles();


        PrintWriter writer = new PrintWriter(outPath + name);
        for (int i = 0; i < 5; i++)
        {
            writer.println(i);
        }
        writer.close();
    }

    private boolean checkUpdated (File folder)
    {
        File[] files = folder.listFiles();
        return false;
    }
}
