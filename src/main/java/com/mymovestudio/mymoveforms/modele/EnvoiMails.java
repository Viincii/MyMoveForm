package com.mymovestudio.mymoveforms.modele;

import com.mymovestudio.mymoveforms.Exceptions.PasFich;

import java.io.File;
import java.util.ArrayList;
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
	private String mail;
	private String objet;
	private String corpsMail;
	private String corpsOnline;
	
	public EnvoiMails() {
		this.objet = " Votre inscription 2021-2022 - Association My Move Studio";
		this.corpsMail = "<p>\n"
				+ "<p>Cher(e) adh&eacute;rent(e),</p>\n"
				+ "\n"
				+ "<p><strong><span style=\"font-family: &quot;Calibri&quot;,sans-serif;\">Veuillez trouver en\n"
				+ "\n"
				+ "pi&egrave;ce jointe votre bulletin d&#39;inscription sur lequel il faudra coller une\n"
				+ "\n"
				+ "photo d&#39;identit&eacute;, v&eacute;rifier les informations et enfin le dater et le signer. Ce\n"
				+ "\n"
				+ "document accompagn&eacute; du paiement sera &agrave; d&eacute;poser <span style=\"color:red;\">SOUS 8 JOURS en main propre </span> &agrave; notre bureau au 2E rue Marceau\n"
				+ "\n"
				+ "&agrave; Houilles.</span></strong> Si vous avez coch&eacute; dans le formulaire &laquo; je fournis\n"
				+ "\n"
				+ "un nouveau certificat m&eacute;dical d&#39;aptitude &agrave; la pratique du sport &raquo;, il faudra\n"
				+ "\n"
				+ "nous fournir votre aptitude m&eacute;dicale avant votre deuxi&egrave;me cours.</p>\n"
				+ "<p>Plan pour acc&eacute;der &agrave; notre bureau :&nbsp;<a href=http://www.mymovestudio.com/index.php/component/content/article/13-dat/164 target=\"_blank\">http://www.mymovestudio.com/index.php/component/content/article/13-dat/164&nbsp;</a><br /> Les horaires de permanence sont disponibles sur la page d&#39;accueil de notre site\n"
				+ "\n"
				+ ": <a href=http://www.mymovestudio.com/>[http://www.mymovestudio.com%3c/a%3e&nbsp;%3c/p]www.mymovestudio.com</a>&nbsp;</p>\n"
				+ "<p> Les types de paiements accept&eacute;s sont consultable ici: <a href=http://mymovestudio.com/index.php/tarifs>http://mymovestudio.com/index.php/tarifs</a> </p>\n"
				+ "<p>COURS ENFANTS :</p>\n"
				+ "\n"
				+ "<ul type=\"disc\">\n"
				+ "\n"
				+ "<li class=\"MsoNormal\" style=\"mso-margin-top-alt: auto; mso-margin-bottom-alt: auto; mso-list: l0 level1 lfo1; tab-stops: list 36.0pt;\">A chaque cours enfant\n"
				+ "\n"
				+ "     correspond une tenue obligatoire. Vous la trouverez <a href=http://www.mymovestudio.com/FILES/tenues-2021-2022-MyMoveStudio.pdf target=\"_blank\">sur le lien suivant</a>.\n"
				+ "\n"
				+ "     Merci de l&#39;acheter pendant l&#39;&eacute;t&eacute; et d&#39;en &eacute;quiper votre enfant d&egrave;s le\n"
				+ "\n"
				+ "     premier cours.&nbsp;</li>\n"
				+ "\n"
				+ "<li class=\"MsoNormal\" style=\"mso-margin-top-alt: auto; mso-margin-bottom-alt: auto; mso-list: l0 level1 lfo1; tab-stops: list 36.0pt;\">Avant le d&eacute;p&ocirc;t du dossier\n"
				+ "\n"
				+ "     au bureau, il faudra t&eacute;l&eacute;charger et compl&eacute;ter l&#39;attestation\n"
				+ "\n"
				+ "     parentale&nbsp;<a href=http://www.mymovestudio.com/FILES/2021_2022_attestationpourlesmineurs.pdf target=\"_blank\">disponible\n"
				+ "\n"
				+ "     ICI</a></li>\n"
				+ "\n"
				+ "</ul>\n"
				+ "\n"
				+ "<p>FACTURE pour votre comit&eacute; d&#39;entreprise :&nbsp;Si vous avez besoin d&#39;une\n"
				+ "\n"
				+ "facture, merci de la t&eacute;l&eacute;charger, de la compl&eacute;ter et de la joindre &agrave; votre\n"
				+ "\n"
				+ "dossier afin que nous puissions la tamponner et signer : <a href=http://www.mymovestudio.com/FILES/facture-proformat-inscription-2021-2022.doc target=\"_blank\">http://www.mymovestudio.com/FILES/facture-proformat-inscription-2021-2022.doc</a>&nbsp;Nous\n"
				+ "\n"
				+ "vous les redonnerons d&eacute;but octobre en cours.</p>\n"
				+ "\n"
				+ "<p><strong><span style=\"font-family: &quot;Calibri&quot;,sans-serif;\">Pour que votre\n"
				+ "\n"
				+ "inscription soit valide, elle doit comporter les &eacute;l&eacute;ments stipul&eacute;s dans l&#39;article 6A du r&egrave;glement int&eacute;rieur pr&eacute;sent sur le page \"s&#39;inscrire\" de\n"
				+ "\n"
				+ "notre site.</span></strong></p>\n"
				+ "\n"
				+ "<p><strong><span style=\"font-family: &quot;Calibri&quot;,sans-serif;\">Veuillez garder une\n"
				+ "\n"
				+ "copie de tous les documents de votre dossier avant de les d&eacute;poser &agrave; l&#39;association.&nbsp;&nbsp;</span></strong></p>\n"
				+ "\n"
				+ "<p>Association My Move Studio<br /> Salle : 18 rue Marceau 78800 HOUILLES <br /> Bureau : 2E rue Marceau 78800 HOUILLES (ouvert selon permanences, voir site\n"
				+ "\n"
				+ "internet) <br /> <a href=http://www.mymovestudio.com/>www.mymovestudio.com </a><br /> <a href=mailto:contact@mymovestudio.com>contact@mymovestudio.com </a><br /> 07.68.07.72.07<br /></p>"
				+ "\n"
				+ "</p>";
		
		this.corpsOnline = "<p>\n"
				+ "<p>Cher(e) adh&eacute;rent(e),</p>\n"
				+ "\n"
				+ "<p><strong><span style=\"font-family: &quot;Calibri&quot;,sans-serif;\">Veuillez trouver en\n"
				+ "\n"
				+ "pi&egrave;ce jointe votre bulletin d&#39;inscription sur lequel il faudra coller une\n"
				+ "\n"
				+ "photo d&#39;identit&eacute;, v&eacute;rifier les informations et enfin le dater et le signer. Ce\n"
				+ "\n"
				+ "document accompagn&eacute; du paiement sera &agrave; d&eacute;poser <span style=\"color:red;\">SOUS 8 JOURS en main propre </span> &agrave; notre bureau au 2E rue Marceau\n"
				+ "\n"
				+ "&agrave; Houilles.</span></strong></p>\n"
				+ "<p>Plan pour acc&eacute;der &agrave; notre bureau :&nbsp;<a href=http://www.mymovestudio.com/index.php/component/content/article/13-dat/164 target=\"_blank\">http://www.mymovestudio.com/index.php/component/content/article/13-dat/164&nbsp;</a><br /> Les horaires de permanence sont disponibles sur la page d&#39;accueil de notre site\n"
				+ "\n"
				+ ": <a href=http://www.mymovestudio.com/>[http://www.mymovestudio.com%3c/a%3e&nbsp;%3c/p]www.mymovestudio.com</a>&nbsp;</p>\n"
				+ "<p> Les types de paiements accept&eacute;s sont consultable ici: <a href=http://mymovestudio.com/index.php/tarifs>http://mymovestudio.com/index.php/tarifs</a> </p>\n"
				+ "<p>FACTURE pour votre comit&eacute; d&#39;entreprise :&nbsp;Si vous avez besoin d&#39;une\n"
				+ "\n"
				+ "facture, merci de la t&eacute;l&eacute;charger, de la compl&eacute;ter et de la joindre &agrave; votre\n"
				+ "\n"
				+ "dossier afin que nous puissions la tamponner et signer : <a href=http://www.mymovestudio.com/FILES/facture-proformat-inscription-2021-2022.doc target=\"_blank\">http://www.mymovestudio.com/FILES/facture-proformat-inscription-2021-2022.doc</a>&nbsp;. <br/>Nous\n"
				+ "\n"
				+ "vous les redonnerons d&eacute;but octobre en cours.</p>\n"
				+ "\n"
				+ "<p><strong><span style=\"font-family: &quot;Calibri&quot;,sans-serif;\">Pour que votre\n"
				+ "\n"
				+ "inscription soit valide, elle doit comporter les &eacute;l&eacute;ments stipul&eacute;s dans l&#39;article 6A du r&egrave;glement int&eacute;rieur pr&eacute;sent sur le page \"s&#39;inscrire\" de\n"
				+ "\n"
				+ "notre site.</span></strong></p>\n"
				+ "\n"
				+ "<p><strong><span style=\"font-family: &quot;Calibri&quot;,sans-serif;\">Veuillez garder une\n"
				+ "\n"
				+ "copie de tous les documents de votre dossier avant de les d&eacute;poser &agrave; l&#39;\n"
				+ "\n"
				+ "association.&nbsp;&nbsp;</span></strong></p>\n"
				+ "\n"
				+ "<p>Association My Move Studio<br /> Salle : 18 rue Marceau 78800 HOUILLES <br /> Bureau : 2E rue Marceau 78800 HOUILLES (ouvert selon permanences, voir site\n"
				+ "\n"
				+ "internet) <br /> <a href=http://www.mymovestudio.com/>www.mymovestudio.com </a><br /> <a href=mailto:contact@mymovestudio.com>contact@mymovestudio.com </a><br /> 07.68.07.72.07<br /></p>\n"
				+ "\n"
				+ "</p>";
		this.mail = "contact@mymovestudio.com";
	}
	
	public void envoiMail(String mail, String mdp,String destinataire, String cheminPdf,String objet, String corps, boolean online) throws Exception {
		File file = new File(cheminPdf);
	    if(!file.canWrite())
	    	throw new PasFich();
	    if(mail.isEmpty())
			mail=this.mail;
		if(objet.isEmpty())
			objet=this.objet;
		if(corps.isEmpty())
			if(online)
				corps = this.corpsOnline;
			else
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
	    message.setFrom(new InternetAddress(mail));
	    message.addRecipients(Message.RecipientType.TO, destinataire);
	    message.addRecipients(Message.RecipientType.CC, "inscriptions@mymovestudio.com");
	    //message.addRecipients(Message.RecipientType.CC, "sara@mymovestudio.com");
	    
	    transport = session.getTransport("smtp");
	    transport.connect(mail, mdp);
	    transport.sendMessage(message, new Address[] { new InternetAddress(destinataire),new InternetAddress("inscriptions@mymovestudio.com")/*,new InternetAddress("sara@mymovestudio.com")*/});


		transport.close();
	}
	
	
	public void envoiNotif(String mdp, String destinataire, ArrayList<PersID> Ids) throws MessagingException {
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.host", "ssl0.ovh.net");
	    prop.setProperty("mail.smtp.user", this.mail);
	    prop.put("mail.smtp.port", 465);
	    prop.put("mail.smtp.auth", "true");
	    prop.setProperty("mail.from", this.mail);
	    prop.setProperty("mail.smtp.ssl.enable", "true");
	    Session session = Session.getInstance(prop);
	    
	    String objet = "Notification de doublon d'ID dans la base";
	    
	    MimeMessage message = new MimeMessage(session);
	    MimeBodyPart content = new MimeBodyPart();
	    MimeMultipart mimeMultipart = new MimeMultipart();
	    Transport transport = null;
	    
	    String corps= "Voici la liste des personnes en double dans la base : <br>";
	    
	    for(PersID id : Ids) {
	    	corps = corps + id.getNom()+" "+ id.getPrenom()+"<br>";
	    }
	    
	    corps = corps + "<br>Veuillez v�rifier les informations de ces personnes.<br>Sportivement, <br>MyMoveForm";
	    	
	    content.setContent(corps, "text/html");
	    	
	    mimeMultipart.addBodyPart(content);

	    	
	    message.setContent(mimeMultipart);
	    message.setSubject(objet);
	    message.setFrom(new InternetAddress(mail));
	    message.addRecipients(Message.RecipientType.TO, destinataire);

	    
	    transport = session.getTransport("smtp");
	    transport.connect(mail, mdp);
	    transport.sendMessage(message, new Address[] { new InternetAddress(destinataire)});


		transport.close();
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}
	
	public void setCorpsMail(String corpsMail) {
		this.corpsMail = corpsMail;
	}
}
