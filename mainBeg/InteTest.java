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
		
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,��VT220,���������  
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
		System.out.println("rf433�汾�ţ�"+rf433);
		//gettx = res.getText();
		res.append("rf433�汾�ţ�"+rf433+"\n");
		
		String zigbeever =opti.optiStr( telnet.sendCommand("zigbee_new.sh getver"));
		//System.out.println("=====================");
		//readUntil("#");
		System.out.println("zigbee �汾�ţ�"+zigbeever);
		//gettx = res.getText();
		res.paintImmediately(res.getBounds());
		res.append("zigbee �汾�ţ�"+zigbeever+"\n");
		
		
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
		
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,��VT220,���������  
		telnet.login("192.168.1.251", 23, "admin", "admin"); 
		
		String lan = opti.optiStr(telnet.sendCommand("ifconfig | grep eth0"));
		//System.out.println(lan);
		if(!lan.equals(""))		
		{
			//System.out.println(disk);
			System.out.println("lan����");
			res.paintImmediately(res.getBounds());
			res.append("lan����\n");
		}
		else 
		{
			res.paintImmediately(res.getBounds());
			System.out.println("lan�쳣");
		}
		
		String wan = opti.optiStr(telnet.sendCommand("ifconfig | grep eth1"));
		//System.out.println(wan);
		if(!wan.equals(""))		
		{
			//System.out.println(disk);
			System.out.println("wan����");
			res.paintImmediately(res.getBounds());
			res.append("wan����\n");
		}
		else 
		{
			System.out.println("wan�쳣");
			res.paintImmediately(res.getBounds());
			res.append("wan�쳣\n");
		}
		
		String disk = opti.optiStr(telnet.sendCommand("hdd_sata.sh get_device | grep sd"));
		if(!disk.equals(""))		
		{
			//System.out.println(disk);
			System.out.println("Ӳ�̴���");
			res.paintImmediately(res.getBounds());
			res.append("Ӳ�̴���\n");
		}
		else 
		{
			res.paintImmediately(res.getBounds());
			System.out.println("δ����Ӳ��");
		}
		
		
		String wifi = opti.optiStr(telnet.sendCommand("iwconfig 2>/dev/null | grep ath0"));
		if(!wifi.equals(""))
		{
			//System.out.println(wifi);
			System.out.println("������������");
			res.paintImmediately(res.getBounds());
			res.append("������������\n");
		}
		else 
		{
			System.out.println("δ������������");
			res.paintImmediately(res.getBounds());
		}
			
		
		String mem = opti.optiStr(telnet.sendCommand("free | grep Mem | awk '{printf $2}'"));
		System.out.println("�ڴ�����Ϊ��" +mem);
		res.paintImmediately(res.getBounds());
		res.append("�ڴ�����Ϊ��" +mem+"\n");
		
		String usb =opti.optiStr( telnet.sendCommand("cat /proc/bus/usb/devices | grep eth | awk -F \"Prot=00 \" '{printf $2}'"));
		//System.out.println(usb);
		//iwconfig 2>/dev/null | grep ath0
		//free | grep Mem | awk '{printf $2}
		//hdd_sata.sh get_device | grep sd
		//userCfgd -reset
		
		if(!usb.equals(""))
		{
			//System.out.println(wifi);
			System.out.println("USB����");
			res.paintImmediately(res.getBounds());
			res.append("USB����\n");
		}
		else 
		{
			System.out.println("δ����USBоƬ");
		}
		telnet.sendCommand("smhmManagerd -reset ");
		telnet.sendCommand("rm -f /media/data/hdd/* ");
		System.out.println("=====================");
		
		res.append("==================================\n");
		res.append("���ֶ�����\n");
		//System.out.println("׼����λ������");
		telnet.sendCommand("userCfgd -reset");
		System.out.println("���ֶ�����");
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
