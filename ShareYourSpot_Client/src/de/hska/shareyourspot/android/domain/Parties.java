package de.hska.shareyourspot.android.domain;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="parties")
public class Parties {

		@ElementList(inline=true)
		private ArrayList<Party> parties;

		public Parties() {
			this.parties = new ArrayList<Party>();
		}
		public Parties(ArrayList<Party> parties) {
			this.parties = parties;
		}

		public boolean isEmpty() {
			if (this.parties.size() == 0)
				return true;
			else
				return false;
		}

		public int size() {
			return this.parties.size();
		}

		public void addParty(Party party) {
			this.parties.add(party);
		}

		public void addPartyList(List<Party> parties) {
			this.parties.addAll(parties);
		}

		public ArrayList<Party> getAllParties() {
			return parties;
		}
		public void setUsers(ArrayList<Party> parties) {
			this.parties = parties;
		}

		@Override
		public String toString() {
			String str = null;
			if (parties.size() == 0)
				str = "Es gibt keine Party!";
			else {
				str = "Parties = [\n";
				for (Party partie : parties) {
					str += (partie.getPartyId() + " " + partie.getName() + "\n");
				}
				str += "]";
			}
			return str;
		}
	
}
