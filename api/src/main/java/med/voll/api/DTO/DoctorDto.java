package med.voll.api.DTO;

import med.voll.api.Enums.Specialty;

public record DoctorDto(String name, String email, String phone, String crm, Specialty specialty, AddressDto address) {
}
