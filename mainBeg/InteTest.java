package mainBeg;

import javax.swing.JTextArea;


public class InteTest {
	
	 
	NewGui gui =new NewGui();
	
	private static General opti = new General();
	public  void m_Inte_Test() {
		// TODO Auto-generated method stub
	
		System.out.println("=====================");
		//new InteTest().getVer();
		//InteTest.checkChip();
		
	}
	
	
	public void getVer(JTextArea res){
		
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin"); 
		telnet.sendCommand("get_custinfo.sh");
		
		
		String gettx=new String();
		String softID =opti.optiStr( telnet.sendCommand("cat /tmp/cust_info | grep SoftwareID"));
		
		//readUntil("#");
		//gettx = res.getText();
		res.setText("==================================\n");
		res.append(softID+"\n");
		System.out.println(softID);
		res.paintImmediately(res.getBounds());
		String rf433 = opti.optiStr(telnet.sendCommand("rf433.sh getver"));
		//System.out.println("=====================");
		//readUntil("#");
		System.out.println("rf433版本号："+rf433);
		//gettx = res.getText();
		res.append("rf433版本号："+rf433+"\n");
		
		String zigbeever =opti.optiStr( telnet.sendCommand("zigbee_new.sh getver"));
		//System.out.println("=====================");
		//readUntil("#");
		System.out.println("zigbee 版本号："+zigbeever);
		//gettx = res.getText();
		res.paintImmediately(res.getBounds());
		res.append("zigbee 版本号："+zigbeever+"\n");
		
		
		//gui.resta.setText("wolaila");
		//res.setText("11111111111111");
		//String secver = telnet.sendCommand("sec_iDo getver");
		//System.out.println("=====================");
		//readUntil("#");
		//System.out.println(secver);
		telnet.distinct();
	}
	
	public static void checkChip(JTextArea res){
		//System.out.println("checkchip");
		
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin"); 
		
		String lan = opti.optiStr(telnet.sendCommand("ifconfig | grep eth0"));
		//System.out.println(lan);
		if(!lan.equals(""))		
		{
			//System.out.println(disk);
			System.out.println("lan正常");
			res.paintImmediately(res.getBounds());
			res.append("lan正常\n");
		}
		else 
		{
			res.paintImmediately(res.getBounds());
			System.out.println("lan异常");
		}
		
		String wan = opti.optiStr(telnet.sendCommand("ifconfig | grep eth1"));
		//System.out.println(wan);
		if(!wan.equals(""))		
		{
			//System.out.println(disk);
			System.out.println("wan正常");
			res.paintImmediately(res.getBounds());
			res.append("wan正常\n");
		}
		else 
		{
			System.out.println("wan异常");
			res.paintImmediately(res.getBounds());
			res.append("wan异常\n");
		}
		
		String disk = opti.optiStr(telnet.sendCommand("hdd_sata.sh get_device | grep sd"));
		if(!disk.equals(""))		
		{
			//System.out.println(disk);
			System.out.println("硬盘存在");
			res.paintImmediately(res.getBounds());
			res.append("硬盘存在\n");
		}
		else 
		{
			res.paintImmediately(res.getBounds());
			System.out.println("未发现硬盘");
		}
		
		
		String wifi = opti.optiStr(telnet.sendCommand("iwconfig 2>/dev/null | grep ath0"));
		if(!wifi.equals(""))
		{
			//System.out.println(wifi);
			System.out.println("无线网卡正常");
			res.paintImmediately(res.getBounds());
			res.append("无线网卡正常\n");
		}
		else 
		{
			System.out.println("未发现无线网卡");
			res.paintImmediately(res.getBounds());
		}
			
		
		String mem = opti.optiStr(telnet.sendCommand("free | grep Mem | awk '{printf $2}'"));
		System.out.println("内存容量为：" +mem);
		res.paintImmediately(res.getBounds());
		res.append("内存容量为：" +mem+"\n");
		
		String usb =opti.optiStr( telnet.sendCommand("cat /proc/bus/usb/devices | grep eth | awk -F \"Prot=00 \" '{printf $2}'"));
		//System.out.println(usb);
		//iwconfig 2>/dev/null | grep ath0
		//free | grep Mem | awk '{printf $2}
		//hdd_sata.sh get_device | grep sd
		//userCfgd -reset
		
		if(!usb.equals(""))
		{
			//System.out.println(wifi);
			System.out.println("USB正常");
			res.paintImmediately(res.getBounds());
			res.append("USB正常\n");
		}
		else 
		{
			System.out.println("未发现USB芯片");
		}
		telnet.sendCommand("smhmManagerd -reset ");
		telnet.sendCommand("rm -f /media/data/hdd/* ");
		System.out.println("=====================");
		
		res.append("==================================\n");
		res.append("请手动重启\n");
		//System.out.println("准备复位。。。");
		telnet.sendCommand("userCfgd -reset");
		System.out.println("请手动重启");
		//telnet.sendCommand("reboot");
		telnet.distinct();
	}


	public static void begin(String[] args) {
		
		System.out.println("Beigin");
		new InteTest().m_Inte_Test();
    	//TelnetOperator.m_telnet();
        //B70_1 m_read = new B70_1();
       // m_read.m_Read();
		
    }




	
}
