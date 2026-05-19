package network;

import java.io.Serializable;

public enum CommandType implements Serializable {
    ADD_RECORD,
    UPDATE_SCHEDULE,
    LIST_OBESE_PERSONS,
    COUNT_RECORDS_BY_STATUS,
}
