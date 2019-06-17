package org.brijframework.college.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.dao.AllotDressDao;
import org.brijframework.college.dao.DressDao;
import org.brijframework.college.dao.SectionDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.dao.StudentClassesDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.dao.SubjectDao;
import org.brijframework.college.dao.UniformPurchaseDao;
import org.brijframework.college.model.AllotDress;
import org.brijframework.college.models.dto.AllotDressDTO;
import org.brijframework.college.models.dto.BookPurchaseDTO;
import org.brijframework.college.models.dto.DressDTO;
import org.brijframework.college.models.dto.UniformPurchaseDTO;
import org.brijframework.college.service.AllotDressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("allotDressService")
public class AllotDressServiceImpl extends
		CRUDServiceImpl<Integer, AllotDress, AllotDressDao> implements
		AllotDressService {
	@Autowired
	public AllotDressServiceImpl(AllotDressDao dao) {
		super(dao);
	}

	@Autowired
	DressDao dressdao;
	@Autowired
	StudentsAdmissionDao studentDao;
	@Autowired
	UniformPurchaseDao uniformPurchaseDao;
	@Autowired
	StudentClassesDao classDao;
	@Autowired
	SubjectDao subjectDao;
	@Autowired
	SessionDao sessionDao;
	@Autowired
	SectionDao sectionDao;

	@Override
	public void savedressdata(int dressId, int quantity) {
		AllotDress allotdress = new AllotDress();
		allotdress.setDress(dressdao.get(dressId));
		allotdress.setQuantity(quantity);
		dao.create(allotdress);

	}

	@Override
	public List<AllotDressDTO> getAllDetails() {
		List<AllotDress> list = dao.findAll(AllotDress.class);
		List<AllotDressDTO> dtolist = new ArrayList<AllotDressDTO>();
		int amount = 0;
		int totalamount = 0;
		for (AllotDress dress : list) {
			amount = dress.getQuantity() * dress.getDress().getPrice();
			totalamount += amount;
			AllotDressDTO dto = new AllotDressDTO();
			dto.setAllotDressId(dress.getAllotDressId());
			dto.setCategory(dress.getDress().getCategory());
			dto.setDressId(dress.getDress().getDressId());
			dto.setDressName(dress.getDress().getDressName());
			dto.setPrice(String.valueOf(dress.getDress().getPrice()));
			dto.setQuantity(dress.getQuantity());
			dto.setSize(dress.getDress().getSize());
			dto.setAmount(amount);
			dto.setTotalamount(totalamount);
			dto.setStudentId(dress.getStudents().getStudentAdmissionNo());
			dtolist.add(dto);

		}

		return dtolist;
	}

	@Override
	public void removealloted(int allotedId) {
		dao.deleteById(allotedId);

	}

	@Override
	public void savedress(DressDTO dressDTO) {
		for (AllotDressDTO dress : dressDTO.getAllotedList()) {
			if (dress.getQuantity() > 0) {
				AllotDress allotdress = new AllotDress();
				allotdress.setDress(dressdao.get(dress.getDressId()));
				allotdress.setQuantity(dress.getQuantity());
				allotdress.setStudents(studentDao.get(dressDTO.getStudentId()));
				dao.create(allotdress);
			}
		}

	}

	@Override
	public String getStudentData() {
		String id = "";
		List<AllotDress> list = dao.findAll(AllotDress.class);
		for (AllotDress dress : list) {
			id = dress.getStudents().getStudentAdmissionNo();
		}
		return id;
	}

	@Override
	public void saveUniform(UniformPurchaseDTO uniformPurchaseDTO) {
		List<AllotDress> already = dao.findAll(AllotDress.class);
		if (already != null) {
			for (AllotDress allot : already) {
				if (allot.getStudents().getStudentAdmissionNo()
						.equals(uniformPurchaseDTO.getStudentId())) {

				} else {
					dao.deleteById(allot.getAllotDressId());
				}
			}
		}

		for (AllotDressDTO dress : uniformPurchaseDTO.getAllotedList()) {
			if (dress.getQuantity() > 0) {
				AllotDress allotdress = new AllotDress();
				allotdress.setUniformPurchase(uniformPurchaseDao.get(dress
						.getProductPurchaseId()));
				allotdress.setQuantity(dress.getQuantity());
				allotdress.setStudents(studentDao.get(uniformPurchaseDTO
						.getStudentId()));
				dao.create(allotdress);
			}

		}

	}

	@Override
	public List<AllotDressDTO> getAllSoldUniformDetails() {
		List<AllotDress> list = dao.findAll(AllotDress.class);
		List<AllotDressDTO> dtolist = new ArrayList<AllotDressDTO>();
		int amount = 0;
		int totalamount = 0;
		for (AllotDress uniform : list) {
			amount = uniform.getQuantity()
					* uniform.getUniformPurchase().getUniformPrice();
			totalamount += amount;
			AllotDressDTO dto = new AllotDressDTO();
			dto.setAllotDressId(uniform.getAllotDressId());
			dto.setCategory(uniform.getUniformPurchase().getUniformCategory());
			dto.setDressName(uniform.getUniformPurchase().getUniformName());
			dto.setSize(uniform.getUniformPurchase().getUniformSize());
			dto.setPrice(String.valueOf(uniform.getUniformPurchase()
					.getUniformPrice()));
			dto.setQuantity(uniform.getQuantity());
			dto.setAmount(amount);
			dto.setTotalamount(totalamount);
			dto.setStudentId(uniform.getStudents().getStudentAdmissionNo());
			dtolist.add(dto);

		}

		return dtolist;
	}

	@Override
	public void emptytable() {
		List<AllotDress> already = dao.findAll(AllotDress.class);
		if (already != null) {
			for (AllotDress allot : already) {
				dao.deleteById(allot.getAllotDressId());
			}
		}
	}

	@Override
	public void savePurchasedUniforms(UniformPurchaseDTO uniformPurchaseDTO) {
		for (AllotDressDTO dress : uniformPurchaseDTO.getAllotedList()) {
			if (dress.getQuantity() > 0) {
				AllotDress allotdress = new AllotDress();
				allotdress.setAmount(dress.getAmount());
				allotdress.setCategory(dress.getCategory());
				allotdress.setNetamount(dress.getNetamount());
				allotdress.setPrice(Integer.parseInt(dress.getPrice()));
				allotdress.setSize(dress.getSize());
				allotdress.setType(dress.getDressName());
				allotdress.setQuantity(dress.getQuantity());

				dao.create(allotdress);
			}
		}
	}

	@Override
	public List<AllotDressDTO> getPurchaseDetails() {
		List<AllotDress> list = dao.findAll(AllotDress.class);
		List<AllotDressDTO> dtolist = new ArrayList<AllotDressDTO>();
		int total = 0;
		for (AllotDress dress : list) {
			AllotDressDTO dto = new AllotDressDTO();
			dto.setAllotDressId(dress.getAllotDressId());
			dto.setAmount(dress.getAmount());
			dto.setCategory(dress.getCategory());
			dto.setDressName(dress.getType());
			dto.setNetamount(dress.getNetamount());
			dto.setPrice(String.valueOf(dress.getPrice()));
			dto.setQuantity(dress.getQuantity());
			dto.setSize(dress.getSize());
			total += dress.getNetamount();
			dto.setTotalamount(total);
			dtolist.add(dto);

		}
		return dtolist;
	}

	@Override
	public Integer getTotalAmount() {
		int total = 0;
		List<AllotDress> list = dao.findAll(AllotDress.class);
		for (AllotDress dress : list) {
			total += dress.getNetamount();
		}
		return total;
	}

	@Override
	public AllotDressDTO getPurchaseDetailsbyId(int allotDressId) {
		AllotDressDTO dto = new AllotDressDTO();
		AllotDress dress = dao.get(allotDressId);
		if (dress != null) {
			dto.setAmount(dress.getAmount());
			dto.setCategory(dress.getCategory());
			dto.setDressName(dress.getType());
			dto.setNetamount(dress.getNetamount());
			dto.setPrice(String.valueOf(dress.getPrice()));
			dto.setQuantity(dress.getQuantity());
			dto.setSize(dress.getSize());
			dto.setAllotDressId(dress.getAllotDressId());
		}
		return dto;

	}

	@Override
	public void updatepurchases(AllotDressDTO allotDressDTO) {
		AllotDress dress = dao.get(allotDressDTO.getAllotDressId());

		dress.setCategory(allotDressDTO.getCategory());
		dress.setNetamount(allotDressDTO.getNetamount());
		dress.setPrice(Integer.parseInt(allotDressDTO.getPrice()));
		dress.setType(allotDressDTO.getDressName());
		dress.setSize(allotDressDTO.getSize());
		dress.setQuantity(allotDressDTO.getQuantity());
		dress.setAmount(allotDressDTO.getAmount());

	}

	@Override
	public void savePurchasedBooks(BookPurchaseDTO bookPurchaseDTO) {
		emptytable();
		if (bookPurchaseDTO.getAllotedList() != null) {
			for (AllotDressDTO book : bookPurchaseDTO.getAllotedList()) {
				if (book.getQuantity() > 0) {
					AllotDress allotdress = new AllotDress();
					allotdress.setAmount(book.getAmount());
					allotdress.setSubject(subjectDao.get(book.getSubjectId()));
					allotdress.setNetamount(book.getNetamount());
					allotdress.setPrice(Integer.parseInt(book.getPrice()));
					allotdress.setClasses(classDao.get(bookPurchaseDTO
							.getClassId()));
					allotdress.setType(book.getBookTitle());
					allotdress.setQuantity(book.getQuantity());
					allotdress.setSession(sessionDao.findCurrentSession());
					allotdress.setSection(sectionDao.get(bookPurchaseDTO.getSectionId()));
					dao.create(allotdress);
				}
			}
		}
	}

	@Override
	public List<AllotDressDTO> getBookPurchaseDetails() {
		List<AllotDress> list = dao.findAll(AllotDress.class);
		List<AllotDressDTO> dtolist = new ArrayList<AllotDressDTO>();
		int total = 0;
		for (AllotDress book : list) {
			AllotDressDTO dto = new AllotDressDTO();
			dto.setAllotDressId(book.getAllotDressId());
			dto.setAmount(book.getAmount());
			dto.setBookTitle(book.getType());
			dto.setNetamount(book.getNetamount());
			dto.setPrice(String.valueOf(book.getPrice()));
			dto.setQuantity(book.getQuantity());
			dto.setSubjectName(book.getSubject().getSubjectName());
			dto.setClassName(book.getClasses().getClassName());
			total += book.getNetamount();
			dto.setTotalamount(total);
			dtolist.add(dto);

		}
		return dtolist;
	}

	@Override
	public AllotDressDTO getBookPurchaseDetailsbyId(int allotDressId) {
		AllotDress book = dao.get(allotDressId);
		AllotDressDTO dto = new AllotDressDTO();
		dto.setAmount(book.getAmount());
		dto.setBookTitle(book.getType());
		dto.setNetamount(book.getNetamount());
		dto.setPrice(String.valueOf(book.getPrice()));
		dto.setQuantity(book.getQuantity());
		dto.setAllotDressId(book.getAllotDressId());
		return dto;
	}

	@Override
	public void updatebookpurchases(AllotDressDTO allotDressDTO) {
		AllotDress book = dao.get(allotDressDTO.getAllotDressId());

		book.setType(allotDressDTO.getBookTitle());
		book.setNetamount(allotDressDTO.getNetamount());
		book.setPrice(Integer.parseInt(allotDressDTO.getPrice()));
		book.setQuantity(allotDressDTO.getQuantity());
		book.setAmount(allotDressDTO.getAmount());

	}
}