package com.mymovestudio.mymoveforms.modele;

import com.mymovestudio.mymoveforms.Exceptions.PasExpo;
import com.mymovestudio.mymoveforms.Exceptions.PasFich;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.mail.MessagingException;

public class BaseDonnee {

	
	private ArrayList<Personne> liste;
	private CreaPDF createur;
	private EnvoiMails eM;
	private ArrayList<PersID> listeIds;
	private ArrayList<PersID> IdsExistants;
	
	public BaseDonnee(){
		this.liste = new ArrayList<Personne>();
		this.createur = new CreaPDF();
		this.eM = new EnvoiMails();
		this.listeIds = new ArrayList<>();
		this.IdsExistants = new ArrayList<>();
	}
	
	public void ajoutP(Personne p) {
		if(!dejaDansListe(p))
			this.liste.add(p);
	}
	
	public void ajoutId(PersID p) {
		if(!idExist(p))
			this.listeIds.add(p);
	}
	
	
	private boolean dejaDansListe(Personne p) {
		for(Personne p2: liste) {
			if(p2.equals(p))
				return true;
		}
		return false;
	}
	
	public boolean idExist(PersID id) {
		for(PersID idL: listeIds)
			if(idL.equals(id))
				return true;
		return false;		
	}
	
	public ArrayList<PersID> getIdsExistants() {
		return IdsExistants;
	}

	public void ajoutIdExistant(PersID id) {
		IdsExistants.add(id);
	}
	
	public boolean notifNecessaire() {
		return !IdsExistants.isEmpty();
	}
	public ArrayList<Personne> getGens(){
		return this.liste;
	}
	
	public void viderBd() {
		liste.clear();
		listeIds.clear();
		IdsExistants.clear();
	}

	@Override
	public String toString() {
		return /*"BaseDonnee \nids =\n"+ listeId() + */"\ndeja existant: "+ IdsExistants;
	}
	private String listeId() {
		String temp = "";
		for(PersID id:listeIds)
			temp = temp + id.getNom()+ " " + id.getPrenom() +"\n";
		return temp;
	}
	public void creerPdf(String chemin, boolean online) throws FileNotFoundException, MalformedURLException {
		for(Personne p: liste)
			this.createur.creerPdf(chemin, p, online);
	}
	
	public void envoiPdfs(String chemin, String mail, String mdp, String objet, String corps, boolean online) throws Exception {
		if(this.liste.isEmpty())
			throw new PasExpo();
		for(Personne p: liste) {
			try {
				eM.envoiMail(mail,mdp, p.getMail(), chemin+"\\"+p.getNumAdh()+" "+p.getNom()+" "+p.getPrenom()+".pdf",objet, corps, online);
			}catch(PasFich e) {
				System.out.println("Probleme de lecture de fichier");
			}
		}
	}
	
	public void envoiNotif(String mdp, String destinataire) throws MessagingException {
		this.eM.envoiNotif(mdp, destinataire, IdsExistants);
	}
}
