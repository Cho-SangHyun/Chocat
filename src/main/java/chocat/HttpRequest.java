package chocat;

import java.util.Map;

public class HttpRequest {
    private RequestStartLine startLine;
    private Map<String, String> headers;
    private Map<String, Object> body;

    public HttpRequest(
            RequestStartLine startLine,
            Map<String, String> headers,
            Map<String, Object> body) {
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

    public Map<String, Object> getBody() {
        return body;
    }
}
