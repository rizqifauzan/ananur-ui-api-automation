package config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing() // Optional: ignore if .env not found
            .load();

    public static String getEnv(String key) {
        String value = dotenv.get(key);
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Missing required environment variable: " + key);
        }
        return value;
    }
}