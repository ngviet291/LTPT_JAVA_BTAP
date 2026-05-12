package entity;

import java.io.Serializable;

public enum DoseStatus implements Serializable {
    SCHEDULED, COMPLETED, CANCELLED, MISSED
}