package com.mymovestudio.mymoveforms.modele;

public class Personne {
	

	private String NumAdh;
	private String nom;
	private String prenom;
	private String datNaiss;
	private String addr;
	private String type;
	private String tel;
	private String mail;
	private String tel2;
	private String mail2;
	private String repLegal;
	private String codePostal;
	private String ville;
	private String packFamille;
	private String assurance;
	private String cours;
	private String cours2;
	private String cours3;
	private String aptMed;
	private String essai;
	
	
	public Personne(String numAdh, String nom, String prenom,String typ, String repL,
			String datNaiss, String addr, 
			String codP, String ville, String tel, String mel, String tel2, String mel2, String packF, String assurance, String cours, String cours2, String cours3, String aptitudeMed, String essai) {
		this.NumAdh = numAdh;
		this.nom = nom.toUpperCase();
		this.prenom = prenom.toLowerCase();
		String premChar = ((Character)prenom.charAt(0)).toString();
		this.prenom= this.prenom.substring(1);
		this.prenom = premChar.concat(this.prenom);
		this.type = typ;
		this.repLegal = repL;
		this.datNaiss = datNaiss;
		this.addr = addr;
		this.codePostal= codP;
		this.ville = ville;
		this.tel = tel;
		this.mail = mel;
		this.tel2 = tel2;
		this.mail2 = mel2;
		this.packFamille= packF;
		this.assurance=assurance;
		this.cours= cours;
		this.cours2= cours2;
		this.cours3= cours3;
		this.aptMed= aptitudeMed;
		this.essai = essai;
	}

	
	@Override
	public String toString() {
		return "Personne [NumAdh=" + NumAdh + ", nom=" + nom + ", prenom=" + prenom + ", datNaiss=" + datNaiss
				+ ", addr=" + addr + ", type=" + type + ", tel=" + tel + ", mail=" + mail + ", tel2=" + tel2
				+ ", mail2=" + mail2 + ", repLegal=" + repLegal + ", codePostal=" + codePostal + ", ville=" + ville
				+ ", packFamille=" + packFamille + ", assurance=" + assurance + ", cours=" + cours + ", cours2="
				+ cours2 + ", cours3=" + cours3 + ", aptMed=" + aptMed + "]\n";
	}

	public String getNumAdh() {
		return NumAdh;
	}


	public String getNom() {
		return nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public String getDatNaiss() {
		return datNaiss;
	}


	public String getAddr() {
		return addr;
	}


	public String getType() {
		return type;
	}


	public String getTel() {
		return tel;
	}


	public String getMail() {
		return mail;
	}


	public String getTel2() {
		return tel2;
	}


	public String getMail2() {
		return mail2;
	}


	public String getRepLegal() {
		return repLegal;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public String getVille() {
		return ville;
	}


	public String getPackFamille() {
		return packFamille;
	}


	public String getAssurance() {
		return assurance;
	}


	public String getCours() {
		return cours;
	}


	public String getCours2() {
		return cours2;
	}


	public String getCours3() {
		return cours3;
	}


	public String getAptMed() {
		return aptMed;
	}
	
	public String getEssai() {
		return essai;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NumAdh == null) ? 0 : NumAdh.hashCode());
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + ((aptMed == null) ? 0 : aptMed.hashCode());
		result = prime * result + ((assurance == null) ? 0 : assurance.hashCode());
		result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result + ((cours == null) ? 0 : cours.hashCode());
		result = prime * result + ((cours2 == null) ? 0 : cours2.hashCode());
		result = prime * result + ((cours3 == null) ? 0 : cours3.hashCode());
		result = prime * result + ((datNaiss == null) ? 0 : datNaiss.hashCode());
		result = prime * result + ((essai == null) ? 0 : essai.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((mail2 == null) ? 0 : mail2.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((packFamille == null) ? 0 : packFamille.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((repLegal == null) ? 0 : repLegal.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		result = prime * result + ((tel2 == null) ? 0 : tel2.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (NumAdh == null) {
			if (other.NumAdh != null)
				return false;
		} else if (!NumAdh.equals(other.NumAdh))
			return false;
		if (addr == null) {
			if (other.addr != null)
				return false;
		} else if (!addr.equals(other.addr))
			return false;
		if (aptMed == null) {
			if (other.aptMed != null)
				return false;
		} else if (!aptMed.equals(other.aptMed))
			return false;
		if (assurance == null) {
			if (other.assurance != null)
				return false;
		} else if (!assurance.equals(other.assurance))
			return false;
		if (codePostal == null) {
			if (other.codePostal != null)
				return false;
		} else if (!codePostal.equals(other.codePostal))
			return false;
		if (cours == null) {
			if (other.cours != null)
				return false;
		} else if (!cours.equals(other.cours))
			return false;
		if (cours2 == null) {
			if (other.cours2 != null)
				return false;
		} else if (!cours2.equals(other.cours2))
			return false;
		if (cours3 == null) {
			if (other.cours3 != null)
				return false;
		} else if (!cours3.equals(other.cours3))
			return false;
		if (datNaiss == null) {
			if (other.datNaiss != null)
				return false;
		} else if (!datNaiss.equals(other.datNaiss))
			return false;
		if (essai == null) {
			if (other.essai != null)
				return false;
		} else if (!essai.equals(other.essai))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (mail2 == null) {
			if (other.mail2 != null)
				return false;
		} else if (!mail2.equals(other.mail2))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (packFamille == null) {
			if (other.packFamille != null)
				return false;
		} else if (!packFamille.equals(other.packFamille))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (repLegal == null) {
			if (other.repLegal != null)
				return false;
		} else if (!repLegal.equals(other.repLegal))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (tel2 == null) {
			if (other.tel2 != null)
				return false;
		} else if (!tel2.equals(other.tel2))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}
}
