package mainBeg;

import java.awt.BorderLayout;
import java.awt.Desktop.Action;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextArea;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TestJToolBar  
{  
    JFrame jf = new JFrame("���Թ�����");  
    JTextArea jta = new JTextArea(6, 35);  
    JToolBar jtb = new JToolBar();  
    JMenuBar jmb = new JMenuBar();  
    JMenu edit = new JMenu("�༭");  
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();  
    //����"ճ��"Action����Action���ڴ����˵�����߰�ť����ͨ��ť  
    AbstractAction pasteAction = new AbstractAction("ճ��", new ImageIcon("ico/paste.png"))  
    {  
        public void actionPerformed(ActionEvent e)  
        {  
            //����������а���stringFlavor����  
            if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor))  
            {  
                try 
                {  
                    //ȡ����������stringFlavor����  
                    String content = (String)clipboard.getData(DataFlavor.stringFlavor);  
                    //��ѡ�������滻�ɼ������е�����  
                    jta.replaceRange(content , jta.getSelectionStart() , jta.getSelectionEnd());  
                }  
                catch (Exception ee)  
                {  
                    ee.printStackTrace();  
                }  
            }  
        }  
    };  
    //����"����"Action  
    AbstractAction copyAction = new AbstractAction("����", new ImageIcon("ico/copy.png"))  
    {  
        public void actionPerformed(ActionEvent e)  
        {  
            StringSelection contents = new StringSelection(jta.getSelectedText());  
            //��StringSelection������������  
            clipboard.setContents(contents, null);  
            //����������а���stringFlavor����  
            if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor))  
            {  
                //��pasteAction����  
                pasteAction.setEnabled(true);  
            }  
        }  
    };  
    public void init()  
    {  
        //pasteActionĬ�ϴ��ڲ�����״̬  
        pasteAction.setEnabled(false);  
        jf.add(new JScrollPane(jta));  
        //��Action������ť�������ð�ť��ӵ�Panel��  
        JButton copyBn = new JButton(copyAction);  
        JButton pasteBn = new JButton(pasteAction);  
        JPanel jp = new JPanel();  
        jp.add(copyBn);  
        jp.add(pasteBn);  
        jf.add(jp , BorderLayout.SOUTH);  
        //�򹤾��������Action���󣬸ö��󽫻�ת���ɹ��߰�ť  
        jtb.add(copyAction);  
        jtb.addSeparator();  
        jtb.add(pasteAction);  
        //��˵������Action���󣬸ö��󽫻�ת���ɲ˵���  
        edit.add(copyAction);  
        edit.add(pasteAction);  
        //��edit�˵���ӵ��˵�����  
        jmb.add(edit);  
        jf.setJMenuBar(jmb);  
        //���ù������͹��߰�ť֮��ľ���  
       // jtb.setMargin(new Insets(20 ,10 , 5 , 30));  
        //�򴰿�����ӹ�����  
        jf.add(jtb , BorderLayout.NORTH);  
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        jf.pack();  
        jf.setVisible(true);  
    }  
    public static void begin(String[] args)   
    {  
        new TestJToolBar().init();  
    }  
}