package com.hyundai.pms.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.MonthlyEntries;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.MonthlyEntriesRepository;

@Service
public class MonthlyEntriesService {
	 @Autowired
	    private MonthlyEntriesRepository monthlyEntriesRepository;

	    public Response saveMonthlyEntry(MonthlyEntries monthlyEntry) {
	         monthlyEntriesRepository.save(monthlyEntry);
	         return new Response(1, "success", monthlyEntry);
	    }
	    
	    public List<Map<String, Object>> getAllMonthlyEntries() {
	        return monthlyEntriesRepository.getAllMonthlyEntries();
	    }
	    
	    public Optional<MonthlyEntries> getMonthlyEntryById(int id) {
	        return monthlyEntriesRepository.findById(id);
	    }
	    
	    public Response updateMonthlyEntry( MonthlyEntries updatedMonthlyEntry) {
	        if (monthlyEntriesRepository.existsById(updatedMonthlyEntry.getMonthlyId())) {
	            updatedMonthlyEntry.setMonthlyId(updatedMonthlyEntry.getMonthlyId());
	             monthlyEntriesRepository.save(updatedMonthlyEntry);
	             return new Response(1, "success", updatedMonthlyEntry);
	        } else {
	            return new Response(2, "error", updatedMonthlyEntry);
	        }
	    }
	    
	    public Response deleteMonthlyEntry(int id) {
	    	if (monthlyEntriesRepository.existsById(id)) {
	        monthlyEntriesRepository.deleteById(id);
	        return new Response(1, "success", null);
	    	}
	    	else {
	    		return new Response(2, "error", null);
	    	}
	    }


		public Response findByProjectId(int projectId) {		
			List<Map<String, Object>> list = monthlyEntriesRepository.findByProjectId(projectId);
			return new Response(1, "Success", list);
		}

}
