package eu.crewmate.sdk.tc;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import eu.crewmate.sdk.common.TcsIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class TcColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Paused line", TcSyntaxHighlighter.PAUSE),
            new AttributesDescriptor("Verification", TcSyntaxHighlighter.VERIFICATION),
            new AttributesDescriptor("Procedure", TcSyntaxHighlighter.PROCEDURE),
            new AttributesDescriptor("Procedure name", TcSyntaxHighlighter.PR_NAME),
            new AttributesDescriptor("Element type", TcSyntaxHighlighter.ELEMENT_TYPE),
            new AttributesDescriptor("Element", TcSyntaxHighlighter.ELEMENT_KEY),
            new AttributesDescriptor("Integer, float or coords", TcSyntaxHighlighter.INT_OPTION),
            new AttributesDescriptor("String options", TcSyntaxHighlighter.STR_OPTION),
            new AttributesDescriptor("Comments", TcSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("Bad value", TcSyntaxHighlighter.BAD_CHARACTER)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return TcsIcon.FILE_ICON;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new TcSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "# Move and copy elements with D&D to another TB\n" +
                "# 1. Startdaten kopieren ...\n" +
                "menu;Allplanmain;Projektbezogen oeffnen;click\n" +
                "procedure;TB_set;project;run;1;nicht angewaehlt;\n" +
                "procedure;TB_set;project;run;5;aktiv;\n" +
                "procedure;TB_set;project;run;6;aktiv im hintergrund;\n" +
                "frame;allplan;;Hotkey;o;\n" +
                "toolbar;palette BIMNavigator;Alle Eintraege zuklappen;click\n" +
                "procedure;DialogResize;common;run;PALETTE BIMNavigator;700,900;\n" +
                "\n" +
                "@dialog;palette BIMNavigator;ElementsTreeView;left_double_click;TB6->Öffnungen->Wandpfeiler->0\n" +
                "verification;image;dialog;run;BN20_ExampleScreenshot;PALETTE BIMNavigator;";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "TC Allplan Testcase Script";
    }

}