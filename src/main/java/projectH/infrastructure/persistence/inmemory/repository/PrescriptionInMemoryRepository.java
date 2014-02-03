package projectH.infrastructure.persistence.inmemory.repository;

import projectH.domain.prescription.Prescription;
import projectH.domain.prescription.PrescriptionRepository;

public class PrescriptionInMemoryRepository implements PrescriptionRepository {

	private boolean empty = true;

	@Override
	public void savePrescription(String patientId, Prescription prescription) {
		this.empty = false;
	}

	public boolean isEmpty() {
		return empty;
	}

}