package com.ruthu.opservice.enumm;

public enum VisitStatus {

        /** Patient has arrived at the reception and is assigned a token. */
    WAITING,

    /** Nurse is checking vitals (BP, Weight, Pulse) before seeing the doctor. */
    TRIAGE,

    /** Patient is currently inside the doctor's consultation room. */
    IN_CONSULTATION,

    /** Doctor has finished the checkup and provided a prescription. */
    COMPLETED,

    /** Patient left before the consultation or the doctor was unavailable. */
    CANCELLED,

    /** Patient is referred to another department or lab for immediate tests. */
    REFERRED

}
