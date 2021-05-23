package huffman;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
//import java.io.Reader;
import java.util.HashMap;
//import javax.imageio.stream.FileImageInputStream;

public class Decompression {
	// First we have code to symbol hashmap in order to convert each prefix code to the original symbol
         // get the file to be decompressed and call function mapping which will get the code and freq and
         //buid a text file using the code and freq to write the elements in the ouputstream file
	static HashMap<String,Byte> codeToSymbol = new HashMap<String, Byte>();
	
	public Decompression(String path) throws IOException {
	     long startTime = System.currentTimeMillis();

		File file = new File(path);
		mapping(file);
		System.out.println("File created..");
		 file.delete();
		 long stopTime = System.currentTimeMillis();
	     long elapsedTime = stopTime - startTime;
	  	System.out.println("========================");
		System.out.println("Decompressing Time = "+elapsedTime+"ms");
	}
	
	
	// convert the file into a string and store it in str
        // let str be a substring from index 0 to strlength -4
        // write str in a buffered output stream
        //then delete f
	public static void fileEmpty(File f) throws IOException
	{
		
		String str = f.toString();
		str = str.substring(0, str.length()-4);
		BufferedOutputStream ewrite = new BufferedOutputStream(new FileOutputStream(str));
		ewrite.close();
		f.delete();
		System.exit(0);
	}

	// in this ffunction create a buffer reader to read the compressed file given
        //read each line from the buffer reader and break between each 2 strings with space as separator 
        //and store them in an array "arr" then break every string in array"arr" and store into another array "a"
        // strings separated by "=" for example if a=b the a[0]:a and a[1]:b
        // insert in the hash map codetosym with key a[0] the decimal value of the byte of a[1]
        // get the frequncy and build a text file using them
	public static void mapping(File f) throws IOException
	{
		
		BufferedReader b = new BufferedReader(new FileReader(f));
		if(f.length()==0)
		{
			fileEmpty(f);	
		}
		String mapp =b.readLine();
		
		String arr[] =mapp.split(" ");
		for (int i=0; i<arr.length;i++)
		{
			String regx= arr[i];
			String a[] = regx.split("=");
			codeToSymbol.put(a[0],Byte.parseByte(a[1]));
		}
		int freq =Integer.parseInt(b.readLine());
		b.close();
		buildTextFile(codeToSymbol, f,freq);
	}

	// in this function create a buffered input stream for the file
        // and convert file to strin and store it in a string called str let str be a substring from index 0 to strlegnth -4
        //write this str in a buffered outputstream
        //while not the end of the inputstream convert int x to a binary string of 8 bits 
        // and apprend this binary value in the stringbuilder buildcode
        // get each char from the stringbuilder buildcode and sore it in the string check
        // and write in outputstreamfile the string with the "check" value calculated from the map 
	public static void buildTextFile(HashMap<String, Byte> map, File f, int freq) throws IOException
	{
		
		int frequencyCounter=0;
		StringBuilder buildCode = new StringBuilder(); 
		String check ="";
		BufferedInputStream b1 = new BufferedInputStream(new FileInputStream(f));
		String str = f.toString();
		str = str.substring(0, str.length()-4);
		BufferedOutputStream bwrite = new BufferedOutputStream(new FileOutputStream(str));
		while(b1.read()!='\n');
		while(b1.read()!='\n');
		int x = 0;
		while((x=b1.read())!= -1)
		{

			String formStr = Integer.toBinaryString(x);
			String format = ("00000000"+formStr).substring(formStr.length());
			buildCode.append(format);
			}
			
		for (int i=0 ; i< buildCode.length();i++)
		{
			check = check + buildCode.charAt(i);
			if(frequencyCounter<freq && map.containsKey(check))
			{
				bwrite.write(map.get(check));
				check="";
				frequencyCounter++;
			}	
		}
			b1.close();
			bwrite.close();
}
}
