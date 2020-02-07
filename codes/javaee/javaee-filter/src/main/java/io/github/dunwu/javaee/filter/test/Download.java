package io.github.dunwu.javaee.filter.test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class Download {

	public static void main(String[] args) throws Exception {
		System.out.println(getContent("http://localhost:8080/filter/book/thinkInJava.xml"));
	}

	public static String getContent(String url) throws Exception {

		URL r = new URL(url);

		r.openConnection();

		InputStream ins = r.openStream();

		Reader reader = new InputStreamReader(ins);

		int len = 0;
		char[] tmp = new char[1024];

		StringBuffer buffer = new StringBuffer();

		while ((len = reader.read(tmp)) != -1) {
			buffer.append(tmp, 0, len);
		}

		return buffer.toString();
	}

}
