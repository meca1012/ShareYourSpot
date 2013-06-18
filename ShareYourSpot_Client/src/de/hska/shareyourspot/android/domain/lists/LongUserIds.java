package de.hska.shareyourspot.android.domain.lists;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "longUserIds")
@XmlAccessorType(FIELD)
public class LongUserIds {

	@XmlElement(name = "userId")
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
}
