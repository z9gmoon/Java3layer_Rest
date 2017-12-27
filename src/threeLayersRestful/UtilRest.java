package threeLayersRestful;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;






public class UtilRest {
	
	public static String getMethod(String url){
		try (
				CloseableHttpClient httpclient = HttpClients.createDefault()) {
//            HttpGet httpget = new HttpGet("http://localhost/ltweb1/rest/sample.php/sinhvien");
            HttpGet httpget = new HttpGet(url);
//            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
//            System.out.println("----------------------------------------");
//            System.out.println(responseBody);
            return responseBody;
        }
		catch(Exception e){return null;}
	}
	public static String postMethod(String url,String json_string){
		try (
				CloseableHttpClient httpclient = HttpClients.createDefault()) {
			//"http://localhost/ltweb1/rest/sample.php/sinhvien/"
            HttpPost httpPost = new HttpPost("http://localhost:8888/api/sinhvien");
            //"{\"id\":15,\"ten\":\"19\",\"khoa\":131122}"
            StringEntity input = new StringEntity(json_string);
            input.setContentType("application/json");
            httpPost.setEntity(input);

//            System.out.println("Executing request " + httpPost.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
                }

            };
            String responseBody = httpclient.execute(httpPost, responseHandler);
//            System.out.println("----------------------------------------");
//            System.out.println(responseBody);
            return responseBody; 
        }
		catch(IOException e){return null;}
	}
	public static String putMethod(String url,String json_string){
		try (
				CloseableHttpClient httpclient = HttpClients.createDefault()) {
			//"http://localhost/ltweb1/rest/sample.php/sinhvien/15"
            HttpPut httpPut = new HttpPut(url);
            //"{\"id\":15,\"ten\":\"19\",\"khoa\":1555555}"
            StringEntity input = new StringEntity(json_string);
            input.setContentType("application/json");
            httpPut.setEntity(input);
            System.out.println(url);
            System.out.println(json_string);

//            System.out.println("Executing request " + httpPut.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
                }

            };
            String responseBody = httpclient.execute(httpPut, responseHandler);
//            System.out.println("----------------------------------------");
//            System.out.println(responseBody);
            return responseBody;
        }
		catch(IOException e){return null;}
	}
	public static String delMethod(String url){
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			//"http://localhost/ltweb1/rest/sample.php/sinhvien/15"
            HttpDelete httpDelete = new HttpDelete(url);

//            System.out.println("Executing request " + httpDelete.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
                }

            };
            String responseBody = httpclient.execute(httpDelete, responseHandler);
//            System.out.println("----------------------------------------");
//            System.out.println(responseBody);
            return responseBody;
        }
		catch(Exception e){return null;}
		
	}
	
	public static Sinhvien JsonToSv(String jsonString){
		
		try{
			Sinhvien sv = new Sinhvien();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsonString);
		JSONObject objJSON = (JSONObject)obj;

		
		//change here
		sv.setMssv(Integer.parseInt(objJSON.get("mssv").toString()));
		sv.setHoTen(objJSON.get("ten").toString());
		sv.setDoB(objJSON.get("ngaysinh").toString());
		sv.setAve(Float.parseFloat(objJSON.get("diemTB").toString()));
		Lop lop = new Lop();
		lop.setIdLop(Integer.parseInt(objJSON.get("idlop").toString()));
		sv.setLop(lop);
		return sv;
		}
		catch(ParseException e){return null;}
		
	}
	
	
	public static Lop JsonToLop(String jsonString){
		try{
			Lop lop = new Lop();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsonString);
		JSONObject objJSON = (JSONObject)obj;

		
		//change here
		lop.setIdLop(Integer.parseInt(objJSON.get("idlop").toString()));
		lop.setTenLop(objJSON.get("tenlop").toString());
		return lop;
		}
		catch(ParseException e){return null;}
	}
	
	@SuppressWarnings("unchecked")
	public static String ObjToJson(Sinhvien sv){
			
	    	  JSONObject obj = new JSONObject();

	  		
	  		//change here
	  		obj.put("mssv", new Integer(sv.getMssv()));	
	  		obj.put("ten",sv.getHoTen());
	  		obj.put("ngaysinh", sv.getDoB());
	  		obj.put("diemTB", new Float(sv.getAve()));
	  		obj.put("idlop", sv.getLop().getIdLop());
			return obj.toJSONString();
	}
}
