package utb.fai;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;



public class EmailSender {
    private Socket socket;
    private InputStream input;
    private OutputStream output;

    //connection to the socjket
    public EmailSender(String host, int port) throws UnknownHostException, IOException {
        socket = new Socket(host, port);
        input = socket.getInputStream();
        output = socket.getOutputStream();
        System.out.println("Connected to server");
    }

    
    public void send(String from, String to, String subject, String text) throws IOException {

        String message = "EHLO " + InetAddress.getLocalHost().getHostName() + "\r\n";
        output.write(message.getBytes());
        output.flush();
        readResponse();


        message = "MAIL FROM:<" + from + ">\r\n";
        output.write(message.getBytes());
        output.flush();
        readResponse();

        message = "RCPT TO:<" + to + ">\r\n";
        output.write(message.getBytes());
        output.flush();
        readResponse();

        message = "DATA\r\n";
        output.write(message.getBytes());
        output.flush();
        readResponse();

        message = "Subject: " + subject + "\r\n\r\n" + text + "\r\n.\r\n";
        output.write(message.getBytes());
        output.flush();
        readResponse();

        message = "QUIT\r\n";
        output.write(message.getBytes());
        output.flush();
        readResponse();
    }

    private void readResponse() throws IOException {
        byte[] response = new byte[1024];
        int len = input.read(response);
        System.out.write(response, 0, len);
        System.out.flush();
    }

    /*
     * Sends QUIT and closes the socket
     */
    public void close() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
