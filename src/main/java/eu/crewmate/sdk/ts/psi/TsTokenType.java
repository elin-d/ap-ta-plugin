package eu.crewmate.sdk.ts.psi;

import com.intellij.psi.tree.IElementType;
import eu.crewmate.sdk.ts.TsLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class TsTokenType extends IElementType {

    public TsTokenType(@NotNull @NonNls String debugName) {
        super(debugName, TsLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "TsTokenType." + super.toString();
    }

}
