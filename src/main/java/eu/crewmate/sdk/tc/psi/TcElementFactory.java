package eu.crewmate.sdk.tc.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import eu.crewmate.sdk.tc.TcFileType;

public class TcElementFactory {

    public static TcKey createKey(Project project, String name) {
        final TcFile file = createFile(project, "dialog;" + name + ";");
        return ((TcAction) file.getFirstChild().getFirstChild()).getKeyList().get(0);
    }

    public static TcPrname createPrname(Project project, String name) {
        final TcFile file = createFile(project, "procedure;" + name + ";project;run;");
        return ((TcProcedureLine) file.getFirstChild().getFirstChild()).getPrname();
    }

    public static TcFile createFile(Project project, String text) {
        String name = "new.tc";
        return (TcFile) PsiFileFactory.getInstance(project).createFileFromText(name, TcFileType.INSTANCE, text);
    }
}
