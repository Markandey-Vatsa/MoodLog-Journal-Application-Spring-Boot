package net.personalProject.journalApp.Config;

import net.personalProject.journalApp.Services.UserService;
import net.personalProject.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminBootstrap implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Create default admin user if no admin users exist
        try {
            boolean hasAdmin = userService.getAllEntries().stream()
                    .anyMatch(user -> user.getRoles() != null && user.getRoles().contains("ADMIN"));

            if (!hasAdmin) {
                User adminUser = new User();
                adminUser.setUserName("admin");
                adminUser.setPassword("admin123");
                adminUser.setEmail("admin@moodlogger.com");
                adminUser.setSentimentAnalysis(true);

                userService.saveAdmin(adminUser);
                System.out.println("Bootstrap admin user created: admin/admin123");
            } else {
                System.out.println("Admin user already exists, skipping bootstrap");
            }
        } catch (Exception e) {
            System.out.println("Error during admin bootstrap: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
