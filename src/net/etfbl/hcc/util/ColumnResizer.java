package net.etfbl.hcc.util;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ColumnResizer {

    private final static Double TOTAL_SIZE = 100.0;

    // Suppresses default constructor, ensuring non-instantiability.
    private ColumnResizer() {}
    
    public static <T> void resize(Double[] sizes, TableView<T> table) {
        ObservableList<TableColumn<T, ?>> columns = table.getColumns();
        if (sizes.length != columns.size()) {
            throw new IllegalArgumentException("Missing column or width");
        }
        Double controlSum = 0.0;
        for (Double s : sizes) {
            controlSum += s;
        }
        if (!controlSum.equals(TOTAL_SIZE)) {
            throw new IllegalArgumentException("Size sum not 100%");
        }
        
        int i = 0;
        for (TableColumn<T, ?> t : columns) {
            t.prefWidthProperty().bind(table.widthProperty().divide(TOTAL_SIZE).multiply(sizes[i]));
            i++;
        }
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

}

