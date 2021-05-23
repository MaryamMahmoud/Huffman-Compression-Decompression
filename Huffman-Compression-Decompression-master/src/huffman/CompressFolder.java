package huffman;

//import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
//import java.nio.file.Files;
import java.util.ArrayList;
//import java.util.List;

public class CompressFolder {
	public CompressFolder(File folder) throws Exception {
		ArrayList<String> files = new ArrayList<String>();
		System.out.println(folder.getAbsoluteFile().getParent());
		File outputFile = new File(folder.getAbsoluteFile()+".txt");
		BufferedWriter br  = new BufferedWriter(new FileWriter(outputFile));
	    long startTime = System.currentTimeMillis();
		files = listFilesForFolder(folder);
		writeIntoBufferedWriter(files, br);
		br.close();
		System.out.println(files);
		Compress c = new Compress(outputFile.getAbsolutePath());
		//outputFile.delete();
		  long stopTime = System.currentTimeMillis();
		    long elapsedTime = stopTime - startTime;
		    System.out.println("========================");
			System.out.println("Compressing Time = "+elapsedTime+"ms");
	}
	public void writeIntoBufferedWriter(ArrayList<String> files,BufferedWriter writer){
	    for(String f : files){
	        try{
	        BufferedReader r = new BufferedReader(new FileReader(f));
	        String line = null;
			writer.write("$$"+f+"\n");
			// read each line in file and  
              //write the contents of each line in the buffered writer writer
	        while((line = r.readLine()) != null){
	            writer.write(line);
	            writer.write("\n");
	        }
	        } catch(Exception ex){
	            ex.printStackTrace();
	        }
	    }
	}	

	// this function return an array holding paths of all files in the folder
        //determine whether it is file or directory
        //if the file entry is a directory call the function to get the directory path
        //print the absolute file name of file entry
        // store this path in paths array  
	private ArrayList<String> listFilesForFolder(final File folder) {
		ArrayList<String> paths = new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getAbsolutePath());
	            paths.add(fileEntry.getAbsolutePath());           
	        }
	    }
	    return paths;
	}
}
