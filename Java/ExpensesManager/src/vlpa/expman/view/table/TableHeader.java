package vlpa.expman.view.table;

import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TableHeader extends TableCell {

    public static final Font DEFAULT_HEADER_FONT = Font.font("Arial", FontWeight.BOLD, 14);

    public TableHeader(Object cellValue) {
        super(cellValue);
    }

    public TableHeader(Object cellValue, String... styles) {
        super(cellValue, styles);
    }

    protected void initCellValue() {
        setAlignment(Pos.CENTER);
        Text textValue = new Text(String.valueOf(getCellValue()));
        textValue.setFont(DEFAULT_HEADER_FONT);
        getChildren().add(textValue);
    }
}
