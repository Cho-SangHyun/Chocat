package chocat;

import java.util.HashMap;
import java.util.Map;

public class HttpResponseBuilder {
    private final static String DEFAULT_HTTP_VERSION = "HTTP/1.1";
    private ResponseStartLine startLine;
    private Map<String, String> headers;
    private String body;

    public HttpResponseBuilder() {
        this.headers = new HashMap<>();
    }

    public HttpResponseBuilder startLine(String version, StatusCode statusCode) {
        this.startLine = new ResponseStartLine(version, statusCode);
        return this;
    }

    public HttpResponseBuilder startLine(StatusCode statusCode) {
        return this.startLine(DEFAULT_HTTP_VERSION, statusCode);
    }

    public HttpResponseBuilder headers(String key, Object value) {
        this.headers.put(key, value.toString());
        return this;
    }

    public HttpResponseBuilder body(String body) {
        this.body = body;
        return this;
    }

    public HttpResponse build() {
        return new HttpResponse(this.startLine, this.headers, this.body);
    }
}
