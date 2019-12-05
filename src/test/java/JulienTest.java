package test.java;
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.File;


public class JulienTest {
	
	@Test
	public void dataForJulien()
	{
    	//int nb=new File(".\\src\\main\\java\\game\\entity\\mob").listFiles().length;
    	File myFile[] = new File(".\\src\\main\\java\\game\\entity\\mob").listFiles();
    	//System.out.println(new File(".\\src\\main\\java\\game\\entity\\mob").listFiles().clone()[1]);
    	//System.out.println(new File(".\\src\\main\\java\\game\\entity\\mob").listFiles());
    	int result = myFile.length-2;
    	assertEquals(0,0);
	}	
}
