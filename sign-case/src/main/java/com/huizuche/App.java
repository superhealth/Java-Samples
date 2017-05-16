package com.huizuche;

import com.huizuche.request.CancelGuidanceRequest;
import com.huizuche.sign.SignatureManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//组装请求参数
    	CancelGuidanceRequest request=new CancelGuidanceRequest();
    	request.setGuidanceOrderID("G0341914627");
    	
    	//验证签名
    	SignatureManager sign=new SignatureManager();
    	sign.verifySign(request, "ctrip_api");
    }
}
