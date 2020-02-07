/**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */
package io.github.dunwu.javaee.oss.html;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 获取虾米网我的音乐中所有曲目
 *
 * @author Victor Zhang
 * @since 2016/11/8.
 */
public class XiamiParser {

	private static final String BLOG_URL = "http://www.xiami.com/space/lib-song/u/5524914/page";

	public static void main(String[] args) throws Exception {
		XiamiParser parser = new XiamiParser();
		Set<SongInfo> allSongInfos = new HashSet<>();
		for (int page = 0; page <= 65; page++) {
			Set<SongInfo> curPageSongs = parser.getSongInfoSet(BLOG_URL, page);
			CollectionUtils.addAll(allSongInfos, curPageSongs.iterator());
		}
		System.out.println("总歌曲数目：" + allSongInfos.size());
		parser.printAllSongInfo(allSongInfos);
	}

	/**
	 * 获取指定页HTML 文档指定的body
	 *
	 * @throws IOException
	 */
	private Set<SongInfo> getSongInfoSet(String blogUrl, int page) throws IOException {
		Set<SongInfo> songList = new HashSet<SongInfo>();
		Document doc = Jsoup.connect(blogUrl + "/" + page).get();
		Elements postTitles = doc.body().getElementsByClass("track_list");
		for (Element postTitle : postTitles) {
			Elements songs = postTitle.getElementsByTag("tr");
			for (Element song : songs) {
				Elements name = song.getElementsByClass("song_name");
				for (Element link : name) {
					SongInfo songinfo = new SongInfo();
					songinfo.setName(link.child(0).text());
					Elements artistName = link.getElementsByClass("artist_name");
					songinfo.setArtist(artistName.get(0).text());
					songList.add(songinfo);
				}
			}
		}
		return songList;
	}

	private void printAllSongInfo(Set<SongInfo> songs) {
		for (SongInfo song : songs) {
			System.out.println(song.getName() + "\t" + song.getArtist());
		}
	}

	public class SongInfo {

		private String name;

		private String artist;

		@Override
		public boolean equals(Object obj) {
			if (obj.getClass() != SongInfo.class) {
				return false;
			}
			SongInfo external = (SongInfo) obj;
			if (external.getName().equals(this.getName()) && external.getArtist().equals(this.getArtist())) {
				return true;
			} else {
				return false;
			}
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getArtist() {
			return artist;
		}

		public void setArtist(String artist) {
			this.artist = artist;
		}

		@Override
		public int hashCode() {
			return 1;
		}

	}

}
