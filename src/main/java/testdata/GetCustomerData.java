package testdata;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetCustomerData {
	public static GetCustomerData get(String filename) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new File(filename), GetCustomerData.class);
	}

	@JsonProperty("customername")
	String customername;

	@JsonProperty("cc")
	CC cc;

	@JsonProperty("address")
	String address;

	@JsonProperty("numb")
	Numb numb;

	public String getAddress() {
		return address;
	}

	public String getCustomerName() {
		return customername;
	}

	public String getCCCity() {
		return cc.city;
	}

	public String getCCState() {
		return cc.state;
	}
	public String getNumbPin() {
		return numb.pin;
	}
	public String getNumbMobile() {
		return numb.mobile;
	}
	
	

	static class CC {
		@JsonProperty("city")
		String city;

		@JsonProperty("state")
		String state;
	}

	static class Numb {
		@JsonProperty("pin")
		String pin;

		@JsonProperty("mobile")
		String mobile;
	}

}
