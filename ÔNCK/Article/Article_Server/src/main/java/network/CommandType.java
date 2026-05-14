package network;

import java.io.Serializable;

public enum CommandType implements Serializable {
    LIST_ARTICLES_BY_YIS,
    ADD_REVIEW,
    UPDATE_REVIEW,
    CALCULATE_REVIEW_DAY,

}
