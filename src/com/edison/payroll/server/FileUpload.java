package com.edison.payroll.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.edison.payroll.data.TemplateText;

public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(FileUpload.class
			.getName());
	private TemplateText template;

	public FileUpload() {
		template = new TemplateText();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			ServletFileUpload upload = new ServletFileUpload();
			res.setContentType("text/html");
			FileItemIterator iterator = upload.getItemIterator(req);
			while (iterator.hasNext()) {
				FileItemStream item = iterator.next();
				InputStream stream = item.openStream();
				if (item.isFormField()) {
					log.warning("Got a form field: " + item.getFieldName());
				} else {
					log.warning("Got an uploaded file: " + item.getFieldName()
							+ ", name = " + item.getName());
					if (stream != null && stream.available() > 0) {
						processExcelFile(stream);
						res.getWriter()
								.println(
										template.getTemplate(this,
												"<h1>Completed</h1>"));
					} else {
						res.getWriter().println(
								template.getTemplate(this,
										"<h1>Please upload a file</h1>"));
					}
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

	}

	private int processExcelFile(InputStream file) {

		int count = 0;
		try {
			// Create a workbook using the File System
			XSSFWorkbook myWorkBook = new XSSFWorkbook(file);

			// Get the first sheet from workbook
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);

			/** We now need something to iterate through the cells. **/
			Iterator<Row> rowIter = mySheet.rowIterator();
			while (rowIter.hasNext()) {
				XSSFRow myRow = (XSSFRow) rowIter.next();
				Iterator<Cell> cellIter = myRow.cellIterator();
				while (cellIter.hasNext()) {

					XSSFCell myCell = (XSSFCell) cellIter.next();
					// get cell index
					System.out.println("Cell column index: "
							+ myCell.getColumnIndex());
					// get cell type
					System.out.println("Cell Type: " + myCell.getCellType());

					// get cell value
					switch (myCell.getCellType()) {
					case XSSFCell.CELL_TYPE_NUMERIC:
						System.out.println("Cell Value: "
								+ myCell.getNumericCellValue());
						break;
					case XSSFCell.CELL_TYPE_STRING:
						System.out.println("Cell Value: "
								+ myCell.getStringCellValue());
						break;
					default:
						System.out.println("Cell Value: "
								+ myCell.getRawValue());
						break;
					}
					System.out.println("---");

					if (myCell.getColumnIndex() == 0) {
						count++;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(count);
		return count;

	}
}
