package mainBeg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextArea;

//import org.apache.commons.io.IOUtils;


public class USBRate {
	
	private static General opti = new General();
	static String [] usbApath=new String[2];
	//private FtpTest ftp = new FtpTest("a", "a", "192.168.1.251");
	public static void getUSBPath(String fileName) {
		FileReader reader;
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin");
		//telnet.sendCommand("ls /media/data/ | grep usb > /tmp/usbpath");
		
		try {
			reader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(reader);
			String s1 = new String();
			int i=0;
			
			try {
				
				while((s1 = br.readLine()) != null) {
					 System.out.println(s1);
					 usbApath[i]=s1;
					 i++;
				}
				br.close();
				reader.close();
				System.out.println(usbApath.length);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			
			e1.printStackTrace();
		}
	}
	public static String getUSB3()
	{
		String res = new String ();
		String usbInfo = new String ();
		
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin");
		
		usbInfo = opti.optiStr(telnet.sendCommand("cat /proc/bus/usb/devices"));
		//Spd=?? 
		
		return res;
	}
	public static String getFile()
	{
		String down = new String();
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin");
		boolean get = false;
		String mac = opti.optiStr(telnet.sendCommand("cat /var/mac"));
		System.out.println("mac = "+mac);
		telnet.sendCommand("ftpd_start.sh a a");
		String ftpfile = new String();
		String usbd = new String();
		ftpfile = "/storage/lvm/my_data/"+mac;
		telnet.sendCommand("ls /media/data/ | grep usb > /tmp/usbpath");
		usbd = "/tmp/usbpath";
		
		File fn = new File(ftpfile);
		File usbp = new File(usbd);
		
		String localPath = new String();
		mac = mac.replaceAll(":", "");
		localPath = "E:\\"+mac;
		System.out.println("get file from server.... ");
		FtpTest ftp = new FtpTest("a", "a", "192.168.1.251");
		ftp.getFTPClient();
		get = ftp.getFile(fn, localPath);
		if (get)
		{
			System.out.println("Down file ");
			down = "下载文件成功，文件为  "+ localPath + "\n";
			//return down;
		}
		else 
		{
			down = "下载文件错误，请检查网络\n";
			//return down;
		}
		get = ftp.getFile(usbp, "E:\\usbdir");
		
		return down;
		
	}
	
	
	public static void rateTest(JTextArea rtxt)
	{
		String rate = new String();
		int count = 0;
		TelnetOperator telnet = new TelnetOperator("VT220","#");//Windows,用VT220,否则会乱码  
		telnet.login("192.168.1.251", 23, "admin", "admin");
		String time1 = new String();
		USBRate.getFile();
		
		String time2 = new String();
		String dst_file=new String();
		String ddw = new String();
		String ddr = new String();
		String logFile= new String();
		
		long filesize=0;
		long wrate=0,rrate=0;
		count =1024;
		int i=0;
		
		filesize = 1048576 * count;
		
		logFile="/tmp/test_rate_log";
		//ls /media/data/ | grep usb
		//System.out.println("Time = "+time_1);
		USBRate.getUSBPath("E:\\usbdir");
		System.out.println(usbApath.length);
		System.out.println(usbApath[0]);
		System.out.println(usbApath[1]);
		//usbDir = opti.optiStr(usbDir);
		//rtxt.paintImmediately(rtxt.getBounds());
		rtxt.append("开始测试U盘读写速率，请耐心等候。。。。\n");
		rtxt.paintImmediately(rtxt.getBounds());
		for (i=0;i<usbApath.length;i++)
		{
			//dst_file = "/media/data/"+usbDir+"/test_usb_rate";
			if (usbApath[i].equals(""))
			{
				rate = "未发现U盘，请插入U盘后再测试\n";
				rtxt.setText("未发现U盘，请插入U盘后再测试\n");
				return ;
				//return rate;
			}
			time1 =opti.optiStr(telnet.sendCommand("date +%s"));
			long time_1=new Long(time1);
			System.out.println(dst_file);
			//time2 = telnet.sendCommand("date +%s");
			dst_file = "/media/data/"+usbApath[i]+"/test_usb_rate";
			telnet.sendCommand("rm -rf " + dst_file);
			//dd if=/dev/zero of=${dst_file} bs=1048576 count=${var_rw_count} 2>${log_file}
			ddw = "dd if=/dev/zero of="+ dst_file +" bs=1048576 count="+count+" 2>"+logFile;
			System.out.println(ddw);
			telnet.sendCommand(ddw);
			time2 =opti.optiStr(telnet.sendCommand("date +%s"));
			long time_2=new Long(time2);
			
			long time3 = time_2-time_1;
			if ( 0 != time3)
				wrate = filesize/time3;
			System.out.println("time1 = "+time1+" time2 = "+time2+" rate= "+wrate);
			wrate = wrate/1024;
			
			System.out.println("Write Rate = "+wrate +"KB/s\n");
			
			rtxt.append("U盘("+i+")写速率为 =" +wrate +"KB/s\n");
			rtxt.paintImmediately(rtxt.getBounds());
			time1 =opti.optiStr(telnet.sendCommand("date +%s"));
			time_1=new Long(time1);
			
			ddr = "dd if="+ dst_file +" of=/dev/zero bs=1048576 count="+count+" 2>"+logFile;
			telnet.sendCommand(ddr);
			System.out.println(ddr);
			
			time2 =opti.optiStr(telnet.sendCommand("date +%s"));
			time_2=new Long(time2);
			
			time3 = time_2-time_1;
			if ( 0 != time3)
				rrate = filesize/time3;
			System.out.println("time1 = "+time1+" time2 = "+time2+" rate= "+wrate);
			rrate = rrate/1024;
			System.out.println("Read Rate = "+rrate +"KB/s");
			rtxt.paintImmediately(rtxt.getBounds());
			rtxt.append("U盘("+i+")读速率为 =" +rrate +"KB/s\n");
			rtxt.paintImmediately(rtxt.getBounds());
		}
		
		//return rate;
		return;
	}
	
	
	public static void begin() {
		
		System.out.println("Begin to test USB");
		//USBRate.getFile();
		//USBRate.rateTest();
		
    }
}
