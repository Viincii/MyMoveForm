package application.modele;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import application.Exceptions.PasFich;

public class EnvoiMails {
	private String mail;
	private String objet;
	private String corpsMail;
	
	public EnvoiMails() {
		this.objet = "Votre inscription";
		this.corpsMail = "Ce mail est toujours envoyé depuis mon programme et il semblerait que tout fonctionne <b>normalement</b> :P";
		this.mail = "contact@mymovestudio.com";
	}
	
	public void envoiMail(String mail, String mdp,String destinataire, String cheminPdf,String objet, String corps) throws Exception {
		File file = new File(cheminPdf);
	    if(!file.canWrite())
	    	throw new PasFich();
	    if(mail.isEmpty())
			mail=this.mail;
		if(objet.isEmpty())
			objet=this.objet;
		if(corps.isEmpty())
			corps=this.corpsMail;
				   
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.host", "ssl0.ovh.net");
	    prop.setProperty("mail.smtp.user", mail);
	    prop.put("mail.smtp.port", 465);
	    prop.put("mail.smtp.auth", "true");
	    prop.setProperty("mail.from", mail);
	    prop.setProperty("mail.smtp.ssl.enable", "true");
	    Session session = Session.getInstance(prop);
	    
	    
	    FileDataSource datasource1 = new FileDataSource(file);
	    DataHandler handler1 = new DataHandler(datasource1);
	    MimeBodyPart pdf = new MimeBodyPart();
	    MimeMessage message = new MimeMessage(session);
	    MimeBodyPart content = new MimeBodyPart();
	    MimeMultipart mimeMultipart = new MimeMultipart();
	    Transport transport = null;
	    
	    pdf.setDataHandler(handler1);
	    pdf.setFileName(datasource1.getName());
	    	
	    content.setContent(corps, "text/html");
	    	
	    mimeMultipart.addBodyPart(content);
	    mimeMultipart.addBodyPart(pdf);
	    	
	    message.setContent(mimeMultipart);
	    message.setSubject(objet);
	    message.setFrom(mail);
	    message.addRecipients(Message.RecipientType.TO, destinataire);
	    message.addRecipients(Message.RecipientType.CC, "inscriptions@mymovestudio.com");
	    
	    transport = session.getTransport("smtp");
	    transport.connect(mail, mdp);
	    transport.sendMessage(message, new Address[] { new InternetAddress(destinataire),new InternetAddress("inscriptions@mymovestudio.com")});
	    
	       
	    if (transport != null) {
	    	transport.close();
	    }
	}
	
	public void setObjet(String objet) {
		this.objet = objet;
	}
	
	public void setCorpsMail(String corpsMail) {
		this.corpsMail = corpsMail;
	}
}
