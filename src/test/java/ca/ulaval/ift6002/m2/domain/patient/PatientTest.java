package ca.ulaval.ift6002.m2.domain.patient;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

@RunWith(MockitoJUnitRunner.class)
public class PatientTest {

    private static final int PATIENT_NUMBER = 12345;
    private static final int DEAD_PATIENT_NUMBER = 67890;

    @Mock
    private Prescription prescription;

    private Patient patient;

    @Before
    public void setUp() {
        patient = new Patient(PATIENT_NUMBER);
    }

    @Test
    public void givenNewPatientWhenCountPrescriptionsShouldBeEmpty() {
        int prescriptionsCount = patient.countPrescriptions();

        assertEquals(0, prescriptionsCount);
    }

    @Test
    public void givenPatientWhenAddPrescriptionShouldHaveCountOfOne() {
        patient.receivesPrescription(prescription);
        int prescriptionsCount = patient.countPrescriptions();
        assertEquals(1, prescriptionsCount);
    }

    @Test
    public void givenPatientWhenAddTwoPrescriptionsShouldHaveCountOfTwo() {
        patient.receivesPrescription(prescription);
        patient.receivesPrescription(prescription);

        int prescriptionsCount = patient.countPrescriptions();

        assertEquals(2, prescriptionsCount);
    }

    @Test(expected = DeadPatientException.class)
    public void givenDeadPatientWhenAddingPrescriptionShouldThrow() {
        Patient deadPatient = new Patient(DEAD_PATIENT_NUMBER);
        deadPatient.noteDeath();

        deadPatient.receivesPrescription(prescription);
    }
}
