import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;

public class AES {
	public static void main (String args[]) throws IOException {
		String option = args[0];
		// File keyFile = new File(args[1]);

		try{
		// remember: check for extension and remove it.
			InputStream inputFile = new FileInputStream(args[2]);
			InputStream keyFile = new FileInputStream(args[1]);

		// create new input stream reader
			InputStreamReader inputStream = new InputStreamReader(inputFile); 
			InputStreamReader keyStream = new InputStreamReader(keyFile); 

        // create new buffered reader
			BufferedReader inputReader = new BufferedReader(inputStream);
			BufferedReader keyReader = new BufferedReader(keyStream);

		
			String cipher_key = keyReader.readLine();



			int[][] st = new int[4][4];
			String buf = new String();
			buf = inputReader.readLine();
			
			// Reads line of input, puts it into 4x4 array
			String hexstring = "";
			int hexval = 0;
			for (int i = 0; i < 32; i++) {
				if (i < buf.length()) {
					if (i % 2 == 0) {
						hexstring = buf.substring(i,i+1);
						hexval = Integer.valueOf(hexstring, 16).intValue()*16;
					} else {
						hexstring = buf.substring(i,i+1);
						hexval += Integer.valueOf(hexstring, 16).intValue();
					}
				}
				if (i % 2 == 1) {
					st[i/8][(i/2)%4] = hexval;
				}
			} 
			



			

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					System.out.print(st[i][j]);
				}
				System.out.println();
			}


			// if(option.equalsIgnoreCase("E"))
			// 	encrypt(buf, cipher_key);

			// if(option.equalsIgnoreCase("D"))
			// 	decrypt(buf);

		} catch(Exception e) {
			System.out.println("");
			System.out.println("Caught Exception: " + e);
		}
	}

	public static void encrypt(String data, String key){
		System.out.println(data);
		System.out.println(key);
	}
	public static void decrypt(String key){
		System.out.println(key);
		
	}
}