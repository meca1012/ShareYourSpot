package de.hska.shareyourspot.android.restclient;

import java.net.HttpURLConnection;

import de.hska.shareyourspot.android.domain.User;



public class RestClient extends HttpHandler{
	public static String BASE_URL = "http://192.168.178.63:8080/ShareYourSpot/rest";
	//public static String BASE_URL = "http://www.iwi.hs-karlsruhe.de/ebatc/ShareYourSpot/rest/";

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


	public int loginUser(User user)
	{
//		Integer responseCode = -1;
//		String url = BASE_URL + "/member/loginUser";
//		String xmlObjectStr = serialize(user);
//		if (xmlObjectStr != null) {
//			responseCode = post(url, xmlObjectStr);
//		}
			//return responseCode;
			//TODO: Change to Resposce Code that Login will Work
			return HttpURLConnection.HTTP_ACCEPTED;
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
			
