package org.brijframework.college.controller.admin.fee;

import java.awt.Color;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brijframework.college.models.dto.CommonDTO;
import org.brijframework.college.models.dto.FeesCategoriesDTO;
import org.brijframework.college.models.dto.StudentFeeSubmissionDetailsDTO;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class Pdffeeslip extends AbstractPdfView {
	@Override
	protected Document newDocument() {
		return new Document(PageSize.A4.rotate());
	}
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

		CommonDTO commonDTO = (CommonDTO) model.get("CommonDTO");
		String imagePath = request.getSession().getServletContext()
				.getRealPath("/");
		imagePath += "/img/gargi logo.jpg";

		int i = 0;
		float total = 0f;

		document.open();
		PdfPTable tables1 = new PdfPTable(1);
		tables1.setWidthPercentage(100);
		PdfPTable tables2 = new PdfPTable(1);
		tables2.setWidthPercentage(100);
		PdfPTable tables = new PdfPTable(3);
		float[] columnWidthss = { 4.5f, 1, 4.5f };
		tables.setWidths(columnWidthss);
		tables.setWidthPercentage(100);
		PdfPTable tables3 = new PdfPTable(3);
		float[] columnWidthss3 = { 4.5f, 1, 4.5f };
		tables3.setWidths(columnWidthss3);
		tables.setWidthPercentage(100);
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		PdfPTable stable = new PdfPTable(1);
		stable.setWidthPercentage(100);
		PdfPTable table2 = new PdfPTable(2);
		PdfPTable table3 = new PdfPTable(1);
		PdfPCell pCell;
		pCell = new PdfPCell();
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.setHorizontalAlignment(Element.ALIGN_LEFT);
		/*float[] columnWidth = { 1.8f, 4f, 0.25f, 2f, 2.3f};
		table3.setWidths(columnWidth);*/
		table3.setWidthPercentage(100);

		float[] columnWidths = {1f, 6};
		table2.setWidths(columnWidths);
		table2.setWidthPercentage(100);
	
		pCell = new PdfPCell();
		Image img = Image.getInstance(imagePath);
		img.setAlignment(Element.ALIGN_CENTER);

		pCell.addElement(new Chunk(img, 15, -5));
		pCell.setBorder(Rectangle.NO_BORDER);
		table2.addCell(pCell);
		Paragraph paragraph;
		paragraph = new Paragraph();

	Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
		font.setColor(Color.black);
		paragraph.add(new Chunk("Gargi Public School\n(Govt. Recognised)\n288A,Main Road,Mandawali Fazalpur,New Delhi-110092", font));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(paragraph);
		 Chunk chunk=new Chunk("FEE RECEIPT");
         chunk.setUnderline(+1f,-2f);
		paragraph = new Paragraph();
		 paragraph.add(chunk);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		pCell.addElement(paragraph);
		table2.addCell(pCell);
		font = FontFactory.getFont(FontFactory.TIMES_BOLD, 8);
		font.setColor(Color.black);
	
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("No.                 "+ commonDTO.getReceiptNo() + "                                           "+"Dated          "+ commonDTO.getCurrentPaidAmountDate() + "", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);

		/*pCell.addElement(new Paragraph("" + commonDTO.getReceiptNo() + "", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Dated", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(""
				+ commonDTO.getCurrentPaidAmountDate() + "", font));
		table3.addCell(pCell);
			pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);*/
		pCell.addElement(new Paragraph("Name of Student          "+ commonDTO.getStudentDTO().getFirstName() + " "
				+ commonDTO.getStudentDTO().getLastName() + "", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);

		/*pCell.addElement(new Paragraph(""
				+ commonDTO.getStudentDTO().getFirstName() + " "
				+ commonDTO.getStudentDTO().getLastName() + "", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table3.addCell(pCell);*/
	/*	pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table3.addCell(pCell);*/
		
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Class:             "	+ commonDTO.getStudentDTO().getClassName() + "                         "+"for the month of"+"       " + commonDTO.getMonths(), font));
		table3.addCell(pCell);
		/*pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(""
				+ commonDTO.getStudentDTO().getClassName() + "", font));
		table3.addCell(pCell);
	
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("for the month of", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
        pCell.addElement(new Paragraph("" + commonDTO.getMonths(), font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("    ", font));
		table3.addCell(pCell);
*/
		pCell = new PdfPCell();

		pCell.setBorder(Rectangle.NO_BORDER);
		PdfPTable table1 = new PdfPTable(3);
		font = FontFactory.getFont(FontFactory.TIMES_BOLD, 7);
		font.setColor(Color.black);
		float[] columnWidths1 = { 0.5f, 6, 3 };
		table1.setWidths(columnWidths1);
		table1.setWidthPercentage(100);
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(1);
		pCell.setBorderWidthTop(1);
		pCell.setBorderWidthLeft(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph("", font));
		table1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(1);
		pCell.setBorderWidthTop(1);
		pCell.setBorderWidthLeft(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph("   PARTICULARS    ", font));
		table1.addCell(pCell);
	          pCell = new PdfPCell();
		  pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph("AMOUNT\n Rs.", font));
		table1.addCell(pCell);
		
		String paidBy = "";
		String chequeNo = "";
		font = FontFactory.getFont(FontFactory.TIMES_BOLD, 8);
		font.setColor(Color.black);
		i = 1;

		/*
		 * table1.addCell(new Paragraph("")); Paragraph paragraph2 = new
		 * Paragraph( studentFeeSubmissionDetailsDTO.getMonth(), font);
		 * paragraph2.setAlignment(Element.ALIGN_MIDDLE);
		 * table1.addCell(paragraph2);
		 * 
		 * table1.addCell(new Paragraph(""));
		 */
		/*
		 * for (FeecategoryAmountDTO feecategoryAmountDTO :
		 * studentFeeSubmissionDetailsDTO .getFeecategoryAmountDTOs()) {
		 * 
		 * table1.addCell(new Paragraph("" + (i++) + "",font));
		 * table1.addCell(new Paragraph(feecategoryAmountDTO
		 * .getFeeCategoryName() + " ", font)); table1.addCell(new
		 * Paragraph(String
		 * .valueOf(feecategoryAmountDTO.getFeeCatgoryAmount()), font)); }
		 * table1.addCell(new Paragraph("" + (i++) + "",font));
		 * table1.addCell(new Paragraph(studentFeeSubmissionDetailsDTO
		 * .getStudentFineDTO().getFineName(), font)); table1.addCell(new
		 * Paragraph(String
		 * .valueOf(studentFeeSubmissionDetailsDTO.getStudentFineDTO()
		 * .getFineAmount()), font));
		 */

		for (FeesCategoriesDTO feedto : commonDTO.getFeecategoriesDTOs()) {
			pCell = new PdfPCell();
			pCell.setBorderWidthBottom(0);
			pCell.setBorderWidthTop(0);
			pCell.setBorderWidthLeft(0);
			pCell.setBorderWidthRight(0);
			pCell.addElement(new Paragraph("" + (i++) + "", font));
			table1.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorderWidthBottom(0);
			pCell.setBorderWidthTop(0);
			pCell.setBorderWidthLeft(0);
			pCell.setBorderWidthRight(0);
			pCell.addElement(new Paragraph(feedto.getFeeCategoryName() + " ",
					font));
			table1.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorderWidthBottom(0);
			pCell.setBorderWidthTop(0);
			pCell.setBorderWidthRight(0);
			pCell.addElement(new Paragraph(feedto.getAmounts(), font));
			table1.addCell(pCell);
		
		}
		for (StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO : commonDTO
				.getStudentFeeSubmissionDetailsDTOs()) {
			/*
			 * table1.addCell(new Paragraph("" + (i++) + "",font));
			 * table1.addCell(new Paragraph(studentFeeSubmissionDetailsDTO
			 * .getStudentFineDTO().getFineName(), font)); table1.addCell(new
			 * Paragraph(String
			 * .valueOf(studentFeeSubmissionDetailsDTO.getStudentFineDTO()
			 * .getFineAmount()), font));
			 */
			paidBy = studentFeeSubmissionDetailsDTO.getPaidBy();
			if (studentFeeSubmissionDetailsDTO.getChequeNo() != null) {
				chequeNo = studentFeeSubmissionDetailsDTO.getChequeNo();
			}
			else
			chequeNo="...........";
		}
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(0);
		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthLeft(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph("" + (i++) + "", font));
		table1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(0);
		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthLeft(0);
		pCell.addElement(new Paragraph("Discount", font));
		table1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(0);
		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph(commonDTO.getDiscount(), font));
		total = commonDTO.getTotalFee()
				- Float.parseFloat(commonDTO.getDiscount());
		table1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(0);
		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthLeft(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph("", font));
		table1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(0);
		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthLeft(0);
		pCell.addElement(new Paragraph("Total Rs.", font));
		table1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(0);
		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph(String.valueOf(total), font));
		table1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(0);
		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthLeft(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph("", font));
		table1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(0);
		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthLeft(0);
		pCell.addElement(new Paragraph("Paid Amount Rs.", font));
		table1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(0);
		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph(String.valueOf(Float.parseFloat(commonDTO
				.getPaidAmount()) - Float.parseFloat(commonDTO.getDiscount())),
				font));
		table1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(1);
		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthLeft(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph("", font));
		table1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(1);
		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthLeft(0);
		pCell.addElement(new Paragraph("Dues Rs.", font));
		table1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(1);
		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph(commonDTO.getDue(), font));
		table1.addCell(pCell);
	

		paragraph = new Paragraph();

		font = FontFactory.getFont(FontFactory.TIMES_BOLD, 8);
		font.setColor(Color.black);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		paragraph = new Paragraph();
		paragraph.add(new Chunk("Received the amount by " + paidBy + ".",
				FontFactory.getFont(FontFactory.TIMES,9)));
		paragraph.setAlignment(Element.ALIGN_LEFT);
		pCell.addElement(paragraph);
		paragraph = new Paragraph(new Chunk("Cheque No-" + chequeNo,
				FontFactory.getFont(FontFactory.TIMES_BOLD,8)));
		paragraph.setAlignment(Element.ALIGN_LEFT);
		pCell.addElement(paragraph);
		paragraph = new Paragraph(new Chunk(
				"Subject to realisation of Cheque.",
				FontFactory.getFont(FontFactory.TIMES_BOLD,8)));
		paragraph.setAlignment(Element.ALIGN_LEFT);
		pCell.addElement(paragraph);

		paragraph = new Paragraph(new Chunk("Office Asstt.",
				FontFactory.getFont(FontFactory.TIMES,8)));
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		pCell.addElement(paragraph);
		
		paragraph = new Paragraph(new Chunk(
				"*******Computer Generated Fee Receipt******\n" + new Date(),
				FontFactory.getFont(FontFactory.TIMES, 8)));

		paragraph.setAlignment(Element.ALIGN_CENTER);
		pCell.addElement(paragraph);

		/* table.completeRow(); */
		table.addCell(pCell);
		paragraph = new Paragraph();

		font = FontFactory.getFont(FontFactory.TIMES_BOLD, 8);
		font.setColor(Color.black);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		paragraph = new Paragraph();
		paragraph.add(new Chunk("Received the amount by " + paidBy + ".",
				FontFactory.getFont(FontFactory.TIMES,9)));
		paragraph.setAlignment(Element.ALIGN_LEFT);
		pCell.addElement(paragraph);
		paragraph = new Paragraph(new Chunk("Cheque No-" + chequeNo,
				FontFactory.getFont(FontFactory.TIMES_BOLD,8)));
		paragraph.setAlignment(Element.ALIGN_LEFT);
		pCell.addElement(paragraph);
		paragraph = new Paragraph(new Chunk(
				"Subject to realisation of Cheque.",
				FontFactory.getFont(FontFactory.TIMES_BOLD,8)));
		paragraph.setAlignment(Element.ALIGN_LEFT);
		pCell.addElement(paragraph);

		paragraph = new Paragraph(new Chunk("Office Asstt.",
				FontFactory.getFont(FontFactory.TIMES,8)));
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		pCell.addElement(paragraph);
		
		paragraph = new Paragraph(new Chunk(
				"*******Computer Generated Fee Receipt******\n" + new Date(),
				FontFactory.getFont(FontFactory.TIMES, 8)));

		paragraph.setAlignment(Element.ALIGN_CENTER);
		pCell.addElement(paragraph);

		/* table.completeRow(); */
		stable.addCell(pCell);
		document.addTitle("Fee Receipt for Adm No."
				+ commonDTO.getStudentDTO().getAdmissionNo() + " on date "
				+ commonDTO.getCurrentPaidAmountDate());
		
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table2);
		tables1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table3);
		tables1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table1);
		tables1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table);
		tables1.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table2);
		tables2.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table3);
		tables2.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table1);
		tables2.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(stable);
		tables2.addCell(pCell);

		/*
		 * pCell = new PdfPCell(); pCell.setBorder(Rectangle.NO_BORDER);
		 * pCell.addElement(new Paragraph(new
		 * Chunk("           STUDENT COPY"))); pCell.addElement(new
		 * Paragraph(new Chunk(""))); tables3.addCell(pCell); pCell = new
		 * PdfPCell(); pCell.setBorder(Rectangle.NO_BORDER);
		 * pCell.addElement(new Paragraph(new Chunk("    ")));
		 * 
		 * 
		 * tables3.addCell(pCell); pCell = new PdfPCell();
		 * pCell.setBorder(Rectangle.NO_BORDER); pCell.addElement(new
		 * Paragraph(new
		 * Chunk("                                         SCHOOL COPY")));
		 * pCell.addElement(new Paragraph(new Chunk("    ")));
		 */

		tables3.addCell(pCell);
		pCell = new PdfPCell();

		pCell.addElement(tables1);
		tables.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(new Chunk("    ")));

		tables.addCell(pCell);

		pCell = new PdfPCell();

		pCell.addElement(tables2);

		tables.addCell(pCell);
		/*
		 * document.add(table2); document.add(table3);
		 */
		document.add(tables3);
		document.add(tables);
		
	/*	document.add(table2);
		document.add(table3);
		document.add(table1);
        document.add(table);*/
		document.close();

	}
}
