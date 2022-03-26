package com.mymovestudio.mymoveforms.modele;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RemplirExcel {

	private BaseDonnee bd;
	
	public RemplirExcel(BaseDonnee bd) {
		this.bd=bd;
	}
	
	@SuppressWarnings({ "resource", "deprecation" })
	public void RemplirAvecPers(String nomFich) throws IOException {
		FileInputStream file = new FileInputStream(nomFich);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(2);
		
		Iterator<Row> rowIterator = sheet.iterator();
		Row row = rowIterator.next();
		row = rowIterator.next();
		row = rowIterator.next();
		
		
		for(Personne p: this.bd.getGens()) {
			PersID pID = new PersID(p.getNom(), p.getPrenom());
			if(!this.bd.idExist(pID)) {
				row = sheet.createRow(TrouverLigneVide(rowIterator, row.getRowNum()));
				
		        Cell cellule = row.createCell(0);
		        if(!p.getNumAdh().isEmpty())
		        cellule.setCellValue(Integer.parseInt(p.getNumAdh()));
		        int index = 2;
		        cellule = row.createCell(index);
		        cellule.setCellValue(p.getType());
		        index ++;
		        cellule = row.createCell(index);
		        cellule.setCellValue(p.getNom().toUpperCase());
		        index ++;
		        cellule = row.createCell(index);
		        cellule.setCellValue(p.getPrenom().toUpperCase());
		        index ++;
		        index ++;
		        cellule = row.createCell(index);
		        cellule.setCellValue(p.getPackFamille());
		        index ++;
		        cellule = row.createCell(index);
		        cellule.setCellValue(new Date(p.getDatNaiss()));
		        index ++;
		        index ++;
		        cellule = row.createCell(index);
		        cellule.setCellValue(p.getAddr().toUpperCase().substring(0,p.getAddr().length()-2));
		        index ++;
		        cellule = row.createCell(index);
		        cellule.setCellValue(p.getCodePostal());
		        index ++;
		        cellule = row.createCell(index);
		        cellule.setCellValue(p.getVille().toUpperCase().substring(0, p.getVille().length()-2));
		        index ++;
		        cellule = row.createCell(index);
		        cellule.setCellValue(p.getRepLegal());
		        index ++;
		        cellule = row.createCell(index);
		        if(!p.getTel().isEmpty())
		        	cellule.setCellValue(Integer.parseInt(p.getTel()));
		        index ++;
		        cellule = row.createCell(index);
		        cellule.setCellValue(p.getMail());
		        index ++;
		        index ++;
		        cellule = row.createCell(index);
		        if(p.getTel2()!=null&&!p.getTel2().isEmpty())
		        	cellule.setCellValue(Integer.parseInt(p.getTel2()));
		        index ++;
		        cellule = row.createCell(index);
		        cellule.setCellValue(p.getMail2());
 
			}
			else
				this.bd.ajoutIdExistant(pID);
			
		}
		
        FileOutputStream file2 = new FileOutputStream(nomFich);
        workbook.write(file2);
        file.close();
        file2.close();
	}
	
	
	private int TrouverLigneVide(Iterator<Row> rowIterator, int rowNum) {
		if(rowIterator.hasNext()) {	
			Row row = rowIterator.next();
			while(!(row.getCell(0)==null) &&!(row.getCell(0).getNumericCellValue() == 0.0)) {
				if(rowIterator.hasNext()) {
					row = rowIterator.next();
				}
				else
					break;
			}
			return row.getRowNum();
		}
		else
			return rowNum+1;
	}
}
