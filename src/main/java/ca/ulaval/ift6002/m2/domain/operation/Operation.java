package ca.ulaval.ift6002.m2.domain.operation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.operation.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.patient.Patient;

public abstract class Operation {

    public static final int EMPTY_NUMBER = -1;

    private int number;
    private final String description;
    private final Surgeon surgeon;
    private final Date date;
    private final Room room;
    private final OperationStatus status;
    private final Patient patient;
    private final List<Instrument> instruments;

    protected Operation(String description, Surgeon surgeon, Date date, Room room, OperationStatus status,
            Patient patient) {
        this(description, surgeon, date, room, status, patient, EMPTY_NUMBER);

    }

    protected Operation(String description, Surgeon surgeon, Date date, Room room, OperationStatus status,
            Patient patient, Integer number) {
        this.description = description;
        this.surgeon = surgeon;
        this.date = date;
        this.room = room;
        this.status = status;
        this.patient = patient;
        this.instruments = new ArrayList<>();
        this.number = number;
    }

    public void bookmarkInstrumentToStatus(Serial serial, InstrumentStatus status) {
        Instrument instrument = findInstrument(serial);

        instrument.changeTo(status);
    }

    private Instrument findInstrument(Serial serial) {
        for (Instrument instrument : instruments) {
            if (instrument.hasSerial(serial)) {
                return instrument;
            }
        }

        throw new NoSuchElementException("There are no instrument corresponding to: " + serial);
    }

    public boolean has(Instrument instrument) {
        if (instrument.hasASerial()) {
            if (hasAlreadySerial(instrument)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasAlreadySerial(Instrument instrument) {
        for (Instrument current : instruments) {
            if (instrument.hasSameSerial(current)) {
                return true;
            }
        }

        return false;
    }

    public int countInstruments() {
        return instruments.size();
    }

    public void add(Instrument instrument) {
        if (has(instrument)) {
            throw new IllegalStateException("Instrument with same serial already exists: " + instrument);
        }

        if (!isInstrumentElligible(instrument)) {
            throw new InvalidInstrumentException("Instrument '" + instrument + "' is not elligible");
        }

        instruments.add(instrument);
    }

    public void add(Collection<Instrument> instruments) {
        for (Instrument instrument : instruments) {
            add(instrument);
        }
    }

    protected abstract boolean isInstrumentElligible(Instrument instrument);

    public abstract OperationType getType();

    public Collection<Instrument> getInstruments() {
        return instruments;
    }

    public int getNumber() {
        return number;
    }

    public Room getRoom() {
        return room;
    }

    public Surgeon getSurgeon() {
        return surgeon;
    }

    public Date getDate() {
        return date;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDescription() {
        return description;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public void updateNumber(int number) {
        this.number = number;
    }

    public boolean hasNumber() {
        return number != EMPTY_NUMBER;
    }
}
