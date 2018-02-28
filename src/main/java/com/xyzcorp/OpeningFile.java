package com.xyzcorp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OpeningFile {

    public OpeningFile() {
    }
    
    public static void main(String[] args) throws FileNotFoundException {
		try (FileInputStream fileInputStream = new FileInputStream(new File("..."));
		     BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
			
		} catch(IOException ioe) {
			
		}
	}
}
