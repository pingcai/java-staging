package me.pingcai.vo.rsp;

public class UeditorResponse {

    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";

    private String status;
    private String url;
    private String size;
    private String original;
    private String type;

    private UeditorResponse(){}

    public static UeditorResponse getInstance(boolean isSuccess){
        UeditorResponse u = new UeditorResponse();
        if(isSuccess){
            u.setStatus(SUCCESS);
        }else {
            u.setStatus(ERROR);
        }
        return u;
    }

    /**
     * 私有
     * @param status
     */
    private void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
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
