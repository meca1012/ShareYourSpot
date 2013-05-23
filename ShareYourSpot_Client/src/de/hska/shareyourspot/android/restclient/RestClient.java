package de.hska.shareyourspot.android.restclient;

import de.hska.shareyourspot.android.domain.User;


public class RestClient extends HttpHandler{
	public static String BASE_URL = "http://192.168.178.52:8080/ShareYourSpot/rest";

	public User getBenutzerXML() {
		String uri = BASE_URL +  "/member/getUserXML";
		User benutzer = (User) get(uri, DomainType.User);
		return benutzer;
	}
	}
