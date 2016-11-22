import java.util.*;
import java.net.*;
import java.io.*;

public class WriteNet {

	public static void main(String[] args) {
	//1.変数、データ準備
		byte[] buff = new byte[1024];
		Socket sock = null;
		InputStream inStr = null;
		OutputStream outStr = null;
		boolean flag = true;

	//2.鯖に接続する
		try{
			String hostName = args[0];
			int portNum = Integer.parseInt(args[1]);
			sock = new Socket(hostName,portNum); 
			inStr = sock.getInputStream();
			outStr = sock.getOutputStream();
		}catch (Exception e) {
			System.out.println("エラー");
			e.printStackTrace();
			System.exit(1);
		}

	//3.要求を送信する
		while (flag) {
			try{
				int n = System.in.read(buff);
				if(buff[0]=='*'){
					flag = false;
				}else{
					outStr.write(buff,0,n);
				}
			}catch (Exception e) {
				System.exit(1);
			}
		}
		flag = true;

	//4.返信を受け取り、出力
		while (flag) {
			try{
				int n = inStr.read(buff);
				System.out.write(buff,0,n);
			}catch (Exception e) {
				flag = false;
			}
		}

	//5.鯖への接続を切る
		try{
			inStr.close();
		}catch (Exception e) {
			System.out.println("えらー");
			System.exit(1);
		}
		
	}

}
