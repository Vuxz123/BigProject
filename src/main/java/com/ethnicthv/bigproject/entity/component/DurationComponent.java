package com.ethnicthv.bigproject.entity.component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

/**
 * Một Component sẽ khiến Entity biến mất sao một khoảng thời gian.
 */
public class DurationComponent extends Component {
    private Duration duration;

    /**
     * Hàm khởi tạo nhận vào một duration.
     * @param type Kiểu đơn vị thời gian;
     *             được chọn từ enum Type;
     * @param duration Quãng thời gian entity này tồn tại;
     *                 duration là hằng số, sẽ không thể thay đổi được.
     */
    public DurationComponent(Type type, double duration) {
        switch (type) {
            case NANOSECOND -> this.duration = Duration.millis(duration / 1000000);
            case MICROSECOND -> this.duration = Duration.millis(duration / 1000);
            case MILLISECOND -> this.duration = Duration.millis(duration);
            case SECOND -> this.duration = Duration.seconds(duration);
        }
    }

    @Override
    public void onAdded() {
        super.onAdded();
        FXGL.getGameTimer().runOnceAfter(getEntity()::removeFromWorld, duration);
    }

    public enum Type {
        NANOSECOND, MICROSECOND, MILLISECOND, SECOND
    }
}
