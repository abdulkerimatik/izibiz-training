package com.izibiz.training.lazy.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.izibiz.training.entity.dto.ArchiveGDTO;
import com.izibiz.training.lazy.sorther.LazyShorterArchiveDto;
import com.izibiz.training.service.base.ArchiveService;

public class ArchiveGDTOLazyModel extends LazyDataModel<ArchiveGDTO> {

	private static final long serialVersionUID = 1L;

	private List<ArchiveGDTO> archives;
	private ArchiveService archiveService;
	private Map<String, Object> filtermap;
	
	
	public ArchiveGDTOLazyModel(ArchiveService archiveService) {
		this.setArchiveService(archiveService);

	}

	@Override
	public ArchiveGDTO getRowData(String rowKey) {

		if (archiveService != null) {
			return archiveService.findArchiveeByid(Long.parseLong(rowKey));
		}
		return null;
	}



	@Override
	public List<ArchiveGDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		
		long reusltCount = getArchiveService().getArchivesCount(filtermap,filters);		
		setRowCount((int) reusltCount);
		archives = getArchiveService().getArchives(first, pageSize, sortField, sortOrder, filtermap, filters);	
		
		//List<ArchiveGDTO> data = new ArrayList<ArchiveGDTO>();
		/*
		 * // filter for (ArchiveGDTO arc : archives) { boolean match = true;
		 * 
		 * if (filters != null ) {
		 * 
		 * for (Entry<String, Object> filter : filters.entrySet()) { String key =
		 * filter.getKey(); Object value = filter.getValue();
		 * 
		 * switch (key) { case "archiveId": if (value != null &&
		 * !arc.getArchiveId().contains(value.toString())) match = false; break; case
		 * "receiver": if (value != null &&
		 * !arc.getReceiver().contains(value.toString())) match = false; break; default:
		 * break; } } } if (match) { data.add(arc); } }
		 */
		// sort 
		
		/*
		 * if (sortField != null) { Collections.sort(archives, new
		 * LazyShorterArchiveDto(sortField, sortOrder)); }
		 */

			return archives;
	}

	public List<ArchiveGDTO> getArchives() {
		return archives;
	}

	public void setArchives(List<ArchiveGDTO> archives) {
		this.archives = archives;
	}

	public ArchiveService getArchiveService() {
		return archiveService;
	}

	public void setArchiveService(ArchiveService archiveService) {
		this.archiveService = archiveService;
	}

	public Map<String, Object> getFiltermap() {
		return filtermap;
	}

	public void setFiltermap(Map<String, Object> filtermap) {
		this.filtermap = filtermap;
	}



}
