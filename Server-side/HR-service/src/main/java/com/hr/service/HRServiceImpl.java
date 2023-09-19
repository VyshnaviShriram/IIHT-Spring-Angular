package com.hr.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.hr.entity.HR;

@Service
public class HRServiceImpl implements Ihrservice{
	
	
	List<HR> list = List.of(
			new HR(1001L,"Vivek","9876543210"),
			new HR(1002L,"Thanuja","9876543211"),
			new HR(1003L,"Seema","9876543212"),
			new HR(1004L,"Tharun","9876543213"),
			new HR(1005L,"Ram","9876543214"),
			new HR(1006L,"Krish","9876543215"));

	@Override
	public HR getHR(Long id) {
		return list.stream().filter(val->val.getUserID().equals(id)).findAny().orElse(null);
	}

}
