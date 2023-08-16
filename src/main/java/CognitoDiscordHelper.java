import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class CognitoDiscordHelper {

    private static final String DISCORD_TOKEN = "discord bot token";

    public static void main(String[] args) {
        DiscordApi api = new DiscordApiBuilder()
        .setToken(DISCORD_TOKEN)
        .setAllNonPrivilegedIntents() // Enables all non-privileged intents
        .setAllIntents()    // Enables all privileged intents (use with caution, I used it here because I'm doing research on other alternatives.)
        .login()
        .join();

    System.out.println("Connected as: " + api.getYourself().getName());
    System.out.println("Connected servers: " + api.getServers().size());

    // Set up an event listener for messages
    api.addMessageCreateListener(event -> {
        String content = event.getMessageContent();

        if (content.equals("!downloads")) {
            int estimatedUserCount = CognitoHelper.getEstimatedUserCount();
            event.getChannel().sendMessage("Estimated number of users in Cognito userpool: " + estimatedUserCount);
        }
    });
    }
}
