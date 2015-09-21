package spittr;


import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Spittle {
	private final Long id;
	private final Date time;
	private final String message;
	private Double longitude;
	private Double latitude;
	
	public Spittle(Date time, String message) {
		this(time, message, null, null);
	}
	
	public Spittle(Date time, String message, Double longitude, Double latitude) {
		this.id = null;
		this.time = time;
		this.message = message;
		this.longitude = longitude;
		this.latitude = latitude;
	}



	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Long getId() {
		return id;
	}
	public Date getTime() {
		return time;
	}
	public String getMessage() {
		return message;
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "id", "time");
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id", "time");
	}
	
}
