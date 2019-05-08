package com.algosoft.gov.school.services;

import android.util.Log;


import com.algosoft.gov.school.utility.KeyGenerationClass;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class JSONParser {
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private JSONObject jsonObject;

	// constructor
	public JSONParser() {
	}
	public JSONObject getJSONFromUrl(String url, List<NameValuePair> params) {
	// Making HTTP request
	try {
	// defaultHttpClient
	DefaultHttpClient httpClient = new DefaultHttpClient();
	HttpPost httpPost = new HttpPost(url);
	httpPost.addHeader( "Apikey" , KeyGenerationClass.getEncryptedKey());
	httpPost.addHeader( "Apidate" , KeyGenerationClass.getDate());
	httpPost.setEntity(new UrlEncodedFormEntity(params));
	HttpResponse httpResponse = httpClient.execute(httpPost);
	HttpEntity httpEntity = httpResponse.getEntity();
	is = httpEntity.getContent();
	} catch (UnsupportedEncodingException e) {
	e.printStackTrace();
	} catch (ClientProtocolException e) {
	e.printStackTrace();
	} catch (IOException e) {
	e.printStackTrace();
	}
	try {
	BufferedReader reader = new BufferedReader(new InputStreamReader(
	is, "utf-8"), 8);
	StringBuilder sb = new StringBuilder();
	String line = null;
	while ((line = reader.readLine()) != null) {
	sb.append(line + "\n");
	}
	is.close();
	json = sb.toString();
//	Log.e("JSON", json);
	} catch (Exception e) {
	Log.e("Buffer Error", "Error converting result " + e.toString());
	}
	// try parse the string to a JSON object
	try {
	jObj = new JSONObject(json);
	} catch (JSONException e) {
	Log.e("JSON Parser", "Error parsing data " + e.toString());
	}
	// return JSON String
	return jObj;
	}

	public JSONObject getServerResponseWithJSON(String Url, String jsonData)
	{
		HttpURLConnection urlConnection = null;
		BufferedReader reader = null;
		try {
			URL url = new URL(Url);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			// is output buffer writter
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Accept", "application/json");
			//set headers and method
			Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
			writer.write(jsonData);
			// json data
			writer.close();
			InputStream inputStream = urlConnection.getInputStream();
			//input stream
			StringBuffer buffer = new StringBuffer();
			if (inputStream == null) {
				// Nothing to do.
				return null;
			}
			reader = new BufferedReader(new InputStreamReader(inputStream));

			String inputLine;
			while ((inputLine = reader.readLine()) != null)
				buffer.append(inputLine + "\n");
			if (buffer.length() == 0) {
				// Stream was empty. No point in parsing.
				return null;
			}
			String JsonResponse = buffer.toString();
			//ResponseClass data
			Log.i("ServerResponse",JsonResponse);
			try {
			//send to post execute
				return (new JSONObject(JsonResponse));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;

		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (final IOException e) {
					Log.e("", "Error closing stream", e);
				}
			}
		}
		return null;
	}

	public JSONObject imagesTextsMultipartPost(String url,String phone,String imgPath1){

		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost poster = new HttpPost(url);
			File image1 = new File(imgPath1);
			MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			entity.addPart("phone", new StringBody(phone));
			entity.addPart("image", new FileBody(image1));
			poster.setEntity(entity );

			client.execute(poster, new ResponseHandler<Object>() {
				public Object handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					HttpEntity respEntity = response.getEntity();
					String responseString = EntityUtils.toString(respEntity);
					Log.e("ImageUploadRes:", responseString);
					// try parse the string to a JSON object
					try {
						jsonObject = new JSONObject(responseString);
					} catch (JSONException e) {
						Log.e("JSON Parser", "Error parsing data " + e.toString());
					}
					return jsonObject;
				}
			});
		} catch (Exception e){
			//do something with the error
		}

		return jsonObject;
	}

}
