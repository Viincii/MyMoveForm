package application.modele;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnvoiMails {
	private String objet;
	private String corpsMail;
	
	public EnvoiMails() {
		this.objet = "Votre inscription";
		this.corpsMail = "Ce mail est toujours envoyé depuis mon programme et il semblerait que tout fonctionne <b>normalement</b> :P";
	}
	
	public void envoiMail(String mdp,String destinataire, String cheminPdf) {
		this.envoiMail("contact@mymovestudio.com", mdp, destinataire,cheminPdf);
	}
	
	public void envoiMail(String mail, String mdp,String destinataire, String cheminPdf) {
		
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.host", "ssl0.ovh.net");
	    prop.setProperty("mail.smtp.user", mail);
	    prop.put("mail.smtp.port", 465);
	    prop.put("mail.smtp.auth", "true");
	    prop.setProperty("mail.from", mail);
	    prop.setProperty("mail.smtp.ssl.enable", "true");
	    Session session = Session.getInstance(prop);
	    
	    File file = new File(cheminPdf);
	    FileDataSource datasource1 = new FileDataSource(file);
	    DataHandler handler1 = new DataHandler(datasource1);
	    MimeBodyPart pdf = new MimeBodyPart();
	    MimeMessage message = new MimeMessage(session);
	    MimeBodyPart content = new MimeBodyPart();
	    MimeMultipart mimeMultipart = new MimeMultipart();
	    Transport transport = null;
	    try {
	    	pdf.setDataHandler(handler1);
	    	pdf.setFileName(datasource1.getName());
	    	
	    	content.setContent(corpsMail, "text/html");
	    	
	    	mimeMultipart.addBodyPart(content);
	    	mimeMultipart.addBodyPart(pdf);
	    	
	        message.setContent(mimeMultipart);
	        message.setSubject(objet);
	        message.setFrom(mail);
	        message.addRecipients(Message.RecipientType.TO, destinataire);
	        message.addRecipients(Message.RecipientType.CC, "inscriptions@mymovestudio.com");
	        message.addRecipients(Message.RecipientType.BCC, "vinc.mignot@hotmail.fr");
	        
	    
	        transport = session.getTransport("smtp");
	        transport.connect(mail, mdp);
	        transport.sendMessage(message, new Address[] { new InternetAddress(destinataire),new InternetAddress("inscriptions@mymovestudio.com"),new InternetAddress("vinc.mignot@hotmail.fr")});
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (transport != null) {
	                transport.close();
	            }
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void setObjet(String objet) {
		this.objet = objet;
	}
	
	public void setCorpsMail(String corpsMail) {
		this.corpsMail = corpsMail;
	}
}
