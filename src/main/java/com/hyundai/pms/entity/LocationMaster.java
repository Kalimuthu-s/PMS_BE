package com.hyundai.pms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="location_master")
public class LocationMaster {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="location_id")
	    private Long locationId;
		
		@Column(name="location_name")
	    private String locationName;
		
		@Column(name="city")
	    private String city;
		
		@Column(name="state")
	    private String state;
		
		@Column(name="country")
	    private String country;
		
		@Column(name="postal_code")
		private String postalCode;
	    
	    @Override
		public String toString() {
			return "LocationMaster [locationId=" + locationId + ", locationName=" + locationName + ", city=" + city
					+ ", state=" + state + ", country=" + country + ", postalCode=" + postalCode + "]";
		}
		
		public Long getLocationId() {
			return locationId;
		}
		public void setLocationId(Long locationId) {
			this.locationId = locationId;
		}
		
		public String getLocationName() {
			return locationName;
		}
		public void setLocationName(String locationName) {
			this.locationName = locationName;
		}
		
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		
		public String getPostalCode() {
			return postalCode;
		}
		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}
}