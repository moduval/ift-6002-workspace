package ca.ulaval.ift6002.m2.domain.instrument;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Instrument {

    private InstrumentStatus status;
    private final Serial serial;
    private final Typecode typecode;

    public Instrument(Typecode typecode, InstrumentStatus status, Serial serialNumber) {
        this.status = status;
        this.typecode = typecode;
        this.serial = serialNumber;
    }

    public Instrument(Typecode typecode, InstrumentStatus status) {
        this(typecode, status, new Serial(""));
    }

    public boolean hasSerial(Serial serial) {
        return this.serial.equals(serial);
    }

    public void changeTo(InstrumentStatus status) {
        this.status = status;
    }

    public boolean isAnonymous() {
        return serial.isEmpty();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(status).append(serial).append(typecode).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Instrument other = (Instrument) obj;
        return new EqualsBuilder().append(isAnonymous(), false).append(serial, other.serial).isEquals();
    }

    @Override
    public String toString() {
        return "[" + status + "] Serial:" + serial;
    }

    public InstrumentStatus getStatus() {
        return status;
    }

    public Typecode getTypecode() {
        return typecode;
    }

    public Serial getSerial() {
        return serial;
    }
}
