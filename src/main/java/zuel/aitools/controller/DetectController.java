package zuel.aitools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import zuel.aitools.service.carDetectServiceImp;
import zuel.aitools.service.plantDetectServiceImp;
import zuel.aitools.utils.uploadUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
public class DetectController {

    private carDetectServiceImp carDetectServiceImp;
    private plantDetectServiceImp plantDetectServiceImp;

    @Autowired
    public void setPlantDetectServiceImp(zuel.aitools.service.plantDetectServiceImp plantDetectServiceImp) {
        this.plantDetectServiceImp = plantDetectServiceImp;
    }

    @Autowired
    public void setCarDetectServiceImp(zuel.aitools.service.carDetectServiceImp carDetectServiceImp) {
        this.carDetectServiceImp = carDetectServiceImp;
    }

    @PostMapping("/carWithFile")
    public String get_car(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        String absolutePath = uploadUtil.uploadFile(file, request);
        return carDetectServiceImp.get_car(absolutePath);
    }

    @PostMapping("/plantWithFile")
    public String get_plant(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request) throws IOException {
        String absolutePath = uploadUtil.uploadFile(file, request);
        return plantDetectServiceImp.get_plant(absolutePath);
    }
}
