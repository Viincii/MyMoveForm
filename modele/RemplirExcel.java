package application.modele;

import java.io.FileInputStream;
import java.io.IOException;
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
	
	@SuppressWarnings("resource")
	public void RemplirAvecPers(String nomFich) throws IOException {
		FileInputStream file = new FileInputStream(nomFich);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(2);
		
		int compteur= 0;
		Iterator<Row> rowIterator = sheet.iterator();
		while(rowIterator.hasNext()) {
			compteur++;
			rowIterator.next();
		}
		System.out.println(compteur);
		int idCell;
		for(Personne p: this.bd.getGens()) {
			idCell = 0;
			Row r = sheet.createRow(compteur+1);
	        Cell cellule = r.createCell(idCell);
	        cellule.setCellValue(p.getNumAdh());
	        idCell ++;
	        cellule = r.createCell(idCell);
	        idCell ++;
	        cellule = r.createCell(idCell);
	        cellule.setCellValue(p.getNom());
	        idCell ++;
	        cellule = r.createCell(idCell);
	        cellule.setCellValue(p.getPrenom());
	        compteur++;
		}
	}
}
