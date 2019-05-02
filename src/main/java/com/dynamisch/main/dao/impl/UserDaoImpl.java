package com.dynamisch.main.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import com.dynamisch.main.dao.UserDao;
import com.dynamisch.main.model.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;


@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	public List<User> getAllUser(String filter) {
		// TODO Auto-generated method stub
		Session session=entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Criteria ct=session.createCriteria(User.class);
		Criterion fname=Restrictions.ilike("fname",filter,MatchMode.ANYWHERE);
		Criterion lname=Restrictions.ilike("lname",filter,MatchMode.ANYWHERE);
		Criterion email=Restrictions.ilike("email",filter,MatchMode.ANYWHERE);
		Criterion gender=Restrictions.ilike("gender",filter,MatchMode.ANYWHERE);
		Criterion address=Restrictions.ilike("address",filter,MatchMode.ANYWHERE);
		Criterion phone=Restrictions.ilike("phone",filter,MatchMode.ANYWHERE);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(fname);
		disjunction.add(lname);
		disjunction.add(gender);
		disjunction.add(address);
		disjunction.add(phone);
		disjunction.add(email);
		ct.add(disjunction);
		@SuppressWarnings("unchecked")
		List<User>serachResult=ct.list();
		return serachResult;
	}

	@Override
	public boolean validate(User user) {
		// TODO Auto-generated method stub
		Session session=entityManagerFactory.unwrap(SessionFactory.class).openSession();
		@SuppressWarnings("deprecation")
		Criteria ct=session.createCriteria(User.class);
		ct.add(Restrictions.eq("email",user.getEmail()));
		ct.add(Restrictions.eq("password",user.getPassword()));
		@SuppressWarnings("unchecked")
		List<User>result=ct.list();
		if(result.size()>0) {
			return true;
		}
		return false;
	}

	
	@Override
	public boolean creatPdf(List<User> user, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		
		Document document = new Document(PageSize.A4, 15,15, 45,30);
		
		try {
	String filePath = context.getRealPath("/resources/reports");
	System.out.println("filePath  "+filePath);
	File file = new File(filePath);
	System.out.println("file  "+file);
	boolean exists =  new File(filePath).exists();
	System.out.println("exists  "+exists);
	if(!exists) {
		new File(filePath).mkdir();
	}

	
	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"user"+".pdf"));
	document.open();
	System.out.println("writer  "+writer);
	Font mainFont = FontFactory.getFont("Arial", 10,BaseColor.BLACK);
	Paragraph paragraph = new Paragraph("All Users",mainFont);
	paragraph.setAlignment(Element.ALIGN_CENTER);
	paragraph.setIndentationLeft(50);
	paragraph.setIndentationRight(50);
	paragraph.setSpacingAfter(10);
	document.add(paragraph);
	
	PdfPTable table = new PdfPTable(6);
	table.setWidthPercentage(100);
	table.setSpacingBefore(10f);
	table.setSpacingAfter(10);
	Font tableHeader = FontFactory.getFont("Arial", 10,BaseColor.BLACK);
	Font tableBody = FontFactory.getFont("Arial",9,BaseColor.BLACK);
	
	
	float[] columnWidths = {2f, 2f,2f,2f,2f,2f};
	table.setWidths(columnWidths);
	
	PdfPCell firstName = new PdfPCell(new Paragraph("FirstName",tableHeader));
	firstName.setBorderColor(BaseColor.BLACK);
	firstName.setPaddingLeft(10);
	firstName.setHorizontalAlignment(Element.ALIGN_CENTER);
	firstName.setVerticalAlignment(Element.ALIGN_CENTER);
	firstName.setBackgroundColor(BaseColor.GRAY);
	firstName.setExtraParagraphSpace(5f);
	table.addCell(firstName);

	PdfPCell lastName = new PdfPCell(new Paragraph("lastName",tableHeader));
	lastName.setBorderColor(BaseColor.BLACK);
	lastName.setPaddingLeft(10);
	lastName.setHorizontalAlignment(Element.ALIGN_CENTER);
	lastName.setVerticalAlignment(Element.ALIGN_CENTER);
	lastName.setBackgroundColor(BaseColor.GRAY);
	lastName.setExtraParagraphSpace(5f);
	table.addCell(lastName);
	
	PdfPCell email = new PdfPCell(new Paragraph("email",tableHeader));
	email.setBorderColor(BaseColor.BLACK);
	email.setPaddingLeft(10);
	email.setHorizontalAlignment(Element.ALIGN_CENTER);
	email.setVerticalAlignment(Element.ALIGN_CENTER);
	email.setBackgroundColor(BaseColor.GRAY);
	email.setExtraParagraphSpace(5f);
	table.addCell(email);
	
	PdfPCell phone = new PdfPCell(new Paragraph("phone",tableHeader));
	phone.setBorderColor(BaseColor.BLACK);
	phone.setPaddingLeft(10);
	phone.setHorizontalAlignment(Element.ALIGN_CENTER);
	phone.setVerticalAlignment(Element.ALIGN_CENTER);
	phone.setBackgroundColor(BaseColor.GRAY);
	phone.setExtraParagraphSpace(5f);
	table.addCell(phone);

	PdfPCell gender = new PdfPCell(new Paragraph("gender",tableHeader));
	gender.setBorderColor(BaseColor.BLACK);
	gender.setPaddingLeft(10);
	gender.setHorizontalAlignment(Element.ALIGN_CENTER);
	gender.setVerticalAlignment(Element.ALIGN_CENTER);
	gender.setBackgroundColor(BaseColor.GRAY);
	gender.setExtraParagraphSpace(5f);
	table.addCell(gender);
	
	PdfPCell address = new PdfPCell(new Paragraph("address",tableHeader));
	address.setBorderColor(BaseColor.BLACK);
	address.setPaddingLeft(10);
	address.setHorizontalAlignment(Element.ALIGN_CENTER);
	address.setVerticalAlignment(Element.ALIGN_CENTER);
	address.setBackgroundColor(BaseColor.GRAY);
	address.setExtraParagraphSpace(5f);
	table.addCell(address);
	
	for(User u:user) {
		PdfPCell firstNameValue = new PdfPCell(new Paragraph(u.getFname(),tableHeader));
		firstNameValue.setBorderColor(BaseColor.BLACK);
		firstNameValue.setPaddingLeft(10);
		firstNameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		firstNameValue.setVerticalAlignment(Element.ALIGN_CENTER);
		firstNameValue.setBackgroundColor(BaseColor.WHITE);
		firstNameValue.setExtraParagraphSpace(5f);
		table.addCell(firstNameValue);
		
		
		PdfPCell lastNameValue = new PdfPCell(new Paragraph(u.getLname(),tableHeader));
		lastNameValue.setBorderColor(BaseColor.BLACK);
		lastNameValue.setPaddingLeft(10);
		lastNameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		lastNameValue.setVerticalAlignment(Element.ALIGN_CENTER);
		lastNameValue.setBackgroundColor(BaseColor.WHITE);
		lastNameValue.setExtraParagraphSpace(5f);
		table.addCell(lastNameValue);
		
		
		PdfPCell emailValue = new PdfPCell(new Paragraph(u.getEmail(),tableHeader));
		emailValue.setBorderColor(BaseColor.BLACK);
		emailValue.setPaddingLeft(10);
		emailValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		emailValue.setVerticalAlignment(Element.ALIGN_CENTER);
		emailValue.setBackgroundColor(BaseColor.WHITE);
		emailValue.setExtraParagraphSpace(5f);
		table.addCell(emailValue);
		
		PdfPCell phoneValue = new PdfPCell(new Paragraph(u.getPhone(),tableHeader));
		phoneValue.setBorderColor(BaseColor.BLACK);
		phoneValue.setPaddingLeft(10);
		phoneValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		phoneValue.setVerticalAlignment(Element.ALIGN_CENTER);
		phoneValue.setBackgroundColor(BaseColor.WHITE);
		phoneValue.setExtraParagraphSpace(5f);
		table.addCell(phoneValue);

		PdfPCell genderValue = new PdfPCell(new Paragraph(u.getGender(),tableHeader));
		genderValue.setBorderColor(BaseColor.BLACK);
		genderValue.setPaddingLeft(10);
		genderValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		genderValue.setVerticalAlignment(Element.ALIGN_CENTER);
		genderValue.setBackgroundColor(BaseColor.WHITE);
		genderValue.setExtraParagraphSpace(5f);
		table.addCell(genderValue);
		
		PdfPCell addressValue = new PdfPCell(new Paragraph(u.getAddress(),tableHeader));
		addressValue.setBorderColor(BaseColor.BLACK);
		addressValue.setPaddingLeft(10);
		addressValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		addressValue.setVerticalAlignment(Element.ALIGN_CENTER);
		addressValue.setBackgroundColor(BaseColor.WHITE);
		addressValue.setExtraParagraphSpace(5f);
		table.addCell(addressValue);
	}
document.add(table);
document.close();
writer.close();

	return true;
}
	catch (Exception e) {
	return false;
	}
		
	}

	@Override
	public boolean creatCSV(List<User> user, ServletContext context) {
		 String filePath = context.getRealPath("/resources/reports");
		    System.out.println("filePath  "+filePath);
		    
		    
		    boolean exists =  new File(filePath).exists();
		    System.out.println("exists  "+exists);
		    if(!exists) {
		        new File(filePath).mkdir();
		    }
		    File file = new File(filePath+"/"+File.separator+"user.csv");
		    
		    try {
		        
		        FileWriter fileWriter = new FileWriter(file);
		        CSVWriter csvWriter = new CSVWriter(fileWriter);
		        List<String[]> data = new ArrayList<String[]>();
		        data.add(new String[] {"FirstName","LastName","Email","Gender","Phone","Address"});
		        for(User u :user) {
		            data.add(new String[] {u.getFname(),u.getLname(), u.getEmail(),u.getGender(),u.getPhone(),u.getAddress()});
		        }
		        csvWriter.writeAll(data);
		        csvWriter.close();
		        return true;
		    } catch (Exception e) {
		        return false;
		    }
	}
	
	}


