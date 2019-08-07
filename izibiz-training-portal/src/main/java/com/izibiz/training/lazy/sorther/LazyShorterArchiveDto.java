package com.izibiz.training.lazy.sorther;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.izibiz.training.entity.dto.ArchiveGDTO;

public class LazyShorterArchiveDto implements Comparator<ArchiveGDTO>{


	 private String sortField;    
	 private SortOrder sortOrder;
	     
	 
	    public LazyShorterArchiveDto(String sortField, SortOrder sortOrder2) {
	        this.sortField = sortField;
	        this.sortOrder = sortOrder2;
	    }
	    
	 
	    public int compare(ArchiveGDTO arc1, ArchiveGDTO arc2) {
	        try {
	            Object value1 = ArchiveGDTO.class.getField(this.sortField).get(arc1);
	            Object value2 = ArchiveGDTO.class.getField(this.sortField).get(arc2);
	 
	         int value = ((Comparable)value1).compareTo(value2);
	             
	            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
	        }
	        catch(Exception e) {
	            throw new RuntimeException();
	        }
	    }
	
	
	

}
