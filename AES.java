import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;

public class AES {

	public static AES _aes = new AES();

	public static void main (String args[]) throws IOException {
		String option = args[0];
		File keyFile = new File(args[1]);

		try{
		// remember: check for extension and remove it.
			InputStream inputFile = new FileInputStream(args[2]);
		// create new input stream reader
			InputStreamReader inStream = new InputStreamReader(inputFile); 
        // create new buffered reader
			BufferedReader bufReader = new BufferedReader(inStream);
		// char buffer
			char[] cbuf = new char[inputFile.available()];
			bufReader.read(cbuf, 0, inputFile.available());

			if(option.equalsIgnoreCase("E"))
				_aes.encrypt(cbuf);

			if(option.equalsIgnoreCase("D"))
				_aes.decrypt(cbuf);

		} catch(Exception e) {
			System.out.println("");
			System.out.println("Caught Exception: " + e);
		}
	}

	public void encrypt(char[] cbuf){
		for (int i = 0; i < cbuf.length; i++){
			System.out.print(cbuf[i]);
		}
	}
	public void decrypt(char[] cbuf){
		for (int i = 0; i < cbuf.length; i++){
			System.out.println(cbuf[i]);
		}
	}
}