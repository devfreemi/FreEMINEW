package com.freemi.services.interfaces;

import org.springframework.stereotype.Component;

import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.Otpform;
import com.freemi.entity.general.Otprequeststatus;


@Component
public interface Verifydetailsinterface {
	
	public Otprequeststatus generatemobileotp(Otpform otpform,ClientSystemDetails systemdetails, String sessionid );
	
	public Otprequeststatus verifymobileotp(Otpform otpform,ClientSystemDetails systemdetails,String sessionid );
	
	public Otprequeststatus generateemailotp(Otpform otpform,ClientSystemDetails systemdetails,String sessionid );
	
	public Otprequeststatus verifyemailotp(Otpform otpform,ClientSystemDetails systemdetails,String sessionid );
	
}
