package eu.crewmate.sdk.ts.psi;

import com.intellij.psi.tree.IElementType;
import eu.crewmate.sdk.ts.TsLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class TsElementType extends IElementType {

    public TsElementType(@NotNull @NonNls String debugName) {
        super(debugName, TsLanguage.INSTANCE);
    }

}