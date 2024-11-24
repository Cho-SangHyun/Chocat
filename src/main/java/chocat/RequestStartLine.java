package chocat;

public class RequestStartLine extends StartLine {
    private String method;
    private String path;


    public RequestStartLine(String[] rawStartLine) {
        this.method = rawStartLine[0];
        this.path = rawStartLine[1];
        this.version = rawStartLine[2];
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }
}
