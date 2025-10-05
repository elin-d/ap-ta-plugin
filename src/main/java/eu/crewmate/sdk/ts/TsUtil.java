package eu.crewmate.sdk.ts;

import com.intellij.psi.PsiDirectory;

public class TsUtil {

    public static PsiDirectory findSubDir(PsiDirectory baseDir, String[] subDirs) {
        for (String dir : subDirs) {
            if (baseDir == null) return null;
            baseDir = baseDir.findSubdirectory(dir);
        }
        return baseDir;
    }
}
