package gaodemap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class GetLocationGEO {
	
	public static void main(String[] args) {
		getExamPlace();
//		getGEOFromGaode("北京市朝阳区阜通东大街6号","北京市");
	}
	
	private static List<ExamPlace> getExamPlace() {
		String s = FileUtil.getText(GetLocationGEO.class, "place.csv");
		String[] line = s.split("\n");
		List<ExamPlace> places = new ArrayList<>();
		for (String l : line) {
			System.out.println(l);
		}
		return places;
	}
	
	public static String getGEOFromGaode(String address,String city){
		CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            // 创建httpget.    
            HttpGet httpget = new HttpGet("http://restapi.amap.com/v3/geocode/geo?address="+address+"&city="+city+"&output=JSON&key=b13bfc1b372dae53408d792edc5cbaaa");  
            System.out.println("executing request " + httpget.getURI());  
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();  
                System.out.println("--------------------------------------");  
                // 打印响应状态    
                System.out.println(response.getStatusLine());  
                if (entity != null) {  
                    // 打印响应内容长度    
                    System.out.println("Response content length: " + entity.getContentLength());  
                    // 打印响应内容    
//                    System.out.println("Response content: " + EntityUtils.toString(entity));  
                    JSONObject jsonObject=JSON.parseObject(EntityUtils.toString(entity));
                    JSONArray jsonArray=jsonObject.getJSONArray("geocodes");
                    JSONObject jsonObject1=jsonArray.getJSONObject(0);
                    return jsonObject1.getString("location");
                }  
                System.out.println("------------------------------------");  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
		return "";  
	}
}
