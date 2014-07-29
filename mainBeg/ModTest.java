package mainBeg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JTextArea;

public class ModTest {

	private static General opti = new General();
	
	//static String rf433ID[]=null;
	static String [] rf433ID=new String[5];
	static String [] rf433ID4=new String[5];
	static String [] rf433ID7=new String[5];
	public static void readFileByChars(String fileName) {
		
		//File file = new File(fileName);
		FileReader reader;
		try {
			reader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(reader);
			String s1 = new String();
			int i=0;
			
			try {
				
				while((s1 = br.readLine()) != null) {
					 System.out.println(s1);
					 
					 if (i>3 && i<=7)
						 rf433ID4[i-4]=s1;
					 else if (i>7 && i<=11)
						 rf433ID7[i-8]=s1;
					 else 
						 rf433ID[i]=s1;
					// System.out.println("Get string :"+rf433ID[i]);
					 i++;
				}
				br.close();
				reader.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			
			e1.printStackTrace();
		}
        //Reader reader = null;
	
	/**
	 * 
	 try {
         System.out.println("以字符为单位读取文件内容，一次读一个字节：");
         // 一次读一个字符
         reader = new InputStreamReader(new FileInputStream(file));
         int tempchar;
         while ((tempchar = reader.read()) != -1) {
             // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
             // 但如果这两个字符分开显示时，会换两次行。
             // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
             if (((char) tempchar) != '\r') {
                 System.out.print((char) tempchar);
             }
         }
         reader.close();
     } catch (Exception e) {
         e.printStackTrace();
     }
	}
	**/
	}
	
	public static void zigbee(JTextArea rtxt){
		String zigres = new String();
		String irid = null;
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin"); 
		String zigfind = opti.optiStr(telnet.sendCommand("zigbee_new.sh list all"));
		System.out.println(zigfind);
		if (!zigfind.equals(""))
		{
			
			int a = zigfind.indexOf('<');
			int b = zigfind.indexOf(';');
			irid= zigfind.substring(a+1,b);
			System.out.println("球球id :"+irid);
			
			String addIR = opti.optiStr(telnet.sendCommand("zigbee_new.sh add "+irid));
			//String addIR = telnet.sendCommand("zigbee_new.sh add 0100000000000101");
			System.out.println("Add IR " + addIR);
			//zigres = zigres + "添加红外转发器 " + addIR +"\n";
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("添加红外转发器 ：" +addIR +"\n");
			
			String ver = opti.optiStr(telnet.sendCommand("zigbee_new.sh getver"));
			
			System.out.println("ver:"+ver);
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("红外转发器版本号：" +ver +"\n");
			//zigres = zigres + "红外转发器版本号：" + ver +"\n";
			
			String delIR =opti.optiStr( telnet.sendCommand("zigbee_new.sh del "+irid));
			System.out.println("Delete IR "+delIR);
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("删除红外转发器" +addIR +"\n");
			//zigres = zigres + "删除红外转发器 " + addIR +"\n";
		}
		else 
		{
			System.out.println("找不到红外转发器");
			//zigres = "找不到红外转发器!\n";
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("找不到红外转发器!\n");
		}
		
		return ;
		//zigbee_new.sh add 0100000000000101
		//zigbee_new.sh getver
		//zigbee_new.sh del
		
	}
	public static void rf433(JTextArea rtxt){
		
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin"); 
		String rfres = new String();
		String fileName = "E:/abc.txt";
		int i=0;
		
		ModTest.readFileByChars(fileName);
		for (i=0;i<4;i++)
		{
			//System.out.println("Get string :"+rf433ID[i]);
		}
		
		String setid1 =opti.optiStr( telnet.sendCommand("rf433.sh setid 03 01"));
		
		String rfon1 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID[0]+" on"));
		rtxt.paintImmediately(rtxt.getBounds());
		rtxt.append("chann1 on 1 "+rfon1+"  ");
		String rfon2 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID[1]+" on"));
		rtxt.paintImmediately(rtxt.getBounds());
		rtxt.append("chann1 on 2 "+rfon2+"\n");
		String rfon3 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID[2]+" on"));
		rtxt.paintImmediately(rtxt.getBounds());
		rtxt.append("chann1 on 3 "+rfon3+"  ");
		String rfon4 = opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID[3]+" on"));
		rtxt.paintImmediately(rtxt.getBounds());
		rtxt.append("chann1 on 4 "+rfon4+"\n");
		
		//System.out.println("channe1 on : 1["+rfon1+"] 2["+rfon2+"] 3["+rfon3+"] 4["+rfon4+"] ");
		String rfoff1 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID[0]+" off"));
		rtxt.paintImmediately(rtxt.getBounds());
		rtxt.append("chann1 off 1 "+rfoff1+" ");
		String rfoff2 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID[1]+" off"));
		rtxt.paintImmediately(rtxt.getBounds());
		rtxt.append("chann1 off 2 "+rfoff2+"\n");
		String rfoff3 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID[2]+" off"));
		rtxt.paintImmediately(rtxt.getBounds());
		rtxt.append("chann1 off 3 "+rfoff3+" ");
		String rfoff4 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID[3]+" off"));
		rtxt.paintImmediately(rtxt.getBounds());
		rtxt.append("chann1 off 4 "+rfoff4+"\n");
		//System.out.println("channe1 off : 1["+rfoff1+"] 2["+rfoff2+"] 3["+rfoff3+"] 4["+rfoff4+"] ");
		
		String setid4 =opti.optiStr( telnet.sendCommand("rf433.sh setid 03 04"));
		 rfon1 =opti.optiStr (telnet.sendCommand("rf433.sh set "+rf433ID4[0]+" on"));
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann4 on 1 "+rfon1+" ");
		 rfon2 =opti.optiStr (telnet.sendCommand("rf433.sh set "+rf433ID4[1]+" on"));
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann4 on 2 "+rfon2+"\n");
		 rfon3 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID4[2]+" on"));
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann4 on 3 "+rfon3+" ");
		 rfon4 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID4[3]+" on"));
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann4 on 4 "+rfon4+"\n");
		
		 //System.out.println("channe4 on : 1["+rfon1+"] 2["+rfon2+"] 3["+rfon3+"] 4["+rfon4+"] ");
		 rfoff1 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID4[0]+" off"));
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann4 off 1 "+rfoff1+" ");
		 rfoff2 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID4[1]+" off"));
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann4 off 2 "+rfoff2+"\n");
		 rfoff3 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID4[2]+" off"));
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann4 off 3 "+rfoff3+" ");
		 rfoff4 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID4[3]+" off"));
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann4 off 4 "+rfoff4+"\n");
		 //System.out.println("channe4 off : 1["+rfoff1+"] 2["+rfoff2+"] 3["+rfoff3+"] 4["+rfoff4+"] ");
		
		String setid7 =opti.optiStr( telnet.sendCommand("rf433.sh setid 03 07"));
		
		rfon1 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID7[0]+" on"));
		rtxt.paintImmediately(rtxt.getBounds());
		rtxt.append("chann7 on 1 "+rfon1+" ");
		 rfon2 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID7[1]+" on"));
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann7 on 2 "+rfon2+"\n");
		 rfon3 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID7[2]+" on"));
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann7 on 3 "+rfon3+" ");
		 rfon4 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID7[3]+" on"));
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann7 on 4 "+rfon4+"\n");
		// System.out.println("channe7 on : 1["+rfon1+"] 2["+rfon2+"] 3["+rfon3+"] 4["+rfon4+"] ");
		
		 rfoff1 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID7[0]+" off"));
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann7 off 1 "+rfoff1+" ");
		 rfoff2 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID7[1]+" off"));
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann7 off 2 "+rfoff2+"\n");
		 rfoff3 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID7[2]+" off"));
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann7 off 3 "+rfoff3+" ");
		 rfoff4 =opti.optiStr( telnet.sendCommand("rf433.sh set "+rf433ID7[3]+" off"));	
		 rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("chann7 off 4 "+rfoff4+"\n");	
		 //System.out.println("channe7 off : 1["+rfoff1+"] 2["+rfoff2+"] 3["+rfoff3+"] 4["+rfoff4+"] ");
		  
		  return ;
	}
	
	public static void  secido(JTextArea rtxt){
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin"); 
		String secres = new String();
		int time=0;
		String sec=new String();
		telnet.sendCommand("rm -f /var/run/sec_iDo/sec_log");
		
		while(time<10)
		{
			//System.out.println("sec test");
			sec = telnet.sendCommand("ls /var/run/sec_iDo/ | grep sec_log");
			//System.out.println(sec);
			sec = opti.optiStr(sec);
			if (sec.equals(""))
			{
				System.out.println("Can not find sec info");
				rtxt.paintImmediately(rtxt.getBounds());
				rtxt.append("未发现安防信息，请触发安防设备\n" );
			}
			else
			{
				System.out.println("The sec module is ok");
				//System.out.println("安防模块运行正常。");
				rtxt.paintImmediately(rtxt.getBounds());
				rtxt.append("安防模块运行正常\n" );
				break;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			time++;
		}
		
		return;
		//return secres;
	}
	
	public static void begin() {
		
		System.out.println("Begin to test module");
		//ModTest.rf433();
		//ModTest.secido();
		//ModTest.zigbee();
		//new InteTest().m_Inte_Test();
    	//TelnetOperator.m_telnet();
        //B70_1 m_read = new B70_1();
       // m_read.m_Read();
		
    }
}
