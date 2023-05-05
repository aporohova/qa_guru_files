import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipTests {
    private ClassLoader cl = ZipTests.class.getClassLoader();

    @Test
    @DisplayName("Проверка pdf файла")
    void pdfZip() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains("readme.pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("Information for Viewing Aircraft Records"));
                }

            }
        }
    }

    @Test
    @DisplayName("Проверка xlsx файла")
    void xlsxZip() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("Free_Test_Data_100KB_XLSX.xlsx")) {
                    XLS xls = new XLS(zis);
                    Assertions.assertEquals("Great Britain", xls.excel.getSheetAt(0).getRow(4).getCell(5).getStringCellValue());
                    Assertions.assertEquals("Shennice", xls.excel.getSheetAt(0).getRow(5).getCell(1).getStringCellValue());
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка csv файла")
    void csvZip() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("Book2.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertArrayEquals(new String[] {"10","738b5aDe6B1C6A5","Gaines Inc","http://sandoval-hooper.com/","Uzbekistan","Multi-lateral scalable protocol","1997","Outsourcing / Offshoring","9698"}, content.get(3));
                }
            }

    }
}}
