package de.hska.shareyourspot.android.domain;

import java.io.Serializable;
import java.util.Date;
import static de.hska.shareyourspot.android.helper.Constants.KEINE_ID;
import org.simpleframework.xml.Element;

public class Picture implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4118518519877132454L;
	
	public static final String PREFIX = "Picture.";
	public static final String PICTURE = "findPicture";
	public static final String PICTURE_BY_ID = PREFIX
			+ "Picture.findPictureById";

	private Long pictureId = KEINE_ID;

	@Element(required=false)
	private byte[] imgData;

	@Element(required=false)
	private String imgType;
	
	@Element(required=false)
	private Double latitude;
	
	@Element(required=false)
	private Double longitude;

	@Element(required=false)
	private Date created;

	@Element(required=false)
	private Date modified;
	
	public Picture() {
	}

	public Long getPictureId() {
		return pictureId;
	}

	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}

	public byte[] getImgData() {
		return imgData;
	}

	public void setImgData(byte[] imgData) {
		this.imgData = imgData;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public void setValues(Picture picture) {
		this.created = picture.created;
		this.imgData = picture.imgData;
		this.imgType = picture.imgType;
		this.modified = picture.created;
		this.pictureId = picture.pictureId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imgType == null) ? 0 : imgData.hashCode());
		result = prime * result + ((imgData == null) ? 0 : imgData.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result
				+ ((modified == null) ? 0 : modified.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		Picture other = (Picture) obj;

		if (imgType == null) {
			if (other.imgType != null) {
				return false;
			}
		} else if (!imgType.equals(other.imgType)) {
			return false;
		}

		if (imgData == null) {
			if (other.imgData != null) {
				return false;
			}
		} else if (!imgData.equals(other.imgData)) {
			return false;
		}

		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
			return false;
		}

		if (modified == null) {
			if (other.modified != null) {
				return false;
			}
		} else if (!modified.equals(other.modified)) {
			return false;
		}

		return true;
	}
}
