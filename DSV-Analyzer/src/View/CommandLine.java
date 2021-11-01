package View;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
@SuppressWarnings("All")
public class CommandLine {

    private final Scanner sc;
    private File mainFolder;
    private File outputFile;
    private final ArrayList<String> fileMasks;



    public CommandLine() {
        sc = new Scanner(System.in);
        fileMasks = new ArrayList<>();
    }

    public File getMainFolder() {
        return mainFolder;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public ArrayList<String> getFileMasks() {
        return fileMasks;
    }

    public void start() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("Properties.config"));

        mainFolder = new File((String) props.get("MainFolder"));
        outputFile = new File((String) props.get("OutputFile"));
        String[]a = ((String)props.get("Masks")).toString().split(" ");
        fileMasks.addAll(Arrays.asList(a));
        fileMasks.set(0, fileMasks.get(0).replace("\"", ""));
        fileMasks.set(fileMasks.size() - 1, fileMasks.get(fileMasks.size() - 1).replace("\"", ""));

    }

    public static void main(String[] args) throws IOException {
        (new CommandLine()).start();
    }
}
