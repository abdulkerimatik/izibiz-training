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

	public static List<String> channels = Arrays.asList("�Z�B�Z", "ITM", "AQVILA", "CANVAS");

	private static HashMap<String, List<String>> dealers = new HashMap<String, List<String>>();
	private static HashMap<String, List<String>> accounts = new HashMap<String, List<String>>();
	static {
		for (String c : channels) {
			dealers.put(c, Arrays.asList(c + " Ana Da��t�c�", c + " Yedek Da��t�c�", c + " Rastgele Da��t�c�"));
			for (String d : dealers.get(c)) {
				accounts.put(d,
						Arrays.asList(d + " -Ana Bayi" + d + " -Bayi 2", d + " -Yedek Bayi", d + " -torpilli bayi"));
			}
		}

		/*channels.forEach(c -> {
			dealers.put(c, Arrays.asList(c + " Ana Da��t�c�", c + " Yedek Da��t�c�", c + " Rastgele Da��t�c�"));
			dealers.get(c).forEach(d -> {
				accounts.put(d,
						Arrays.asList(d + " -Ana Bayi" + d + " -Bayi 2", d + " -Yedek Bayi", d + " -torpilli bayi"));
			});
		});*/

	}

	public static List<String> getDealers(String channelName) {
		return dealers.get(channelName);
	}

	public static List<String> getAccounts(String dealerName) {
		return accounts.get(dealerName);
	}
}
