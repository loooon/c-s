package com.credit.common.util.pdf;

import java.io.*;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.credit.common.util.file.PathUtil;
import com.itextpdf.text.pdf.BaseFont;


/**
 * Created by wangjunling on 2017/4/5.
 */
public class PdfUtil
{

	public static final String pdfPath = "/blackbox/files/report";

	public static byte[] html2pdf(String html,String basePath,String fileName) throws Exception {
	/*	DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(HTML);*/

		ITextRenderer renderer = new ITextRenderer();
//		renderer.setDocument(new File(HTML));
		renderer.setDocumentFromString(html,"file:"+basePath+"/resource/img/report");
//		renderer.setDocument(doc,null);
		ITextFontResolver fontResolver = renderer.getFontResolver();
		String rootPath = PathUtil.getRootPath(PdfUtil.class);
//		String fontPath = rootPath+"\\com\\credit\\common\\util\\pdf\\font\\msyh.ttc";
//		fontResolver.addFont( PathUtil.getFullPathRelateClass("/font/msyh.ttc", PdfUtil.class),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
		if (System.getProperty("os.name").contains("Window")) {
			URL url = PdfUtil.class.getResource("/font/msyh.ttc");
			String fontPath = url.getPath().replaceAll("%20", " ");
			fontResolver.addFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		} else {
			fontResolver.addFont("/usr/share/fonts/win/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		}

		File pdfFile = new File(pdfPath, fileName);
		if(!pdfFile.exists())
		{
			pdfFile.createNewFile();
		}
		OutputStream os = new FileOutputStream(pdfFile);
//		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		renderer.layout();
		renderer.createPDF(os);
		os.close();
//		byte[] bytes = byteArrayOutputStream.toByteArray();
//		byteArrayOutputStream.close();
		return FileUtils.readFileToByteArray(pdfFile);
}

	public static void main(String[] args) throws Exception{

		String rootPath = PathUtil.getRootPath(PdfUtil.class);

		System.out.println(rootPath);
	}

}
