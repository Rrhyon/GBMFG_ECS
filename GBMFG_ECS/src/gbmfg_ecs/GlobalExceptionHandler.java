package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description:
 * Date: August 13, 2024
 */
public class GlobalExceptionHandler {

    public String handleException(Exception e) {
        e.printStackTrace();
        return "An error occurred: " + e.getMessage();
    }
}
