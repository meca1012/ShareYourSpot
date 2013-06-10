package de.hska.shareyourspot.android.domain;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="users")
public class Users {
	
	@ElementList(inline=true)
	private ArrayList<User> users;

	public Users() {
		this.users = new ArrayList<User>();
	}
	public Users(ArrayList<User> users) {
		this.users = users;
	}

	public boolean isEmpty() {
		if (this.users.size() == 0)
			return true;
		else
			return false;
	}

	public int size() {
		return this.users.size();
	}

	public void addUser(User user) {
		this.users.add(user);
	}

	public void addUserList(List<User> users) {
		this.users.addAll(users);
	}

	public ArrayList<User> getAllUser() {
		return this.users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		String str = null;
		if (this.users.size() == 0)
			str = "Es gibt keine User!";
		else {
			str = "Users = [\n";
			for (User user : users) {
				str += (user.getId() + " " + user.getName() + 
						" (" + user.getEmail() + ")" + ",\n");
			}
			str += "]";
		}
		return str;
	}

}
