package com.mymovestudio.mymoveforms.modele;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LecteurExcel {

	private BaseDonnee bdPers;
	public LecteurExcel(BaseDonnee bd) {
		this.bdPers = bd;
	}
	
	@SuppressWarnings("resource")
	public void remplirPers(String nomFich) throws IOException {
		FileInputStream file = new FileInputStream(nomFich);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		  
	    //Permet d'iterer sur les lignes d'un onglet
	    Iterator<Row> rowIterator = sheet.iterator();
	    Row row = rowIterator.next();
	    //On parcours toute les lignes
	    while (rowIterator.hasNext()) {
	    	String numAdh = null;
	    	String typ = null;
	    	String nom = null;
	    	String prenom = null;
	    	String representantLegal = null;
	    	String dat = null;
	    	String addr = null;
	    	String codePost = null;
	    	String ville = null;
	    	String tel = null;
	    	String mel = null;
	    	String tel2= null;
	    	String mel2=null;
	    	String packF=null;
	    	String assurance=null;
	    	String cours=null;
	    	String cours2=null;
	    	String cours3=null;
	    	String aptitudeMed =null;
	    	String essai=null;
	    	
	        row = rowIterator.next();
	        Iterator<Cell> cellIterator = row.cellIterator();
	        if(cellIterator.hasNext()) {
		        Cell cellule = cellIterator.next();
		        if(cellule.getColumnIndex()==0) {
		        	numAdh = cellule.getStringCellValue();
			        cellule = cellIterator.next();
		        }
		        if(cellule.getColumnIndex()==1) {
			        typ = cellule.getStringCellValue();
			        cellule = cellIterator.next();
		        }
		        if(cellule.getColumnIndex()==2) {
			        nom = cellule.getStringCellValue();
			        cellule = cellIterator.next();
		        }
		        if(cellule.getColumnIndex()==3) {
			        prenom = cellule.getStringCellValue();
			        cellule = cellIterator.next();
		        }
		        
		        if(cellule.getColumnIndex()==4) {
			        representantLegal = cellule.getStringCellValue();
			        cellule = cellIterator.next();
		        }
		        
		        if(cellule.getColumnIndex()==5) {
			        dat= this.conversionDat(cellule);
			        cellule = cellIterator.next();
		        }
		        
		        if(cellule.getColumnIndex()==6) {
			        addr = cellule.getStringCellValue();
			        cellule = cellIterator.next();
		        }
		        
		        if(cellule.getColumnIndex()==7) {
			        codePost = this.conversionInt(cellule);
			        cellule = cellIterator.next();
		        }
		        
		        if(cellule.getColumnIndex()==8) {
			        ville = cellule.getStringCellValue();
			        cellule = cellIterator.next();
		        }
		        
		        if(cellule.getColumnIndex()==9) {
			        tel = this.conversionTel(cellule);
			        cellule = cellIterator.next();
		        }
		        
		        if(cellule.getColumnIndex()==10) {
			        mel = this.conversion(cellule);
			        cellule = cellIterator.next();
		        }
		        if(cellule.getColumnIndex()==11) {
			        tel2 = this.conversionTel(cellule);
			        cellule = cellIterator.next();
		        }
		        
		        if(cellule.getColumnIndex()==12) {
		        	mel2 = this.conversion(cellule);
			        cellule = cellIterator.next();
		        }
		        
		        cellule = cellIterator.next();//On passe la situation
		        if(cellule.getColumnIndex()==14) {
		        	packF = cellule.getStringCellValue();
		        	cellule = cellIterator.next();
		        }
		        cellule = cellIterator.next();//On passe le réglement
		        cellule = cellIterator.next();//et l'adhésion obligatoire
		        if(cellule.getColumnIndex()==17) {
			        assurance = cellule.getStringCellValue();
			        cellule = cellIterator.next();
		        }
		        if(cellule.getColumnIndex()==18) {
			        cours = cellule.getStringCellValue();
			        cellule = cellIterator.next();
		        }
		        if(cellule.getColumnIndex()==19) {
			        cours2 = cellule.getStringCellValue();
			        cellule = cellIterator.next();
		        }
		        if(cellule.getColumnIndex()==20) {
			        cours3 = cellule.getStringCellValue();
			        cellule = cellIterator.next();
		        }
		        if(cellule.getColumnIndex()==21) {
			        aptitudeMed = cellule.getStringCellValue();
			        cellule = cellIterator.next();
		        }
		        if(cellule.getColumnIndex()==22) {
		        	essai = cellule.getStringCellValue();
		        }
		        
		        this.bdPers.ajoutP(new Personne(numAdh,nom,prenom,typ,representantLegal,dat,addr,codePost,ville,tel,mel, tel2, mel2, packF, assurance,cours,cours2,cours3, aptitudeMed, essai));
	        }
	    }
		
		file.close();
	}
	
	public String conversionInt(Cell cellule) {
		CellType cT = cellule.getCellType();
		String res = "";
        switch(cT) {
	        case NUMERIC:
	        	res = ""+Integer.parseInt(((Double)cellule.getNumericCellValue()).toString());
	        	break;
	        case STRING:
	        	res = cellule.getStringCellValue();
	        	break;
		default:
			break;
        }
        return res;
	}

	public String conversion(Cell cellule) {
		CellType cT = cellule.getCellType();
		String res = "";
        switch(cT) {
	        case NUMERIC:
	        	res = ((Double)cellule.getNumericCellValue()).toString();
	        	break;
	        case STRING:
	        	res = cellule.getStringCellValue();
	        	break;
		default:
			break;
        }
        return res;
	}
	
	@SuppressWarnings("deprecation")
	public String conversionDat(Cell cellule) {
		CellType cT = cellule.getCellType();
		String res = "";
        switch(cT) {
	        case NUMERIC:
	        	Date temp = ((Date)cellule.getDateCellValue());
	        	res = temp.getDate()+"/"+(temp.getMonth()+1)+"/"+(temp.getYear()+1900);
	        	break;
	        case STRING:
	        	res = cellule.getStringCellValue();
	        	break;
		default:
			break;
        }
        return res;
	}
	
	public String conversionTel(Cell cellule) {
		CellType cT = cellule.getCellType();
		String res = "";
        switch(cT) {
	        case NUMERIC:
	        	Double temp = ((Double)cellule.getNumericCellValue());
	        	res = "0"+ temp.intValue();
	        	break;
	        case STRING:
	        	res = cellule.getStringCellValue().replaceAll(" ", "");
	        	break;
		default:
			break;
        }
        return res;
	}
	
	@SuppressWarnings("resource")
	public void remplirIDs(String cheminBD) throws IOException {
		FileInputStream file = new FileInputStream(cheminBD);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		//Parti prospect
		XSSFSheet sheet = workbook.getSheetAt(2);
	    Iterator<Row> rowIterator = sheet.iterator();
	    Row row = rowIterator.next();
	    row = rowIterator.next();
	    row = rowIterator.next();
	    String nom;
    	String prenom;
	    //On parcours toute les lignes
	    while (!(row.getCell(0)==null) &&!(row.getCell(0).getNumericCellValue() == 0.0)) {
	        nom = row.getCell(3).getStringCellValue();
	        prenom = row.getCell(4).getStringCellValue();
	        this.bdPers.ajoutId(new PersID(nom, prenom));
	        row = rowIterator.next();
	    }
	    
	    
	    //Parti Adh
	    
	    sheet = workbook.getSheetAt(3);
	    rowIterator = sheet.iterator();
	    row = rowIterator.next();
	    row = rowIterator.next();
	    row = rowIterator.next();
	    //On parcours toute les lignes
	    while (!(row.getCell(0)==null) &&!(row.getCell(0).getNumericCellValue() == 0.0)) {
	        nom = row.getCell(3).getStringCellValue();
	        prenom = row.getCell(4).getStringCellValue();
	        
	        this.bdPers.ajoutId(new PersID(nom, prenom));
	        row = rowIterator.next();
	    }
	}
}