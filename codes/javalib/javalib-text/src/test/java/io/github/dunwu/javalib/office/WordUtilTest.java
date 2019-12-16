package io.github.dunwu.javalib.office;

import org.junit.Test;

import java.io.IOException;

/**
 * @author Zhang Peng
 * @since 2018-11-08
 */
public class WordUtilTest {

    @Test
    public void testCreateDocx() {
        try {
            WordUtil.create("d://temp.docx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateDocxWithContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("At tutorialspoint.com, we strive hard to ");
        sb.append("provide quality tutorials for self-learning ");
        sb.append("purpose in the domains of Academics, Information ");
        sb.append("Technology, Management and Computer Programming Languages.");

        try {
            WordUtil.create("d://temp2.docx", sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateDocxWithBorder() {
        StringBuilder sb = new StringBuilder();
        sb.append("At tutorialspoint.com, we strive hard to ");
        sb.append("provide quality tutorials for self-learning ");
        sb.append("purpose in the domains of Academics, Information ");
        sb.append("Technology, Management and Computer Programming Languages.");

        try {
            WordUtil.createWithBorders("d://temp3.docx", sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateDocxWithTable() {
        try {
            WordUtil.createWithTable("d://temp4.docx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateDocxWithFontStyle() {
        try {
            WordUtil.createWithFontStyle("d://temp5.docx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateDocxWithAlign() {
        try {
            WordUtil.createWithAlign("d://temp6.docx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExtractor() {
        try {
            WordUtil.extractor("d://temp6.docx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        try {
            WordUtil.setDocxProperties("d://temp6.docx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // @Test
    // public void test2() {
    // 	File dir = new File("D:\\Docs\\ZP\\notes\\软件工程\\软件工程文档标准模板");
    // 	File[] files = dir.listFiles();
    // 	for (File file : files) {
    // 		if (!file.isDirectory()) {
    // 			try {
    // 				WordUtil.setDocProperties(file.getAbsolutePath());
    // 			} catch (IOException e) {
    // 				e.printStackTrace();
    // 			}
    // 		}
    // 	}
    // }
}
