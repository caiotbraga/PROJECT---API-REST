create table schedule_consultation(

    id bigint not null auto_increment,
    doctor_id bigint not null,
    patient_id bigint not null,
    consultation_date datetime not null,

    primary key(id),
    constraint fk_schedule_consultation_doctor_id foreign key (doctor_id) references doctors(id),
    constraint fk_schedule_consultation_patient_id foreign key (patient_id) references patients(id)
);
