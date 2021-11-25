package kr.co.codingmonkey.sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.codingmonkey.domain.AttachDto;
import net.coobird.thumbnailator.Thumbnailator;

public class WebCrawlerEx {
	public static void main(String[] args) throws MalformedURLException, IOException {
		//parsing : 검수번역
		//xml parsing ex) $parseXML()
		//html parsing
		for(int j=2; j<=100; j++ ) {
			String url ="https://search.musinsa.com/ranking/best?period=now&age=ALL&mainCategory=&subCategory=&leafCategory=&price=&golf=false&newProduct=false&exclusive=false&discount=false&soldOut=false&page=1&viewType=small&priceMin=&priceMax=&page="+j;
			System.out.println(j+"페이지");
			//지정된 url 웹페이지 가져오기
			Document doc = Jsoup.parse(new URL(url), 20000);
			//url로 넘어온 페이지의 지정된 id속성 가져오기
			Element outerUl = doc.selectFirst("#goodsRankList");
			//그 id속성에서 li_box클래스 가져오기
			Elements lis = outerUl.select(".li_box");
			//html 파일 저장
			saveHTML(j, doc.toString());
			
//			if(j==1)
//				return;
			System.out.println(lis.size());
			
			for(int i=0; i<lis.size(); i++) {
				Element li = lis.get(i);
				//이미지 파일에 들어갈 경로의 폴더명
				String pk = lis.get(i).attr("data-goods-no");
				String link = li.selectFirst(".img-block").attr("href");
				System.out.println(link);
				//세부페이지의 링크
				//썸네일
				String thumbLink = li.selectFirst("img.lazyload.lazy").attr("data-original");
				System.out.println(thumbLink);
				
				fileDownload(pk, thumbLink);
			}
		}
	}
	//제품정보, 가격정보, 사이즈, 이미지
	
	static void saveHTML(int pageNum, String html) throws UnsupportedEncodingException, IOException {
		String uploadFolder ="C:/Users/chyn1/OneDrive/Desktop/workspace/upload/musinsa";
		File file = new File(uploadFolder, pageNum+".html");
		FileCopyUtils.copy(html.getBytes("utf-8"), file);
	}
	
	static void fileDownload(String pk, String link) throws MalformedURLException, IOException {
		//inputstream 
		String uploadFolder ="C:/Users/chyn1/OneDrive/Desktop/workspace/upload/musinsa";
		File uploadPath = new File(uploadFolder, pk);
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
			InputStream is = new URL(link).openStream();
			File file = new File(uploadPath, "thumb.jpg");
			
			FileCopyUtils.copy(FileCopyUtils.copyToByteArray(is), file);
			//이미지 여부, 이미지일 경우 섬네일 제작
		}
	
	
}
