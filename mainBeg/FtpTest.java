package mainBeg;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;


public class FtpTest {
	
	public FtpTest(String userName,String password,String ip){
		this.userName = userName;
		this.password = password;
		this.ip = ip;
	}
	
	/**
	 * �ϴ��ļ�
	 * 
	 * @param File f Ҫ�ϴ����ļ�
	 * @param String uploadDir �ϴ��ļ���·��
	 * 
	 * @return boolean b �ϴ����
	 * */
	public boolean putFile(File f,String uploadDir) {
		InputStream instream = null;
		boolean result = false;
		try{
			try{
				ftpClient.changeWorkingDirectory(uploadDir);
				instream = new BufferedInputStream(new FileInputStream(f));
				result = ftpClient.storeFile(f.getName(), instream);
			}finally{
				if(instream!=null){
					instream.close();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * ��ftp�����������ļ�
	 * 
	 * @param File f Ҫ��ȡ��ftp�������ϵ��ļ�
	 * @param String localPath ���ش�ŵ�·��
	 * 
	 * @return boolean �ļ������Ƿ�ɹ�
	 * */
	public boolean getFile(File f  , String localPath){
		OutputStream outStream = null;
		boolean result = false;
		try{
			try{
				System.out.println("===f=====");
				outStream = new BufferedOutputStream(new FileOutputStream(new File(localPath)));
				
				String path = f.getPath();
				System.out.println("=path="+path);
				path = path.replaceAll("\\\\", "/");
				System.out.println("=path1="+path);
				String filepath = path.substring(0, path.lastIndexOf("/")+1)+"";
				String fileName = path.substring(path.lastIndexOf("/")+1)+"";
				System.out.println("==filepath==["+filepath+"] filename=["+fileName+"]");
				boolean b = ftpClient.changeWorkingDirectory(filepath);
				
				if(b){
					result = ftpClient.retrieveFile(fileName, outStream);
				}
			}finally{
				if(outStream != null){
					outStream.close();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * ��ȡftp����
	 * 
	 * @return ftpClient
	 * */
	public FTPClient getFTPClient(){
		
		try {
			ftpClient = new FTPClient();

			ftpClient.connect(ip);
			ftpClient.login(userName, password);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ftpClient;
	}
	
	/**
	 *  �ر�ftpClient����
	 *  
	 *  @param FTPClient Ҫ�رյ�ftpClient����
	 *  
	 * */
	public void closeFTPClient(FTPClient ftpClient){
		try {
			try{
				ftpClient.logout();
			}finally{
				ftpClient.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*ftp�û���*/
	private String userName;
	/*ftp����*/
	private String password;
	/*ftp������ip*/
	private String ip;
	private FTPClient ftpClient;
	
	
	
	
}