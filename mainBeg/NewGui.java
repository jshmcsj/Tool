package mainBeg;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NewGui {

	JFrame frame = new JFrame("B70测试小工具");
	JButton exit = new JButton("退出"); 
	
	JButton irtest=new JButton("红外测试");
	JButton usbRate=new JButton("USB速率");
	JButton inteTest=new JButton("集成测试");
	JButton wifiRSSI=new JButton("无线RSSI");
	JButton modelTest=new JButton("模块测试");
	JButton downlog=new JButton("导出测试文件");
	
	
	JButton hxdCode=new JButton("宏兴达发码");
	JButton irCode=new JButton("主机发码");
	JButton delIR=new JButton("删除设备");
	JButton begIR=new JButton("红外学习开始");
	JButton getIR=new JButton("红外学习获得");
	
	public JTextArea resta = new JTextArea(10, 30); 
	
	class Intetest implements ActionListener
	{
		
	    public void actionPerformed(ActionEvent e)
	    {
	    	//USBRate usbrate = new USBRate();
	    	resta.paintImmediately(resta.getBounds());
	    	
	    	resta.setText("");
	    	InteTest inte = new InteTest();
	    	inte.getVer(resta);
	    	//inte.m_Inte_Test();
	    	inte.checkChip(resta);
	    	
	        //System.out.println("用了没？");    
	    }
	}
	class USBrate implements ActionListener
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    	USBRate usbrate = new USBRate();
	    	//InteTest inte = new InteTest();
	    	//usbrate.getFile();
	    	resta.paintImmediately(resta.getBounds());
	    	resta.setText("");
	    	usbrate.rateTest(resta);
	        //System.out.println("用了没？");    
	    }
	}
	
	class WifiRSSI implements ActionListener
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    	//String wifires= new String();
	    	WifiTh wifirssi = new WifiTh();
	    	//InteTest inte = new InteTest();
	    	resta.paintImmediately(resta.getBounds());
	    	resta.setText("");
	    	 wifirssi.m_wifi_RSSI(resta);
	    	//resta.setText(wifires);
	        //System.out.println("用了没？");    
	    }
	}
	
	class ModelTest implements ActionListener
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    	ModTest modtest = new ModTest();
	    	//InteTest inte = new InteTest();
	    	//modtest.begin();
	    	String zigres = new String();
	    	String rfres = new String();
	    	String secres = new String();
	    	resta.paintImmediately(resta.getBounds());
	    	resta.setText("");
	    	modtest.secido(resta);
	    	modtest.zigbee(resta);
	    	//System.out.println(zigres);
	    	//resta.setText(zigres);
	    	modtest.rf433(resta);
	        //System.out.println("用了没？");    
	    }
	}
	
	class IRtest implements ActionListener
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    	IRTest irtest = new IRTest();
	    	resta.paintImmediately(resta.getBounds());
	    	resta.setText("");
	    	//InteTest inte = new InteTest();
	    	IRTest.putfile(resta);
	    	IRTest.irtest(resta);
	        //System.out.println("用了没？");    
	    }
	}
	
	class ExitFun implements ActionListener
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    	System.exit(0); 
	    }
	}
	
	class DownLog implements ActionListener
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    	resta.paintImmediately(resta.getBounds());
	    	resta.setText("");
	    	String down = new String();
	    	down = USBRate.getFile();
	    	resta.paintImmediately(resta.getBounds());
	    	resta.setText(down);
	    }
	}
	class delir implements ActionListener
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    	//smhmManagerd -equip_del 1
	    	IRTest.delir(resta);
	    }
	}
	class ircode implements ActionListener
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    	IRTest.ircode(resta);
	    }
	}
	class cgetIR implements ActionListener
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    	IRTest.getir(resta);
	    }
	}
	class cbegIR implements ActionListener
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    	System.out.println("aaa");
	    	IRTest.begir(resta);
	    }
	}
	
	class hxdcode implements ActionListener
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    	IRTest.hxdcode(resta);
	    }
	}
	
	//JButton usbRate=new JButton("USB速率");
	public static void main(String[] args)   
    {  
        //设置Swing窗口使用Java风格  
		System.out.println("=====All the begin====");
        JFrame.setDefaultLookAndFeelDecorated(true); 
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        new NewGui().init();  
    }
	
	
	private void init() {
		// TODO Auto-generated method stub
		JPanel bottom = new JPanel(); 
		//JPanel top1 = new JPanel(); 
		frame.add(bottom , BorderLayout.SOUTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		bottom.add(exit);
		//bottom.add(irtest,BorderLayout.EAST);
		
		JPanel south = new JPanel(new GridLayout(3,2,50,4));
		JPanel irTest = new JPanel(new GridLayout(6,5,50,4));
		//south.add(new JButton ("黑客技术"));
		south.add(usbRate);
		south.add(inteTest);
		south.add(wifiRSSI);
		south.add(modelTest);
		
		south.add(downlog);
		
		irTest.add(irtest);
		irTest.add(hxdCode);
		irTest.add(irCode);
		irTest.add(delIR);
		irTest.add(begIR);
		irTest.add(getIR);
	
		usbRate.addActionListener(new USBrate());
		inteTest.addActionListener(new Intetest());
		modelTest.addActionListener(new ModelTest());
		wifiRSSI.addActionListener(new WifiRSSI());
		irtest.addActionListener(new IRtest());
		downlog.addActionListener(new DownLog());
		exit.addActionListener(new ExitFun());
		
		delIR.addActionListener(new delir());
		irCode.addActionListener(new ircode());
		hxdCode.addActionListener(new hxdcode());
		getIR.addActionListener(new cgetIR());
		begIR.addActionListener(new cbegIR());
		       // south.add(new JButton ("黑客技术"));
		
		       //south.add(new JButton ("抓鸡高手"));
		Box topLeft = Box.createVerticalBox();  
        //使用JScrollPane作为普通组件的JViewPort  
        JScrollPane taJsp = new JScrollPane(resta);  
        topLeft.add(taJsp);  
        
        
        Box top = Box.createHorizontalBox();  
        top.add(topLeft);
        
        resta.setText("");
        //resta.setText("aaabbbb");
       // resta.
        frame.add(top);
		frame.add(south,BorderLayout.NORTH);
		frame.add(irTest,BorderLayout.WEST);
		//frame.add(irtest);
		//frame.add(usbRate);
		frame.setSize(600, 500);
		frame.setVisible(true);
		
	}  
}
