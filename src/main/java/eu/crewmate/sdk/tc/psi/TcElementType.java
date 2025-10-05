package eu.crewmate.sdk.tc.psi;

import com.intellij.psi.tree.IElementType;
import eu.crewmate.sdk.tc.TcLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class TcElementType extends IElementType {

    public TcElementType(@NotNull @NonNls String debugName) {
        super(debugName, TcLanguage.INSTANCE);
    }

}