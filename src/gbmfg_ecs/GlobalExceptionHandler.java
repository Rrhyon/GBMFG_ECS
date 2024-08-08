/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
public class GlobalExceptionHandler {

    public String handleException(Exception e) {
        e.printStackTrace();
        return "An error occurred: " + e.getMessage();
    }
}
