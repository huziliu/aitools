package zuel.aitools.utils;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

public class uploadUtil {
    public static String uploadFile(CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        //获取上传路径
        String fileName = file.getOriginalFilename();
        String path = request.getServletContext().getRealPath("/upload");
        File realPath = new File(path);
        if (!realPath.exists()) {
            realPath.mkdir();
        }

        //获取输入输出流
        InputStream inputStream = file.getInputStream();
        File target_file=new File(realPath,fileName);
        OutputStream outputStream = new FileOutputStream(target_file);
        String absolutePath = target_file.getAbsolutePath();
        System.out.println(absolutePath);

        //读写文件
        int len = 0;
        byte buffer[] = new byte[1024];
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
            outputStream.flush();
        }

        //关闭流
        inputStream.close();
        outputStream.close();

        return absolutePath;
    }
}
