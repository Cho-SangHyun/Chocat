package chocat;

import java.util.Map;

public class HttpRequest {
    private RequestStartLine startLine;
    private Map<String, String> headers;
    private String body;

    public HttpRequest(
            RequestStartLine startLine,
            Map<String, String> headers,
            String body) {
        this.startLine = startLine;
        this.headers = headers;
        this.body = body;
    }

    public RequestStartLine getStartLine() {
        return startLine;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
