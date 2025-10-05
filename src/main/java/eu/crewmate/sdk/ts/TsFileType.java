package eu.crewmate.sdk.ts;

import com.intellij.openapi.fileTypes.LanguageFileType;
import eu.crewmate.sdk.common.TcsIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TsFileType extends LanguageFileType {

    public static final TsFileType INSTANCE = new TsFileType();

    private TsFileType() {
        super(TsLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "TS File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Allplan Testsuite file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "ts";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return TcsIcon.TS_ICON;

    }
}