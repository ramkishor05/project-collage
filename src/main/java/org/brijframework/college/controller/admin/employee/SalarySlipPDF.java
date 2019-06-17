package org.brijframework.college.controller.admin.employee;

import java.awt.Color;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brijframework.college.models.dto.EmployeeSalaryDTO;
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

public class SalarySlipPDF extends AbstractPdfView {
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        String word="";
       EmployeeSalaryDTO salary = (EmployeeSalaryDTO) model.get("list");
		String imagePath = request.getSession().getServletContext()
				.getRealPath("/");
		//imagePath += "/img/agschool.jpg";
		imagePath += "/img/nida.jpg";
		//imagePath += "/img/nida.jpg";
          	document.open();
          	String paidBy = "";
    		String chequeNo = "";
		PdfPTable table2 = new PdfPTable(3);
		PdfPTable table3 = new PdfPTable(7);
		PdfPCell pCell;
		pCell = new PdfPCell();
		
		table2.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.setHorizontalAlignment(Element.ALIGN_LEFT);
		float[] columnWidth = { 0.7f, 1, 2.7f, 0.25f, 1.4f, 3, 0.1f };
		table3.setWidths(columnWidth);
		table3.setWidthPercentage(100);

		float[] columnWidths = { 0.5f, 2.5f, 6 };
		table2.setWidths(columnWidths);
		table2.setWidthPercentage(100);
		PdfPTable table9 = new PdfPTable(2);
		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
		font.setColor(Color.black);
		float[] columnWidths19 = {8,2};
		table9.setWidths(columnWidths19);
		table9.setWidthPercentage(100);
		pCell.setBorder(Rectangle.NO_BORDER);
		
		pCell.addElement(new Paragraph(new Chunk("    ")));
		table2.addCell(pCell);
		pCell = new PdfPCell();
		Image img = Image.getInstance(imagePath);
		img.setAlignment(Element.ALIGN_CENTER);

		pCell.addElement(new Chunk(img, 15, -5));
		pCell.setBorder(Rectangle.NO_BORDER);
		table2.addCell(pCell);
		Paragraph paragraph;
		paragraph = new Paragraph();

		 font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
		font.setColor(Color.black);
	
		paragraph.add(new Chunk("Nida Inter College", font));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		// paragraph.setSpacingBefore(30);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(paragraph);
		paragraph = new Paragraph(new Chunk(
				"Massuri, Ghaziabad",
				FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		pCell.addElement(paragraph);
		table2.addCell(pCell);
			font = FontFactory.getFont(FontFactory.TIMES, 10);
		font.setColor(Color.black);
		
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph("    ", font));
			table3.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph("Slip No:", font));
			table3.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
            	pCell.addElement(new Paragraph("" + salary.getSlipNo() + "", font));
			table3.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph("", font));
			table3.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph("Date:", font));
			table3.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph(""
					+ salary.getDateOfPayment() + "", font));
			table3.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph("    ", font));
			table3.addCell(pCell);
			
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph("    ", font));
			table3.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph("Name:", font));
			table3.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);

			pCell.addElement(new Paragraph(salary.getFirstName()+" "+salary.getLastName(), font));
			table3.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph("", font));
			table3.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph("Id.", font));
			table3.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph(""+salary.getEmployeeId()+"", font));
			table3.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph("    ", font));
			table3.addCell(pCell);
			
			
			pCell = new PdfPCell();
			
			pCell.setBorderWidthTop(0);
			pCell.addElement(new Paragraph("Description", font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
			
			pCell.setBorderWidthTop(0);
			pCell.addElement(new Paragraph("Amount(Rs)", font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorderWidthBottom(0);
			pCell.setBorderWidthTop(0);
			pCell.addElement(new Paragraph("Salary Amount", font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorderWidthBottom(0);
			pCell.setBorderWidthTop(0);
			pCell.addElement(new Paragraph(String.valueOf(salary.getSalaryAmount()), font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorderWidthBottom(0);
			pCell.setBorderWidthTop(0);
			pCell.addElement(new Paragraph("Advance", font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorderWidthBottom(0);
			pCell.setBorderWidthTop(0);
			pCell.addElement(new Paragraph(String.valueOf(salary.getAdvanceAmount()),font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorderWidthBottom(0);
			pCell.setBorderWidthTop(0);
			pCell.addElement(new Paragraph("Fine", font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorderWidthBottom(0);
			pCell.setBorderWidthTop(0);
			pCell.addElement(new Paragraph(String.valueOf(salary.getFineAmount()),font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
			
			pCell.setBorderWidthTop(0);
			pCell.addElement(new Paragraph("Incentive", font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
			
			pCell.setBorderWidthTop(0);
			pCell.addElement(new Paragraph(String.valueOf(salary.getIncentive()), font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorderWidthBottom(0);
			;
			pCell.addElement(new Paragraph("Net Amount", font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorderWidthBottom(0);
		
			pCell.addElement(new Paragraph(String.valueOf(salary.getNetPayableAmount()), font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorderWidthBottom(0);
			pCell.setBorderWidthTop(0);
			pCell.addElement(new Paragraph("Paid Amount", font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorderWidthBottom(0);
			pCell.setBorderWidthTop(0);
			pCell.addElement(new Paragraph(String.valueOf(salary.getPaidAmount()), font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
		
			pCell.setBorderWidthTop(0);
			pCell.addElement(new Paragraph("Dues", font));
			table9.addCell(pCell);
			pCell = new PdfPCell();
			
			pCell.setBorderWidthTop(0);
			pCell.addElement(new Paragraph(String.valueOf(salary.getDueAmount()), font));
			table9.addCell(pCell);
			word=salary.getPaidAmountInWord();
			paidBy = salary.getPaidBy();
			if (salary.getChequeNo() != null) {
				chequeNo = salary.getChequeNo();
			}
			else
			{
			chequeNo="...........";
		}
			
		pCell = new PdfPCell();

		pCell.setBorder(Rectangle.NO_BORDER);
		PdfPTable table1 = new PdfPTable(1);
		table1.setWidthPercentage(100);
		font = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
		font.setColor(Color.black);
			
		
		pCell = new PdfPCell();
		
		paragraph = new Paragraph(new Chunk("Salary for " +salary.getMonth(),
				FontFactory.getFont(FontFactory.TIMES_BOLD,9)));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		pCell.addElement(paragraph);
		pCell.setBorderWidthBottom(0);
		
		table1.addCell(pCell);
		if(salary.getAdvance().equals("none"))
		{
			
			pCell = new PdfPCell();
			pCell.setBorderWidthTop(0);
			paragraph = new Paragraph(new Chunk("",
					FontFactory.getFont(FontFactory.TIMES_BOLD,9)));
			paragraph.setAlignment(Element.ALIGN_CENTER);
			pCell.addElement(paragraph);
			table1.addCell(pCell);
		}
		else
		{
			
		pCell = new PdfPCell();
		pCell.setBorderWidthTop(0);
		paragraph = new Paragraph(new Chunk(""+salary.getAdvance(),
				FontFactory.getFont(FontFactory.TIMES_BOLD,9)));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		pCell.addElement(paragraph);
	
		table1.addCell(pCell);
		}
        document.add(table2);
		document.add(table3);
		document.add(table1);
		document.add(table9);
		font = FontFactory.getFont(FontFactory.TIMES, 10);
		font.setColor(Color.black);
		paragraph = new Paragraph();
		paragraph.add(new Chunk(" Amount in Words Rs-  " + word
				+ " Only\n\n", font));
		paragraph.setAlignment(Element.ALIGN_LEFT);
		document.add(paragraph);
		paragraph = new Paragraph();
		paragraph.add(new Chunk("Paid the amount by " + paidBy + ".",
				FontFactory.getFont(FontFactory.TIMES_ROMAN)));
		paragraph.setAlignment(Element.ALIGN_LEFT);
		document.add(paragraph);
		paragraph = new Paragraph(new Chunk("Cheque No-" + chequeNo,
				FontFactory.getFont(FontFactory.TIMES_BOLD)));
		paragraph.setAlignment(Element.ALIGN_LEFT);
		document.add(paragraph);
		paragraph = new Paragraph(new Chunk(
				"Subject to realisation of Cheque.",
				FontFactory.getFont(FontFactory.TIMES_BOLD)));
		paragraph.setAlignment(Element.ALIGN_LEFT);
		document.add(paragraph);

		paragraph = new Paragraph(new Chunk("Cashier", FontFactory.getFont(
				FontFactory.TIMES, 10)));
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph);
		paragraph = new Paragraph(new Chunk(
				"*******Computer Generated Salary Slip******\n" + new Date(),
				FontFactory.getFont(FontFactory.TIMES, 8)));

		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);
		document.addTitle("Salary Slip");
		document.close();

	}
}