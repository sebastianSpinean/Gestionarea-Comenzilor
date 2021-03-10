package businessLayer;

import java.io.FileOutputStream;
import java.util.ArrayList;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class PdfGenerator {
	
	private int nrCol;//numarul coloanelor
	private ArrayList<String> title;//titlurile coloanelor
	private ArrayList<String> cells;//continutul celulelor
	private String name;//numele pdf-ului

	
     public PdfGenerator(String underStock, String name) {//se genereaza un pdf cu un anumit mesaj  
    	 this.name=name;
    	 
    	try {
    		Document document = new Document();
    		PdfWriter.getInstance(document, new FileOutputStream(name+".pdf"));
			document.open();
			Chunk chunk = new Chunk(underStock);
			document.add(chunk);
			document.close();
    	}
		catch(Exception e) {
			
		}
		
		
	}
	
	
	public PdfGenerator(int nrCol, ArrayList<String> title, ArrayList<String> cells, String name ) {
		this.nrCol=nrCol;
		this.title = title;
		this.cells = cells;
		this.name=name;
		generateTable();
		
	
	}
	
	public void generateTable() {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(name+".pdf"));
			 
			document.open();
			 
			PdfPTable table = new PdfPTable(nrCol);//se genereaza un tabel
			for(String i : title) {//pe prima linie se adauga titlurile coloanelor
				table.addCell(i);
			}
			for(String i : cells) {//se adauga celulele
				table.addCell(i);
			}
			
			
			
			document.add(table);
			document.close();		
			}
		catch(Exception e) {
			
		}
	}
	
	
	
	
		
}
