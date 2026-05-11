package network;

import java.io.Serializable;

public enum CommandType implements Serializable {
    ADD_PATIENT,
    GET_NO_TREATMENTS_BY_DOCTORS,
    GET_DOCTORS_BY_DEPARTMENT
}
