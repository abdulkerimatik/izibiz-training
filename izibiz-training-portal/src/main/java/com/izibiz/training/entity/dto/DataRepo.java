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

	public static List<String> channels = Arrays.asList("Ä°ZÄ°BÄ°Z", "ITM", "AQVILA", "CANVAS");
	public static List<String> activationTypeList = Arrays.asList("BaÅŸka entegratÃ¶rden geÃ§iÅŸ yapan mÃ¼ÅŸteri",
			"GÄ°B den geÃ§iÅŸ yapam mÃ¼ÅŸteri", "Nevi deÄŸiÅŸikliÄŸi", "Yeni mÃ¼ÅŸteri");
	public static List<String> companyTypeList = Arrays.asList("Ã–ZEL","KAMU");
	public static List<String> customerTypeList = Arrays.asList("Å�AHIS","KURUM");
	private static HashMap<String, List<String>> dealers = new HashMap<String, List<String>>();
	private static HashMap<String, List<String>> accounts = new HashMap<String, List<String>>();
	public static List<CustomerClientDTO> customerClientList = new ArrayList<CustomerClientDTO>();
	static {
		for (String c : channels) {
			dealers.put(c, Arrays.asList(c + " Ana DaÄŸÄ±tÄ±cÄ±", c + " Yedek DaÄ±tÄ±cÄ±", c + " Rastgele DaÄŸÄ±tÄ±cÄ±"));
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
