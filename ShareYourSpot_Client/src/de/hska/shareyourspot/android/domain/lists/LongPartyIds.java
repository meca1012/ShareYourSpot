package de.hska.shareyourspot.android.domain.lists;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LongPartyIds")
@XmlAccessorType(FIELD)
public class LongPartyIds {

	@XmlElement(name = "partyId")
	private List<Long> parties;
	
	public LongPartyIds() {
		super();
	}

	public List<Long> getParties() {
		return parties;
	}

	public void setParties(List<Long> parties) {
		this.parties = parties;
	}
}


