package test.java;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class JulienTest {

	
	@Test
	public void dataForJulien()
	{
    	//int nb=new File(".\\src\\main\\java\\game\\entity\\mob").listFiles().length;
    	File myFile[] = new File(".\\src\\main\\java\\game\\entity\\mob").listFiles();
    	assertEquals((myFile.length-2),8);
//    	System.out.println(new File(".\\src\\main\\java\\game\\entity\\mob").listFiles().clone()[1]);
    	System.out.println(new File(".\\src\\main\\java\\game\\entity\\mob").listFiles());
	}
	

}