package Lab2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AvatarGenerator {

    public static void main(String[] args) {

        try {
            var avatarStream = AvatarGenerator.getRandomAvatarStream(); //class method. gets random avatar image
            AvatarGenerator.showAvatar(avatarStream); //class method. shows avatar
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); //instance method. prints stacktrace
        }

    }

    public static InputStream getRandomAvatarStream() throws IOException, InterruptedException {
        // Pick a random style
        String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
        var style = styles[(int)(Math.random() * styles.length)]; //math.random is a class method and styles.length is an instance variable

        // Generate a random seed
        var seed = (int)(Math.random() * 10000); //class method

        // Create an HTTP request for a random avatar
        var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed)); //URI.create is a class method and .formatted is an instance method
        var request = HttpRequest.newBuilder(uri).build(); //.newbuilder is a class method and .build() is an instance method

        // Send the request
        try (var client = HttpClient.newHttpClient()) { // class method
            var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream()); //instance method
            return response.body(); //instance variable
        }
    }

    public static void showAvatar(InputStream imageStream) {
            JFrame frame = new JFrame("PNG Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //class variable
            frame.setResizable(false); //instance method
            frame.setSize(200, 200); //instance method
            frame.getContentPane().setBackground(Color.BLACK); //instance method

            try {
                // Load the PNG image
                Image image = ImageIO.read(imageStream); //class method

                // Create a JLabel to display the image
                JLabel imageLabel = new JLabel(new ImageIcon(image));
                frame.add(imageLabel, BorderLayout.CENTER); //instance method

            } catch (IOException e) {
                e.printStackTrace(); //instance method
            }

            frame.setVisible(true); //instance method
    }
}
