package de.hska.shareyourspot.android.domain.lists;


import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="longPartyIds")
public class LongPartyIds {

	@ElementList(name = "partyId")
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
	
	public void setParties(Long partyId) {
		if(this.parties == null){
			this.parties = new ArrayList<Long>();
		}
		this.parties.add(partyId);
	}
}


