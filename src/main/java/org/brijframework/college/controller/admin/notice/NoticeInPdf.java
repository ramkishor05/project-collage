package org.brijframework.college.controller.admin.notice;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brijframework.college.models.dto.NoticeDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@RequestMapping("/admin/**")
public class NoticeInPdf extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		NoticeDTO noticeDTO = (NoticeDTO) model.get("NoticeDto");
		String imagePath = request.getSession().getServletContext()
				.getRealPath("/");

		imagePath += "/img/cuts.jpg";
		//imagePath += "/img/jfk.jpg";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String noticeDate = dateFormat.format(new java.util.Date());
		document.open();
		/*********** PDF Slip *********/
		for (int i = 1; i <= 5; i++) {
			Paragraph paragraph9 = new Paragraph();
			paragraph9.add(new Chunk("Nida Inter College", FontFactory
					.getFont(FontFactory.TIMES_BOLD)));
			paragraph9.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph9);
			Paragraph paragraph = new Paragraph();
			paragraph.add(new Chunk("Date:-" + noticeDate, FontFactory
					.getFont(FontFactory.TIMES_BOLD)));
			paragraph.setAlignment(Element.ALIGN_RIGHT);
			document.add(paragraph);
			Paragraph paragraph1 = new Paragraph();
			paragraph1.add(new Chunk("Notice:-" + noticeDTO.getNoticeSubject(),
					FontFactory.getFont(FontFactory.TIMES_BOLD)));
			paragraph1.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph1);
			Paragraph paragraph2 = new Paragraph();
			paragraph2.add(new Chunk("Respected Sir/Madam,", FontFactory
					.getFont(FontFactory.TIMES_BOLD)));
			document.add(paragraph2);
			Paragraph paragraph3 = new Paragraph();
			paragraph3.add(new Chunk("             "
					+ noticeDTO.getNoticeDescription(), FontFactory
					.getFont(FontFactory.TIMES_ROMAN)));
			document.add(paragraph3);
			document.add(new Paragraph(" "));
			Paragraph paragraph4 = new Paragraph();
			paragraph4.add(new Chunk("(" + noticeDTO.getGenratedBy() + ")",
					FontFactory.getFont(FontFactory.TIMES_BOLD)));
			paragraph4.setAlignment(Element.ALIGN_RIGHT);
			document.add(paragraph4);
			if (i < 5) {
				Image img = Image.getInstance(imagePath);
				document.add(img);
			}
		}
		document.addTitle("Notice");
		document.close();
	}
}
