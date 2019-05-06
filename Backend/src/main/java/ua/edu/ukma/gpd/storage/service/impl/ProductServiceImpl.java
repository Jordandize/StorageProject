package ua.edu.ukma.gpd.storage.service.impl;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.edu.ukma.gpd.storage.dao.ProductDao;
import ua.edu.ukma.gpd.storage.entity.Category;
import ua.edu.ukma.gpd.storage.entity.Product;
import ua.edu.ukma.gpd.storage.exception.NotUniqueValueException;
import ua.edu.ukma.gpd.storage.service.ProductService;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    public ProductDao productDao;

    @Override
    public Long add(Product product) throws Exception {
        try {
            Product exists = getByName(product.getName());
            if (exists == null) {
                return productDao.create(product);
            } else {
              throw new NotUniqueValueException("Product", "name", product.getName());
            }
        } catch (Exception e) {
        	throw new Exception("Exeption occured in ProductServiceImpl: operation add [" + product + "] failed.", e);
        }
    }
    
    @Override
    public boolean update(Product product) throws Exception {
    	 try {
             return productDao.update(product);
         } catch (EmptyResultDataAccessException e){
             return false;
        } catch (Exception e) {
        	throw new Exception("Exeption occured in ProductServiceImpl: operation update [" + product + "] failed.", e);
        }
    }
    @Override
    public boolean delete(Product product) throws Exception {
    	 try {
             return productDao.delete(product);
         } catch (EmptyResultDataAccessException e){
             return false;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new Exception("Exeption occured in ProductServiceImpl: operation delete [" + product + "] failed.", e);
        }
    }

    @Override
    public Product getById(Long id) throws Exception {
        try {
            return productDao.findById(id);
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (Exception e){
            throw new Exception("Exeption occured in ProductServiceImpl: operation getById [" + id + "] failed.", e);
        }
    }

	@Override
	public Product getByName(String name) throws Exception {
        try {
            return productDao.findByName(name);
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (Exception e){
            throw new Exception("Exeption occured in ProductServiceImpl: operation getByName [" + name + "] failed.", e);
        }
	}

    @Override
    public List<Product> getAll() throws Exception{
        try {
            return productDao.findAll();
        } catch (Exception e){
            throw new Exception("ProductServiceImpl: Get all products operation failed", e);
        }
    }
    
    @Override
    public List<Product> getByCategory(Category category) throws Exception {
        try {
            return productDao.findByCategory(category);
        } catch (Exception e){
            throw new Exception("ProductServiceImpl: Get all products operation failed", e);
        }
    }

	@Override
	public List<Product> getAllPresented() throws Exception {
        try {
            return productDao.findAllPresent();
        } catch (Exception e){
            throw new Exception("ProductServiceImpl: Get all products operation failed", e);
        }
	}

	@Override
	public List<Product> getAllNotPresented() throws Exception {
        try {
            return productDao.findAllNotPresent();
        } catch (Exception e){
            throw new Exception("ProductServiceImpl: Get all products operation failed", e);
        }
	}

	@Override
	public List<Product> getAllEnds(int quantity) throws Exception {
        try {
            return productDao.findAllEnds(quantity);
        } catch (Exception e){
            throw new Exception("ProductServiceImpl: Get all products operation failed", e);
        }
        
     
	}

	@Override
	public void createAdminReport() throws Exception {
		// TODO Auto-generated method stub
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Products sheet");
        List<Product> list = productDao.findAll();
        int rownum = 0;
        Cell cell;
        Row row;
		HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        row = sheet.createRow(rownum);
        
        // EmpNo
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("ProductId");
        cell.setCellStyle(style);
        // EmpName
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("ProductName");
        cell.setCellStyle(style);
        // Salary
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Amount");
        cell.setCellStyle(style);
        // Grade
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Description");
        cell.setCellStyle(style);

 
        // Data
        for (Product emp : list) {
            rownum++;
            row = sheet.createRow(rownum);
 
            // EmpNo (A)
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(emp.getId());
            // EmpName (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(emp.getName());
            // Salary (C)
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(emp.getAmount());
            // Grade (D)
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(emp.getDescription());

        }

 
        FileOutputStream outFile = new FileOutputStream("adminReport.xls");
        workbook.write(outFile);
        System.out.println("Created file: " + File.listRoots());
	}

	@Override
	public void createAdminReportAvailability() throws Exception {
		// TODO Auto-generated method stub
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Products sheet");
        List<Product> list = productDao.findAllPresent();
        int rownum = 0;
        Cell cell;
        Row row;
		HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        row = sheet.createRow(rownum);
        // Title 1
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("All present");
        cell.setCellStyle(style);
        rownum++;
        row = sheet.createRow(rownum);
        // EmpNo
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("ProductId");
        cell.setCellStyle(style);
        // EmpName
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("ProductName");
        cell.setCellStyle(style);
        // Salary
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Amount");
        cell.setCellStyle(style);
        // Grade
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Description");
        cell.setCellStyle(style);

 
        // Data
        for (Product emp : list) {
            rownum++;
            row = sheet.createRow(rownum);
 
            // EmpNo (A)
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(emp.getId());
            // EmpName (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(emp.getName());
            // Salary (C)
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(emp.getAmount());
            // Grade (D)
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(emp.getDescription());

        }
        
        rownum++;
        row = sheet.createRow(rownum);
     // Title 2
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("All not presented");
        cell.setCellStyle(style);
 
        list = productDao.findAllNotPresent();
        rownum++;
        row = sheet.createRow(rownum);
        // EmpNo
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("ProductId");
        cell.setCellStyle(style);
        // EmpName
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("ProductName");
        cell.setCellStyle(style);
        // Salary
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Amount");
        cell.setCellStyle(style);
        // Grade
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Description");
        cell.setCellStyle(style);

 
        // Data
        for (Product emp : list) {
            rownum++;
            row = sheet.createRow(rownum);
 
            // EmpNo (A)
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(emp.getId());
            // EmpName (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(emp.getName());
            // Salary (C)
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(emp.getAmount());
            // Grade (D)
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(emp.getDescription());

        }
        
        rownum++;
        row = sheet.createRow(rownum);
     // Title 3
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("All that will end up soon. ");
        cell.setCellStyle(style);
 
        list = productDao.findAllEnds(5);
        rownum++;
        row = sheet.createRow(rownum);
        // EmpNo
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("ProductId");
        cell.setCellStyle(style);
        // EmpName
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("ProductName");
        cell.setCellStyle(style);
        // Salary
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Amount");
        cell.setCellStyle(style);
        // Grade
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Description");
        cell.setCellStyle(style);

 
        // Data
        for (Product emp : list) {
            rownum++;
            row = sheet.createRow(rownum);
 
            // EmpNo (A)
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(emp.getId());
            // EmpName (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(emp.getName());
            // Salary (C)
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(emp.getAmount());
            // Grade (D)
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(emp.getDescription());

        }
 
        FileOutputStream outFile = new FileOutputStream("adminReport2.xls");
        workbook.write(outFile);
        System.out.println("Created file: " + File.listRoots());
     
	}


}
