package chocat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Chocat {
    private static final int DEFAULT_PORT = 8080;
    private static final String DEFAULT_CHARACTER_SET = "UTF-8";
    private final HttpRequestParser httpRequestParser;
    private final ServerSocket serverSocket;

    public Chocat() {
        httpRequestParser = new HttpRequestParser();
        try {
            this.serverSocket = new ServerSocket(DEFAULT_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        while (true) {
            try {
                // 클라이언트의 연결요청을 받으면 새로운 소켓 생성, 이 소켓을 통해 클라이언트와 1 : 1 통신
                // socket.getLocalPort() = 내가 사용하는 로컬머신 포트
                // socket.getPort() = 상대가 사용하는 머신의 포트
                Socket socket = serverSocket.accept();
                System.out.println("New connection from " + socket.getInetAddress());

                // Socket : 통신담당. 입력을 받는 input, 출력을 하는 output stream을 제공
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                // 클라이언트가 준 입력 출력
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream, DEFAULT_CHARACTER_SET));

                // 요청 파싱
                HttpRequest request = httpRequestParser.parse(reader);

                // 요청 출력
                System.out.println("Method: " + request.getStartLine().getMethod());
                System.out.println("Path: " + request.getStartLine().getPath());
                System.out.println("Version: " + request.getStartLine().getVersion());

                System.out.println("Headers:");
                for (String key : request.getHeaders().keySet()) {
                    System.out.println(key + ": " + request.getHeaders().get(key));
                }

                for (String key : request.getBody().keySet()) {
                    System.out.println(key + ": " + request.getBody().get(key));
                }

                // 클라이언트에게 메시지 전달
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(outputStream, DEFAULT_CHARACTER_SET));
                writer.write("Hello, I am Chocat\n");
                writer.flush();

                // 소켓 닫기
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
