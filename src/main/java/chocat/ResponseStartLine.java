package chocat;

public class ResponseStartLine extends StartLine {
    private StatusCode statusCode;

    public ResponseStartLine(String version, StatusCode statusCode) {
        this.version = version;
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public String toString() {
        return version + " " + statusCode.getCode() + " " + statusCode.getReasonPhrase();
    }
}
