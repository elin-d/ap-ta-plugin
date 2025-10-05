package eu.crewmate.sdk.ts.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import eu.crewmate.sdk.ts.TsFileType;

public class TsElementFactory {

    public static TsProperty createProperty(Project project, String name) {
        TsFile file = createFile(project, name);
        return (TsProperty) file.getFirstChild();
    }

    public static TsFile createFile(Project project, String text) {
        String name = "dummy.ts";
        return (TsFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, TsFileType.INSTANCE, text);
    }
}
