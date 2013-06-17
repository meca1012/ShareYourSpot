package de.hska.shareyourspot.android.restclient;

import java.net.HttpURLConnection;
import java.util.List;

import de.hska.shareyourspot.android.domain.Parties;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.domain.Post;
import de.hska.shareyourspot.android.domain.Users;
import de.hska.shareyourspot.android.restclient.HttpHandler.DomainType;

public class RestClient extends HttpHandler {

	// public static String BASE_URL =
	// "http://192.168.178.63:8080/ShareYourSpot/rest";

	public static String BASE_URL = "http://10.85.41.8:8080/ShareYourSpot-1/rest";
//	public static String BASE_URL = "http://10.0.2.2:8080/ShareYourSpot-1/rest";

	public User getBenutzerXML() {
		String url = BASE_URL + "/member/getUserXML";
		User benutzer = (User) get(url, DomainType.User);
		return benutzer;
	}

	public int registerUser(User user) {
		Integer responseCode = -1;
		String url = BASE_URL + "/member/createUser";
		String xmlObjectStr = serialize(user);
		if (xmlObjectStr != null) {
			responseCode = post(url, xmlObjectStr);
		}
		return responseCode;
	}

	public Post createPost(Post post) {
		Integer responseCode = -1;
		String url = BASE_URL + "/post/createPost";
		String xmlObjectStr = serialize(post);
		System.out.println(xmlObjectStr);
		if (xmlObjectStr != null) {
			post = (Post)post(url, xmlObjectStr, DomainType.Post);
		}
		return post;
	}

	// TODO: Comment.java from Backend
	public int addComment(Post post) {
		Integer responseCode = -1;
		String url = BASE_URL + "/post/createPost";
		String xmlObjectStr = serialize(post);
		if (xmlObjectStr != null) {
			responseCode = post(url, xmlObjectStr);
		}
		return responseCode;
	}

	public int createGroup(Party party) {
		Integer responseCode = -1;
		String url = BASE_URL + "/member/createParty";
		String xmlObjectStr = serialize(party);
		if (xmlObjectStr != null) {
			responseCode = post(url, xmlObjectStr);
		}
		return responseCode;
	}

	public User addFriend(User user) {
		// Integer responseCode = -1;
		String url = BASE_URL + "/post/addFriend";
		String xmlObjectStr = serialize(user);
		if (xmlObjectStr != null) {
			user = (User) post(url, xmlObjectStr, DomainType.User);
		}
		// return responseCode;
		return user;
	}

	public User joinGroup(User user) {
		// Integer responseCode = -1;
		String url = BASE_URL + "/member/addUserToParty";
		String xmlObjectStr = serialize(user);
		if (xmlObjectStr != null) {
			user = (User) post(url, xmlObjectStr, DomainType.User);
		}
		// return responseCode;
		return user;
	}

	public User loginUser(User user) {
		User reponseUser = null;
		String url = BASE_URL + "/member/login";
		String xmlObjectStr = serialize(user);
		if (xmlObjectStr != null) {
			reponseUser = (User) post(url, xmlObjectStr, DomainType.User);
			return reponseUser;
		}
		return reponseUser;
	}

	public Users searchUser(User user) {
		Users foundUsers = null;
		String url = BASE_URL + "/member/findUserByName";
		String xmlObjectStr = serialize(user);
		if (xmlObjectStr != null) {
			foundUsers = (Users) post(url, xmlObjectStr, DomainType.Users);
		}
		return foundUsers;
	}
	
	public Parties searchParties(Party party) {
		Parties parties = null;
		String url = BASE_URL + "/member/findParty";
		String xmlObjectStr = serialize(party);
		if (xmlObjectStr != null) {
			parties = (Parties) post(url, xmlObjectStr, DomainType.Parties);
		}
		return parties;
	}
	
	public Parties getParties() {
		String url = BASE_URL + "/member/getAllParties";
		Parties parties = (Parties) get(url, DomainType.Parties);
		return parties;
	}
	

	public Parties getPartiesForUser(User u) {
		Parties p = new Parties();
		String url = BASE_URL + "/member/getPartiesByUser";
		String xmlObjectStr = serialize(u);
	
		if (xmlObjectStr != null) {
			p = (Parties) post(url, xmlObjectStr, DomainType.Parties);
		}
		return p;
}
	public Party getParty(Long id) {
		String url = BASE_URL + "/member/getParty/" + id;
		Party party= (Party) get(url, DomainType.Party);
		return party;
	}
	
}

// SAMPLES

// POST

// public Integer createNewBenutzer(Benutzer benutzer) {
// Integer responseCode = -1;
// String uri = BASE_URL + BENUTZER;
// String xmlStr = serialize(benutzer);
// if (xmlStr != null) {
// responseCode = post(uri, xmlStr);
// }
// return responseCode;
// }

// DELETE
// public int deleteTaetikeit(String taetigkeitId) {
// String uri = BASE_URL + TAETIGKEIT + taetigkeitId;
// Integer statusCode = delete(uri);
// return statusCode;
// }

