package zuel.aitools.service;

import zuel.aitools.pojo.Sentence;

import java.util.List;

public interface OCRService {
    public List<Sentence> getStringArrayList(String path);
}
