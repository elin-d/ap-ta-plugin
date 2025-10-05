package eu.crewmate.sdk.tc.psi;

import com.intellij.psi.tree.IElementType;
import eu.crewmate.sdk.tc.TcLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class TcTokenType extends IElementType {

    public TcTokenType(@NotNull @NonNls String debugName) {
        super(debugName, TcLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "TcTokenType." + super.toString();
    }

}
