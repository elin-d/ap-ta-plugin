package eu.crewmate.sdk.tc;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import eu.crewmate.sdk.tc.psi.TcTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public class TcCompletionContributor extends CompletionContributor {

    public static final Map<String, String> ImageVerifications = Map.of(
            "image;Region;run;", "Image:Region",
            "image;RegionWCS;run;", "Image:WCS",
            "image;frame;run;", "Image:Frame",
            "image;dialog;run;", "Image:Dialog",
            "image;element;run;", "Image:Element",
            "image-looks-same;Region;run;", "Looks-same:Region",
            "image-looks-same;RegionWCS;run;", "Looks-same:WCS",
            "image-looks-same;frame;run;", "Looks-same:Frame",
            "image-looks-same;dialog;run;", "Looks-same:Dialog",
            "image-looks-same;element;run;", "Looks-same:Element"

    );
    public static final Map<String, String> DataVerifications = Map.of(
            "data;ElementItemExistence;run;", "Data:ElementItemExistence",
            "data;ElementExistence;run;", "Data:ElementExistence",
            "data;ElementProperty;run;", "Data:ElementProperty",
            "data;ElementItemProperty;run;", "Data:ElementItemProperty",
            "data;TextInDialogLine;run;", "Data:TextInDialogLine",
            "file;txt;run;", "File:TXT",
            "file;xml;run;", "File:XML"
    );
    public static final Map<String, String> PerformanceVerifications = Map.of(
            "performance;DurationTime;run;Allplan;", "Perfomance:DurationTime",
            "performance;customtime_Interval", "Performance: CustomtimeInterval"

    );
    private static final Set<String> actionSet = Set.of("click", "run", "move", "movecursor", "right_click",
            "select", "setfocus", "setstate", "left_double_click", "right_double_click", "shift_click", "ctrl_click",
            "type", "check", "uncheck", "toggle", "left_d&d", "left_drag", "left_drop", "hotkey", "activate_click",
            "visible_click", "mouse_over", "mouse_move", "settransparency", "expand", "expandall");

    public TcCompletionContributor() {

        extend(CompletionType.BASIC, PlatformPatterns.psiElement(TcTypes.VALUE),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        actionSet.forEach(x -> resultSet.addElement(LookupElementBuilder.create(x).withIcon(AllIcons.Actions.Run_anything)));
                    }
                }
        );
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(TcTypes.VER_TYPE),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        ImageVerifications.forEach((k, v) -> resultSet.addElement(LookupElementBuilder.create(k)
                                .withPresentableText(v).withIcon(AllIcons.RunConfigurations.TestState.Green2)));
                        DataVerifications.forEach((k, v) -> resultSet.addElement(LookupElementBuilder.create(k)
                                .withPresentableText(v).withIcon(AllIcons.RunConfigurations.TestState.Green2)));
                        PerformanceVerifications.forEach((k, v) -> resultSet.addElement(LookupElementBuilder.create(k)
                                .withPresentableText(v).withIcon(AllIcons.RunConfigurations.TestState.Green2)));

                    }
                }
        );
    }
}