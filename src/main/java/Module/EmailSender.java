package Module;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.DBDAO;

public class EmailSender {

    
	public static void sendEmail(String email, int code) {
		String message= "Cemessage est généré automatiquement ni répondez pas. Votre code de vérification est: "+code;
		String subject= "Code de Vérification";
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp-mail.outlook.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
	 
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("joseph.ouassa@groupe-esigelec.org", "Loicsteeeeeeeeeeve1&9");
			}
		});
	 
		try {
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress("joseph.ouassa@groupe-esigelec.org"));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);
	 
			Transport.send(mimeMessage);
	 
			System.out.println("E-mail envoyé avec succès !");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        
    }

}
