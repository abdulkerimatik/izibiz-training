package com.izibiz.training.entity.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataRepo {


	public static List<DespatchDTO> despatches = new ArrayList<>();

	public static List<ArchiveDTO> archive = new ArrayList<ArchiveDTO>();
	public static List<ArchiveGDTO> archiveG = new ArrayList<ArchiveGDTO>();
	
	public static List<ReconciDTO> reconci=new ArrayList<ReconciDTO>();
	public static List<ReconciDTO> reconcisent=new ArrayList<ReconciDTO>();

	public static List<String> channels = Arrays.asList("Kanal-1", "Kanal-2", "Kanal-3", "Kanal-4");
	public static List<String> activationTypeList = Arrays.asList("Başka entegratörden geçiş yapan müşteri",
			"GİB den geçiş yapam müşteri", "Nevi değişikliği", "Yeni müşteri");
	public static List<String> companyTypeList = Arrays.asList("ÖZEL","KAMU");
	public static List<String> customerTypeList = Arrays.asList("ŞAHIS","KURUM");
	private static HashMap<String, List<String>> dealers = new HashMap<String, List<String>>();
	private static HashMap<String, List<String>> accounts = new HashMap<String, List<String>>();
	public static List<CustomerClientDTO> customerClientList = new ArrayList<CustomerClientDTO>();
	static {
		for (String c : channels) {
			dealers.put(c, Arrays.asList(c + " Dağıtıcı-1", c + " Dağıtıcı-2", c + " Dağıtıcı-3"));
			for (String d : dealers.get(c)) {
				accounts.put(d,
						Arrays.asList(d + " -Bayi-1" + d + " -Bayi 2", d + " -Bayi-3", d + " -Bayi-4"));
			}
		}
		for(int i=0;i<100;i++) {
			customerClientList.add(new CustomerClientDTO("izi00"+i, "MERKEZ", "İzibiz A.Ş."+i,"4840847211"+i,true));	
		}
		
	}
	
	

	public static List<String> getDealers(String channelName) {
		return dealers.get(channelName);
	}

	public static List<String> getAccounts(String dealerName) {
		return accounts.get(dealerName);
	}
}
