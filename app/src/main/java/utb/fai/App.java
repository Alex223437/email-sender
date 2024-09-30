package utb.fai;

public class App {

    public static void main(String[] args) {
        // checking arguments
        if (args.length < 5) {
            System.out.println("Usage: java App <smtp_host> <port> <from_email> <to_email> <subject> <message>");
            return;
        }

        String smtpHost = args[0];
        int port = Integer.parseInt(args[1]);
        String fromEmail = args[2];
        String toEmail = args[3];
        String subject = args[4];
        String message = args[5];

        // send email
        try {
            EmailSender sender = new EmailSender(smtpHost, port);
            sender.send(fromEmail, toEmail, subject, message);
            sender.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}