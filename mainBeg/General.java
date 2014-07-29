package mainBeg;

public class General {
	
	public String optiStr(String in){
		
		String out = new String();
		int sharp = 0;
		sharp = in.indexOf('#');
		//System.out.println("="+in+"=");
		if (sharp < 2)
			return "";
		else 
		{
			if ('\n' == in.charAt(sharp-1))
			{
				out = in.substring(0,sharp-2);
			}
			else 
			{
				out = in.substring(0,sharp-1);
			}
		}
		
		
		return out;
	
	}
	
	
	public General(){
		//General opti = new General();
	}
	
	
}
