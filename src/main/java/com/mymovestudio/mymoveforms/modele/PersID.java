package com.mymovestudio.mymoveforms.modele;

import java.text.Normalizer;

public class PersID {

	
	private String nom;
	private String prenom;
	
	

	public PersID(String n, String p) {
		nom = n;
		prenom = p;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}
	

	
	public boolean equals(PersID obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (nom == null) {
			if (obj.nom != null)
				return false;
		} 
		String nom1 = normalizeString(this.nom);
		String nom2 = normalizeString(obj.getNom());;
		String prenom1 = normalizeString(this.prenom);;
		String prenom2 = normalizeString(obj.getPrenom());;
		if (!nom1.equals(nom2))
			return false;
		if (prenom == null) {
			if (obj.prenom != null)
				return false;
		} else if (!prenom1.equals(prenom2))
			return false;
		return true;
	}
	
	private String normalizeString(String s) {
		return Normalizer.normalize(s.toUpperCase(), Normalizer.Form.NFKD).replaceAll("[^\\p{ASCII}]", "").replaceAll(" ", "");
	}
}
