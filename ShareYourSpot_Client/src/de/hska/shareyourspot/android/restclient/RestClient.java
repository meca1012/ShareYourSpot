package de.hska.shareyourspot.android.restclient;

import java.net.HttpURLConnection;
import java.util.List;

import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.domain.Post;
import de.hska.shareyourspot.android.domain.Users;


public class RestClient extends HttpHandler{

	//public static String BASE_URL = "http://192.168.178.63:8080/ShareYourSpot/rest";
	public static String BASE_URL = "http://10.85.41.8:8080/ShareYourSpot-1/rest";

	public User getBenutzerXML() {
		String url = BASE_URL +  "/member/getUserXML";
		User benutzer = (User) get(url, DomainType.User);
		return benutzer;
	}
	
	public int registerUser(User user)
	{
		Integer responseCode = -1;
		String url = BASE_URL + "/member/createUser";
		String xmlObjectStr = serialize(user);
		if (xmlObjectStr != null) {
			responseCode = post(url, xmlObjectStr);
		}
		return responseCode;
	}
	
	public int createPost(Post post)
	{
		Integer responseCode = -1;
		String url = BASE_URL + "/post/createPost";
		String xmlObjectStr = serialize(post);
		if (xmlObjectStr != null) {
			responseCode = post(url, xmlObjectStr);
		}
		return responseCode;
	}
	
	public int createGroup(Party party)
	{
		Integer responseCode = -1;
		String url = BASE_URL + "/post/createGroup";
		String xmlObjectStr = serialize(party);
		if (xmlObjectStr != null) {
			responseCode = post(url, xmlObjectStr);
		}
		return responseCode;
	}


	public int loginUser(User user)
	{
		Integer responseCode = -1;
		String url = BASE_URL + "/member/loginUser";
		String xmlObjectStr = serialize(user);
		if (xmlObjectStr != null) {
			responseCode = post(url, xmlObjectStr);
		}
			return responseCode;
			//TODO: Change to Resposce Code that Login will Work
			//return HttpURLConnection.HTTP_ACCEPTED;
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
}

//SAMPLES
		
		//POST
			
		//	public Integer createNewBenutzer(Benutzer benutzer) {
		//		Integer responseCode = -1;
		//		String uri = BASE_URL + BENUTZER;
		//		String xmlStr = serialize(benutzer);
		//		if (xmlStr != null) {
		//			responseCode = post(uri, xmlStr);
		//		}
		//		return responseCode;
		//	}
		
		//DELETE
		//	public int deleteTaetikeit(String taetigkeitId) {
		//		String uri = BASE_URL + TAETIGKEIT + taetigkeitId;
		//		Integer statusCode = delete(uri);
		//		return statusCode;
		//	}
			
