package PYF;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Token {
	public static void main(String[] args) {
		Pattern p=Pattern.compile("java", Pattern.CASE_INSENSITIVE);//creates the pattern.
		Scanner s=new Scanner(System.in);
		System.out.println("Enter a statement");
		String i=s.nextLine();
		Matcher m=p.matcher(i);//search pattern in the  given string. 
		boolean found=m.find();
		if(found) {
			System.out.println("matched");
		}
		else
			System.out.println("failed");
		s.close();
	}
}
