package online.shixun.asl.core;

import java.util.HashMap;
import java.util.Map;

public class BaseAction {
	
	/**
     * ���سɹ��Ľ��
     * */
    protected Map<String,Object> getSuccessResult(Object data){
        Map<String,Object>result = new HashMap<>();
        result.put("code",0);
        result.put("msg","ok");
        result.put("data",data);
        return result;
    }
    
    /**
     * ����ʧ�ܵĽ��
     * */
    protected Map<String,Object>getFailResult(int code,String msg){
        Map<String,Object>result = new HashMap<>();
        result.put("code",code);
        result.put("msg",msg);
        result.put("data",null);
        return result;
    }
}
