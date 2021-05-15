package application.modele;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.image.ImageType;
import com.itextpdf.io.image.PngImageData;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

public class CreaPDF {
	private Document doc;
	private PdfWriter writer;
	private PdfDocument docPdf;


	public CreaPDF(){

	}
	
	public void creerPdf(String chemin, Personne p) throws FileNotFoundException, MalformedURLException {
		this.writer=new PdfWriter(chemin+"/"+p.getNumAdh()+" "+p.getNom()+" "+p.getPrenom()+".pdf");
		this.docPdf = new PdfDocument(this.writer);
		PageSize pageSize = PageSize.A4;
		this.doc = new Document(docPdf , PageSize.A4);
		
		PdfCanvas canvas = new PdfCanvas(docPdf.addNewPage());
		canvas.addImageFittedIntoRectangle(ImageDataFactory.create("Images/fond.png"), pageSize, false);
		
		if(p.getNumAdh()!=null)
			ajoutePara(p.getNumAdh(), 680, 410);
		ajoutePara(p.getNom()+" "+ p.getPrenom(), 720, 100);
		ajoutePara(p.getDatNaiss(), 720	, 260);
		ajoutePara(p.getAddr()+" "+p.getCodePostal()+" "+p.getVille().split(",")[0], 707, 70);
		ajoutePara(p.getTel(), 694, 120);
		ajoutePara(p.getMail(), 695, 240);
		if(p.getTel2()!=null) {
			ajoutePara(p.getTel2(), 682, 120);
			ajoutePara(p.getMail2(), 682, 240);
		}
		if(p.getRepLegal()!=null){
			try {
			String[] rP=p.getRepLegal().split(" ");
			ajoutePara(rP[0], 672,230);
			ajoutePara(rP[1], 672, 330);
			}catch(Exception e) {
				ajoutePara(p.getRepLegal(), 672, 230);
			}
		}
		
		if(p.getPackFamille()!=null) {
			ajoutePara(p.getPackFamille(), 658, 180);
		}
		
		ajoutePara(p.getCours(), 625, 20, 435,9);
		if(p.getCours2()!=null)
			ajoutePara(p.getCours2(), 615,20, 430,9);
		
		if(p.getCours3()!=null) {
			ajoutePara(p.getCours3(), 605,20, 430,9);
		}

		
		ajoutePara(p.getAptMed(), 550, 20,540, 8);
		ajoutePara(p.getAssurance(), 458, 30, 10);
		ajoutePara(p.getEssai(), 250, 30,520, 9);
		doc.close();
	}
	
	private void ajoutePara(String st, float top, float left) {
		ajoutePara(st, top, left, 420, 10);
	}
	
	private void ajoutePara(String st, float top, float left, float taille ) {
		ajoutePara(st, top, left, 420, taille);
	}
	
	private void ajoutePara(String st, float top, float left,float largeur, float taille ) {
		Paragraph p = new Paragraph(st);
		Style s = new Style();
		s.setFontSize(taille);
		s.setFixedPosition(left, top, largeur);
		Color fontColor = new DeviceRgb(0, 0, 255);
		s.setFontColor(fontColor);
		p.addStyle(s);
		doc.add(p);
	}
}
