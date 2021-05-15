package application.modele;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import application.Exceptions.PasExpo;

public class BaseDonnee {

	
	private ArrayList<Personne> liste;
	private CreaPDF createur;
	private EnvoiMails eM;
	
	public BaseDonnee(){
		this.liste = new ArrayList<Personne>();
		this.createur = new CreaPDF();
		this.eM = new EnvoiMails();
	}
	
	public void ajoutP(Personne p) {
		this.liste.add(p);
	}
	
	public ArrayList<Personne> getGens(){
		return this.liste;
	}

	@Override
	public String toString() {
		return "BaseDonnee [liste=" + liste + "]";
	}
	
	public void creerPdf(String chemin) throws FileNotFoundException, MalformedURLException {
		for(Personne p: liste)
			this.createur.creerPdf(chemin, p);
	}
	
	public void envoiPdfs(String chemin, String mail, String mdp, String corps) throws Exception {
		if(this.liste.isEmpty())
			throw new PasExpo();
		for(Personne p: liste) {
			try {
				eM.envoiMail(mdp, p.getMail(), chemin+"/"+p.getNumAdh()+" "+ p.getNom()+" "+ p.getPrenom()+".pdf");
			}catch(Exception e) {
				
			}
		}
	}
}
