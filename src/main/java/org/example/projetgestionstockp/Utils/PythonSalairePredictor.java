package org.example.projetgestionstockp.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PythonSalairePredictor {

    public static double calculerSalaire(int experience, String service, String role) {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "C:\\Users\\4B\\AppData\\Local\\Programs\\Python\\Python313\\python.exe",  // chemin absolu
                    "C:\\Users\\4B\\IdeaProjects\\projetGestionStockP\\predire_salaire.py",
                    String.valueOf(experience),
                    service,
                    role
            );

            pb.redirectErrorStream(true);
            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        return Double.parseDouble(line.trim()); // car le script Python affiche directement 2900.0
                    } catch (NumberFormatException e) {
                        System.err.println("Non-numeric output: " + line);
                    }
                }
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0.0; // par défaut si erreur
    }
}
