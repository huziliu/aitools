package zuel.aitools.service;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import zuel.aitools.Constants.Constants;

import java.util.HashMap;
import java.util.Map;

@Service
public class carDetectServiceImp implements carDetectService{
    @Override
    public String get_car(String path) {
        // 初始化一个AipImageClassify
        AipImageClassify client = new AipImageClassify(Constants.APP_ID_GRAPH, Constants.API_KEY_GRAPH, Constants.SECRET_KEY_GRAPH);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
       /* client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理*/

        // 调用接口
        HashMap<String,String> options=new HashMap<String, String>();
        options.put("top_num","1");
        JSONObject res = client.carDetect(path,options);
        System.out.println(res);

        //结果封装进string builder对象中
        StringBuilder sb_out=new StringBuilder();
        sb_out.append("车型:");
        sb_out.append(res.getJSONArray("result").getJSONObject(0).getString("name"));
        sb_out.append("  ");
        sb_out.append("年份:");
        sb_out.append(res.getJSONArray("result").getJSONObject(0).getString("year"));
        sb_out.append("  ");
        sb_out.append("颜色:");
        sb_out.append(res.getString("color_result"));

//        System.out.println(res.toString(2));
        return sb_out.toString();
    }
}
