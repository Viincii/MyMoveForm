package application.controleur;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.mail.AuthenticationFailedException;

import application.Exceptions.PasExpo;
import application.Exceptions.PasFich;
import application.Exceptions.PasMdp;
import application.modele.BaseDonnee;
import application.modele.LecteurExcel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.bd = new BaseDonnee();
		this.lE= new LecteurExcel(bd);
	}
	

    @FXML
    void lancementCrea(ActionEvent event) {
    	this.infos.setText("");
    	this.chargement.setVisible(false);
    	try {
    		chargement.setProgress(0);
    		chargement.setVisible(true);
    		if(this.cheminExport.getText()!=null&&this.cheminPdfs.getText()!=null) {
    			this.lE.remplirPers(this.cheminExport.getText());
				chargement.setProgress(50);
				this.bd.creerPdf(this.cheminPdfs.getText());
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
    	this.infos.setText("");
    	this.chargement.setVisible(false);
    }

    @FXML
    void EnvoiDesPDFs(ActionEvent event) {
    	Envoi.setVisible(true);
    	Creation.setVisible(false);
    	this.infos.setText("");
    	this.chargement.setVisible(false);
    }
    
    @FXML
    void lancementEnvoi(ActionEvent event) {
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
    		this.bd.envoiPdfs(cheminPdfs.getText(), mail.getText(), mdp.getText(), objetMail.getText(), corps.getText());
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
			e.printStackTrace();
			this.infos.setText("Une erreur imprévue est apparue, veuillez vérifier les chemins renseignés et la cohérence des données.\n Si le problème persiste, merci de contacter votre boulet de développeur.");
		}
    }

    @FXML
    void parcourir(ActionEvent event) {
    	FileChooser parcou = new FileChooser();
    	File f = parcou.showOpenDialog(null);
    	if(f!=null)
    		this.cheminExport.setText(f.getPath());
    }
    
    @FXML
    void parcourirPdfs(ActionEvent event) {
    	DirectoryChooser parcou = new DirectoryChooser();
    	File f = parcou.showDialog(null);
    	if(f!=null)
    		this.cheminPdfs.setText(f.getPath());
    }
}
