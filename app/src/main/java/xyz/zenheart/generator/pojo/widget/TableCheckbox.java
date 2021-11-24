package xyz.zenheart.generator.pojo.widget;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>项目名称: cgenerator </p>
 * <p>描述: 表单 </p>
 * <p>创建时间: 2021/9/8 </p>
 *
 * @author CKM
 * @version v1.0
 */
@Slf4j
@Setter
public class TableCheckbox extends CheckBox implements ObservableValue<TableCheckbox> {

    public TableCheckbox() {
        this.addPropertyListener();
    }

    public TableCheckbox(boolean selected) {
        this.setSelected(selected);
        this.addPropertyListener();
    }

    private void addPropertyListener() {
        this.selectedProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
        });
    }

    @Override
    public void addListener(ChangeListener listener) {
        System.out.println("dddddddddd");
    }

    @Override
    public void removeListener(ChangeListener listener) {

    }

    @Override
    public TableCheckbox getValue() {
        return this;
    }

    @Override
    public void addListener(InvalidationListener listener) {
        System.out.println(getText());
    }

    @Override
    public void removeListener(InvalidationListener listener) {
    }
}