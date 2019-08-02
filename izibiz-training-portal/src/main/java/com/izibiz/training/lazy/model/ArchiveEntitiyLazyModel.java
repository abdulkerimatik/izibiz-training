package com.izibiz.training.lazy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.izibiz.training.entity.Archive;
import com.izibiz.training.entity.dto.ArchiveGDTO;
import com.izibiz.training.lazy.sorther.LazyShorterArchiveDto;
import com.izibiz.training.service.base.ArchiveService;


public class ArchiveEntitiyLazyModel  extends LazyDataModel<ArchiveGDTO>{

	private static final long serialVersionUID = 1L;

	
	private List<ArchiveGDTO> archives;
	private ArchiveService archiveService;
	private Map<String, Object> filtermap;
	
	
	
    public ArchiveEntitiyLazyModel(ArchiveService archiveService) {
        this.setArchiveService(archiveService);
    }

    

    @Override
    public ArchiveGDTO getRowData(String rowKey) {   
    	
    	if(archiveService != null) {
    	 return archiveService.findArchiveeByid(Long.parseLong(rowKey));  
    	}  	
        return null;
    }
 
    
    @Override
    public Object getRowKey(ArchiveGDTO arc) {
        return arc.getId();
    }
 
    
    @Override
    public List<ArchiveGDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
    	
    	long reusltCount = getArchiveService().getArchivesCount(filters);
		setRowCount((int)reusltCount);
		return getArchiveService().getArchives(first, pageSize, sortField, sortOrder, filtermap);
		 
		
		List<ArchiveGDTO> data = new ArrayList<ArchiveGDTO>();
		  
	 
	        //filter
	        for(ArchiveGDTO arc : archives) {
	            boolean match = true;
	 
	            if (filters != null) {
	                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
	                    try {
	                        String filterProperty = it.next();
	                        Object filterValue = filters.get(filterProperty);
	                        String fieldValue = String.valueOf(arc.getClass().getField(filterProperty).get(arc));
	 
	                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
	                            match = true;
	                    }
	                    else {
	                            match = false;
	                            break;
	                        }
	                    } catch(Exception e) {
	                        match = false;
	                    }
	                }
	            }
	 
	            if(match) {
	                data.add(arc);
	            }
	        }
	 
	        //sort
	        if(sortField != null) {
	            Collections.sort(data, new LazyShorterArchiveDto(sortField, sortOrder));
	        }
	 
	        //rowCount
	        int dataSize = data.size();
	        this.setRowCount(dataSize);
	 
	        //paginate
	        if(dataSize > pageSize) {
	            try {
	                return data.subList(first, first + pageSize);
	            }
	            catch(IndexOutOfBoundsException e) {
	                return data.subList(first, first + (dataSize % pageSize));
	            }
	        }
	        else {
	            return data;
	        }
		
		
		
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
