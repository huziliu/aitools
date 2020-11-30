package zuel.aitools.pojo;

import java.util.Arrays;

public class Sentence {
    private String childList[];

    public Sentence() {
    }

    public Sentence(String[] childList) {
        this.childList = childList;
    }

    public String[] getChildList() {
        return childList;
    }

    public void setChildList(String[] childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "words=" + Arrays.toString(childList) +
                '}';
    }
}
