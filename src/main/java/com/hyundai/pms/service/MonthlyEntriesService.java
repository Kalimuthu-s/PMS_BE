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
	    
	    public Response getAllMonthlyEntries() {
	        List<Map<String, Object>> list = monthlyEntriesRepository.getAllMonthlyEntries();
	         return new Response(1, "Success", list);
	    }
	    
	    public Optional<MonthlyEntries> getMonthlyEntryById(int id) {
	        return monthlyEntriesRepository.findById(id);
	    }
	    
	   

	    
	    public Response updateMonthlyEntry( MonthlyEntries updatedMonthlyEntry) {
		    
	        if (monthlyEntriesRepository.existsById(updatedMonthlyEntry.getMonthlyId())) {	          
	            updatedMonthlyEntry.setMonthlyId(updatedMonthlyEntry.getMonthlyId());
	            double totalContributionForjanuary = getTotalForJanuary(updatedMonthlyEntry.getYear(),updatedMonthlyEntry.getEmp_id());
	            if ((totalContributionForjanuary + updatedMonthlyEntry.getJanuary()) > 1.0) {
	            	System.err.println("===========> Jan condition");
	            	return new Response(2,"Total contribution for the employee in january month exceeds 1",null);
	            }
	            double totalContributionForfebruary = getTotalForFebruary(updatedMonthlyEntry.getYear(),updatedMonthlyEntry.getEmp_id());
	            if ((totalContributionForfebruary + updatedMonthlyEntry.getFebruary()) > 1.0) {
	            	System.err.println("===========> Feb condition");
	            	return  new Response(2,"Total contribution for the employee in february month exceeds 1",null);
	            }
	            double totalContributionForMarch = getTotalForMarch(updatedMonthlyEntry.getYear(),updatedMonthlyEntry.getEmp_id());
	            if ((totalContributionForMarch + updatedMonthlyEntry.getMarch()) > 1.0) {
	            	System.err.println("===========> Mar condition");
	            	return  new Response(2,"Total contribution for the employee in march month exceeds 1",null);
	            }
	            double totalContributionForApril = getTotalForApril(updatedMonthlyEntry.getYear(),updatedMonthlyEntry.getEmp_id());
	            if ((totalContributionForApril + updatedMonthlyEntry.getApril()) > 1.0) {
	            	System.err.println("===========> Apr condition");
	            	return  new Response(2,"Total contribution for the employee in april month exceeds 1",null);
	            }
	            double totalContributionForMay = getTotalForMay(updatedMonthlyEntry.getYear(),updatedMonthlyEntry.getEmp_id());
	            if ((totalContributionForMay + updatedMonthlyEntry.getMay()) > 1.0) {
	            	System.err.println("===========> May condition");
	            	return   new Response(2,"Total contribution for the employee in may month exceeds 1",null);
	            }
	            double totalContributionForJune = getTotalForJune(updatedMonthlyEntry.getYear(),updatedMonthlyEntry.getEmp_id());
	            if ((totalContributionForJune + updatedMonthlyEntry.getJune()) > 1.0) {
	            	System.err.println("===========> Jun condition");
	            	return    new Response(2,"Total contribution for the employee in june month exceeds 1",null);
	            }
	            double totalContributionForJuly = getTotalForJuly(updatedMonthlyEntry.getYear(),updatedMonthlyEntry.getEmp_id());
	            if ((totalContributionForJuly + updatedMonthlyEntry.getJuly()) > 1.0) {
	            	System.err.println("===========> Jul condition");
	            	return    new Response(2,"Total contribution for the employee in june month exceeds 1",null);
	            }
	            double totalContributionForAugust = getTotalForAugust(updatedMonthlyEntry.getYear(),updatedMonthlyEntry.getEmp_id());
	            if ((totalContributionForAugust + updatedMonthlyEntry.getAugust()) > 1.0) {
	            	System.err.println("===========> Aug condition");
	            	return   new Response(2,"Total contribution for the employee in August month exceeds 1",null);
	            }
	            double totalContributionForSeptember = getTotalForSeptember(updatedMonthlyEntry.getYear(),updatedMonthlyEntry.getEmp_id());
	            if ((totalContributionForSeptember + updatedMonthlyEntry.getSeptember()) > 1.0) {
	            	System.err.println("===========> Sep condition");
	            	return   new Response(2,"Total contribution for the employee in september month exceeds 1",null);
	            }
	            double totalContributionForOctober = getTotalForOctober(updatedMonthlyEntry.getYear(),updatedMonthlyEntry.getEmp_id());
	            if ((totalContributionForOctober + updatedMonthlyEntry.getOctober()) > 1.0) {
	            	System.err.println("===========> Oct condition");
	            	return   new Response(2,"Total contribution for the employee in october month exceeds 1",null);
	            }
	            double totalContributionForNovember = getTotalForNovember(updatedMonthlyEntry.getYear(),updatedMonthlyEntry.getEmp_id());
	            if ((totalContributionForNovember + updatedMonthlyEntry.getNovember()) > 1.0) {
	            	System.err.println("===========> Nov condition");
	             return    new Response(2,"Total contribution for the employee in 	november month exceeds 1",null);
	            }
	            double totalContributionForDecember = getTotalForDecember(updatedMonthlyEntry.getYear(),updatedMonthlyEntry.getEmp_id());
	            if ((totalContributionForDecember + updatedMonthlyEntry.getDecember()) > 1.0) {
	            	System.err.println("===========> Dec condition");
	            	return  new Response(2,"Total contribution for the employee in December month exceeds 1",null);
	            }
	            
	             monthlyEntriesRepository.save(updatedMonthlyEntry);
	             return new Response(1, "success", updatedMonthlyEntry);
	        } else {
	            return new Response(2, "error", updatedMonthlyEntry);
	        }
	    }
	    
	    private double getTotalForJanuary(String year,int employeeId) {
    	    List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
    	    double sum = 0;
    	    for (MonthlyEntries one : existingEntries) {
					sum+=one.getJanuary();
			}
    	    return sum;
    
    }
    
    private double getTotalForFebruary(String year,int employeeId) {
	    List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	    double sum = 0;
	    for (MonthlyEntries one : existingEntries) {
				sum+=one.getFebruary();

		}
	    return sum;

    }
    
    private double getTotalForMarch(String year, int employeeId) {
    	 List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
    	    double sum = 0;
    	    for (MonthlyEntries one : existingEntries) {
					sum+=one.getMarch();
			}
		return sum;
	}
    
    private double getTotalForApril(String year, int employeeId) {
    	 List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
    	    double sum = 0;
    	    for (MonthlyEntries one : existingEntries) {
					sum+=one.getApril();
			}
		return sum;
	}
    
    private double getTotalForMay(String year, int employeeId) {
    	 List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
    	    double sum = 0;
    	    for (MonthlyEntries one : existingEntries) {
//				if(one.getJanuary()!=null && one.getJanuary()!=0) {
					sum+=one.getMay();
			}
		return sum;
	}
    
    private double getTotalForJune(String year, int employeeId) {
    	 List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
    	    double sum = 0;
    	    for (MonthlyEntries one : existingEntries) {
//				if(one.getJanuary()!=null && one.getJanuary()!=0) {
					sum+=one.getJune();
			}
		return sum;
	}
    
	private double getTotalForJuly(String year, int employeeId) {
		 List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	   	    double sum = 0;
    	    for (MonthlyEntries one : existingEntries) {
					sum+=one.getJuly();
			}
		return sum;
	}
	
	private double getTotalForAugust(String year, int employeeId) {
		 List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	   	    double sum = 0;
    	    for (MonthlyEntries one : existingEntries) {
					sum+=one.getAugust();
			}
		return sum;
	}
	
	private double getTotalForSeptember(String year, int employeeId) {
		 List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	   	    double sum = 0;
    	    for (MonthlyEntries one : existingEntries) {
					sum+=one.getSeptember();
			}
		return sum;
	}
	
	private double getTotalForOctober(String year, int employeeId) {
		 List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	   	    double sum = 0;
    	    for (MonthlyEntries one : existingEntries) {
					sum+=one.getOctober();
			}
		return 0.0;
	}
	
	private double getTotalForNovember(String year, int employeeId) {
		 List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	   	    double sum = 0;
    	    for (MonthlyEntries one : existingEntries) {
					sum+=one.getNovember();
			}
		return sum;
	}

	private double getTotalForDecember(String year, int employeeId) {
		 List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	   	    double sum = 0;
    	    for (MonthlyEntries one : existingEntries) {
					sum+=one.getDecember();
			}
		return sum;
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
