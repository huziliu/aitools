package zuel.aitools.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import zuel.aitools.Constants.Constants;
import zuel.aitools.translate.demo.TransApi;

import java.io.UnsupportedEncodingException;

@Service
public class translateServiceImp implements translateService {
    @Override
    public String getTranslate(String word) {
        TransApi api = new TransApi(Constants.APP_ID_Trans, Constants.SECURITY_KEY_Trans);
        String output = null;
        try {
            output = api.getTransResult(word, "auto", "zh");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder sb_out = new StringBuilder();

        //将翻译结果转换为json对象
        JSONObject jsonObject = JSON.parseObject(output);
        JSONArray jsonArray = jsonObject.getJSONArray("trans_result");
        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
        //提取出原文以及译文,并封装在string builder对象中
        String src = jsonObject1.getString("src");
        sb_out.append("单词:");
        sb_out.append(src);
        String dst = jsonObject1.getString("dst");
        sb_out.append("  ");
        sb_out.append("解释:");
        sb_out.append(dst);

        return sb_out.toString();
    }
}
