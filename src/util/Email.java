package util;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import com.sun.mail.util.MailSSLSocketFactory;

 
public class Email{
	
   public static void sendEmail (String to , String subject , String content) throws GeneralSecurityException{   
	   // 收件人电子邮箱
	      //String to = "651488039@qq.com";
	 
	      // 发件人电子邮箱
	      String from = "3192238069@qq.com";
	  
	   // 指定发送邮件的主机为 smtp.qq.com
	        String host = "smtp.qq.com";  //QQ 邮件服务器

	        // 获取系统属性
	        Properties properties = System.getProperties();

	        // 设置邮件服务器
	        properties.setProperty("mail.smtp.host", host);

	        properties.put("mail.smtp.auth", "true");
	        MailSSLSocketFactory sf = new MailSSLSocketFactory();
	        sf.setTrustAllHosts(true);
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.ssl.socketFactory", sf);
	        // 获取默认session对象
	        Session session = Session.getDefaultInstance(properties,new Authenticator(){
	            public PasswordAuthentication getPasswordAuthentication()
	            {
	                return new PasswordAuthentication("3192238069@qq.com", "ylixfaoisggqdhcg"); //发件人邮件用户名、密码
	            }
	        });

	        try{
	            // 创建默认的 MimeMessage 对象
	            MimeMessage message = new MimeMessage(session);

	            // Set From: 头部头字段
	            
	            message.setFrom(new InternetAddress(from));

	            // Set To: 头部头字段
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	            // Set Subject: 头部头字段
	            message.setSubject(subject);
	            //message.setSubject("This is the Subject Line!");

	            // 设置消息体
	            //message.setText("This is actual message");
	            message.setText(content);
	            
	            // 发送消息
	            Transport.send(message);
	            System.out.println("Sent message successfully....from runoob.com");
	        }catch (MessagingException mex) {
	            mex.printStackTrace();
	        }
   }
   public static void main(String[] args) throws GeneralSecurityException {
	   //Email e = new Email();
	   String subject = "曹操的密码";
	   String content = "曹尼玛";
	   Email.sendEmail("651488039@qq.com", subject, content);
   }
}