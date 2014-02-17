package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;

public interface RepositoryFactory {

    DrugRepository createDrugRepository();

    InstrumentRepository createInstrumentRepository();

    OperationRepository createOperationRepository();

    PatientRepository createPatientRepository();
}