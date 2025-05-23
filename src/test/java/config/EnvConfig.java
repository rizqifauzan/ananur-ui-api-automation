package config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing() // Optional: ignore if .env not found
            .load();

    public static String getEnv(String key) {
        return dotenv.get(key);
    }
}