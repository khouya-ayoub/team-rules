package fr.team.rules;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        installHookScript(Hook.COMMIT_MSG);
    }

    private static void installHookScript(Hook hook) {
        final String path = ".git/hooks";
        final String scriptPath = path + hook.getScriptName();

        try {
            FileUtils.writeStringToFile(new File(scriptPath), hook.getScript(), "UTF-8");
            File hookFile = new File(scriptPath);
            hookFile.setExecutable(true);
            System.out.println("Script installed successfully");
        } catch (IOException e) {
            System.err.println("Error installing Script : " + e.getMessage());
        }
    }
}
