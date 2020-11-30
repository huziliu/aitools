package zuel.aitools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import zuel.aitools.pojo.Sentence;
import zuel.aitools.service.OCRServiceImp;
import zuel.aitools.service.translateServiceImp;
import zuel.aitools.utils.uploadUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@RestController
public class TranslateController {

    private OCRServiceImp ocrServiceImp;
    private translateServiceImp translateServiceImp;

    @Autowired
    public void setTranslateServiceImp(zuel.aitools.service.translateServiceImp translateServiceImp) {
        this.translateServiceImp = translateServiceImp;
    }

    @Autowired
    public void setServiceImp(OCRServiceImp ocrServiceImp) {
        this.ocrServiceImp = ocrServiceImp;
    }

    @PostMapping("/translateWithFile")
    public String get_file(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        String absolutePath = uploadUtil.uploadFile(file, request);

        ServletContext context=request.getServletContext();
        context.setAttribute("absolutePath",absolutePath);

        return "ok";

    }

    @GetMapping("/translateWithFile")
    public List<Sentence> get_ocr(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        String absolutePath = (String) servletContext.getAttribute("absolutePath");
        return this.ocrServiceImp.getStringArrayList(absolutePath);
    }

    @GetMapping("/translateWord")
    public String get_translate(@RequestParam(value = "word", defaultValue = "") String word){
         return translateServiceImp.getTranslate(word);
        //return "ok";
    }
}
