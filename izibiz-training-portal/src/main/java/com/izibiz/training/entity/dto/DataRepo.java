package com.izibiz.training.entity.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataRepo {

	public static List<InvoiceDTO> invoice = new ArrayList<InvoiceDTO>();
	public static List<DespatchDTO> despatches = new ArrayList<>();

	public static List<ArchiveDTO> archive = new ArrayList<ArchiveDTO>();
	
	public static List<ReconciDTO> reconci=new ArrayList<ReconciDTO>();
	public static List<ReconciDTO> reconcisent=new ArrayList<ReconciDTO>();

	public static List<String> channels = Arrays.asList("İZİBİZ", "ITM", "AQVILA", "CANVAS");
	public static List<String> activationTypeList = Arrays.asList("Başka entegratörden geçiş yapan müşteri",
			"GİB den geçiş yapam müşteri", "Nevi değişikliği", "Yeni müşteri");
	public static List<String> companyTypeList = Arrays.asList("ÖZEL","KAMU");
	public static List<String> customerTypeList = Arrays.asList("ŞAHIS","KURUM");
	private static HashMap<String, List<String>> dealers = new HashMap<String, List<String>>();
	private static HashMap<String, List<String>> accounts = new HashMap<String, List<String>>();
	public static List<CustomerClientDTO> customerClientList = new ArrayList<CustomerClientDTO>();
	static {
		for (String c : channels) {
			dealers.put(c, Arrays.asList(c + " Ana Dağıtıcı", c + " Yedek Dağıtıcı", c + " Rastgele Dağıtıcı"));
			for (String d : dealers.get(c)) {
				accounts.put(d,
						Arrays.asList(d + " -Ana Bayi" + d + " -Bayi 2", d + " -Yedek Bayi", d + " -torpilli bayi"));
			}
		}
		for(int i=0;i<100;i++) {
			customerClientList.add(new CustomerClientDTO("izi00"+i, "MERKEZ", "İzibiz A.Ş."+i,"48408472"+i,true));	
		}
		
	}
	
	

	public static List<String> getDealers(String channelName) {
		return dealers.get(channelName);
	}

	public static List<String> getAccounts(String dealerName) {
		return accounts.get(dealerName);
	}
}
