package mainBeg;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NewGui {

	JFrame frame = new JFrame("B70����С����");
	JButton exit = new JButton("�˳�"); 
	
	JButton irtest=new JButton("�������");
	JButton usbRate=new JButton("USB����");
	JButton inteTest=new JButton("���ɲ���");
	JButton wifiRSSI=new JButton("����RSSI");
	JButton modelTest=new JButton("ģ�����");
	JButton downlog=new JButton("���������ļ�");
	
	
	JButton hxdCode=new JButton("���˴﷢��");
	JButton irCode=new JButton("��������");
	JButton delIR=new JButton("ɾ���豸");
	JButton begIR=new JButton("����ѧϰ��ʼ");
	JButton getIR=new JButton("����ѧϰ���");
	
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
	    	
	        //System.out.println("����û��");    
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
	        //System.out.println("����û��");    
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
	        //System.out.println("����û��");    
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
	        //System.out.println("����û��");    
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
	        //System.out.println("����û��");    
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
	
	//JButton usbRate=new JButton("USB����");
	public static void main(String[] args)   
    {  
        //����Swing����ʹ��Java���  
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
		//south.add(new JButton ("�ڿͼ���"));
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
		       // south.add(new JButton ("�ڿͼ���"));
		
		       //south.add(new JButton ("ץ������"));
		Box topLeft = Box.createVerticalBox();  
        //ʹ��JScrollPane��Ϊ��ͨ�����JViewPort  
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
