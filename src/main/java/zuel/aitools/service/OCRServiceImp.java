package zuel.aitools.service;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import zuel.aitools.Constants.Constants;
import zuel.aitools.pojo.Sentence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OCRServiceImp implements OCRService{
    @Override
    public List<Sentence> getStringArrayList(String path) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(Constants.APP_ID_OCR, Constants.API_KEY_OCR, Constants.SECRET_KEY_OCR);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
       /* client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理*/

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));

        JSONArray words_result = res.getJSONArray("words_result");
//        JSONArray output_array = new JSONArray();
        List<Sentence> output_list=new ArrayList<Sentence>();

        for (Object o : words_result) {
            JSONObject jsonObject= (JSONObject) o;
            Sentence sentence=new Sentence();
            String words = jsonObject.getString("words");
//            String[] word = words.split(" |\\.|,|\\?|!|;|-");
            String[] word = words.split(" ");
            sentence.setChildList(word);
            output_list.add(sentence);
        }
        return output_list;
       /* String s[]={"1","2"};
        List<Sentence> list=new ArrayList<Sentence>();
        list.add(new Sentence(s));
        return list;*/
    }
}
