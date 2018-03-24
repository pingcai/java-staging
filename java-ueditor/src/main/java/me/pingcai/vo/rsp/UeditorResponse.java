package me.pingcai.vo.rsp;

public class UeditorResponse {

    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";

    private String state;
    private String title;
    private String original;
    private String type;
    private String url;
    private String size;

    private UeditorResponse(){}

    public static UeditorResponse getInstance(boolean isSuccess){
        UeditorResponse u = new UeditorResponse();
        if(isSuccess){
            u.setState(SUCCESS);
        }else {
            u.setState(ERROR);
        }
        return u;
    }

    public String getState() {
        return state;
    }

    private void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
