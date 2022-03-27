package com.mymovestudio.mymoveforms.controleur;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.mail.AuthenticationFailedException;

import com.mymovestudio.mymoveforms.Exceptions.CheminVide;
import com.mymovestudio.mymoveforms.Exceptions.PasExpo;
import com.mymovestudio.mymoveforms.Exceptions.PasFich;
import com.mymovestudio.mymoveforms.Exceptions.PasMdp;
import com.mymovestudio.mymoveforms.modele.BaseDonnee;
import com.mymovestudio.mymoveforms.modele.LecteurExcel;
import com.mymovestudio.mymoveforms.modele.RemplirExcel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class Controleur implements Initializable{

	private BaseDonnee bd;
	private LecteurExcel lE;
	private RemplirExcel rE;
	
	@FXML
    private HBox Menu;

    @FXML
    private TextField cheminExport;

    @FXML
    private TextField cheminPdfs;

    @FXML
    private VBox Creation;

    @FXML
    private VBox Envoi;

    @FXML
    private VBox Prospects;
    
    @FXML
    private TextField mail;

    @FXML
    private PasswordField mdp;

    @FXML
    private TextField objetMail;

    @FXML
    private TextArea corps;

    @FXML
    private ProgressIndicator chargement;

    @FXML
    private Label infos;
    
    @FXML
    private CheckBox online;
    
    @FXML
    private TextField cheminBD;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.bd = new BaseDonnee();
		this.lE= new LecteurExcel(bd);
		this.rE = new RemplirExcel(bd);
	}
	

    
    
    @FXML
    void CreationPdfs(ActionEvent event) {
    	Envoi.setVisible(false);
    	Creation.setVisible(true);
    	Prospects.setVisible(false);
    	this.infos.setText("");
    	this.chargement.setVisible(false);
    }

    @FXML
    void EnvoiDesPDFs(ActionEvent event) {
    	Envoi.setVisible(true);
    	Creation.setVisible(false);
    	Prospects.setVisible(false);
    	this.infos.setText("");
    	this.chargement.setVisible(false);
    }
    
    @FXML
    void AddProspects(ActionEvent event) {
    	Envoi.setVisible(false);
    	Creation.setVisible(false);
    	Prospects.setVisible(true);
    	this.infos.setText("");
    	this.chargement.setVisible(false);
    }
    
    @FXML
    void lancementCrea(ActionEvent event) {//Action de creation de PDFs
    	this.infos.setText("");
    	this.chargement.setVisible(false);
    	try {
    		chargement.setProgress(0);
    		chargement.setVisible(true);
    		if(this.cheminExport.getText()!=null&&this.cheminPdfs.getText()!=null&&this.cheminBD.getText()!=null) {
    			this.lE.remplirPers(this.cheminExport.getText());
    			this.lE.remplirIDs(this.cheminBD.getText());
				chargement.setProgress(25);
				boolean formOnline = online.isSelected();
				this.bd.creerPdf(this.cheminPdfs.getText(), formOnline);
				chargement.setProgress(50);
				this.rE.RemplirAvecPers(this.cheminBD.getText());
	    		chargement.setProgress(100);
				this.infos.setText("C'est fini! Tout c'est bien passé! Les PDF sont dans le dossier donné au dessus.");
				if(bd.notifNecessaire()) {
					this.infos.setText("Une notification a été envoyé par mail.");
					this.bd.envoiNotif(mdp.getText(), "vinc.mignot@hotmail.fr"/*"contact@mymovestudio.com"*/);
				}
				this.bd.viderBd();
    		}
    		else {
    			throw new CheminVide();
    		}
		}
    	catch (IOException e){
    		this.infos.setText("Il a eu une erreur de création de PDF.");
    	}
    	catch (IllegalStateException e) {
    		this.infos.setText("Un problème de format est survenu, merci de bien vérifier la base et l'export");
    	}
    	catch (CheminVide e) {
    		this.infos.setText("Au moins un des champs n'est pas renseigné.");
    	}
    	catch (AuthenticationFailedException e) {
			this.infos.setText("Le mot de passe saisi semble incorrect.");
		}
    	catch (Exception e) {
    		e.printStackTrace();
			this.infos.setText("Une erreur imprévue est apparue, veuillez vérifier les chemins renseignés et la cohérence des données.\n Si le problème persiste, merci de contacter votre développeur.");
		}
    }
    
    @FXML
    void lancementEnvoi(ActionEvent event) {//Action d'envoi de mails
    	this.infos.setText("");
    	this.chargement.setVisible(false);
    	try {
    		chargement.setProgress(0);
    		chargement.setVisible(true);
    		if(mdp.getText().isEmpty())
    			throw new PasMdp();
    		if(!cheminExport.getText().isEmpty())
    			lE.remplirPers(cheminExport.getText());
    		chargement.setProgress(50);
    		this.bd.envoiPdfs(cheminPdfs.getText(), mail.getText(), mdp.getText(), objetMail.getText(), corps.getText(), online.isSelected());
    		chargement.setProgress(100);
    		
		} catch (PasExpo e) {
			this.infos.setText("Veuillez renseigner le champ export, la base de donnée est actuellement vide");
		} catch (PasFich e) {
 			this.infos.setText("Problème d'ouverture de fichiers.");
		} catch (PasMdp e) {
			this.infos.setText("Veuillez renseigner le mot de passe.");
		}catch (AuthenticationFailedException e) {
			this.infos.setText("Le mot de passe saisi semble incorrect.");
		} catch (Exception e) {
			this.infos.setText("Une erreur imprévue est apparue, veuillez vérifier les chemins renseignés et la cohérence des données.\n Si le problème persiste, merci de contacter votre développeur.");
		}
    }
    
    @FXML
    void LancementProspects(ActionEvent event) {//Action d'ajout des prospects
    	this.infos.setText("");
    	this.chargement.setVisible(false);
    	try {
    		chargement.setProgress(0);
    		chargement.setVisible(true);
    		if(this.cheminExport.getText()!=null&&this.cheminPdfs.getText()!=null&&this.cheminBD.getText()!=null) {
    			this.lE.remplirPers(this.cheminExport.getText());
    			this.lE.remplirIDs(this.cheminBD.getText());
    		}
    		else
    			throw new CheminVide();
    		chargement.setProgress(50);
    		this.rE.RemplirAvecPers(this.cheminBD.getText());
    		chargement.setProgress(100);
    		this.infos.setText("Fini!");
    		if(bd.notifNecessaire()) {
				this.infos.setText("Une notification a été envoyé par mail.");
				this.bd.envoiNotif(mdp.getText(), "vinc.mignot@hotmail.fr"/*"contact@mymovestudio.com"*/);
			}
    		this.bd.viderBd();
    	}
    	catch (IllegalStateException e) {
    		this.infos.setText("Un problème de format est survenu, merci de bien vérifier la base et l'export");
    	}
    	catch (CheminVide e) {
    		this.infos.setText("Au moins un des champs n'est pas renseigné.");
    	}
    	catch (AuthenticationFailedException e) {
			this.infos.setText("Le mot de passe saisi semble incorrect.");
		}
    	catch(Exception e) {
    		this.infos.setText("Une erreur s'est produite, vérifier que le fichier est bien fermé et le chemin correct");
    	}
    }
    
    

    @FXML
    void parcourir(ActionEvent event) {
    	this.infos.setText("");
    	this.chargement.setVisible(false);
    	FileChooser parcou = new FileChooser();
    	File f = parcou.showOpenDialog(null);
    	if(f!=null)
    		this.cheminExport.setText(f.getPath());
    }
    
    @FXML
    void parcourirPdfs(ActionEvent event) {
    	this.infos.setText("");
    	this.chargement.setVisible(false);
    	DirectoryChooser parcou = new DirectoryChooser();
    	File f = parcou.showDialog(null);
    	if(f!=null)
    		this.cheminPdfs.setText(f.getPath());
    }
    
    @FXML
    void parcourirBD(ActionEvent event) {
    	this.infos.setText(""); 
    	this.chargement.setVisible(false);
    	FileChooser parcou = new FileChooser();
    	File f = parcou.showOpenDialog(null);
    	if(f!=null)
    		this.cheminBD.setText(f.getPath());
    }
    
    
    
    
}
