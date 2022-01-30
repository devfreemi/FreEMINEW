package com.freemi.services.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.freemi.connector.restclient.Hdfcbackendservice;
import com.freemi.entity.general.Datarquestresponse;
import com.freemi.entity.general.Hdfccity;
import com.freemi.entity.general.Hdfcpincode;
import com.freemi.entity.general.Searchlocationdetials;
import com.freemi.entity.general.Select2Results;
import com.freemi.entity.loan.Trackloanstatusapiresponse;
import com.freemi.services.interfaces.HdfcService;

@Service	
public class HdfcServiceImpl implements HdfcService {

	private static final Logger logger = LoggerFactory.getLogger(HdfcServiceImpl.class);
	
	@Autowired
	Hdfcbackendservice hdfcbackendservice;
	
	@Override
	public Datarquestresponse getrequestedloanhistory(String mobile, String userid, String starttime, String endtime) {
		Datarquestresponse response = new Datarquestresponse();
		List<String[]> result = null;
		try {
			logger.info("Request received to fetch HDFC loan application history for mobile- " + mobile);
			Map<String, String> data = new HashMap<String, String>();
			data.put("mobile", mobile);
			result = hdfcbackendservice.getloanrequestslist(data);
			
			if(result!=null && result.size()>0) {
				response.setStatus("1");
			}else if(result!=null && result.size() == 0) {
				response.setStatus("1");
				response.setMsg("NO_DATA");
			} else {
				response.setStatus("0");
				response.setMsg("SERVICE_CONN_FAILURE");
			}
			response.setData(result);
			
		}catch(Exception e) {
			logger.info("Failed to process hdfc loan request history fetch",e);
			response.setStatus("0");
			response.setMsg("INTERNAL_ERROR");
		}
		
		return response;
	}

	@Override
	public Object getloanstatus(String mobile, String loanreferenceid, String ackno, String loanrequeststatus, String bank) {
		
		Trackloanstatusapiresponse response = new Trackloanstatusapiresponse();
		try {
			logger.info("Request received to fetch HDFC loan application history for mobile- " + mobile);
			Map<String, String> data = new HashMap<String, String>();
			data.put("mobile", mobile);
			data.put("refno", loanreferenceid);
			if(bank.equalsIgnoreCase("HDFC")) {
				response = hdfcbackendservice.trackloanstatus(data);
			}else {
				logger.info("Passed bank details not supported- "+ bank);
				response.setApplicationstatus("Invalid bank reference. Request not processed");
			}
		}catch(Exception e) {
			logger.info("Failed to process hdfc loan request history fetch",e);
			response.setApplicationstatus("Failed to process request. Kindly contact admin if issue persist.");
		}
		
		return response;
	}

	@Override
	@Cacheable(value = "statecities", unless = "#result.size() == 0")
	public List<Select2Results> searchcity(Map<String, String> filters, String stateid) {

		Select2Results r = null;
		Hdfccity[] citylist = null;
		List<Select2Results> results = new ArrayList<Select2Results>();
		try {
			String state = filters.get("stateid");
			logger.info("Selected state: " + state);
			if (state != null) {
				citylist = hdfcbackendservice.cityGet(filters, state);
				if (citylist != null) {
					for (int i = 0; i < citylist.length; i++) {
						r = new Select2Results();
//						r.setId(citylist[i].getLmccityid());
						r.setId(citylist[i].getLmccityname());
						r.setText(citylist[i].getLmccityname());
						results.add(r);
					}
				}
				/*
				 * else { logger.info("Data size is null"); r = new Select2Results();
				 * r.setId("NA"); r.setText("No city found"); results.add(r); }
				 */

			} else {
				logger.info("Search state is null.. get from request " + stateid);

				/*
				 * r = new Select2Results(); r.setId("EMPTY_SEARCH"); r.setText("Select city");
				 * results.add(r);
				 */
			}

			logger.info("Returning total city- " + results.size());
		} catch (Exception e) {
			logger.error("Error fecthing company list info", e);

			/*
			 * r = new Select2Results(); r.setId("ERROR"); r.setText("Error fetching list");
			 * results.add(r);
			 */
		}
		return results;
	}

	@Override
	@Cacheable(value = "statecities2", unless = "#result.size() == 0")
	public Map<String, String> searchcity2(Map<String, String> filters, String stateid) {
		
		Map<String, String> dataarr = new HashMap<String, String>();
		Hdfccity[] citylist = null;
		logger.info("Fetch city details for- "+ stateid);
		try {
			String state = filters.get("stateid");
			logger.info("Selected state: " + state);
			if (state != null) {
				citylist = hdfcbackendservice.cityGet(filters, state);
				if (citylist != null) {
					for (int i = 0; i < citylist.length; i++) {
//						dataarr.put(Integer.toString(citylist[i].getLmccityid()), citylist[i].getLmccityname());
						dataarr.put(citylist[i].getLmccityname(), citylist[i].getLmccityname());
					}
				}
				/*
				 * else { dataarr.put("NA", "No city found"); }
				 */

			}
			/*
			 * else { logger.info("Search state is null.. get from request "+ stateid);
			 * dataarr.put("NA", "No city found"); }
			 */

			logger.info("Returning total city- " + dataarr.size());
		} catch (Exception e) {
			logger.error("Error fecthing company list info", e);
//			dataarr.put("NA", "No city found");
		}
		return dataarr;
	}

	@Override
	@Cacheable(value = "citypincode", key = "#data.stateid+'_'+#data.cityid", unless = "#result.size() == 0")
	public List<Select2Results> searchcitypincode(Searchlocationdetials data, Map<String, String> filters,
			String stateid, String cityid) {

		List<Select2Results> dataarr = new ArrayList<Select2Results>();

		Select2Results r = null;
		try {

			logger.info("Selected state: " + stateid + " -> city- " + cityid);

			if (cityid != null && stateid != null) {
				Hdfcpincode[] res = hdfcbackendservice.getcitypincode(data, filters, stateid, cityid);

				if (res != null) {
					for (int i = 0; i < res.length; i++) {
						r = new Select2Results();
//						r.setId(res[i].getZipcodeid());
						r.setId(res[i].getZipcode());
						r.setText(res[i].getZipcode());
						dataarr.add(r);
					}
				}
				/*
				 * else { logger.info("Data size is null"); r = new Select2Results();
				 * r.setId("NA"); r.setText("No PINCODE found"); dataarr.add(r); }
				 */

			}
			/*
			 * else { logger.info("Search string is null.. Return blank result "); r = new
			 * Select2Results(); r.setId("EMPTY_DATA"); r.setText("No pincode");
			 * dataarr.add(r); }
			 */

			logger.info("Returning- " + dataarr.size());
		} catch (Exception e) {
			logger.error("Error fecthing city pincode list info", e);

			/*
			 * r = new Select2Results(); r.setId("ERROR"); r.setText("Error fetching list");
			 * dataarr.add(r);
			 */
		}
		return dataarr;
	}

	@Override
	@Cacheable(value = "citypincode2", key = "#data.stateid+'_'+#data.cityid", unless = "#result.size() == 0")
	public Map<String, String> searchcitypincode2(Searchlocationdetials data, Map<String, String> filters,
			String stateid, String cityid) {

		Map<String, String> dataarr = new HashMap<String, String>();

		try {

			logger.info("searchcitypincode2(): Selected state: " + stateid + " -> city- " + cityid);

			if (cityid != null && stateid != null) {
				Hdfcpincode[] res = hdfcbackendservice.getcitypincode(data, filters, stateid, cityid);

				if (res != null) {
					for (int i = 0; i < res.length; i++) {
//						dataarr.put(Integer.toString(res[i].getZipcodeid()), Integer.toString(res[i].getZipcode()));
						dataarr.put(Integer.toString(res[i].getZipcode()), Integer.toString(res[i].getZipcode()));
					}
				} else {
					logger.info("Data size is null");
				}

			} else {
				logger.info("Search string is null.. Return blank result ");
			}

			logger.info("Returning- " + dataarr.size());
		} catch (Exception e) {
			logger.error("Error fecthing city pincode list info", e);
		}
		return dataarr;
	}

	/*
	 * @Cacheable(value = "employerlist", unless = "#result == null") public
	 * List<Select2Results> searchemployer(Map<String,String> filters, String
	 * searchstr){
	 * 
	 * List<Select2Results> dataarr= new ArrayList<Select2Results>();
	 * 
	 * Select2Results r =null; try{
	 * 
	 * logger.info("Selected employer search string: "+ searchstr + " :length- "+
	 * (searchstr!=null?searchstr.length():null) );
	 * 
	 * if(searchstr!= null && searchstr.length()>3 && searchstr.length()<16) {
	 * Hdfcemployerlist[] res =
	 * hdfcbackendservice.getemployerlist(filters,searchstr);
	 * 
	 * if(res!=null && res.length!=0) { for(int i=0; i<res.length;i++) { r = new
	 * Select2Results(); r.setId(res[i].getEmployercode());
	 * r.setText(res[i].getEmployername()); dataarr.add(r); } }
	 * 
	 * else { logger.info("Data size is null"); r = new Select2Results();
	 * r.setId("NA"); r.setText("No employer found"); dataarr.add(r); }
	 * 
	 * 
	 * }
	 * 
	 * else { logger.info("Search string is null.. Return null list of employer ");
	 * r = new Select2Results(); r.setId("EMPTY_DATA"); r.setText("No employer");
	 * dataarr.add(r); }
	 * 
	 * logger.info("Returning- "+ dataarr.size()); }catch(Exception e){
	 * 
	 * logger.error("Error fecthing emplotyer list info",e);
	 * 
	 * r = new Select2Results(); r.setId("ERROR"); r.setText("Error fetching list");
	 * dataarr.add(r);
	 * 
	 * }
	 * 
	 * logger.info("Returning list of data");
	 * 
	 * return dataarr; }
	 */

}
