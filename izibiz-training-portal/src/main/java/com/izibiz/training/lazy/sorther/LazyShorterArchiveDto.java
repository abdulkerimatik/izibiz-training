package com.izibiz.training.lazy.sorther;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.izibiz.training.entity.dto.ArchiveGDTO;

public class LazyShorterArchiveDto implements Comparator<ArchiveGDTO>{


	 private String sortField;    
	 private SortOrder sortOrder;
	     
	 
	    public LazyShorterArchiveDto(String sortField, org.primefaces.model.SortOrder sortOrder2) {
	        this.sortField = sortField;
	        this.sortOrder = sortOrder2;
	    }
	    
	 
	    public int compare(ArchiveGDTO arc1, ArchiveGDTO arc2) {
	       return 0;
	    }
	
	
	

}
