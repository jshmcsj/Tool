package mainBeg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *������OpenURLDemo
 *���ܣ�Java Swing�����ť���������
 *���ߣ��ٶ�֪��СŮ�ѧ����ѿ + Wentasy 
 *�������ڣ�2012/06/10
 *�޸����ڣ�����
 *ԭ������
 */
public class OpenURLDemo {
	public OpenURLDemo() {
		JFrame frame = new JFrame();//�½�JFrame����
		JButton btn = new JButton();//�½���ť
		//�԰�ť���м���
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Thread() {
					//��дrun����
					public void run() {
						//��������
						String cmd = "cmd.exe /c start ";
						
						//���챾���ļ�·��������ҳURL
						//String file = "http://www.baidu.com";
						String file = "C:/Users/Wentasy/Desktop/core_java_3_api/index.html";
						
						try {
							//ִ�в���
							Runtime.getRuntime().exec(cmd + file);
						} catch (IOException ignore) {
							//��ӡ�쳣
							ignore.printStackTrace();
						}
					}
				}.start();//�����߳�
			}
		});
		//��Ӱ�ť
		frame.getContentPane().add(btn);
		//����Ϊ����
		frame.setAlwaysOnTop(true);
		//���ùرղ���
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//���ô�С
		frame.setSize(100, 100);
		//���ÿɼ���
		frame.setVisible(true);
	}
	
	/**
	 *��������main
	 *���ܣ��������,���ڲ���
	 */
	//public static void main(String[] args) {
		//new OpenURLDemo();
	//}
}
