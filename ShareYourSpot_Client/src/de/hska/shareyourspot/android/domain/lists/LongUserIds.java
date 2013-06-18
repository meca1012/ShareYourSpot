package de.hska.shareyourspot.android.domain.lists;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "longUserIds")

public class LongUserIds {

	@Element(name = "userId")
	private List<Long> users;
	
	public LongUserIds() {
		super();
	}

	public List<Long> getUsers() {
		return users;
	}

	public void setUsers(List<Long> users) {
		this.users = users;
	}	
	
	public void setUsers(Long userId) {
		if(this.users == null){
			this.users = new ArrayList<Long>();
		}
		this.users.add(userId);
	}
}
