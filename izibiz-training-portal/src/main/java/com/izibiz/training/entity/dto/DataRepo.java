package com.izibiz.training.entity.dto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.izibiz.training.entity.dto.InvoiceDTO;


public class DataRepo {

	public static List<InvoiceDTO> invoice=new ArrayList<InvoiceDTO>();
	public static List<DespatchDTO> despatches = new ArrayList<>();
	public static List<String> despatchSeriesList = Arrays.asList("IRS","ABC","XY1");
	
}
