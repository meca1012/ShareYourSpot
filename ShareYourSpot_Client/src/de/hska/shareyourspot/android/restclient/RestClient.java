package de.hska.shareyourspot.android.restclient;

import java.net.HttpURLConnection;
import java.util.List;

import de.hska.shareyourspot.android.domain.Comment;
import de.hska.shareyourspot.android.domain.Parties;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.Posts;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.domain.Post;
import de.hska.shareyourspot.android.domain.Users;

public class RestClient extends HttpHandler {

	//public static String BASE_URL = "http://10.85.41.8:80/ShareYourSpot/rest";
	//http://www.iwi.hs-karlsruhe.de/ShareYourSpot/rest/member/getUserXML
	//public static String BASE_URL = "http://54.229.23.113:8080/ShareYourSpot/rest";
	//public static String BASE_URL = "http://192.168.0.27:8080/ShareYourSpot-1/rest";
	//public static String BASE_URL = "http://10.0.2.2:8080/ShareYourSpot-1/rest";
	public static String BASE_URL = "http://www.iwi.hs-karlsruhe.de/ShareYourSpot/rest";

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
			post = (Post) post(url, xmlObjectStr, DomainType.Post);
		}
		return post;
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

	public User addUserToParty(User user) {
		// Integer responseCode = -1;
		String url = BASE_URL + "/member/addUserToParty";
		String xmlObjectStr = serialize(user);
		if (xmlObjectStr != null) {
			user = (User) post(url, xmlObjectStr, DomainType.User);
		}
		// return responseCode;
		return user;
	}
	
	public User joinGroup(Long userId, Long partyId) {
		String url = BASE_URL + "/member/joinGroup/"+userId+"/"+partyId;
		User user = (User) get(url, DomainType.User);
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
	
	//	allParties
	public Parties getAllParties() {
		String url = BASE_URL + "/member/getAllParties";
		Parties parties = (Parties) get(url, DomainType.Parties);
		return parties;
	}

	public Party getPartyByName(Party party) {
		Party returnParty = new Party();
		String url = BASE_URL + "/member/getPartyByName";
		String xmlObjectStr = serialize(party);
		if (xmlObjectStr != null) {
			returnParty = (Party) post(url, xmlObjectStr, DomainType.Party);
		}
		return returnParty;
	}

	// myParties
	public Parties getPartiesByUser(User u) {
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
		Party party = (Party) get(url, DomainType.Party);
		return party;
	}
	
	public Post getPost(Long id) {
		String url = BASE_URL + "/post/getPost/" + id;
		Post post = (Post) get(url, DomainType.Post);
		return post;
	}
	
	public User leaveGroup(Long userId, Long groupId) {
		String url = BASE_URL + "/member/leaveGroup/" + userId +"/"+groupId;
		User user = (User) get(url, DomainType.User);
		return user;
	}

	public Comment addComment(Comment comment) {
		Integer responseCode = -1;
		Comment returnComment = new Comment();
		String url = BASE_URL + "/post/addCommentToPost";
		String xmlObjectStr = serialize(comment);
		if (xmlObjectStr != null) {
			returnComment = (Comment) post(url, xmlObjectStr, DomainType.Comment);
		}
		System.out.println(responseCode);
		return returnComment;
	}
	
	public Comment getComment(Long id) {
		String url = BASE_URL + "/post/getComment/" + id;
		Comment comment = (Comment) get(url, DomainType.Comment);
		return comment;
	}
	
//	public Comments getCommentsToPost(Long id){
//		String url = BASE_URL + "/post/getCommentsToPost/" + id;
//		Comments comments = (Comments) get(url, DomainType.Comments);
//		return comments;
//	}

	public Posts getPostByUser(User user) {
		Posts p = new Posts();
		String url = BASE_URL + "/post/getAllPosts";
		String xmlObjectStr = serialize(user);

		if (xmlObjectStr != null) {
			p = (Posts) post(url, xmlObjectStr, DomainType.Posts);
		}
		return p;
	}
	
	public boolean storeJpegOnServer(byte[] file, String Filename) throws Exception
	{
		executeMultipartPost(BASE_URL + "" , "spotPicture", file);
		return true;
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

