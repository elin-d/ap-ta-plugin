package eu.crewmate.sdk.tc;

import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

import java.util.ArrayList;
import java.util.List;

public class TcUtil {

    public static List<PsiElement> findVerificationBases(PsiDirectory dir, String filename) {
        List<PsiElement> foundBases = new ArrayList<>();
        for (PsiDirectory vdir : dir.getSubdirectories()) {
            PsiFile baseFile = vdir.findFile(filename + ".tif");
            if (baseFile != null) {
                foundBases.add(baseFile);
            }
        }
        return foundBases;
    }

}
