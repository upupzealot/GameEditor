package panel.code.editor;

import java.awt.Color;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;

@SuppressWarnings("serial")
public class CodeArea extends RSyntaxTextArea{
	public CodeArea() {
		setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LUA);
		setCodeFoldingEnabled(true);
		
		SyntaxScheme scheme = getSyntaxScheme();
		
		setBackground(new Color(38, 39, 33));
		setForeground(new Color(247, 247, 241));
		setSelectionColor(new Color(73, 72, 62));
		setCurrentLineHighlightColor(new Color(62, 61, 49));
		setCaretColor(new Color(247, 247, 241));
		
		scheme.getStyle(Token.RESERVED_WORD).foreground = new Color(249, 37, 112);
		scheme.getStyle(Token.RESERVED_WORD_2).foreground = new Color(249, 37, 112);
		
		scheme.getStyle(Token.OPERATOR).foreground = new Color(249, 37, 112);
		scheme.getStyle(Token.SEPARATOR).foreground = new Color(249, 37, 112);
		setMatchedBracketBGColor(new Color(62, 61, 49));
		setMatchedBracketBorderColor(new Color(249, 37, 112));
		setAnimateBracketMatching(false);
		
		scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = new Color(228, 217, 115);
		
		revalidate();
		
		setText("function add(a, b)\n	return a + b\nend\n\"hello world\"");
	}
}