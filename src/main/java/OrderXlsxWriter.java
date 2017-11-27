/**
 * Created by wangdong on 2017/10/27.
 */


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import entity.Order;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class OrderXlsxWriter {

    protected File file;
    protected OutputStream os;
    protected Workbook book = null;
    public OrderXlsxWriter() {
        super();
    }


    public OrderXlsxWriter(File file) throws IOException, InvalidFormatException {
        super();
        this.file = file;
        if(!file.exists()) {
            file.createNewFile();
        }
        os = new FileOutputStream(file);
        book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Order");

        String[] title = {"supplyname","orderdate","provinceorgname","ordercode","orgname","taxamount","materialcode","materialname"};
        Row titleRow = sheet.createRow(0);
        for(int i = 0; i < title.length; i++) {
            Cell cell = titleRow.createCell(i + 1);
            cell.setCellValue(title[i]);
        }
    }
    public void Write(Order order) throws IOException {
        Sheet sheet = book.getSheet("Order");
        int lastRowNum = sheet.getLastRowNum();
        Row currentRow = sheet.createRow(lastRowNum + 1);
        currentRow.createCell(0).setCellFormula("ROW() - 1");
        currentRow.createCell(1).setCellValue(order.getSupplyname());
        currentRow.createCell(2).setCellValue(order.getOrderdate());
        currentRow.createCell(3).setCellValue(order.getProvinceorgname());
        currentRow.createCell(4).setCellValue(order.getOrdercode());
        currentRow.createCell(5).setCellValue(order.getOrgname());
        currentRow.createCell(6).setCellValue(order.getTaxamount());
        currentRow.createCell(7).setCellValue(order.getMaterialcode());
        currentRow.createCell(8).setCellValue(order.getMaterialname());

    }

    public void Write(Collection<Order> orders) throws IOException {
        for(Order u : orders) {
            this.Write(u);
        }
    }

    public void Write(Order... orders) throws IOException {
        for(Order u : orders) {
            this.Write(u);
        }
    }

    public void Extract() throws IOException {
        book.write(os);
        book.close();
    }

}
