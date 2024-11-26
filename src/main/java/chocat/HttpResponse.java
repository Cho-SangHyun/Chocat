package chocat;

import java.util.Map;

public class HttpResponse {
    private ResponseStartLine startLine;
    private Map<String, String> headers;
    private String body;

    public HttpResponse(
            ResponseStartLine startLine,
            Map<String, String> headers,
            String body) {
        this.startLine = startLine;
        this.headers = headers;
        this.body = body;
    }

    public String toString() {
        StringBuffer resString = new StringBuffer();
        resString.append(startLine.toString() + "\n");

        for (Map.Entry<String, String> headerField : headers.entrySet()) {
            resString.append(headerField.getKey() + ": "
                    + headerField.getValue() + "\n");
        }

        resString.append("\n" + body);
        return resString.toString();
    }
}
