package com.mymovestudio.mymoveforms.modele;


import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;

public class CreaPDF {
	private Document doc;
	private PdfWriter writer;
	private PdfDocument docPdf;


	public CreaPDF(){

	}
	
	public void creerPdf(String chemin, Personne p, boolean online) throws FileNotFoundException, MalformedURLException {
		this.writer=new PdfWriter(chemin+"\\"+p.getNumAdh()+" "+p.getNom()+" "+p.getPrenom()+".pdf");
		this.docPdf = new PdfDocument(this.writer);
		PageSize pageSize = PageSize.A4;
		this.doc = new Document(docPdf , PageSize.A4);
		
		
		PdfCanvas canvas = new PdfCanvas(docPdf.addNewPage());
		if(online) { //Formulaire de cours en ligne
			canvas.addImageFittedIntoRectangle(ImageDataFactory.create("Images\\Online.png"), pageSize, false);
			if(p.getNumAdh()!=null)
				ajoutePara(p.getNumAdh(), 670, 410);
			ajoutePara(p.getNom()+" "+ p.getPrenom(), 702, 110);
			ajoutePara(p.getDatNaiss(), 702	, 360);
			ajoutePara(p.getAddr()+" "+p.getCodePostal()+" "+p.getVille().split(",")[0], 688, 70);
			ajoutePara(p.getTel(), 678, 120);
			ajoutePara(p.getMail(), 678, 235);
			
			if(p.getPackFamille()!=null) {
				ajoutePara(p.getPackFamille(), 665, 170);
			}
			
			String Cours = p.getCours();
			
			if(p.getCours2()!=null)
				Cours = Cours + "; " + p.getCours2();
			
			if(p.getCours3()!=null) {
				Cours = Cours + "; " + p.getCours3();
			}
			
			if(p.getCours2()==null && p.getCours3()==null)
				ajoutePara(Cours, 625,20,435,9);
			else	
				ajoutePara(Cours, 590, 20, 435,9);
			
			ajoutePara(p.getEssai(), 380, 30,520, 9);
		}
		else { // Formulaire en pr�sentiel
			canvas.addImageFittedIntoRectangle(ImageDataFactory.create("Images\\Présentiel.png"), pageSize, false);
			if(p.getNumAdh()!=null)
				ajoutePara(p.getNumAdh(), 792, 505);
			
			ajoutePara(p.getNom()+" "+ p.getPrenom(), 718, 100);
			ajoutePara(p.getDatNaiss(), 718	, 395);
			ajoutePara(p.getAddr()+" "+p.getCodePostal()+" "+p.getVille().split(",")[0], 707, 70);
			ajoutePara(p.getTel(), 694, 120);
			ajoutePara(p.getMail(), 695, 240);
			
			if(p.getTel2()!=null)
				ajoutePara(p.getTel2(), 682, 120);
			if(p.getMail2()!=null)
				ajoutePara(p.getMail2(), 682, 240);
			if(p.getRepLegal()!=null){
				ajoutePara(p.getRepLegal(), 670, 260);
				
			}
			
			if(p.getPackFamille()!=null) {
				ajoutePara(p.getPackFamille(), 658, 90);
			}
			
			String Cours = p.getCours();
			
			if(p.getCours2()!=null)
				Cours = Cours + "; " + p.getCours2();
			
			if(p.getCours3()!=null) {
				Cours = Cours + "; " + p.getCours3();
			}
			
			if(p.getCours2()==null && p.getCours3()==null)
				ajoutePara(Cours, 625,20,435,9);
			else	
				ajoutePara(Cours, 590, 20, 435,9);
			ajoutePara(p.getAptMed(), 550, 20,540, 8);
			ajoutePara(p.getAssurance(), 458, 30, 10);
			ajoutePara(p.getEssai(), 250, 30,520, 9);
		}
		
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
