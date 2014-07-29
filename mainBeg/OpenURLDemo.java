package mainBeg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *类名：OpenURLDemo
 *功能：Java Swing点击按钮启动浏览器
 *作者：百度知道小女辍学卖豆芽 + Wentasy 
 *开发日期：2012/06/10
 *修复日期：暂无
 *原因：暂无
 */
public class OpenURLDemo {
	public OpenURLDemo() {
		JFrame frame = new JFrame();//新建JFrame对象
		JButton btn = new JButton();//新建按钮
		//对按钮进行监听
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Thread() {
					//重写run方法
					public void run() {
						//构造命令
						String cmd = "cmd.exe /c start ";
						
						//构造本地文件路径或者网页URL
						//String file = "http://www.baidu.com";
						String file = "C:/Users/Wentasy/Desktop/core_java_3_api/index.html";
						
						try {
							//执行操作
							Runtime.getRuntime().exec(cmd + file);
						} catch (IOException ignore) {
							//打印异常
							ignore.printStackTrace();
						}
					}
				}.start();//启动线程
			}
		});
		//添加按钮
		frame.getContentPane().add(btn);
		//设置为顶层
		frame.setAlwaysOnTop(true);
		//设置关闭操作
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//设置大小
		frame.setSize(100, 100);
		//设置可见性
		frame.setVisible(true);
	}
	
	/**
	 *方法名：main
	 *功能：程序入口,用于测试
	 */
	//public static void main(String[] args) {
		//new OpenURLDemo();
	//}
}
