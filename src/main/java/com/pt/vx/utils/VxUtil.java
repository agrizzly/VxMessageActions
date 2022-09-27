package com.pt.vx.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.pt.vx.domain.TokenInfo;


import java.util.logging.Logger;


public class VxUtil {

    private static final String AppID = "wx2599c7926ea0f7cc";

    private static final String appSecret= "0efee3df8bebaaac882aff0e43341ee4";

    private static final Logger log = Logger.getAnonymousLogger();

    public static TokenInfo getToken(){
        String TOKEN_URL ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
        String result = HttpUtil.get(String.format(TOKEN_URL, AppID, appSecret));
        return JSONUtil.toBean(result,TokenInfo.class);
    }


    public static void sendMessage(String message){
        if("wx2599c7926ea0f7cc".equals(AppID) || "0efee3df8bebaaac882aff0e43341ee4".equals(appSecret)){
            log.warning("请先填写AppID和appSecret");
            return;
        }
        String PUSH_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";
        TokenInfo token = getToken();
        String result =  HttpUtil.post(String.format(PUSH_URL,token.getAccess_token()),message);
        log.info(result);
    }

}
