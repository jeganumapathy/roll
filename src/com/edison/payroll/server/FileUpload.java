package com.edison.payroll.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.edison.payroll.client.HashConverter;
import com.edison.payroll.data.EmployeeData;
import com.edison.payroll.data.TemplateText;
import com.edison.payroll.server.service.StoreFactory;

public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(FileUpload.class
			.getName());
	private TemplateText template;
	private HashConverter converter;

	public FileUpload() {
		template = new TemplateText();
		converter = new HashConverter();
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
						res.getWriter()
								.println(
										template.getTemplate(this,
												"<h1>Please select a file to upload</h1>"));
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
				temp = new HashMap<Integer, String>();
				while (cellIter.hasNext()) {
					XSSFCell myCell = (XSSFCell) cellIter.next();
					parseData(myCell);
					// get cell index
				}
				list.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		printAllValue();
		return count;
	}

	public void parseData(XSSFCell cell) {
		int index = cell.getColumnIndex();
		String value = getValue(cell);
		if (firstIndex && index == 0 && firstIndexCount != 0) {
			firstIndex = false;
		}
		if (firstIndex) {
			coloum.put(index, value);
			firstIndexCount++;
		} else {
			temp.put(index, value);
		}
	}

	public void printAllValue() {
		converter.setHeaderHash(coloum);
		converter.iterate();
		for (HashMap<Integer, String> temp : list) {
			EmployeeData data = converter.convertHash(temp);
			StoreFactory.store(data);
		}
	}

	public boolean firstIndex = true;
	public int firstIndexCount = 0;

	public String getValue(XSSFCell cell) {
		String value = new String();
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_NUMERIC:
			double doubleValue = cell.getNumericCellValue();
			value = String.valueOf(doubleValue);
			break;
		case XSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		default:
			value = cell.getRawValue();
			break;
		}
		return value;
	}

	HashMap<Integer, String> coloum = new HashMap<Integer, String>();
	HashMap<Integer, String> temp = new HashMap<Integer, String>();
	ArrayList<HashMap<Integer, String>> list = new ArrayList<HashMap<Integer, String>>();
}
