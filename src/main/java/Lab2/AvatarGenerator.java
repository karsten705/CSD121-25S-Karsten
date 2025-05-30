// Package Declaration
package lab2;
// Declaring package named lab2 which contains related classes.

// Imports from Java Standard Library
import javax.imageio.ImageIO; // Part of the `javax.imageio` package. Provides classes for reading and writing images.
import javax.swing.*;         // Part of the `javax.swing` package. Provides classes for building GUIs.
import java.awt.*;            // Part of the `java.awt` package. Provides classes for creating UIs and for painting graphics and images.
import java.io.IOException;   // From the `java.io` package. Handles I/O exceptions.
import java.io.InputStream;   // From the `java.io` package. Represents input stream of bytes.
import java.net.URI;          // From the `java.net` package. Represents a Uniform Resource Identifier.
import java.net.http.HttpClient; // From the `java.net.http` package. Allows sending HTTP requests and receiving responses.
import java.net.http.HttpRequest; // From the same package. Represents an HTTP request.
import java.net.http.HttpResponse; // Represents an HTTP response, used with HttpClient.

// Class Declaration
public class AvatarGenerator { 
// This defines a public class named `AvatarGenerator`.

    public static void main(String[] args) {
        // main is the entry point of the Java application.
        // args is a reference to an array of Strings, used for command-line arguments.

        // try block: for handling exceptions
        try {
            // Calls a static method `getRandomAvatarStream()` from the AvatarGenerator class.
            // Returns an InputStream object representing the avatar image stream.
            var avatarStream = AvatarGenerator.getRandomAvatarStream();
            // `getRandomAvatarStream` is a static method.
            // `avatarStream` is a variable of type InputStream (reference type).

            // Calls a static method `showAvatar`, passing the avatarStream as an argument.
            AvatarGenerator.showAvatar(avatarStream);
            // `showAvatar` is a static method. It accepts an InputStream and displays it in a GUI.

        } catch (IOException | InterruptedException e) {
            // Catches IOException (I/O operation failed) or InterruptedException (if thread is interrupted).
            e.printStackTrace(); // Instance method of Throwable that prints the exception’s stack trace.
        }
    }

    // Static method to get a randomly generated avatar image as an InputStream
    public static InputStream getRandomAvatarStream() throws IOException, InterruptedException {
        // Array of avatar styles
        String[] styles = {
            "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral",
            "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons",
            "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps",
            "personas", "pixel-art", "pixel-art-neutral"
        };
        // `styles` is a reference type array of Strings (each style is a string literal).

        // Generates a random index and selects a style
        var style = styles[(int)(Math.random() * styles.length)];
        // Math.random() is a static method in java.lang.Math that returns a double between 0.0 and 1.0.
        // (int)(...) is casting to an int (primitive type).
        // styles.length is an instance variable of an array.
        // style is a String (reference type) representing one of the avatar styles.

        // Generates a random seed (used to vary avatar image)
        var seed = (int)(Math.random() * 10000);
        // seed is of primitive type `int`.

        // Construct the avatar URL using formatted string
        var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));
        // String.formatted(...) is an instance method that formats a string using the given arguments.
        // URI.create() is a static method of java.net.URI that returns a URI object.
        // 'uri' is of type URI (reference type).

        // Build an HTTP GET request using the URI
        var request = HttpRequest.newBuilder(uri).build();
        // HttpRequest.newBuilder() is a static method returning an HttpRequest.Builder object.
        // .build() is an instance method that finalizes the HttpRequest object.
        // 'request' is of type HttpRequest (reference type).

        // Create a new HTTP client (constructor call)
        try (var client = HttpClient.newHttpClient()) {
            // HttpClient.newHttpClient() is a static factory method that returns a new HttpClient instance.
            // 'client' is of type HttpClient (reference type).

            // Send the HTTP request and get the response
            var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            // client.send() is an instance method that sends the request and returns an HttpResponse.
            // HttpResponse.BodyHandlers.ofInputStream() is a static method returning a response body handler.
            // 'response' is of type HttpResponse<InputStream> (reference type).

            return response.body();
            // .body() is an instance method of HttpResponse that returns the response body (InputStream).
        }
    }

    // Static method to display the avatar in a GUI
    public static void showAvatar(InputStream imageStream) {
        // imageStream is a parameter of reference type InputStream.

        JFrame frame = new JFrame("PNG Viewer");
        // Constructor call: new JFrame(String title) creates a new window.
        // JFrame is part of javax.swing. Used to create a top-level window.

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // .setDefaultCloseOperation is an instance method of JFrame.
        // JFrame.EXIT_ON_CLOSE is a class constant (class variable) telling the program to exit when closed.

        frame.setResizable(false);
        // .setResizable is an instance method. It disables window resizing.

        frame.setSize(200, 200);
        // .setSize is an instance method. Sets the dimensions of the JFrame.

        frame.getContentPane().setBackground(Color.BLACK);
        // .getContentPane() is an instance method of JFrame that returns the content pane (Container object).
        // .setBackground() is an instance method of Container (from java.awt).
        // Color.BLACK is a static constant from java.awt.Color (class variable).

        try {
            // Load the PNG image from InputStream
            Image image = ImageIO.read(imageStream);
            // ImageIO.read() is a static method that reads an image from an InputStream.
            // 'image' is of type Image (reference type, from java.awt), used to store the loaded avatar image.

            JLabel imageLabel = new JLabel(new ImageIcon(image));
            // Constructor call: new ImageIcon(Image) wraps the image into an icon.
            // Constructor call: new JLabel(Icon) creates a label to display the image.
            // imageLabel is of type JLabel (reference type), used to hold and display the avatar.

            frame.add(imageLabel, BorderLayout.CENTER);
            // .add is an instance method of JFrame that adds a component to the content pane.
            // BorderLayout.CENTER is a static constant from java.awt.BorderLayout (class variable).

        } catch (IOException e) {
            e.printStackTrace();
            // Instance method to print the stack trace for debugging.
        }

        frame.setVisible(true);
        // .setVisible is an instance method of JFrame that makes the frame appear on screen.
    }
}
