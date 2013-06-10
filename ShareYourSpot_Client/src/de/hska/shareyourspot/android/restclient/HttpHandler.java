package de.hska.shareyourspot.android.restclient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import de.hska.shareyourspot.android.domain.User;

import android.os.StrictMode;



abstract class HttpHandler {
	
	public final String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
	
	public HttpHandler()
	{
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = 
			        new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			}	
	}
	
	public static enum DomainType {
		User
	};
	
	protected Object get(String uri, DomainType type) {
		Object obj = null;
		try {
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String output = br.readLine();
			obj = deserialize(output, type);
			
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	private int getResponseCode(String uri) {
		int responseCode = -1;
		try {
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			responseCode = conn.getResponseCode();
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseCode;
	}

	protected int post(String uri, String input) {
		int statusCode = 0;
		try {
			input = xmlHeader + input;
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/xml");
			OutputStream os = conn.getOutputStream();
			if (input != null)
				os.write(input.getBytes());
			os.flush();
			statusCode = conn.getResponseCode();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED &&
				conn.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED &&
				conn.getResponseCode() != HttpURLConnection.HTTP_NOT_FOUND
				)  {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			statusCode = conn.getResponseCode();
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return statusCode;
	}

	protected int delete(String uri) {
		int statusCode = 0;
		try {
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			statusCode = conn.getResponseCode();
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return statusCode;
	}

	protected String serialize(Object obj) {
		Serializer serializer = new Persister();
		String output = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			serializer.write(obj, os);
			output = new String(os.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	private Object deserialize(String xmlStr, DomainType type) {
		Serializer serializer = new Persister();
		try {
			switch (type) {
			case User:
				return serializer.read(User.class, xmlStr);

			default:
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Problem with SimpleXML");
		}
		return null;
	}
}
