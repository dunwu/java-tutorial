package io.github.dunwu.javalib.office;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Zhang Peng
 * @see <a href="https://poi.apache.org/">https://poi.apache.org/</a>
 * @see <a href= "https://www.w3cschool.cn/apache_poi_word/apache_poi_word_overview.html">https://www.w3cschool.cn/apache_poi_word/apache_poi_word_overview.html</a>
 * @since 2018-11-08
 */
public class WordUtil {

    /**
     * 创建空白文档
     *
     * @param filename
     * @throws IOException
     */
    public static void create(String filename) throws IOException {
        // Blank Document
        XWPFDocument document = new XWPFDocument();
        // Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File(filename));
        document.write(out);
        out.close();
        System.out.printf("create %s written successully\n", filename);
    }

    /**
     * 创建 *.docx 文档，包含 content 内容
     *
     * @param filename
     * @throws IOException
     */
    public static void create(String filename, String content) throws IOException {
        // Blank Document
        XWPFDocument document = new XWPFDocument();
        // Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File(filename));

        // create Paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(content);
        document.write(out);
        out.close();
        System.out.printf("create %s written successully\n", filename);
    }

    /**
     * 创建 *.docx 文档，包含 content 内容，content 内容置于边框中
     *
     * @param filename
     * @throws IOException
     */
    public static void createWithBorders(String filename, String content) throws IOException {
        // Blank Document
        XWPFDocument document = new XWPFDocument();

        // Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File(filename));

        // create paragraph
        XWPFParagraph paragraph = document.createParagraph();

        // Set bottom border to paragraph
        paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);

        // Set left border to paragraph
        paragraph.setBorderLeft(Borders.BASIC_BLACK_DASHES);

        // Set right border to paragraph
        paragraph.setBorderRight(Borders.BASIC_BLACK_DASHES);

        // Set top border to paragraph
        paragraph.setBorderTop(Borders.BASIC_BLACK_DASHES);

        XWPFRun run = paragraph.createRun();
        run.setText(content);

        document.write(out);
        out.close();
        System.out.printf("create %s written successully\n", filename);
    }

    /**
     * 表格
     *
     * @param filename
     * @throws IOException
     */
    public static void createWithTable(String filename) throws IOException {
        // Blank Document
        XWPFDocument document = new XWPFDocument();

        // Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File(filename));

        // create table
        XWPFTable table = document.createTable();
        // create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("col one, row one");
        tableRowOne.addNewTableCell().setText("col two, row one");
        tableRowOne.addNewTableCell().setText("col three, row one");
        // create second row
        XWPFTableRow tableRowTwo = table.createRow();
        tableRowTwo.getCell(0).setText("col one, row two");
        tableRowTwo.getCell(1).setText("col two, row two");
        tableRowTwo.getCell(2).setText("col three, row two");
        // create third row
        XWPFTableRow tableRowThree = table.createRow();
        tableRowThree.getCell(0).setText("col one, row three");
        tableRowThree.getCell(1).setText("col two, row three");
        tableRowThree.getCell(2).setText("col three, row three");

        document.write(out);
        out.close();
        System.out.printf("create %s written successully\n", filename);
    }

    /**
     * 字体样式
     *
     * @param filename
     * @throws IOException
     */
    public static void createWithFontStyle(String filename) throws IOException {
        // Blank Document
        XWPFDocument document = new XWPFDocument();

        // Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File(filename));

        // create paragraph
        XWPFParagraph paragraph = document.createParagraph();

        // Set Bold an Italic
        XWPFRun paragraphOneRunOne = paragraph.createRun();
        paragraphOneRunOne.setBold(true);
        paragraphOneRunOne.setItalic(true);
        paragraphOneRunOne.setText("Font Style");
        paragraphOneRunOne.addBreak();

        // Set text Position
        XWPFRun paragraphOneRunTwo = paragraph.createRun();
        paragraphOneRunTwo.setText("Font Style two");
        paragraphOneRunTwo.setTextPosition(100);

        // Set Strike through and Font Size and Subscript
        XWPFRun paragraphOneRunThree = paragraph.createRun();
        paragraphOneRunThree.setStrike(true);
        paragraphOneRunThree.setFontSize(20);
        paragraphOneRunThree.setSubscript(VerticalAlign.SUBSCRIPT);
        paragraphOneRunThree.setText(" Different Font Styles");

        document.write(out);
        out.close();
        System.out.printf("create %s written successully\n", filename);
    }

    /**
     * 对齐方式
     *
     * @param filename
     * @throws IOException
     */
    public static void createWithAlign(String filename) throws IOException {
        // Blank Document
        XWPFDocument document = new XWPFDocument();

        // Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File(filename));

        // create paragraph
        XWPFParagraph paragraph = document.createParagraph();

        // Set alignment paragraph to RIGHT
        paragraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun run = paragraph.createRun();
        run.setText("At tutorialspoint.com, we strive hard to " + "provide quality tutorials for self-learning "
            + "purpose in the domains of Academics, Information "
            + "Technology, Management and Computer Programming " + "Languages.");

        // Create Another paragraph
        paragraph = document.createParagraph();

        // Set alignment paragraph to CENTER
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run = paragraph.createRun();
        run.setText("The endeavour started by Mohtashim, an AMU "
            + "alumni, who is the founder and the managing director "
            + "of Tutorials Point (I) Pvt. Ltd. He came up with the "
            + "website tutorialspoint.com in year 2006 with the help"
            + "of handpicked freelancers, with an array of tutorials" + " for computer programming languages. ");
        document.write(out);
        out.close();
        System.out.printf("create %s written successully\n", filename);
    }

    /**
     * 文本提取
     *
     * @param filename
     * @throws IOException
     */
    public static void extractor(String filename) throws IOException {
        XWPFDocument docx = new XWPFDocument(new FileInputStream(filename));
        // using XWPFWordExtractor Class
        XWPFWordExtractor we = new XWPFWordExtractor(docx);
        System.out.println(we.getText());
    }

    public static void setDocxProperties(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filename));
        XWPFDocument doc = new XWPFDocument(fis);

        POIXMLProperties properties = doc.getProperties();
        POIXMLProperties.CoreProperties coreProperties = properties.getCoreProperties();
        coreProperties.setCreator("星环信息科技有限公司");
        coreProperties.setLastModifiedByUser("星环信息科技有限公司");
        POIXMLProperties.ExtendedProperties extendedProperties = properties.getExtendedProperties();
        extendedProperties.getUnderlyingProperties().setCompany("星环信息科技有限公司");

        FileOutputStream fos = new FileOutputStream(new File(filename));
        doc.write(fos);

        fos.close();
        doc.close();
        fis.close();
    }

    public static void setDocProperties(String filename) throws IOException {
        System.out.println("filename = [" + filename + "]");
        FileInputStream fis = new FileInputStream(new File(filename));
        HWPFDocument doc = new HWPFDocument(fis);

        SummaryInformation summaryInformation = doc.getSummaryInformation();
        summaryInformation.setAuthor("张鹏");
        summaryInformation.setLastAuthor("张鹏");
        DocumentSummaryInformation documentSummaryInformation = doc.getDocumentSummaryInformation();
        documentSummaryInformation.setCompany("张鹏");
        documentSummaryInformation.setDocumentVersion("1");

        FileOutputStream fos = new FileOutputStream(new File(filename));
        doc.write(fos);

        fos.close();
        doc.close();
        fis.close();
    }

}
