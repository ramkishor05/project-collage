package org.brijframework.college.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.brijframework.college.dao.DressDao;
import org.brijframework.college.model.Dress;
import org.brijframework.college.models.dto.DressDTO;
import org.brijframework.college.service.DressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("dressService")
public class DressServiceImpl extends CRUDServiceImpl<Integer, Dress, DressDao>
		implements DressService {

	@Autowired
	public DressServiceImpl(DressDao dao) {
		super(dao);

	}

	@Override
	public List<DressDTO> findfirstdressName() {
		List<Dress> dresses = dao.findfirstdress();
		List<DressDTO> dressDto = new ArrayList<DressDTO>();
		for (Dress dress : dresses) {
			DressDTO dto = new DressDTO();
			dto.setCategory(dress.getCategory());
			dto.setDressId(dress.getDressId());
			dto.setDressName(dress.getDressName());
			dto.setPrice(String.valueOf(dress.getPrice()));
			dto.setSize(dress.getSize());
			dressDto.add(dto);

		}
		return dressDto;
	}

	@Override
	public List<DressDTO> getDressNames() {
		List<Dress> dresses = dao.getDressNameslist();
		List<DressDTO> dressDto = new ArrayList<DressDTO>();
		for (Dress dress : dresses) {
			DressDTO dto = new DressDTO();
			dto.setDressId(dress.getDressId());
			dto.setDressName(dress.getDressName());
			dressDto.add(dto);

		}
		Set<String> attributes = new HashSet<String>();
		List<DressDTO> duplicates = new ArrayList<DressDTO>();
		for (DressDTO list : dressDto) {
			if (attributes.contains(list.getDressName())) {
				duplicates.add(list);
			}
			attributes.add(list.getDressName());
		}
		dressDto.removeAll(duplicates);
		return dressDto;

	}

	@Override
	public DressDTO getDressById(int dressId) {
		Dress dress = dao.get(dressId);
		DressDTO dto = new DressDTO();
		dto.setCategory(dress.getCategory());
		dto.setDressId(dress.getDressId());
		dto.setDressName(dress.getDressName());
		dto.setPrice(String.valueOf(dress.getPrice()));
		dto.setSize(dress.getSize());
		return dto;

	}

	@Override
	public void updatePrices(int dressId, int newprice) {
		Dress dresses = dao.get(dressId);
		dresses.setPrice(newprice);

	}

	@Override
	public DressDTO getCurrentdressname() {
		Dress dress = dao.getFirstDress();
		DressDTO dto = new DressDTO();
		dto.setDressId(dress.getDressId());
		dto.setDressName(dress.getDressName());
		return dto;
	}

	@Override
	public List<DressDTO> getDressDetails(int dressId) {
		Dress dress1 = dao.get(dressId);
		List<Dress> dresses = dao.getDressAllDetails(dress1.getDressName());
		List<DressDTO> dressDto = new ArrayList<DressDTO>();
		for (Dress dress : dresses) {
			DressDTO dto = new DressDTO();
			dto.setCategory(dress.getCategory());
			dto.setDressId(dress.getDressId());
			dto.setDressName(dress.getDressName());
			dto.setPrice(String.valueOf(dress.getPrice()));
			dto.setSize(dress.getSize());
			dressDto.add(dto);

		}
		return dressDto;
	}

	@Override
	public List<DressDTO> getDressName() {
		List<Dress> dresses = dao.getDressNamelist();
		List<DressDTO> dressDto = new ArrayList<DressDTO>();
		for (Dress dress : dresses) {
			DressDTO dto = new DressDTO();
			dto.setDressId(dress.getDressId());
			dto.setDressName(dress.getDressName());
			dressDto.add(dto);

		}
		Set<String> attributes = new HashSet<String>();
		List<DressDTO> duplicates = new ArrayList<DressDTO>();
		for (DressDTO list : dressDto) {
			if (attributes.contains(list.getDressName())) {
				duplicates.add(list);
			}
			attributes.add(list.getDressName());
		}
		dressDto.removeAll(duplicates);
		return dressDto;
	}

	@Override
	public List<DressDTO> getDressCategory(int dressId) {
		Dress dress1 = dao.get(dressId);
		List<Dress> dresses = dao.getCategoryList(dress1.getDressName());
		List<DressDTO> dressDto = new ArrayList<DressDTO>();
		for (Dress dress : dresses) {
			DressDTO dto = new DressDTO();
			dto.setDressId(dress.getDressId());
			dto.setCategory(dress.getCategory());
			dressDto.add(dto);

		}
		Set<String> attributes = new HashSet<String>();
		List<DressDTO> duplicates = new ArrayList<DressDTO>();
		for (DressDTO list : dressDto) {
			if (attributes.contains(list.getCategory())) {
				duplicates.add(list);
			}
			attributes.add(list.getCategory());
		}
		dressDto.removeAll(duplicates);
		return dressDto;
	}

	@Override
	public List<DressDTO> getDressSize(int dressId) {
		Dress dress1 = dao.get(dressId);
		List<Dress> dresses = dao.getSizeList(dress1.getCategory(),
				dress1.getDressName());
		List<DressDTO> dressDto = new ArrayList<DressDTO>();
		for (Dress dress : dresses) {
			DressDTO dto = new DressDTO();
			dto.setDressId(dress.getDressId());
			dto.setSize(dress.getSize());
			dressDto.add(dto);

		}
		Set<String> attributes = new HashSet<String>();
		List<DressDTO> duplicates = new ArrayList<DressDTO>();
		for (DressDTO list : dressDto) {
			if (attributes.contains(list.getSize())) {
				duplicates.add(list);
			}
			attributes.add(list.getSize());
		}
		dressDto.removeAll(duplicates);
		return dressDto;
	}

	@Override
	public DressDTO getPrice(int dressId) {
		Dress dress = dao.get(dressId);
		DressDTO dto = new DressDTO();
		dto.setCategory(dress.getCategory());
		dto.setDressId(dress.getDressId());
		dto.setDressName(dress.getDressName());
		dto.setPrice(String.valueOf(dress.getPrice()));
		dto.setSize(dress.getSize());
		return dto;

	}

	@Override
	public List<DressDTO> getDressNameandCategory() {
		List<Dress> dresses = dao.getDressNamelist();
		List<DressDTO> list = new ArrayList<DressDTO>();
		for (Dress dress : dresses) {
			List<String> categoryList = new ArrayList<String>();
			DressDTO dto = new DressDTO();
			dto.setDressName(dress.getDressName());
			List<String> category1 = dao.getDistinctCateegory(dress
					.getDressName());
			for (String var : category1) {
				categoryList.add(var);
			}
			if (categoryList.contains("School")) {
				dto.setStatus(0);
			} else
				dto.setStatus(1);
			dto.setCategoryList(categoryList);
			dto.setDressId(dress.getDressId());
			list.add(dto);
		}
		Set<String> attributes = new HashSet<String>();
		List<DressDTO> duplicates = new ArrayList<DressDTO>();
		for (DressDTO listdto : list) {
			if (attributes.contains(listdto.getDressName())) {
				duplicates.add(listdto);
			}
			attributes.add(listdto.getDressName());
		}
		list.removeAll(duplicates);
		return list;
	}

	@Override
	public int getDressIdforcategory(String name, String category) {
		List<Dress> dress=dao.getSizeList(category, name);
		int dressId=0;
		for(Dress dress1:dress)
		{
			dressId=dress1.getDressId();
		}
		return dressId;
	}

	@Override
	public DressDTO getPriceforsize(int dressId, String size) {
	Dress dress=dao.get(dressId);
	Dress dress1=dao.findprice(dress.getDressName(),dress.getCategory(),size);
	DressDTO dto=new DressDTO();
	dto.setPrice(String.valueOf(dress1.getPrice()));
	dto.setDressId(dress1.getDressId());
	return dto;
	}

	
}
