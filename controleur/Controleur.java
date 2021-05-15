package application.controleur;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Exceptions.PasExpo;
import application.modele.BaseDonnee;
import application.modele.LecteurExcel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controleur implements Initializable{

	private BaseDonnee bd;
	private LecteurExcel lE;
	
	@FXML
    private ProgressIndicator chargement;

    @FXML
    private VBox Creation;

    @FXML
    private TextField mail;

    @FXML
    private VBox Envoi;

    @FXML
    private PasswordField mdp;
    
    @FXML
    private TextArea corps;

    @FXML
    private TextField cheminExport;

    @FXML
    private HBox Menu;

    @FXML
    private TextField cheminExportCrea;

    @FXML
    private Label infos;

    @FXML
    private TextField chemDosPdfs;
    
    @FXML
    private TextField cheminPdfsEnv;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.bd = new BaseDonnee();
		this.lE= new LecteurExcel(bd);
	}
	

    @FXML
    void lancementCrea(ActionEvent event) {
    	try {
    		chargement.setProgress(0);
    		chargement.setVisible(true);
    		if(this.cheminExportCrea.getText()!=null&&this.chemDosPdfs.getText()!=null) {
    			this.lE.remplirPers(this.cheminExportCrea.getText());
				chargement.setProgress(50);
				this.bd.creerPdf(this.chemDosPdfs.getText());
				chargement.setProgress(100);
				this.infos.setText("C'est fini! Tout c'est bien passé! Les PDF sont dans le dossier donné au dessus.");
    		}
    		else {
    			throw new Exception();
    		}
		} catch (Exception e) {
			this.infos.setText("Une erreur imprévue est apparue, veuillez vérifier les chemins renseignés et la cohérence des données.\n Si le problème persiste, merci de contacter votre boulet de développeur.");
		}
    }
    
    @FXML
    void CreationPdfs(ActionEvent event) {
    	Envoi.setVisible(false);
    	Creation.setVisible(true);
    }

    @FXML
    void EnvoiDesPDFs(ActionEvent event) {
    	Envoi.setVisible(true);
    	Creation.setVisible(false);
    }
    
    @FXML
    void lancementEnvoi(ActionEvent event) {
    	try {
    		chargement.setProgress(0);
    		chargement.setVisible(true);
    		
    		if(cheminExport.getText()!=null)
    			lE.remplirPers(cheminExport.getText());
    		chargement.setProgress(50);
    		this.bd.envoiPdfs(cheminPdfsEnv.getText(), mail.getText(), mdp.getText(), corps.getText());
    		chargement.setProgress(100);
    		//TODO Vérification mail, mdp et fait changement du corps du mail
    		
		} catch (PasExpo e) {
			this.infos.setText("Veuillez renseigner le champ export, la base de donnée est actuellement vide");
		}
    	 catch (IOException e) {
 			this.infos.setText("IO");
 		}
    	catch (Exception e) {
			this.infos.setText("Une erreur imprévue est apparue, veuillez vérifier les chemins renseignés et la cohérence des données.\n Si le problème persiste, merci de contacter votre boulet de développeur.");
		}
    }

}
