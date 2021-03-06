package kr.co.codingmonkey.sample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.codingmonkey.domain.BoardVo;
import kr.co.codingmonkey.domain.Criteria;
import kr.co.codingmonkey.mapper.BoardMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) //메인메서드 없이 실행
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j //롬복
public class Sample2Tests {
	@Setter @Autowired
	private BoardMapper mapper;
	
	@Test
	public void testGetListPaging() {
		Criteria cri = new Criteria();
//		cri.setType("TC");
//		cri.setKeyword("고양이");
		
		mapper.getListWithPaging(cri).forEach(log::info);
	}
	
	public static Map<String, Object> extendsBeanUtils(Object bean) {
		Map<String, Object> map = null;
		try {
			PropertyUtils.describe(bean);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	@Test
	public void testBeanUtils() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<BoardVo> list = mapper.getListWithPaging(new Criteria());
		List<Map<String, Object>> result = mapper.getListWithPaging(new Criteria()).stream().map(Sample2Tests::extendsBeanUtils).collect(Collectors.toList());
		List<String> mapNames = Arrays.asList(new String[] {"bno", "title", "writer", "regDate"});

		mapper.getListWithPaging(new Criteria())
		.stream().map(Sample2Tests::extendsBeanUtils).collect(Collectors.toList()).forEach(map->{
			mapNames.forEach(name-> log.info(map.get(name)));
		});
		
	}
	
	@Test
	public void testCreateExcelByBoard() throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<Map<String, Object>> list = mapper.getListWithPaging(new Criteria())
				.stream().map(Sample2Tests::extendsBeanUtils).collect(Collectors.toList());

		List<String> mapNames = Arrays.asList(new String[] {"bno", "title", "writer", "regDate"});
		
//		Map<String, String> map = BeanUtils.describe(list.get(0));
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("board Sheet");
		
		Cell cell = getCell(sheet, 0, 0);
		cell.setCellValue("글번호");
		
		cell = getCell(sheet, 0, 1);
		cell.setCellValue("제목");
		
		cell = getCell(sheet, 0, 2);
		cell.setCellValue("작성자");
		
		cell = getCell(sheet, 0, 3);
		cell.setCellValue("작성일");

		for(int i = 0; i<list.size(); i++) {
			for(int j=0; j<mapNames.size(); j++) {
				cell = getCell(sheet, i+1, j);
				cell.setCellValue(list.get(i).get(mapNames.get(j)).toString());
				if(j==3) {
					CellStyle style = workbook.createCellStyle();
					style.setDataFormat(workbook.createDataFormat().getFormat("yy/m/dd hh:mm"));
					cell.setCellStyle(style);
				}
				
				
			}
			
//			cell = getCell(sheet, i+1, 1);
//			cell.setCellValue(list.get(0).get(mapNames.get(1)));
//			
//			cell = getCell(sheet, i+1, 2);
//			cell.setCellValue(list.get(0).getWriter());
//			
//			cell = getCell(sheet, i+1, 3);
//			cell.setCellValue(list.get(0).getRegDate());
//			CellStyle style = workbook.createCellStyle();
//			style.setDataFormat(workbook.createDataFormat().getFormat("yy/MMdd hh:mm"));
//			cell.setCellStyle(style);
		}
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		
		FileOutputStream stream = new FileOutputStream("C://Users//chyn1//OneDrive//Desktop//workspace//poi//board2.xlsx");
		workbook.write(stream);
		
		
	}
	
	@Test
	public void TestCreateExcel() throws IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("TEST Sheet");
		
		Cell cell = getCell(sheet, 0, 0);
		cell.setCellValue("Test Value");
		
		cell = getCell(sheet, 0 , 1);
		cell.setCellValue(100);
		
		cell = getCell(sheet, 0, 2);
		cell.setCellValue(Calendar.getInstance());
		
		CellStyle style = workbook.createCellStyle();
		style.setDataFormat(workbook.createDataFormat().getFormat("yy/MMdd hh:mm"));
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.TOP);
		style.setFillBackgroundColor(IndexedColors.BLUE.index);
		Font font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.index);
		cell.setCellStyle(style);
		
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		
		cell = getCell(sheet, 1, 0);
		cell.setCellValue(1);
		cell = getCell(sheet, 1, 1);
		cell.setCellValue(2);
		cell = getCell(sheet, 1, 2);
		cell.setCellFormula("SUM(A2:B2)");
		
		FileOutputStream stream = new FileOutputStream("C://Users//chyn1//OneDrive//Desktop//workspace//poi//test.xlsx");
		workbook.write(stream);
		workbook.close();
	}

//	@Test
//	public void testReadExcel() throws IOException {
//		FileInputStream stream = new FileInputStream("C://Users//chyn1//OneDrive//Desktop//workspace//poi//score.xlsx");
//		Workbook workbook = new XSSFWorkbook(stream);
//		Sheet sheet = workbook.getSheetAt(0);
//		List<String> mapNames = new ArrayList<>();
//		
//		for(int i=0; i<mapNames.size() ; i++) {
//			String name = getCell(sheet, 0, 6).getStringCellValue();
//			if(name.equals("")) break;
//			mapNames.add(name);
//		}
//		log.info(mapNames);
//		
//		
//		List<Map<String, Object>> list = new ArrayList<>();
//		log.info(getCell(sheet, 10, 10).getCellType());
//		
//		outer:
//		for(int j = 0; j<mapNames.size() ; j++) {
//			Map<String, Object> map = new HashMap<>();
//			if(getCell(sheet)) { }
//			CellType type = getCell(sheet, 1, j).getCellType();
//			switch (type) {
//			case NUMERIC: case FORMULA:
//				map.put(mapNames.get(j), (int)getCell(sheet, i+1, j).getNumericCellValue());
//				break;
//			case STRING:
//				map.put(mapNames.get(j), getCell(sheet, i+1, j).getStringCellValue());
//				break;
//			
//			default:
//				break;
//			}
//		}
//		list.add(map);
//	}
//	list.forEach();
//			
//			String value = getCell(sheet, 1, j).getStringCellValue();
//			map.put(mapNames.get(j), value);
//		}
//		
//		double d = getCell(sheet, 1,0).getNumericCellValue();
//		log.info(getCell(sheet, 1, 0).getCellType());
//		log.info(d);
//	}
	
	public Row getRow(Sheet sheet, int rownum) {
		Row row = sheet.getRow(rownum);
		if(row==null) {
			row = sheet.createRow(rownum);
		}
		return row;
	}
	public Cell getCell(Row row, int cellnum) {
		Cell cell = row.getCell(cellnum);
		if(cell == null) {
			cell = row.createCell(cellnum);
		}
		return cell;
	}
	
	
	public Cell getCell(Sheet sheet, int rownum, int cellnum) {
		Row row = getRow(sheet, rownum);
		return getCell(row, cellnum);
	}

}
