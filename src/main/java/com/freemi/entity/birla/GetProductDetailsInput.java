package com.freemi.entity.birla;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetProductDetailsInput {
	
	@JsonProperty("ProductDetailsRequest")
	private ProductDetailsRequest productDetailsRequestObject;
	
	

	public ProductDetailsRequest getProductDetailsRequestObject() {
		return productDetailsRequestObject;
	}



	public void setProductDetailsRequestObject(ProductDetailsRequest productDetailsRequestObject) {
		this.productDetailsRequestObject = productDetailsRequestObject;
	}



	public static class ProductDetailsRequest {

		@JsonProperty("UserId")
		private String userId;
		
		@JsonProperty("Password")
		private String password;
		
		@JsonProperty("authCode")
		private String AuthCode;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getAuthCode() {
			return AuthCode;
		}

		public void setAuthCode(String authCode) {
			AuthCode = authCode;
		}
		
		


	}
}
