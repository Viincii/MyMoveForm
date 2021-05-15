package application.modele;

public class Personne {
	private String NumAdh;
	//private String NumUfolep;
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
		this.nom = nom;
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


}
