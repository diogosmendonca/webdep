package br.cefetrj.webdep.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * Classe responsável pelos serviços de envio de e-mail.
 * 
 * @author diogo
 * @since 0.1
 */
public class EMailServices {
	
	/**
	 * Classe para envio de e-mail via provedor GMail. 
	 * 
	 * Para funcionar corretamente deve ser configurado o uso da conta do gmail
	 * com aplicativos menos seguros. Configuração em https://www.google.com/settings/security/lesssecureapps.
	 * 
	 * @param from remetente, deve ser uma conta do gmail.
	 * @param password senha
	 * @param to lista de destinatários
	 * @param subject título do e-mail
	 * @param body corpo do e-mail.
	 * @return se o envio ocorreu sem problemas
	 * 
	 * @author diogo
	 * @since 0.1
	 * 
	 * @see Modificado de http://stackoverflow.com/questions/46663/how-can-i-send-an-email-by-java-application-using-gmail-yahoo-or-hotmail?rq=1
	 */
	public static boolean sendFromGMail(String from, String password, String[] to, String subject, String body) {
        boolean status = false;
        
        if (from == null)
			throw new IllegalArgumentException("from parameter cannot be null");
        
        if (!from.endsWith("@gmail.com"))
			throw new IllegalArgumentException("from parameter is not a gmail account");
        
        if (password == null)
			throw new IllegalArgumentException("password parameter cannot be null");
        
        if (to == null)
			throw new IllegalArgumentException("to parameter cannot be null");
        
        if (subject == null)
			throw new IllegalArgumentException("subject parameter cannot be null");
        
        if (body == null)
			throw new IllegalArgumentException("body parameter cannot be null");
		
		Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            status = true;
        }
        catch (AddressException ae) {
        	Logger lg = Logger.getLogger(EMailServices.class);
        	lg.error("Address problem while sending email", ae);
        }
        catch (MessagingException me) {
        	Logger lg = Logger.getLogger(EMailServices.class);
        	lg.error("Message problem while sending email", me);
        }
        
        return status;
    }

}
