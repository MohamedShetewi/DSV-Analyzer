package Contoller;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Pattern;

@SuppressWarnings("ALL")
public class DirectoryNavigator {

    private File mainFile;
    private ArrayList<String> fileMasks;
    private ArrayList<File> allFiles;

    public ArrayList<File> getAllFiles() {
        return allFiles;
    }

    public DirectoryNavigator(File mainFile, ArrayList<String> fileMasks) {
        this.mainFile = mainFile;
        this.fileMasks = fileMasks;
        this.allFiles = new ArrayList<>();
        startNavigation();
    }

    private void startNavigation() {
        Stack<File> fileStack = new Stack<>();
        fileStack.push(mainFile);

        while (!fileStack.isEmpty()) {
            File curFile = fileStack.pop();
            File[] listOfFilesOrDir = curFile.listFiles();

            if (listOfFilesOrDir != null)
                for (File file : listOfFilesOrDir)
                    if (file.isDirectory())
                        fileStack.push(file);
                    else {
                        boolean isMatched = false;
                        for(String mask : fileMasks)
                        {
                            String regexMask = mask.replace("*",".");
                            isMatched |= Pattern.compile(regexMask).matcher(file.getName()).find();
                        }
                        if(isMatched)
                            allFiles.add(file);
                    }
        }
    }

}
