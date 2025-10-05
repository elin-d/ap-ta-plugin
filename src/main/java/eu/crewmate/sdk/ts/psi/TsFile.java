package eu.crewmate.sdk.ts.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import eu.crewmate.sdk.ts.TsFileType;
import eu.crewmate.sdk.ts.TsLanguage;
import org.jetbrains.annotations.NotNull;

public class TsFile extends PsiFileBase {

    public TsFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, TsLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return TsFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Ts File";
    }

}
