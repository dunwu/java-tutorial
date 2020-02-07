package io.github.dunwu.javaee.jsp.util.ip;

/*
 * LumaQQ - Java QQ Client
 *
 * Copyright (C) 2004 luma <stubma@163.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * <pre>
 * ������ȡQQwry.dat�ļ����Ը���ip��ú���λ�ã�QQwry.dat�ĸ�ʽ��
 * һ. �ļ�ͷ����8�ֽ�
 * 	   1. ��һ����ʼIP�ľ���ƫ�ƣ� 4�ֽ�
 *     2. ���һ����ʼIP�ľ���ƫ�ƣ� 4�ֽ�
 * ��. "������ַ/����/����"��¼��
 *     ���ֽ�ip��ַ�����ÿһ����¼�ֳ���������
 *     1. ���Ҽ�¼
 *     2. ������¼
 *     ���ǵ�����¼�ǲ�һ���еġ����ҹ��Ҽ�¼�͵�����¼����������ʽ
 *     1. ��0�������ַ���
 *     2. 4���ֽڣ�һ���ֽڿ���Ϊ0x1��0x2
 * 		  a. Ϊ0x1ʱ����ʾ�ھ���ƫ�ƺ󻹸���һ������ļ�¼��ע���Ǿ���ƫ��֮�󣬶��������ĸ��ֽ�֮��
 *        b. Ϊ0x2ʱ����ʾ�ھ���ƫ�ƺ�û�������¼
 *        ����Ϊ0x1����0x2���������ֽڶ���ʵ�ʹ��������ļ��ھ���ƫ��
 * 		  ����ǵ�����¼��0x1��0x2�ĺ��岻����������������������ֽڣ�Ҳ�϶��Ǹ���3���ֽ�ƫ�ƣ��������
 *        ��Ϊ0��β�ַ���
 * ��. "��ʼ��ַ/������ַƫ��"��¼��
 *     1. ÿ����¼7�ֽڣ�������ʼ��ַ��С��������
 *        a. ��ʼIP��ַ��4�ֽ�
 *        b. ����ip��ַ�ľ���ƫ�ƣ�3�ֽ�
 *
 * ע�⣬����ļ����ip��ַ�����е�ƫ����������little-endian��ʽ����java�ǲ���
 * big-endian��ʽ�ģ�Ҫע��ת��
 * </pre>
 *
 * @author �����
 */
public class IPSeeker {

	private static final File IP_FILE = new File(IPSeeker.class.getResource("").getFile(), "QQWry.dat");

	// һЩ�̶������������¼���ȵȵ�
	private static final int IP_RECORD_LENGTH = 7;

	private static final byte AREA_FOLLOWED = 0x01;

	private static final byte NO_AREA = 0x2;

	// ��һģʽʵ��
	private static IPSeeker instance = new IPSeeker();

	// ������Ϊcache����ѯһ��ipʱ���Ȳ鿴cache���Լ��ٲ���Ҫ���ظ�����
	private Hashtable ipCache;

	// ����ļ�������
	private RandomAccessFile ipFile;

	// �ڴ�ӳ���ļ�
	private MappedByteBuffer mbb;

	// ��ʼ�����Ŀ�ʼ�ͽ����ľ���ƫ��
	private long ipBegin, ipEnd;

	// Ϊ���Ч�ʶ����õ���ʱ����
	private IPLocation loc;

	private byte[] buf;

	private byte[] b4;

	private byte[] b3;

	/**
	 * ˽�й��캯��
	 */
	private IPSeeker() {
		ipCache = new Hashtable();
		loc = new IPLocation();
		buf = new byte[100];
		b4 = new byte[4];
		b3 = new byte[3];
		try {
			ipFile = new RandomAccessFile(IP_FILE, "r");
		} catch (FileNotFoundException e) {
			System.out.println(IP_FILE.getAbsolutePath());
			System.out.println(IP_FILE);
			System.out.println("IP��ַ��Ϣ�ļ�û���ҵ���IP��ʾ���ܽ��޷�ʹ��");
			ipFile = null;
		}
		// ������ļ��ɹ�����ȡ�ļ�ͷ��Ϣ
		if (ipFile != null) {
			try {
				ipBegin = readLong4(0);
				ipEnd = readLong4(4);
				if (ipBegin == -1 || ipEnd == -1) {
					ipFile.close();
					ipFile = null;
				}
			} catch (IOException e) {
				System.out.println("IP��ַ��Ϣ�ļ���ʽ�д���IP��ʾ���ܽ��޷�ʹ��");
				ipFile = null;
			}
		}
	}

	/**
	 * ��offsetλ�ö�ȡ4���ֽ�Ϊһ��long����ΪjavaΪbig-endian��ʽ������û�취 ������ôһ����������ת��
	 *
	 * @param offset
	 * @return ��ȡ��longֵ������-1��ʾ��ȡ�ļ�ʧ��
	 */
	private long readLong4(long offset) {
		long ret = 0;
		try {
			ipFile.seek(offset);
			ret |= (ipFile.readByte() & 0xFF);
			ret |= ((ipFile.readByte() << 8) & 0xFF00);
			ret |= ((ipFile.readByte() << 16) & 0xFF0000);
			ret |= ((ipFile.readByte() << 24) & 0xFF000000);
			return ret;
		} catch (IOException e) {
			return -1;
		}
	}

	/**
	 * @return ��һʵ��
	 */
	public static IPSeeker getInstance() {
		return instance;
	}

	/**
	 * ����һ���ص�Ĳ���ȫ���֣��õ�һϵ�а���s�Ӵ���IP��Χ��¼
	 *
	 * @param s �ص��Ӵ�
	 * @return ����IPEntry���͵�List
	 */
	public List getIPEntriesDebug(String s) {
		List ret = new ArrayList();
		long endOffset = ipEnd + 4;
		for (long offset = ipBegin + 4; offset <= endOffset; offset += IP_RECORD_LENGTH) {
			// ��ȡ����IPƫ��
			long temp = readLong3(offset);
			// ���temp������-1����ȡIP�ĵص���Ϣ
			if (temp != -1) {
				IPLocation loc = getIPLocation(temp);
				// �ж��Ƿ�����ص����������s�Ӵ�����������ˣ���������¼��List�У����û�У�����
				if (loc.country.indexOf(s) != -1 || loc.area.indexOf(s) != -1) {
					IPEntry entry = new IPEntry();
					entry.country = loc.country;
					entry.area = loc.area;
					// �õ���ʼIP
					readIP(offset - 4, b4);
					entry.beginIp = Utils.getIpStringFromBytes(b4);
					// �õ�����IP
					readIP(temp, b4);
					entry.endIp = Utils.getIpStringFromBytes(b4);
					// ��Ӹü�¼
					ret.add(entry);
				}
			}
		}
		return ret;
	}

	/**
	 * ��offsetλ�ö�ȡ3���ֽ�Ϊһ��long����ΪjavaΪbig-endian��ʽ������û�취 ������ôһ����������ת��
	 *
	 * @param offset
	 * @return ��ȡ��longֵ������-1��ʾ��ȡ�ļ�ʧ��
	 */
	private long readLong3(long offset) {
		long ret = 0;
		try {
			ipFile.seek(offset);
			ipFile.readFully(b3);
			ret |= (b3[0] & 0xFF);
			ret |= ((b3[1] << 8) & 0xFF00);
			ret |= ((b3[2] << 16) & 0xFF0000);
			return ret;
		} catch (IOException e) {
			return -1;
		}
	}

	/**
	 * ����һ��ip���ҵ�����¼��ƫ�ƣ�����һ��IPLocation�ṹ
	 *
	 * @param offset
	 * @return
	 */
	private IPLocation getIPLocation(long offset) {
		try {
			// ����4�ֽ�ip
			ipFile.seek(offset + 4);
			// ��ȡ��һ���ֽ��ж��Ƿ��־�ֽ�
			byte b = ipFile.readByte();
			if (b == AREA_FOLLOWED) {
				// ��ȡ����ƫ��
				long countryOffset = readLong3();
				// ��ת��ƫ�ƴ�
				ipFile.seek(countryOffset);
				// �ټ��һ�α�־�ֽڣ���Ϊ���ʱ������ط���Ȼ�����Ǹ��ض���
				b = ipFile.readByte();
				if (b == NO_AREA) {
					loc.country = readString(readLong3());
					ipFile.seek(countryOffset + 4);
				} else {
					loc.country = readString(countryOffset);
				}
				// ��ȡ������־
				loc.area = readArea(ipFile.getFilePointer());
			} else if (b == NO_AREA) {
				loc.country = readString(readLong3());
				loc.area = readArea(offset + 8);
			} else {
				loc.country = readString(ipFile.getFilePointer() - 1);
				loc.area = readArea(ipFile.getFilePointer());
			}
			return loc;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * ��offsetλ�ö�ȡ�ĸ��ֽڵ�ip��ַ����ip�����У���ȡ���ipΪbig-endian��ʽ������ �ļ�����little-endian��ʽ���������ת��
	 *
	 * @param offset
	 * @param ip
	 */
	private void readIP(long offset, byte[] ip) {
		try {
			ipFile.seek(offset);
			ipFile.readFully(ip);
			byte temp = ip[0];
			ip[0] = ip[3];
			ip[3] = temp;
			temp = ip[1];
			ip[1] = ip[2];
			ip[2] = temp;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * �ӵ�ǰλ�ö�ȡ3���ֽ�ת����long
	 *
	 * @return
	 */
	private long readLong3() {
		long ret = 0;
		try {
			ipFile.readFully(b3);
			ret |= (b3[0] & 0xFF);
			ret |= ((b3[1] << 8) & 0xFF00);
			ret |= ((b3[2] << 16) & 0xFF0000);
			return ret;
		} catch (IOException e) {
			return -1;
		}
	}

	/**
	 * ��offsetƫ�ƴ���ȡһ����0�������ַ���
	 *
	 * @param offset
	 * @return ��ȡ���ַ����������ؿ��ַ���
	 */
	private String readString(long offset) {
		try {
			ipFile.seek(offset);
			int i;
			for (i = 0, buf[i] = ipFile.readByte(); buf[i] != 0; buf[++i] = ipFile.readByte())
				;
			if (i != 0) {
				return Utils.getString(buf, 0, i, "GBK");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	/**
	 * ��offsetƫ�ƿ�ʼ����������ֽڣ�����һ��������
	 *
	 * @param offset
	 * @return �������ַ���
	 * @throws IOException
	 */
	private String readArea(long offset) throws IOException {
		ipFile.seek(offset);
		byte b = ipFile.readByte();
		if (b == 0x01 || b == 0x02) {
			long areaOffset = readLong3(offset + 1);
			if (areaOffset == 0) {
				return "δ֪����";
			} else {
				return readString(areaOffset);
			}
		} else {
			return readString(offset);
		}
	}

	/**
	 * ����һ���ص�Ĳ���ȫ���֣��õ�һϵ�а���s�Ӵ���IP��Χ��¼
	 *
	 * @param s �ص��Ӵ�
	 * @return ����IPEntry���͵�List
	 */
	public List getIPEntries(String s) {
		List ret = new ArrayList();
		try {
			// ӳ��IP��Ϣ�ļ����ڴ���
			if (mbb == null) {
				FileChannel fc = ipFile.getChannel();
				mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, ipFile.length());
				mbb.order(ByteOrder.LITTLE_ENDIAN);
			}

			int endOffset = (int) ipEnd;
			for (int offset = (int) ipBegin + 4; offset <= endOffset; offset += IP_RECORD_LENGTH) {
				int temp = readInt3(offset);
				if (temp != -1) {
					IPLocation loc = getIPLocation(temp);
					// �ж��Ƿ�����ص����������s�Ӵ�����������ˣ���������¼��List�У����û�У�����
					if (loc.country.indexOf(s) != -1 || loc.area.indexOf(s) != -1) {
						IPEntry entry = new IPEntry();
						entry.country = loc.country;
						entry.area = loc.area;
						// �õ���ʼIP
						readIP(offset - 4, b4);
						entry.beginIp = Utils.getIpStringFromBytes(b4);
						// �õ�����IP
						readIP(temp, b4);
						entry.endIp = Utils.getIpStringFromBytes(b4);
						// ��Ӹü�¼
						ret.add(entry);
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return ret;
	}

	/**
	 * ���ڴ�ӳ���ļ���offsetλ�ÿ�ʼ��3���ֽڶ�ȡһ��int
	 *
	 * @param offset
	 * @return
	 */
	private int readInt3(int offset) {
		mbb.position(offset);
		return mbb.getInt() & 0x00FFFFFF;
	}

	/**
	 * @param offset
	 * @return
	 */
	private IPLocation getIPLocation(int offset) {
		// ����4�ֽ�ip
		mbb.position(offset + 4);
		// ��ȡ��һ���ֽ��ж��Ƿ��־�ֽ�
		byte b = mbb.get();
		if (b == AREA_FOLLOWED) {
			// ��ȡ����ƫ��
			int countryOffset = readInt3();
			// ��ת��ƫ�ƴ�
			mbb.position(countryOffset);
			// �ټ��һ�α�־�ֽڣ���Ϊ���ʱ������ط���Ȼ�����Ǹ��ض���
			b = mbb.get();
			if (b == NO_AREA) {
				loc.country = readString(readInt3());
				mbb.position(countryOffset + 4);
			} else {
				loc.country = readString(countryOffset);
			}
			// ��ȡ������־
			loc.area = readArea(mbb.position());
		} else if (b == NO_AREA) {
			loc.country = readString(readInt3());
			loc.area = readArea(offset + 8);
		} else {
			loc.country = readString(mbb.position() - 1);
			loc.area = readArea(mbb.position());
		}
		return loc;
	}

	/**
	 * ��offsetλ�ö�ȡ�ĸ��ֽڵ�ip��ַ����ip�����У���ȡ���ipΪbig-endian��ʽ������ �ļ�����little-endian��ʽ���������ת��
	 *
	 * @param offset
	 * @param ip
	 */
	private void readIP(int offset, byte[] ip) {
		mbb.position(offset);
		mbb.get(ip);
		byte temp = ip[0];
		ip[0] = ip[3];
		ip[3] = temp;
		temp = ip[1];
		ip[1] = ip[2];
		ip[2] = temp;
	}

	/**
	 * ���ڴ�ӳ���ļ��ĵ�ǰλ�ÿ�ʼ��3���ֽڶ�ȡһ��int
	 *
	 * @return
	 */
	private int readInt3() {
		return mbb.getInt() & 0x00FFFFFF;
	}

	/**
	 * ���ڴ�ӳ���ļ���offsetλ�õõ�һ��0��β�ַ���
	 *
	 * @param offset
	 * @return
	 */
	private String readString(int offset) {
		try {
			mbb.position(offset);
			int i;
			for (i = 0, buf[i] = mbb.get(); buf[i] != 0; buf[++i] = mbb.get())
				;
			if (i != 0) {
				return Utils.getString(buf, 0, i, "GBK");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	/**
	 * @param offset
	 * @return
	 */
	private String readArea(int offset) {
		mbb.position(offset);
		byte b = mbb.get();
		if (b == 0x01 || b == 0x02) {
			int areaOffset = readInt3();
			if (areaOffset == 0) {
				return "δ֪����";
			} else {
				return readString(areaOffset);
			}
		} else {
			return readString(offset);
		}
	}

	/**
	 * ����IP�õ�������
	 *
	 * @param ip ip���ֽ�������ʽ
	 * @return �������ַ���
	 */
	public String getCountry(byte[] ip) {
		// ���ip��ַ�ļ��Ƿ�����
		if (ipFile == null) {
			return "�����IP���ݿ��ļ�";
		}
		// ����ip��ת��ip�ֽ�����Ϊ�ַ�����ʽ
		String ipStr = Utils.getIpStringFromBytes(ip);
		// �ȼ��cache���Ƿ��Ѿ����������ip�Ľ����û���������ļ�
		if (ipCache.containsKey(ipStr)) {
			IPLocation loc = (IPLocation) ipCache.get(ipStr);
			return loc.country;
		} else {
			IPLocation loc = getIPLocation(ip);
			ipCache.put(ipStr, loc.getCopy());
			return loc.country;
		}
	}

	/**
	 * ����ip����ip��Ϣ�ļ����õ�IPLocation�ṹ����������ip���������Աip�еõ�
	 *
	 * @param ip Ҫ��ѯ��IP
	 * @return IPLocation�ṹ
	 */
	private IPLocation getIPLocation(byte[] ip) {
		IPLocation info = null;
		long offset = locateIP(ip);
		if (offset != -1) {
			info = getIPLocation(offset);
		}
		if (info == null) {
			info = new IPLocation();
			info.country = "δ֪����";
			info.area = "δ֪����";
		}
		return info;
	}

	/**
	 * �������������ip�����ݣ���λ���������ip���ҵ����ļ�¼��������һ������ƫ�� ����ʹ�ö��ַ����ҡ�
	 *
	 * @param ip Ҫ��ѯ��IP
	 * @return ����ҵ��ˣ����ؽ���IP��ƫ�ƣ����û���ҵ�������-1
	 */
	private long locateIP(byte[] ip) {
		long m = 0;
		int r;
		// �Ƚϵ�һ��ip��
		readIP(ipBegin, b4);
		r = compareIP(ip, b4);
		if (r == 0) {
			return ipBegin;
		} else if (r < 0) {
			return -1;
		}
		// ��ʼ��������
		for (long i = ipBegin, j = ipEnd; i < j; ) {
			m = getMiddleOffset(i, j);
			readIP(m, b4);
			r = compareIP(ip, b4);
			// log.debug(Utils.getIpStringFromBytes(b));
			if (r > 0) {
				i = m;
			} else if (r < 0) {
				if (m == j) {
					j -= IP_RECORD_LENGTH;
					m = j;
				} else {
					j = m;
				}
			} else {
				return readLong3(m + 4);
			}
		}
		// ���ѭ�������ˣ���ôi��j�ض�����ȵģ������¼Ϊ����ܵļ�¼�����ǲ���
		// �϶����ǣ���Ҫ���һ�£�����ǣ��ͷ��ؽ�����ַ���ľ���ƫ��
		m = readLong3(m + 4);
		readIP(m, b4);
		r = compareIP(ip, b4);
		if (r <= 0) {
			return m;
		} else {
			return -1;
		}
	}

	/**
	 * �����Աip��beginIp�Ƚϣ�ע�����beginIp��big-endian��
	 *
	 * @param ip      Ҫ��ѯ��IP
	 * @param beginIp �ͱ���ѯIP��Ƚϵ�IP
	 * @return ��ȷ���0��ip����beginIp�򷵻�1��С�ڷ���-1��
	 */
	private int compareIP(byte[] ip, byte[] beginIp) {
		for (int i = 0; i < 4; i++) {
			int r = compareByte(ip[i], beginIp[i]);
			if (r != 0) {
				return r;
			}
		}
		return 0;
	}

	/**
	 * �õ�beginƫ�ƺ�endƫ���м�λ�ü�¼��ƫ��
	 *
	 * @param begin
	 * @param end
	 * @return
	 */
	private long getMiddleOffset(long begin, long end) {
		long records = (end - begin) / IP_RECORD_LENGTH;
		records >>= 1;
		if (records == 0) {
			records = 1;
		}
		return begin + records * IP_RECORD_LENGTH;
	}

	/**
	 * ������byte�����޷��������бȽ�
	 *
	 * @param b1
	 * @param b2
	 * @return ��b1����b2�򷵻�1����ȷ���0��С�ڷ���-1
	 */
	private int compareByte(byte b1, byte b2) {
		if ((b1 & 0xFF) > (b2 & 0xFF)) // �Ƚ��Ƿ����
		{
			return 1;
		} else if ((b1 ^ b2) == 0)// �ж��Ƿ����
		{
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * ����IP�õ�������
	 *
	 * @param ip ip���ֽ�������ʽ
	 * @return �������ַ���
	 */
	public String getArea(byte[] ip) {
		// ���ip��ַ�ļ��Ƿ�����
		if (ipFile == null) {
			return "�����IP���ݿ��ļ�";
		}
		// ����ip��ת��ip�ֽ�����Ϊ�ַ�����ʽ
		String ipStr = Utils.getIpStringFromBytes(ip);
		// �ȼ��cache���Ƿ��Ѿ����������ip�Ľ����û���������ļ�
		if (ipCache.containsKey(ipStr)) {
			IPLocation loc = (IPLocation) ipCache.get(ipStr);
			return loc.area;
		} else {
			IPLocation loc = getIPLocation(ip);
			ipCache.put(ipStr, loc.getCopy());
			return loc.area;
		}
	}

	public String getAddress(String ip) {
		String country = getCountry(ip).equals(" CZ88.NET") ? "" : getCountry(ip);
		String area = getArea(ip).equals(" CZ88.NET") ? "" : getArea(ip);
		String address = country + " " + area;
		return address.trim();
	}

	/**
	 * ����IP�õ�������
	 *
	 * @param ip IP���ַ�����ʽ
	 * @return �������ַ���
	 */
	public String getCountry(String ip) {
		return getCountry(Utils.getIpByteArrayFromString(ip));
	}

	/**
	 * ����IP�õ�������
	 *
	 * @param ip IP���ַ�����ʽ
	 * @return �������ַ���
	 */
	public String getArea(String ip) {
		return getArea(Utils.getIpByteArrayFromString(ip));
	}

	/**
	 * <pre>
	 * ������װip�����Ϣ��Ŀǰֻ�������ֶΣ�ip���ڵĹ��Һ͵���
	 * </pre>
	 *
	 * @author �����
	 */
	private class IPLocation {

		public String country;

		public String area;

		public IPLocation() {
			country = area = "";
		}

		public IPLocation getCopy() {
			IPLocation ret = new IPLocation();
			ret.country = country;
			ret.area = area;
			return ret;
		}

	}

}
