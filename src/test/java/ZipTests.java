import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipTests {
    private ClassLoader cl = ZipTests.class.getClassLoader();

    @Test
    void pdfZip () throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                //Assertions.assertTrue(entry.getName().contains("readme.pdf"));
                if (entry.getName().contains("readme.pdf"));
                PDF pdf = new PDF(zis);
                Assertions.assertTrue(pdf.text.contains("Information for Viewing Aircraft Records"));
            }

    }
}}
