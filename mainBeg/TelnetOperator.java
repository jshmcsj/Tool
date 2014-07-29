package mainBeg;

import java.io.IOException;  
import java.io.InputStream;  
import java.io.PrintStream;  
import java.io.UnsupportedEncodingException;  
  
import org.apache.commons.net.telnet.TelnetClient;  

public class TelnetOperator {

	 private String prompt = "# "; //������ʶ�ַ���,Windows����>,Linux����#  
	 private char promptChar = '#';   //������ʶ�ַ�  
	 private TelnetClient telnet;  
	 private InputStream in;     // ������,���շ�����Ϣ  
	 private PrintStream out;    // �������д�� ����  
	
	 public TelnetOperator(String termtype,String prompt){  
	        telnet = new TelnetClient(termtype);  
	        setPrompt(prompt);  
	    }  
	      
	    public TelnetOperator(String termtype){  
	        telnet = new TelnetClient(termtype);  
	    }  
	      
	    public TelnetOperator(){  
	        telnet = new TelnetClient();  
	    }  
	      
	    /** 
	     * ��¼��Ŀ������ 
	     * @param ip 
	     * @param port 
	     * @param username 
	     * @param password 
	     */  
	    public void login(String ip, int port, String username, String password){
	    	
	        try {  
	            telnet.connect(ip, port);  
	           
	            in = telnet.getInputStream();  
	            
	            out = new PrintStream(telnet.getOutputStream());  
	            readUntil("Login:");  
	            
	            write(username);  
	            readUntil("Password:");  
	            write(password); 
	            
	            readUntil("#");  
	           // String rs = null;  
	            
	        } catch (Exception e) {  
	            throw new RuntimeException(e);  
	        }  
	    }  
	      
	    /** 
	     * ��ȡ������� 
	     *  
	     * @param pattern   ƥ�䵽���ַ���ʱ���ؽ�� 
	     * @return 
	     */  
	    public  String readUntil(String pattern) {  
	        //StringBuffer sb = new StringBuffer();  
	    	//System.out.println("="+pattern+"=");  
	        try  
	        {  
	        	char lastChar = (char)-1;
	        	boolean flag = pattern!=null&&pattern.length()>0;  
	            if(flag)  
	                lastChar = pattern.charAt(pattern.length() - 1);  
	           // lastChar = pattern.charAt(pattern.length() - 1);  
	            StringBuffer sb1 = new StringBuffer();  
	            char ch = (char)in.read();  
	            while (ch != -1 )  
	            {  
	                sb1.append(ch);  
	                //System.out.print(sb1.toString());  
	                if (ch == lastChar)  
	                {  
	                    if (sb1.toString().endsWith(pattern))  
	                    {  
	                        return sb1.toString();  
	                    }  
	                }  
	                ch = (char)in.read();  
	                //System.out.print(ch);  
	            }  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        } 
	        /**
	        try {  
	            char lastChar = (char)-1;  
	            boolean flag = pattern!=null&&pattern.length()>0;  
	            if(flag)  
	                lastChar = pattern.charAt(pattern.length() - 1);  
	            char ch;  
	            int code = -1;  
	            while ((code = in.read()) != -1) {  
	                ch = (char)code;  
	                sb.append(ch);  
	                  
	                //ƥ�䵽������ʶʱ���ؽ��  
	                if (flag) {  
	                    if (ch == lastChar && sb.toString().endsWith(pattern)) {  
	                        return sb.toString();  
	                    }  
	                }else{  
	                    //���ûָ��������ʶ,ƥ�䵽Ĭ�Ͻ�����ʶ�ַ�ʱ���ؽ��  
	                    if(ch == promptChar)  
	                        return sb.toString();  
	                }  
	                //��¼ʧ��ʱ���ؽ��  
	                if(sb.toString().contains("Login Failed")){  
	                    return sb.toString();  
	                }  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        **/
	       // return sb.toString();  
	        return null;
	    }  
	      
	    /** 
	     * �������� 
	     *  
	     * @param value 
	     */  
	    public void write(String value) {  
	        try {  
	        	//System.out.println("=" + value);
	            out.println(value);  
	            out.flush();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	      
	    /** 
	     * ��������,����ִ�н�� 
	     *  
	     * @param command 
	     * @return 
	     */  
	    String res;
	    public String sendCommand(String command) {  
	        try {  
	        	//System.out.println("end of send = "+command);
	            write(command); 
	            //System.out.println("prompt=" + prompt);
	             readUntil("\n");
	             //readUntil("#");
	            res = readUntil(prompt);
	            //System.out.println("***********************"); 
	            //System.out.println(res);
	            //System.out.println("==========================");
	            //return readUntil(prompt);
	            return (res);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        System.out.println("end of send");
	        return null;  
	    }  
	      
	    /** 
	     * �ر����� 
	     */  
	    public void distinct(){  
	        try {  
	            if(telnet!=null&&!telnet.isConnected())  
	                telnet.disconnect();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    public void setPrompt(String prompt) {  
	        if(prompt!=null){  
	            this.prompt = prompt;  
	            this.promptChar = prompt.charAt(prompt.length()-1);  
	        }  
	    }  
	    
	   // public void testEth(){
	    public static void m_telnet(){
	    	TelnetOperator telnet = new TelnetOperator("VT220","#"); //Windows,��VT220,���������  
		    telnet.login("192.168.1.251", 23, "admin", "admin");  
	    	//String rs = telnet.sendCommand("ls");  
	 	    //System.out.println(rs);
		    /*********test eth*********************/
	 	    String rs = telnet.sendCommand("ethtool eth0");
	 	    telnet.distinct();
	 	    System.out.println(rs);
	 	    int m_s=rs.indexOf("Speed: ");
	 	    int m_end=rs.indexOf("Mb/s");
	 	    String m_card=rs.substring(m_s+7,m_end);
	 	    System.out.println(m_card);
	 	    //String a[] = rs.split("Speed: ");
	 	    //System.out.println(a[1]);
	 	    //String b[] = a[1].split("Mb/s");
	 	    //System.out.println(b[0]);
	 	
	         try {  
	             rs = new String(rs.getBytes("ISO-8859-1"),"GBK");   //תһ�±���  
	         } catch (UnsupportedEncodingException e) {  
	             e.printStackTrace();  
	         }
	         /**************end ***test *********eth***************/
	         /************** ***test **rssi**********************/
	         String rs2 = telnet.sendCommand("wlanconfig ath0 list");
	         
	         	//telnet.readUntil("list");
		 	    telnet.distinct();
		 	    // 
		 	    String te[]=rs2.split("\n");
		 	    
		 	    //System.out.println(rs);
		 	    //String a[] = rs.split("Speed: ");
		 	    //System.out.println(a[1]);
		 	   // String b[] = a[1].split("Mb/s");
		 	    System.out.println(rs2);
		 	   // System.out.println(te[1]);
		 	    int s = te[1].indexOf("RSSI");
		 	    int s1 = te[1].indexOf("IDLE");
		 	    String rssi = te[2].substring(s+1, s1-1);
		 	    System.out.println(rssi);
		 	
		         try {  
		             rs2 = new String(rs2.getBytes("ISO-8859-1"),"GBK");   //תһ�±���  
		         } catch (UnsupportedEncodingException e) {  
		             e.printStackTrace();  
		         }
		     /************** **end test **rssi**********************/
		         
		     /************** ***test **rssi**********************
		         String usb_rate = telnet.sendCommand("/tmp/b70_test-2.sh | grep write=");
		         System.out.println(usb_rate);
		         int w_b = usb_rate.indexOf("write=");
		         int w_e = usb_rate.indexOf(",");
		         int r_b = usb_rate.indexOf("read=");
		         String r_rate=usb_rate.substring(r_b);
		         //System.out.println(r_rate);
		         	//telnet.readUntil("list");
			 	    telnet.distinct();
			 	    // 
			 	
			         try {  
			        	 usb_rate = new String(usb_rate.getBytes("ISO-8859-1"),"GBK");   //תһ�±���  
			         } catch (UnsupportedEncodingException e) {  
			             e.printStackTrace();  
			         }
		 /************** **end test **rssi**********************/
			         
			    String zig_id = telnet.sendCommand("zigbee_new.sh list all_innet");
			    int id_b = zig_id.indexOf("<");
		        int id_e = zig_id.indexOf(";");
		        String  id = zig_id.substring(id_b+1, id_e);
		        
		        String wendu = telnet.sendCommand("smhmManagerd  -th_ss_get_temp "+id);
		        String shidu = telnet.sendCommand("smhmManagerd  -th_ss_get_hum "+id);
		        //System.out.println(id);
	     //zigbee_new.sh list all_innet
			         
			         
	    }
	    
	    
	    /**
	     *  wlanconfig ath0 list
ADDR               AID CHAN RATE RSSI IDLE  TXSEQ  RXSEQ CAPS ACAPS ERP    STATE HTCAPS
68:df:dd:d8:87:2a    2    6  72M   71    0    849  18880 EPSs         0       1f WQGS   RSN WME
	     * @param args
	     
	    public static void main(String[] args) {
	    	
	    	//TelnetOperator.m_telnet();
	        //B70_1 m_read = new B70_1();
	       // m_read.m_Read();
	    }*/

}
