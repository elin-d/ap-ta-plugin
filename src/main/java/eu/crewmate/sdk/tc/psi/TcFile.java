package eu.crewmate.sdk.tc.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import eu.crewmate.sdk.tc.TcFileType;
import eu.crewmate.sdk.tc.TcLanguage;
import org.jetbrains.annotations.NotNull;

public class TcFile extends PsiFileBase {

    public TcFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, TcLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return TcFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Tc File";
    }

}
