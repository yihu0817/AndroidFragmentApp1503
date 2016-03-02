package com.scxh.android1503.http;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Constances;
import com.scxh.android1503.util.HttpConnectionUtil;
import com.scxh.android1503.util.HttpConnectionUtil.HttpCallBack;
import com.scxh.android1503.util.HttpConnectionUtil.Method;
import com.scxh.android1503.util.Logs;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class HttpConnectActivity extends Activity implements OnClickListener {
	private Button mConnectBtn, mGetConnetBtn, mPostConnetBtn,
			mGetHttpClientBtn, mConnectGetClientBtn, mConnectPOSTClientBtn,
			mOkHttpBtn, mHttpClientBtn, mJsonHttpBtn;
	private TextView mShowMessageTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http_connect_layout);

		mConnectBtn = (Button) findViewById(R.id.http_connect_btn);
		mShowMessageTxt = (TextView) findViewById(R.id.http_show_message_txt);
		mGetConnetBtn = (Button) findViewById(R.id.http_connect_get_btn);
		mPostConnetBtn = (Button) findViewById(R.id.http_connect_post_btn);
		mGetHttpClientBtn = (Button) findViewById(R.id.http_client_get_btn);
		mConnectGetClientBtn = (Button) findViewById(R.id.http_client_connect_get_btn);
		mConnectPOSTClientBtn = (Button) findViewById(R.id.http_client_connect_post_btn);
		mHttpClientBtn = (Button) findViewById(R.id.http_client_btn);
		mOkHttpBtn = (Button) findViewById(R.id.http_okhttp_btn);
		mJsonHttpBtn = (Button) findViewById(R.id.http_jsonhttp_btn);
		mConnectBtn.setOnClickListener(this);
		mGetConnetBtn.setOnClickListener(this);
		mPostConnetBtn.setOnClickListener(this);
		mGetHttpClientBtn.setOnClickListener(this);
		mConnectGetClientBtn.setOnClickListener(this);
		mConnectPOSTClientBtn.setOnClickListener(this);
		mHttpClientBtn.setOnClickListener(this);
		mOkHttpBtn.setOnClickListener(this);
		mJsonHttpBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.http_connect_btn:
			String httpUrl = Constances.BASE_URL+"/app/print";

			new AsyncTask<String, Void, String>() {
				@Override
				protected String doInBackground(String... params) {
					String httpUrl = params[0];
					try {
						URL url = new URL(httpUrl);
						HttpURLConnection connect = (HttpURLConnection) url
								.openConnection();
						connect.setRequestMethod("GET");
						connect.connect();

						String str = "";
						int statusCode = connect.getResponseCode();
						Logs.v("statusCode  :" + statusCode);
						if (statusCode == HttpURLConnection.HTTP_OK) {
							str = readStrFromInputStream(connect
									.getInputStream());
						}
						return str;

					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;

				}

				protected void onPostExecute(String result) {
					mShowMessageTxt.setText(result);
				}
			}.execute(httpUrl);

			break;

		case R.id.http_connect_get_btn:
			httpUrl = Constances.BASE_URL+"/app/login"; // //http://192.168.1.156/app/login?user=xiaoming&psw=abcd"

			/** 解决get传参汉字乱码问题 */
			String userName = "张三";
			try {
				userName = URLEncoder.encode(userName, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

			httpUrl = httpUrl + "?user=" + userName + "&psw=abcd&sex=男";
			Logs.v("httpUrl :" + httpUrl);

			new AsyncTask<String, Void, String>() {
				@Override
				protected String doInBackground(String... params) {
					String httpUrl = params[0];
					try {
						URL url = new URL(httpUrl);
						HttpURLConnection connect = (HttpURLConnection) url
								.openConnection();
						connect.setRequestMethod("GET");
						connect.connect();

						String str = readStrFromInputStream(connect
								.getInputStream());
						return str;

					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;

				}

				protected void onPostExecute(String result) {
					mShowMessageTxt.setText(result);
				}
			}.execute(httpUrl);

			break;
		case R.id.http_connect_post_btn:
			httpUrl = Constances.BASE_URL+"/app/login";

			new AsyncTask<String, Void, String>() {
				@Override
				protected String doInBackground(String... params) {
					String httpUrl = params[0];
					try {
						/** 建立Http连接 */
						URL url = new URL(httpUrl);
						HttpURLConnection connect = (HttpURLConnection) url
								.openConnection();
						connect.setRequestMethod("POST");
						connect.connect();

						/** 向服务端发送数据 */
						writeStrToOutPutStream("user=张三&psw=123456",
								connect.getOutputStream());

						/** 从服务端读取数据 */
						String str = readStrFromInputStream(connect
								.getInputStream());

						/** 断开Http连接 */
						connect.disconnect();

						return str;

					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;

				}

				protected void onPostExecute(String result) {
					mShowMessageTxt.setText(result);
				}
			}.execute(httpUrl);
			break;
		case R.id.http_client_get_btn:
			httpUrl = Constances.BASE_URL+"/app/print";

			AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
				@Override
				protected String doInBackground(String... params) {
					String url = params[0];
					Logs.v("httpUrl  :" + url);

					HttpClient client = new DefaultHttpClient();
					HttpGet request = new HttpGet(url);

					// HttpPost request = new HttpPost(url);

					try {
						HttpResponse response = client.execute(request);
						String str = EntityUtils.toString(response.getEntity());
						return str;
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}

				@Override
				protected void onPostExecute(String result) {
					super.onPostExecute(result);
					if (result != null) {
						mShowMessageTxt.setText(result);
					}
				}
			};

			task.execute(httpUrl);

			break;
		case R.id.http_client_connect_get_btn:
			httpUrl = Constances.BASE_URL+"/app/login";
			String parameter = "?user=admin&psw=123456";

			httpUrl = httpUrl + parameter;

			AsyncTask<String, Void, String> taskGet = new AsyncTask<String, Void, String>() {
				@Override
				protected String doInBackground(String... params) {
					String url = params[0];

					HttpClient client = new DefaultHttpClient();
					HttpGet request = new HttpGet(url);

					try {
						HttpResponse response = client.execute(request);
						String str = EntityUtils.toString(response.getEntity());
						return str;
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}

				@Override
				protected void onPostExecute(String result) {
					super.onPostExecute(result);
					if (result != null) {
						mShowMessageTxt.setText(result);
					}
				}
			};

			taskGet.execute(httpUrl);
			break;
		case R.id.http_client_connect_post_btn:

			AsyncTask<String, Void, String> postTask = new AsyncTask<String, Void, String>() {

				@Override
				protected String doInBackground(String... params) {
					String url = params[0];

					HttpClient httpClient = new DefaultHttpClient();
					HttpPost request = new HttpPost(url);

					try {
						/** post传参 */
						BasicNameValuePair userNameValue = new BasicNameValuePair(
								"user", "李四");
						BasicNameValuePair pswNameValue = new BasicNameValuePair(
								"psw", "123456");
						ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
						pairs.add(userNameValue);
						pairs.add(pswNameValue);
						HttpEntity entity = new UrlEncodedFormEntity(pairs,
								HTTP.UTF_8);

						request.setEntity(entity);

						HttpResponse response = httpClient.execute(request);

						String str = EntityUtils.toString(response.getEntity());

						return str;
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}

				@Override
				protected void onPostExecute(String result) {
					super.onPostExecute(result);

					mShowMessageTxt.setText(result);
				}
			};

			postTask.execute(Constances.BASE_URL+"/app/login");

			break;
		case R.id.http_client_btn:
			HttpConnectionUtil connect = new HttpConnectionUtil();
			connect.asyncTaskHttp(Constances.BASE_URL+"/app?jsonName=students",
					Method.GET, new HttpCallBack() {

						@Override
						public void returnMessage(String message) {
							Logs.v("message :" + message);
							mShowMessageTxt.setText(message);
						}
					});

			break;

		case R.id.http_okhttp_btn:

			break;

		case R.id.http_jsonhttp_btn:

			break;
		}
	}

	/**
	 * 从输入流读数据
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public String readStrFromInputStream(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		is.close();
		return sb.toString();
	}

	/**
	 * 向服务端写数据
	 * 
	 * @param parameter
	 * @param os
	 * @throws IOException
	 */
	public void writeStrToOutPutStream(String parameter, OutputStream os)
			throws IOException {
		PrintWriter writer = new PrintWriter(os);
		writer.print(parameter);
		writer.flush();

		writer.close();
		os.close();
	}

	/*
	 * public interface Method { public static final String GET = "GET"; public
	 * static final String POST = "POST"; }
	 *//**
	 * 根据传URI 参数，请求方式， 从服务端获取数据
	 * 
	 * @param url
	 * @param method
	 * @param parameter
	 * @return
	 */
	/*
	 * public void httpConnectUtil(String url, final String method, final
	 * HashMap<String, Object> parameter) {
	 * 
	 * AsyncTask<String, Void, String> postTask = new AsyncTask<String, Void,
	 * String>() {
	 * 
	 * @Override protected String doInBackground(String... params) { String url
	 * = params[0];
	 * 
	 * HttpClient httpClient = new DefaultHttpClient();
	 * 
	 * if (method.equals(Method.GET)) { if (parameter != null) {
	 *//**
	 * 组装Get请求参数 http://192.168.1.156/app/login?user=张三&psw=123456"
	 * */
	/*
	 * url = url + "?"; StringBuilder sb = new StringBuilder(url); for (String
	 * key : parameter.keySet()) { Object values = parameter.get(key);
	 * sb.append(key); sb.append("="); sb.append(values); sb.append("&"); } url
	 * = sb.substring(0, sb.length() - 1); }
	 * 
	 * HttpGet request = new HttpGet(url); try { HttpResponse response =
	 * httpClient.execute(request); String str =
	 * EntityUtils.toString(response.getEntity());
	 * 
	 * return str; } catch (ClientProtocolException e) { e.printStackTrace(); }
	 * catch (IOException e) { e.printStackTrace(); } return null;
	 * 
	 * } else { HttpPost request = new HttpPost(url); try {
	 *//** post传参 */
	/*
	 * // BasicNameValuePair userNameValue = new // BasicNameValuePair("user",
	 * "李四"); // BasicNameValuePair pswNameValue = new //
	 * BasicNameValuePair("psw", "123456"); // pairs.add(userNameValue); //
	 * pairs.add(pswNameValue); if (parameter != null) {
	 * ArrayList<BasicNameValuePair> pairs = new
	 * ArrayList<BasicNameValuePair>(); for (String key : parameter.keySet()) {
	 * Object values = parameter.get(key); BasicNameValuePair userNameValue =
	 * new BasicNameValuePair( key, (String) values); pairs.add(userNameValue);
	 * }
	 * 
	 * HttpEntity entity = new UrlEncodedFormEntity(pairs, HTTP.UTF_8);
	 * 
	 * request.setEntity(entity); } HttpResponse response =
	 * httpClient.execute(request); String str =
	 * EntityUtils.toString(response.getEntity());
	 * 
	 * return str; } catch (ClientProtocolException e) { e.printStackTrace(); }
	 * catch (IOException e) { e.printStackTrace(); } return null; }
	 * 
	 * }
	 * 
	 * @Override protected void onPostExecute(String result) {
	 * super.onPostExecute(result);
	 * 
	 * mShowMessageTxt.setText(result); } };
	 * 
	 * postTask.execute(url);
	 * 
	 * }
	 */

}
