package mainBeg;

import java.io.File;

import javax.swing.JTextArea;


public class IRTest {
	
	private static General opti = new General();
	
	public static String irid_a=new String();
	public static String smhm_irid=new String();
	
	public static void  putfile(JTextArea rtxt)
	{
		//String down = new String();
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin");
		telnet.sendCommand("ftpd_start.sh a a");
		String updir = new String();
		String irfile = new String();
		String hxdfile = new String();
		hxdfile = "E:\\hxd_code";
		irfile = "E:\\ir_code";
		updir = "/media/data/hdd/";
		boolean put = false;
		File hxd = new File(hxdfile);
		File ir = new File(irfile);
		FtpTest ftp = new FtpTest("a", "a", "192.168.1.251");
		ftp.getFTPClient();
		put = ftp.putFile(hxd,updir);
		if (put)
		{
			System.out.println("upload file "+hxdfile+" ok\n");
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("上传文件 " +hxdfile +" 成功\n");
		}
		else 
		{
			System.out.println("upload file "+hxdfile+" fail\n");
		
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("上传文件 " +hxdfile +" 失败\n");
		}
			
		
		put = ftp.putFile(ir,updir);
		if (put)
		{
			System.out.println("upload file "+irfile+" ok\n");
			//System.out.println("upload file ok\n");
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("上传文件 " +irfile +" 成功\n");
		}
		else 
		{
			System.out.println("upload file "+irfile+" fail\n");
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("上传文件 " +irfile +" 失败\n");
		}
		return;
	}
	
	
	private static String getRes(String in){
		String res = new String();
		int ok = 0;
		String tmps = new String();
		ok = in.indexOf("[ok]");
		
		int a=0,b=0;
		if (ok<0)
		{
			//System.out.println("Add IR failed " );
			return "";
		}
		else
		{
			//System.out.println("Add IR ok "+ok );
			tmps = in.substring(ok+4,in.length());
			//String te[]=addIR.split("[ok]");
			//System.out.println(tmps);
			a = tmps.indexOf("[");
			b = tmps.indexOf("]");
			
			res = tmps.substring(a+1,b);
			//System.out.println("Get id "+res);
			return res;
		}
		
		//return res;
	}
	public static void delir(JTextArea rtxt){
		
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin"); 
		System.out.println("===================="+smhm_irid);
		if (!smhm_irid.equals(""))
		{
			String delres = opti.optiStr(telnet.sendCommand("smhmManagerd -equip_del "
					+ smhm_irid));
			System.out.println(delres);
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("删除设备："+ delres+"\n");
		}
		else 
		{
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("无法找到设备ID，请重新添加并确认添加成功。\n");
		}
		return ;
	}
	
	
	public static void getir(JTextArea rtxt){
		
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin"); 
		//System.out.println("===================="+smhm_irid);
		
		String getres = opti.optiStr(telnet.sendCommand("smhm_ir_study.sh get_status"));
		System.out.println(getres);
		rtxt.paintImmediately(rtxt.getBounds());
		rtxt.append(getres+"\n");
	
		rtxt.paintImmediately(rtxt.getBounds());
			//rtxt.append("无法找到设备ID，请重新添加并确认添加成功。\n");
		
		return ;
	}
	
	public static void begir(JTextArea rtxt){
		
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin"); 
		//System.out.println("====================");
		
		String begres = opti.optiStr(telnet.sendCommand("smhm_ir_study.sh start"));
		System.out.println(begres);
		rtxt.paintImmediately(rtxt.getBounds());
		rtxt.append(begres+"\n");
	
		return ;
	}
	
	
	
	public static void hxdcode(JTextArea rtxt){
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin"); 
		String hxdres = opti.optiStr(telnet.sendCommand("zigbee_new.sh set acir "
				+irid_a + " /media/data/hdd/hxd_code"));
		
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("宏兴达发码:"+ hxdres+"\n");
	}
	
	public static void ircode(JTextArea rtxt){
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin"); 
		String irres = opti.optiStr(telnet.sendCommand("zigbee_new.sh set ir "
				+irid_a + " /media/data/hdd/ir_code"));
		
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("主机发码:"+ irres+"\n");
	}
	
	public static void irtest(JTextArea rtxt){
		
		String irid = null;
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin"); 
		/*****************搜素设备******************/
		System.out.println("*****************搜素设备*****************");
		String zigfind = opti.optiStr(telnet.sendCommand("zigbee_new.sh list all"));
		System.out.println(zigfind);
		if (!zigfind.equals(""))
		{
			
			int a = zigfind.indexOf('<');
			int b = zigfind.indexOf(';');
			int ok = 0;
			String tmps=new String();
			String rid=new String();
			
			irid= zigfind.substring(a+1,b);
			System.out.println("球球id :"+irid);
			System.out.println("*****************添加设备*****************");
			irid_a = irid;
			String addIR = opti.optiStr(telnet.sendCommand("smhmManagerd -v10_equip_add 0 kt_room 3 1 "
						+irid +" | grep ok"));
			ok = addIR.indexOf("[ok]");
			
			//String addIR = telnet.sendCommand("zigbee_new.sh add 0100000000000101");
			System.out.println("Add IR " + addIR);
			
			
			if (ok<0)
			{
				System.out.println("Add IR failed " );
			}
			else
			{
				//System.out.println("Add IR ok "+ok );
				tmps = addIR.substring(ok+4,addIR.length());
				//String te[]=addIR.split("[ok]");
				//System.out.println(tmps);
				a = tmps.indexOf("[");
				b = tmps.indexOf("]");
				
				rid = tmps.substring(a+1,b);
				System.out.println("Get id "+rid);
			}
			
			rid = getRes(addIR);
			smhm_irid= rid;
			System.out.println("****************获取版本号*****************");
			String ver = opti.optiStr(telnet.sendCommand("zigbee_new.sh getver "+irid));
			
			System.out.println("ver:"+ver);
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("红外转发器版本号：" +ver +"\n");
			//rm -f /tmp/zigbee/status/
			System.out.println("****************获取温湿度*****************");
			opti.optiStr(telnet.sendCommand("rm -f /tmp/zigbee/status/"+ addIR));
			if (!rid.equals(""))
			{
				rtxt.paintImmediately(rtxt.getBounds());
				rtxt.append("添加红外转发器 ：" +addIR +"\n");
				String temp = opti.optiStr(telnet.sendCommand("smhmManagerd  -th_ss_get_temp "+rid));
				temp = getRes(temp);
				System.out.println("temp: "+temp);
				rtxt.paintImmediately(rtxt.getBounds());
				rtxt.append("温度 ：" +temp +"\n");
				String hum = opti.optiStr(telnet.sendCommand("smhmManagerd  -th_ss_get_hum "+rid));
				hum = getRes(hum);
				System.out.println("hum: "+hum);	
				rtxt.paintImmediately(rtxt.getBounds());
				rtxt.append("湿度：" +hum +"\n");
			}
			else 
			{
				System.out.println("Add IR failed " );
				rtxt.paintImmediately(rtxt.getBounds());
				rtxt.append("添加红外转发器 失败\n");
			}
			
			//smhmManagerd  -th_ss_get_temp
			//smhmManagerd  -th_ss_get_hum
			//String delIR =opti.optiStr( telnet.sendCommand("zigbee_new.sh del "+irid));
			//System.out.println("Delete IR "+delIR);
		}
		else 
		{
			System.out.println("找不到红外转发器");
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("找不到红外转发器\n");
		}
	}
	public static void begin(String[] args) {
		//IRTest.irtest();
	}
}
