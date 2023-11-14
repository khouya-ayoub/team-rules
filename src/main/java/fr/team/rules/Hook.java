package fr.team.rules;

public enum Hook {
    COMMIT_MSG("commit-msg",
            "#!/bin/bash\n" +
            "#\n" +
            "# Exemple de script d'accroche (hook) pour vérifier le message du journal de commits.\n" +
            "# Appelé par \"git commit\" avec un argument, le nom du fichier\n" +
            "# contenant le message du commit. Le script d'accroche devrait se terminer avec un statut non nul\n" +
            "# après avoir émis un message approprié s'il souhaite interrompre le commit.\n" +
            "# Le script d'accroche est autorisé à modifier le fichier du message du commit.\n" +
            "\n" +
            "# Dans notre cas, nous devons tester le message du commit, qui devrait :\n" +
            "# ==> Commencer par Feat, Release, Fix ou Tech\n" +
            "# * Feat : si le commit est utilisé pour une nouvelle fonctionnalité.\n" +
            "# * Release : utilisé lors de la création d'une nouvelle version (Release).\n" +
            "# * Fix : employé pour corriger un bug ou un problème.\n" +
            "# * Tech : Utilisé à des fins techniques.\n" +
            "\n" +
            "RED='\\033[0;31m'\n" +
            "GREEN='\\033[0;32m'\n" +
            "YELLOW='\\033[0;33m'\n" +
            "BLUE='\\033[0;34m'\n" +
            "MAGENTA='\\033[0;35m'\n" +
            "CYAN='\\033[0;36m'\n" +
            "WHITE='\\033[0;37m'\n" +
            "RESET='\\033[0m'\n" +
            "\n" +
            "LINE_READ=0\n" +
            "REGEX_MSG=\"^(Feat|Fix|Release|Tech) \\((SLPOF-[0-9]+)\\) : .+\"\n" +
            "while read line;\n" +
            "do\n" +
            "  if [[ \"$LINE_READ\" == \"0\" ]]; then\n" +
            "    if ! [[ $line =~ $REGEX_MSG ]]; then\n" +
            "      echo -e \"${RED}#################################################################################\"\n" +
            "      echo -e \"#                                    .                                          #\"\n" +
            "      echo -e \"#                                   / \\\\                                         #\"\n" +
            "      echo -e \"#                                  / ! \\\\                                        #\"\n" +
            "      echo -e \"#                                 /_____\\\\                                       #\"\n" +
            "      echo -e \"#                                                                               #${RESET}\"\n" +
            "      echo -e \"${RED}#${RESET} Votre message de commit ne correspond pas à la spécification !                ${RED}#${RESET}\"\n" +
            "      echo -e \"${RED}#${RESET} Il doit commencer par (${GREEN}Feat${RESET} | ${YELLOW}Fix${RESET} | ${BLUE}Release${RESET} | ${CYAN}Tech${RESET}) suivi de numéro de ticket ${RED}#${RESET}\"\n" +
            "      echo -e \"${RED}#${RESET} entre parenthèses, suivi d'un espace puis deux points et le message           ${RED}#${RESET}\"\n" +
            "      echo -e \"${RED}#${RESET} Exemple : ${GREEN}Feat (SLPOF-0000) : exemple message de commit valide.${RESET}               ${RED}#${RESET}\"\n" +
            "      echo -e \"${RED}#################################################################################${RESET}\"\n" +
            "      exit 1\n" +
            "    fi\n" +
            "    LINE_READ=$(($LINE_READ + 1))\n" +
            "  fi\n" +
            "done < $1"),

    PRE_COMMIT("pre-commit", "");

    private final String scriptName;
    private final String script;

    Hook(String n, String s) {
        this.scriptName = n;
        this.script = s;
    }

    public String getScriptName() {
        return scriptName;
    }

    public String getScript() {
        return script;
    }
}
