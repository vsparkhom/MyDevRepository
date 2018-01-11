package vlpa.expman.view.table;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TableCell extends HBox {

    public static final Insets DEFAULT_PADDING = new Insets(0, 5, 0, 5);
    public static final Color DEFAULT_TEXT_COLOR = Color.rgb(80, 80, 80);
    private static final SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

    private Object cellValue;
    private String[] styles;

    public TableCell(Object cellValue) {
        this.cellValue = cellValue;
        initCellValue();
    }

    public TableCell(Object cellValue, String ... styles) {
        this(cellValue);
        this.styles = styles;
        initCellStyles();
    }

    protected void initCellValue() {
        setPadding(DEFAULT_PADDING);
        Text textValue = new Text(String.valueOf(getCellValue()));
        textValue.setFill(DEFAULT_TEXT_COLOR);
        getChildren().add(textValue);
    }

    protected void initCellStyles() {
        for (String style : styles) {
            getStyleClass().add(style);
        }
    }

    public Object getCellValue() {
        if (cellValue instanceof Date) {
            return format.format(cellValue);
        }
        return cellValue;
    }

    public String[] getStyles() {
        return styles;
    }
}
