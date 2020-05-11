/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.extras;

/**
 *
 * @author ABDULRAHMAN ILLO
 */

import java.io.UnsupportedEncodingException;
import java.util.Date;
import javafx.scene.control.Alert;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

	/**
	 * Utility method to send simple HTML email
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
         *              @param  from
             */
    public static void sendEmail(Session session, String toEmail, String subject, String body,String from){
    try
        {
          MimeMessage msg = new MimeMessage(session);
          //set message headers
          msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
          msg.addHeader("format", "flowed");
          msg.addHeader("Content-Transfer-Encoding", "8bit");

          msg.setFrom(new InternetAddress(from, "Test"));

          msg.setReplyTo(InternetAddress.parse(from, false));

          msg.setSubject(subject, "UTF-8");

          msg.setText(body, "UTF-8");

          msg.setSentDate(new Date());
          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
          System.out.println("Message is ready");
            Transport.send(msg);  

          System.out.println("EMail Sent Successfully!!");
        }
        catch (UnsupportedEncodingException | MessagingException e) {
          e.printStackTrace();
        }
    }
}
