import java.util.Scanner;

public class Frequency {

	public static void main(String args[])
	   {
	        double k;
	        String input;
	        char ch = 0;
	        double percent;
	        
	        System.out.print("Enter a String : ");
	        input=new Scanner(System.in).nextLine();

	        for(char c='a'; c<='z'; c++)
	        {
	            k=0;
	            for(int j=0; j<input.length(); j++)
	            {
	                ch = input.charAt(j);
	                if((ch+"").equalsIgnoreCase(c+""))
	                {
	                    k++;
	                }
	            }
	            if(k>0)
	            {
	            	percent = (k/input.length() * 100);
	                System.out.println("The character " + c + " : " + percent + " %");
	            }
	        }
	   }

}
