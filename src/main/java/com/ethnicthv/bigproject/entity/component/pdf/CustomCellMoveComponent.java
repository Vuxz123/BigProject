package com.ethnicthv.bigproject.entity.component.pdf;

import com.almasb.fxgl.core.collection.grid.Cell;
import com.almasb.fxgl.entity.component.Component;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.geometry.Point2D;

public class CustomCellMoveComponent extends Component {
    private int nextCellX;
    private int nextCellY;
    private int offsetX;
    private int offsetY;
    private int cellWidth;
    private int cellHeight;
    private double speed;
    private boolean isAllowRotation = false;
    private ReadOnlyBooleanWrapper isAtDestinationProp = new ReadOnlyBooleanWrapper(true);
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;

    public CustomCellMoveComponent(int offsetX, int offsetY, int cellWidth, int cellHeight, double speed) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.speed = speed;
    }

    public ReadOnlyBooleanProperty atDestinationProperty() {
        return this.isAtDestinationProp.getReadOnlyProperty();
    }

    public boolean isAtDestination() {
        return this.isAtDestinationProp.getValue();
    }

    public boolean isMoving() {
        return !this.isAtDestination();
    }

    public boolean isMovingUp() {
        return this.isMovingUp;
    }

    public boolean isMovingDown() {
        return this.isMovingDown;
    }

    public boolean isMovingLeft() {
        return this.isMovingLeft;
    }

    public boolean isMovingRight() {
        return this.isMovingRight;
    }

    public int getCellWidth() {
        return this.cellWidth;
    }

    public int getCellHeight() {
        return this.cellHeight;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getCellX() {
        return (int)((this.entity.getAnchoredPosition().getX() - offsetX ) / (double)this.cellWidth);
    }

    public int getCellY() {
        return (int)((this.entity.getAnchoredPosition().getY() - offsetY )/ (double)this.cellHeight);
    }

    public void setPositionToCell(Cell cell) {
        this.setPositionToCell(cell.getX(), cell.getY());
    }

    public void setPositionToCell(int cellX, int cellY) {
        int cx = cellX * this.cellWidth + this.cellWidth / 2 + offsetX;
        int cy = cellY * this.cellHeight + this.cellHeight / 2 + offsetY;
        this.entity.setAnchoredPosition(cx, cy);
        this.isAtDestinationProp.setValue(true);
        this.isMovingLeft = false;
        this.isMovingRight = false;
        this.isMovingUp = false;
        this.isMovingDown = false;
    }

    public void moveToCell(Cell cell) {
        this.moveToCell(cell.getX(), cell.getY());
    }

    public void moveToCell(int cellX, int cellY) {
        this.nextCellX = cellX;
        this.nextCellY = cellY;
        this.isAtDestinationProp.set(false);
    }

    public CustomCellMoveComponent allowRotation(boolean isAllowRotation) {
        this.isAllowRotation = isAllowRotation;
        return this;
    }

    @Override
    public void onUpdate(double tpf) {
        if (!this.isAtDestination()) {
            double tpfSpeed = tpf * this.speed;
            int cx = this.nextCellX * this.cellWidth + this.cellWidth / 2 + offsetX;
            int cy = this.nextCellY * this.cellHeight + this.cellHeight / 2 + offsetY;
            Point2D entityAnchoredPosition = this.entity.getAnchoredPosition();
            double dx = (double)cx - entityAnchoredPosition.getX();
            double dy = (double)cy - entityAnchoredPosition.getY();
            if (this.isAllowRotation) {
                this.updateRotation(dx, dy);
            }

            int offsetX = (int)(entityAnchoredPosition.getX() - this.entity.getX());
            int offsetY = (int)(entityAnchoredPosition.getY() - this.entity.getY());
            if (Math.abs(dx) <= tpfSpeed) {
                this.isMovingLeft = false;
                this.isMovingRight = false;
                this.entity.setX(cx - offsetX);
            } else {
                this.isMovingLeft = Math.signum(dx) < 0.0;
                this.isMovingRight = Math.signum(dx) > 0.0;
                this.entity.translateX(tpfSpeed * Math.signum(dx));
            }

            if (Math.abs(dy) <= tpfSpeed) {
                this.isMovingUp = false;
                this.isMovingDown = false;
                this.entity.setY(cy - offsetY);
            } else {
                this.isMovingUp = Math.signum(dy) < 0.0;
                this.isMovingDown = Math.signum(dy) > 0.0;
                this.entity.translateY(tpfSpeed * Math.signum(dy));
            }

            entityAnchoredPosition = this.entity.getAnchoredPosition();
            if ((int)entityAnchoredPosition.getX() == cx && (int)entityAnchoredPosition.getY() == cy) {
                this.setPositionToCell(this.nextCellX, this.nextCellY);
            }

        }
    }

    private void updateRotation(double dx, double dy) {
        if (dx > 0.0) {
            this.entity.setRotation(0.0);
        } else if (dx < 0.0) {
            this.entity.setRotation(180.0);
        } else if (dy > 0.0) {
            this.entity.setRotation(90.0);
        } else if (dy < 0.0) {
            this.entity.setRotation(270.0);
        }

    }

    public boolean isComponentInjectionRequired() {
        return false;
    }
}
