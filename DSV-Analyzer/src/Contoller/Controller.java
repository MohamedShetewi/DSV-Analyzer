package Contoller;

import Model.Analyzer;
import Model.DSVFile;
import Model.DSVReader;
import Model.Statistics.FileFormat;
import Model.Statistics.FileStructure;
import View.CommandLine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

@SuppressWarnings("ALL")
public class Controller {


    private final ArrayList<DSVFile> dsvFiles;
    private final File outputFile;
    private ArrayList<File> matchedFiles;
    private Hashtable<FileStructure, Integer> fileStructureStatistics;
    private Hashtable<FileFormat, Integer> fileFormatStatistics;

    public Controller() throws IOException, InterruptedException {
        CommandLine commandLine = new CommandLine();
        commandLine.start();
        File mainFolder = commandLine.getMainFolder();
        outputFile = commandLine.getOutputFile();
        ArrayList<String> fileMasks = commandLine.getFileMasks();

        DirectoryNavigator directoryNavigator = new DirectoryNavigator(mainFolder, fileMasks);
        matchedFiles = directoryNavigator.getAllFiles();

        dsvFiles = new ArrayList<>();

        startParallelReading();
        startParallelAnalyzing();
        prepareStatistics();
        outputToFile();
    }

    private void outputToFile() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--------------------------Encountered File Structured--------------------------\n");
        fileStructureStatistics.forEach((file , count) -> {
            stringBuilder.append("{ ");
            for(int i = 0; i < file.getColumns().length;i++){
                stringBuilder.append(file.getColumns()[i]).append(" : ").append(file.getDataTypes()[i]);
                if( i + 1 < file.getColumns().length)
                    stringBuilder.append(", ");
            }
            stringBuilder.append("}").append(" ----> ").append(count).append(" time\\s\n");
        });
        stringBuilder.append("--------------------------Encountered File Formats--------------------------\n");

        fileFormatStatistics.forEach((file , count) -> {
            stringBuilder.append("Delimiter = ").append(file.getDelimiterName()).append("  ");
            stringBuilder.append("Formats:").append(Arrays.toString(file.getUsedFormats()));
            stringBuilder.append(" ----> ").append(count).append(" time\\s\n");
        });

        FileWriter fileWriter = new FileWriter(outputFile);
        fileWriter.append(stringBuilder.toString());
        fileWriter.close();
    }

    private void startParallelReading() {
        matchedFiles.parallelStream()
                .forEach(matchedFile -> {
                    DSVReader dsvReader = null;
                    try {
                        dsvReader = new DSVReader(matchedFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert dsvReader != null;
                    dsvFiles.add(new DSVFile(matchedFile.getName(), dsvReader.getTable(), dsvReader.getDelimiterType(), dsvReader.getColumnNames()));
                });
    }


    @SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
    private void prepareStatistics(){
        fileStructureStatistics = new Hashtable<>();
        fileFormatStatistics = new Hashtable<>();

        dsvFiles.forEach(dsvFile -> {

            FileStructure fileStructure = new FileStructure(dsvFile.getColumnNames().toArray(new String[dsvFile.getColumnNames().size()]), dsvFile.getColumnsType());
            fileStructureStatistics.put(fileStructure
                    , fileStructureStatistics.getOrDefault(fileStructure, 0 ) + 1);

            FileFormat fileFormat = new FileFormat(""+dsvFile.getDelimiter(), dsvFile.getColumnsType());
            fileFormatStatistics.put(fileFormat
                    ,fileFormatStatistics.getOrDefault(fileFormat, 0 ) + 1);
        });
    }

    public void startParallelAnalyzing() throws InterruptedException {
        dsvFiles.parallelStream().forEach(Analyzer::new);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        long startTime = System.nanoTime();
        Controller c = new Controller();

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime / 1e9 + " sec");

    }

}
