package p1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class BasicApi {

	public void writeToExcel(String jsonAsString) {
		try {
			File file = new File(System.getProperty("user.dir") + "\\res\\Data.xlsx");
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);

			Sheet sheet = wb.getSheetAt(0);
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			Row row = sheet.getRow(0);
			Row newRow = null;
			String listAry[] = { "proposed_use", "existing_construction_type", "status_date",
					"permit_expiration_date" + "proposed_units", "proposed_construction_type", "permit_number",
					"description", "revised_cost", "street_name", "supervisor_district", "street_suffix", "lot",
					"filed_date", "issued_date", "plansets", "block" + "number_of_existing_stories",
					"number_of_proposed_stories", "neighborhoods_analysis_boundaries", "permit_type_definition",
					"permit_type", "permit_creation_date", "zipcode", "record_id",
					"proposed_construction_type_description", "estimated_cost", "street_number", "existing_use",
					"existing_units", "location", "status", "existing_construction_type_description" };
			JSONArray ja = (JSONArray) new JSONParser().parse(jsonAsString);
			Iterator itr2 = ja.iterator();
			HashMap hm;

			for (int k = 0; k < ja.size(); k++) {
				hm = (HashMap) ja.get(k);
				if (k != 0) {
					newRow = sheet.createRow(rowCount + 1 + k);
				} else {
					newRow = sheet.createRow(rowCount + 1);
				}
				for (int j = 0; j < listAry.length; j++) {
					Cell cell = newRow.createCell(j);
					// System.out.println("val-"+hm.get(listAry[j]));
					cell.setCellValue(hm.get(listAry[j]) + "");
				}

			}
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(file);
			wb.write(outputStream);
			outputStream.close();
		}

		catch (Exception e) {

			e.printStackTrace();
		}
	}

}
