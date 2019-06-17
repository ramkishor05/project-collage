package org.brijframework.college.controller.admin.fee;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brijframework.college.models.dto.CommonDTO;
import org.brijframework.college.models.dto.FeesCategoriesDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.models.dto.StudentFeeSubmissionDetailsDTO;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfKennedySingleSlip extends AbstractPdfView {
	/*@Override
	protected Document newDocument() {
		return new Document(PageSize.A4.rotate());
	}*/

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CommonDTO commonDTO = (CommonDTO) model.get("CommonDTO");
		String imagePath = request.getSession().getServletContext()
				.getRealPath("/");
		imagePath += "/img/jfk.jpg";

		document.open();
		PdfPTable tables1 = new PdfPTable(1);
		tables1.setWidthPercentage(100);
		PdfPTable tables2 = new PdfPTable(1);
		tables2.setWidthPercentage(100);
		PdfPTable tables = new PdfPTable(1);
		/*float[] columnWidthss = { 4.5f, 1, 4.5f };
		tables.setWidths(columnWidthss);*/
		tables.setWidthPercentage(100);

		PdfPTable table2 = new PdfPTable(2);
		table2.setHorizontalAlignment(Element.ALIGN_LEFT);
		float[] columnWidths = { 1f, 6 };
		table2.setWidths(columnWidths);
		table2.setWidthPercentage(100);

		PdfPTable table3 = new PdfPTable(5);
		float[] columnWidthss31 = { 3f, 1f, 3f, 1f, 3f };
		table3.setWidths(columnWidthss31);
		table3.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.setWidthPercentage(100);

		PdfPTable table4 = new PdfPTable(4);
		float[] columnWidthss4 = { 2, 2, 2, 8 };
		table4.setWidths(columnWidthss4);
		table4.setHorizontalAlignment(Element.ALIGN_LEFT);
		table4.setWidthPercentage(100);

		PdfPTable table5 = new PdfPTable(3);
		float[] columnWidthss5 = { 7, 2, 1 };
		table5.setWidths(columnWidthss5);
		table5.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.setWidthPercentage(100);

		PdfPTable table6 = new PdfPTable(2);
		float[] columnWidthss6 = { 8, 2 };
		table6.setWidths(columnWidthss6);
		table6.setHorizontalAlignment(Element.ALIGN_LEFT);
		table6.setWidthPercentage(100);

		PdfPCell pCell;
		pCell = new PdfPCell();
		Image img = Image.getInstance(imagePath);
		img.setAlignment(Element.ALIGN_CENTER);

		pCell.addElement(new Chunk(img, 15, -5));
		pCell.setBorder(Rectangle.NO_BORDER);
		table2.addCell(pCell);
		Paragraph paragraph;
		paragraph = new Paragraph();
		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 15);
		font.setColor(Color.black);
		paragraph.add(new Chunk(
				"New John F.Kennedy School\n Palla No.1, Faridabad, Haryana\n\n",
				font));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(paragraph);
		table2.addCell(pCell);
		font = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		font.setColor(Color.black);
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(0);

		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph("RECEIPT NO.", font));
		table3.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(0);
		pCell.setBorderWidthLeft(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph("", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(0);
		pCell.setBorderWidthLeft(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph("DATE", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(0);
		pCell.setBorderWidthLeft(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph("", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell = new PdfPCell();
		pCell.setBorderWidthBottom(0);

		pCell.setBorderWidthLeft(0);
		pCell.addElement(new Paragraph("FEE FOR MONTH", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell = new PdfPCell();

		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph(
				String.valueOf(commonDTO.getReceiptNo()), font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorderWidthLeft(0);

		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph("", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell = new PdfPCell();
		pCell.setBorderWidthLeft(0);
		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph(commonDTO.getLastDate(), font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell = new PdfPCell();
		pCell.setBorderWidthLeft(0);
		pCell.setBorderWidthTop(0);
		pCell.setBorderWidthRight(0);
		pCell.addElement(new Paragraph("", font));
		table3.addCell(pCell);
		pCell = new PdfPCell();
		pCell = new PdfPCell();
		pCell.setBorderWidthLeft(0);
		pCell.setBorderWidthTop(0);

		pCell.addElement(new Paragraph(commonDTO.getMonths(), font));
		table3.addCell(pCell);
		String paidBy = "";
		String chequeNo = "";
		StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO = commonDTO
				.getStudentFeeSubmissionDetailsDTO();
		paidBy = studentFeeSubmissionDetailsDTO.getPaidBy();
		if (studentFeeSubmissionDetailsDTO.getChequeNo() != null) {
			chequeNo = studentFeeSubmissionDetailsDTO.getChequeNo();

		} else
			chequeNo = "";
		pCell = new PdfPCell();
		pCell.addElement(new Paragraph("Class", font));
		table4.addCell(pCell);
		pCell = new PdfPCell();
		pCell.addElement(new Paragraph("Sec", font));
		table4.addCell(pCell);
		pCell = new PdfPCell();
		pCell.addElement(new Paragraph("Roll No.", font));
		table4.addCell(pCell);
		pCell = new PdfPCell();
		pCell.addElement(new Paragraph("Rupees  "
				+ studentFeeSubmissionDetailsDTO.getPaidAmountInWord() + "",
				font));
		table4.addCell(pCell);

		for (StudentDTO studentDTO : commonDTO.getStudentDTOs()) {

			pCell = new PdfPCell();
			pCell.addElement(new Paragraph(studentDTO.getClassName(), font));
			table4.addCell(pCell);
			pCell = new PdfPCell();
			pCell.addElement(new Paragraph(studentDTO.getSectionName(), font));
			table4.addCell(pCell);
			pCell = new PdfPCell();
			pCell.addElement(new Paragraph(studentDTO.getRollno(), font));
			table4.addCell(pCell);
			pCell = new PdfPCell();
			pCell.addElement(new Paragraph(paidBy + "\n Cheque No" + chequeNo,
					font));
			table4.addCell(pCell);

		}
		pCell = new PdfPCell();
		pCell.addElement(new Paragraph("RECEIVED WITH THANKS FROM", font));
		table5.addCell(pCell);
		pCell = new PdfPCell();
		pCell.addElement(new Paragraph("RS", font));
		table5.addCell(pCell);
		pCell = new PdfPCell();
		pCell.addElement(new Paragraph("P.", font));
		table5.addCell(pCell);
		String names = "";
		for (StudentDTO studentDTO : commonDTO.getStudentDTOs()) {

			names += studentDTO.getFullName() + "  ";
		}
		pCell = new PdfPCell();
		pCell.addElement(new Paragraph(names, font));
		table5.addCell(pCell);

		pCell = new PdfPCell();
		pCell.addElement(new Paragraph(String.valueOf(Float
				.parseFloat(commonDTO.getPaidAmount())), font));
		table5.addCell(pCell);

		pCell = new PdfPCell();
		pCell.addElement(new Paragraph("0", font));
		table5.addCell(pCell);

		pCell = new PdfPCell();
		pCell.addElement(new Paragraph("                  I T E M S", font));
		table6.addCell(pCell);
		pCell = new PdfPCell();
		pCell.addElement(new Paragraph("Rs.    P.", font));
		table6.addCell(pCell);

		for (FeesCategoriesDTO feedto : commonDTO.getFeecategoriesDTOs()) {
			pCell = new PdfPCell();

			pCell.addElement(new Paragraph(feedto.getFeeCategoryName() + " ",
					font));
			table6.addCell(pCell);
			pCell = new PdfPCell();

			pCell.addElement(new Paragraph(feedto.getAmounts(), font));
			table6.addCell(pCell);

		}

		paragraph = new Paragraph();
		paragraph.add(new Chunk("Total", font));
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		pCell = new PdfPCell();
		pCell.addElement(paragraph);
		table6.addCell(pCell);

		pCell = new PdfPCell();
		pCell.addElement(new Paragraph(String.valueOf(commonDTO.getTotalFee()),
				font));
		table6.addCell(pCell);

		paragraph = new Paragraph();
		paragraph.add(new Chunk("Paid", font));
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		pCell = new PdfPCell();
		pCell.addElement(paragraph);
		table6.addCell(pCell);

		pCell = new PdfPCell();
		pCell.addElement(new Paragraph(String.valueOf(Float
				.parseFloat(commonDTO.getPaidAmount())), font));
		table6.addCell(pCell);

		paragraph = new Paragraph();
		paragraph.add(new Chunk("Balance", font));
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		pCell = new PdfPCell();
		pCell.addElement(paragraph);
		table6.addCell(pCell);

		pCell = new PdfPCell();
		pCell.addElement(new Paragraph(commonDTO.getDue(), font));
		table6.addCell(pCell);

		paragraph = new Paragraph();
		paragraph
				.add(new Chunk(
						"\n\nFine will be charged if fee is not \n paid by 10th of the month due\n\n",
						font));
		paragraph.setAlignment(Element.ALIGN_LEFT);
		pCell = new PdfPCell();
		pCell.addElement(paragraph);
		table6.addCell(pCell);

		pCell = new PdfPCell();
		pCell.addElement(new Paragraph(" \n\nSignature\n\n", font));
		table6.addCell(pCell);

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
		pCell.addElement(table5);
		tables1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table4);
		tables1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table6);
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
		pCell.addElement(table5);
		tables2.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table4);
		tables2.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table6);
		tables2.addCell(pCell);

	
		pCell = new PdfPCell();

		pCell.addElement(tables1);
		
		tables.addCell(pCell);
		/*pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(new Chunk("    ")));
		tables.addCell(pCell);
		pCell = new PdfPCell();
		pCell.addElement(tables2);
		tables.addCell(pCell);*/

		document.add(tables);
		document.close();

	}

}
