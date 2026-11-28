package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

/**
 * Shared colors, fonts, and component styling so every screen looks consistent.
 */
public final class AppTheme {

    public static final Color PRIMARY = new Color(25, 55, 95);
    public static final Color PRIMARY_DARK = new Color(18, 40, 70);
    public static final Color ACCENT = new Color(36, 99, 160);
    public static final Color BACKGROUND = new Color(241, 244, 248);
    public static final Color CARD = Color.WHITE;
    public static final Color TEXT = new Color(33, 37, 41);
    public static final Color MUTED = new Color(90, 99, 110);
    public static final Color BORDER = new Color(214, 220, 228);
    public static final Color TABLE_HEADER = new Color(25, 55, 95);
    public static final Color TABLE_STRIPE = new Color(236, 241, 247);
    public static final Color DANGER = new Color(176, 42, 42);
    public static final Color FIELD_BG = Color.WHITE;

    public static final Font FONT_TITLE = font(Font.BOLD, 22);
    public static final Font FONT_SUBTITLE = font(Font.BOLD, 16);
    public static final Font FONT_REGULAR = font(Font.PLAIN, 14);
    public static final Font FONT_BOLD = font(Font.BOLD, 14);
    public static final Font FONT_SMALL = font(Font.PLAIN, 13);
    public static final Font FONT_LARGE = font(Font.BOLD, 26);

    private AppTheme() {
    }

    public static Font font(int style, int size) {
        return new Font("Segoe UI", style, size);
    }

    public static void installLookAndFeelDefaults() {
        UIManager.put("Label.font", FONT_REGULAR);
        UIManager.put("Button.font", FONT_BOLD);
        UIManager.put("TextField.font", FONT_REGULAR);
        UIManager.put("PasswordField.font", FONT_REGULAR);
        UIManager.put("TextArea.font", FONT_REGULAR);
        UIManager.put("ComboBox.font", FONT_REGULAR);
        UIManager.put("Table.font", FONT_REGULAR);
        UIManager.put("TableHeader.font", FONT_BOLD);
        UIManager.put("TitledBorder.font", FONT_BOLD);
        UIManager.put("OptionPane.messageFont", FONT_REGULAR);
        UIManager.put("OptionPane.buttonFont", FONT_BOLD);
        UIManager.put("ToolTip.font", FONT_SMALL);
    }

    public static void stylePanel(JComponent panel) {
        panel.setBackground(BACKGROUND);
        panel.setOpaque(true);
    }

    public static void styleCard(JComponent panel) {
        panel.setBackground(CARD);
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)));
    }

    public static TitledBorder titledBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BORDER),
                title);
        border.setTitleFont(FONT_BOLD);
        border.setTitleColor(PRIMARY);
        return border;
    }

    public static void styleLabel(JLabel label) {
        label.setFont(FONT_REGULAR);
        label.setForeground(TEXT);
    }

    public static void styleMutedLabel(JLabel label) {
        label.setFont(FONT_SMALL);
        label.setForeground(MUTED);
    }

    public static void styleTitle(JLabel label) {
        label.setFont(FONT_TITLE);
        label.setForeground(PRIMARY);
    }

    public static void styleSubtitle(JLabel label) {
        label.setFont(FONT_SUBTITLE);
        label.setForeground(PRIMARY);
    }

    public static void styleField(JTextField field) {
        field.setFont(FONT_REGULAR);
        field.setForeground(TEXT);
        field.setBackground(FIELD_BG);
        field.setBorder(fieldBorder());
        field.setMargin(new Insets(4, 8, 4, 8));
    }

    public static void styleCombo(JComboBox<?> combo) {
        combo.setFont(FONT_REGULAR);
        combo.setBackground(FIELD_BG);
        combo.setForeground(TEXT);
    }

    public static void styleTextArea(JTextArea area) {
        area.setFont(FONT_REGULAR);
        area.setForeground(TEXT);
        area.setBackground(CARD);
        area.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }

    public static void stylePrimaryButton(AbstractButton button) {
        styleButton(button, ACCENT, Color.WHITE);
    }

    public static void styleSecondaryButton(AbstractButton button) {
        styleButton(button, new Color(108, 117, 125), Color.WHITE);
    }

    public static void styleDangerButton(AbstractButton button) {
        styleButton(button, DANGER, Color.WHITE);
    }

    public static void styleNavButton(JButton button) {
        styleButton(button, PRIMARY, Color.WHITE);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        button.setPreferredSize(new Dimension(160, 42));
    }

    public static void styleButton(AbstractButton button, Color background, Color foreground) {
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        button.setFont(FONT_BOLD);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    public static void styleTable(JTable table) {
        table.setFont(FONT_REGULAR);
        table.setForeground(TEXT);
        table.setRowHeight(28);
        table.setGridColor(BORDER);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setSelectionBackground(new Color(201, 220, 239));
        table.setSelectionForeground(TEXT);
        table.setFillsViewportHeight(true);
        table.setBackground(CARD);

        JTableHeader header = table.getTableHeader();
        header.setFont(FONT_BOLD);
        header.setBackground(TABLE_HEADER);
        header.setForeground(Color.WHITE);
        header.setOpaque(true);
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 32));
        header.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbl, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row,
                        column);
                label.setFont(FONT_BOLD);
                label.setBackground(TABLE_HEADER);
                label.setForeground(Color.WHITE);
                label.setOpaque(true);
                label.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
                return label;
            }
        });
    }

    public static void styleReadOnlyValue(JLabel label) {
        label.setFont(FONT_REGULAR);
        label.setForeground(TEXT);
        label.setOpaque(true);
        label.setBackground(new Color(248, 249, 251));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)));
    }

    public static Border fieldBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(6, 8, 6, 8));
    }

    public static void styleChildrenLabels(JComponent parent) {
        for (Component child : parent.getComponents()) {
            if (child instanceof JLabel) {
                styleLabel((JLabel) child);
            }
        }
    }
}
