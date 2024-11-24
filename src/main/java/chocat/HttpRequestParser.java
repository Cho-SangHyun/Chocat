package chocat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestParser {
    private final static String START_LINE_DELIMITER = " ";
    private final static String HEADER_DELIMITER = ": ";
    private final Gson gson;

    public HttpRequestParser() {
        this.gson = new Gson();
    }

    public HttpRequest parse(BufferedReader reader) throws IOException {
        RequestStartLine requestStartLine = parseStartLine(reader);
        Map<String, String> headers = parseHeaders(reader);

        int contentLength = Integer.parseInt((String) headers
                .getOrDefault("Content-Length", "0"));
        Map<String, Object> body = parseBody(reader, contentLength);

        return new HttpRequest(requestStartLine, headers, body);
    }

    private RequestStartLine parseStartLine(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        String[] rawStartLine = line.split(START_LINE_DELIMITER);

        return new RequestStartLine(rawStartLine);
    }

    private Map<String, String> parseHeaders(BufferedReader reader) throws IOException {
        Map<String, String> headers = new HashMap<>();

        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            String[] header = line.split(HEADER_DELIMITER);
            headers.put(header[0], header[1]);
        }

        return headers;
    }

    private Map<String, Object> parseBody(BufferedReader reader, int contentLength) throws IOException {
        if (contentLength <= 0) {
            return new HashMap<>();
        }

        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        char[] bodyContent = new char[contentLength];
        reader.read(bodyContent, 0, contentLength);
        String bodyString = new String(bodyContent);

        return gson.fromJson(bodyString, type);
    }
}
