package mainBeg;

import java.io.UnsupportedEncodingException;

import javax.swing.JTextArea;



public class WifiTh {
	private static General opti = new General();
	
	 public static void m_wifi_RSSI(JTextArea rtxt){
		 //public static String m_wifi_RSSI(JTextArea rtxt){
		TelnetOperator telnet = new TelnetOperator("VT220","#"); //Windows,用VT220,否则会乱码  
		telnet.login("192.168.2.251", 23, "admin", "admin"); 
		//String resStr = new String();
		 String rs2 =opti.optiStr( telnet.sendCommand("wlanconfig ath0 list"));
		 telnet.distinct();
      	//telnet.readUntil("list");
		 //System.out.println(rs2);
		 if (!rs2.equals(""))
		 {
			 String te[]=rs2.split("\n");
		 	    
		 	    //System.out.println(rs);
		 	    //String a[] = rs.split("Speed: ");
		 	    //System.out.println(a[1]);
		 	   // String b[] = a[1].split("Mb/s");
		 	    System.out.println(rs2);
		 	   // System.out.println(te[1]);
		 	    int s = te[0].indexOf("RSSI");
		 	    int s1 = te[0].indexOf("IDLE");
		 	    String rssi = te[1].substring(s+1, s1-1);
		 	    System.out.print("RSSI:");
		 	    System.out.println(rssi);
		 	    rtxt.append("RSSI:" + rssi +"\n");
		         try {  
		             rs2 = new String(rs2.getBytes("ISO-8859-1"),"GBK");   //转一下编码  
		         } catch (UnsupportedEncodingException e) {  
		             e.printStackTrace();  
		         }
		 }
		 else 
		 {
			 System.out.println("Can not get wifi RSSI,please connect B70 first");
			 System.out.println("无法获取RSSI，请先连接B70。");
			 rtxt.append("无法获取RSSI，请先连接B70。\n");
		 }
	 	    
		 return ;
	 	    // 
	 	 
	 }
	  public static void begin(String[] args) {
	    	
		  	//WifiTh.m_wifi_RSSI();
	    	//TelnetOperator.m_telnet();
	        //B70_1 m_read = new B70_1();
	       // m_read.m_Read();
	    }
}
