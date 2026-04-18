package com.ltc.companymanagementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDto {

    @NotBlank(message = "Mutleq doldurulmalidir")
    @Size(message = "Gosterilen olchu qeder ad yazilmalidir Mes: minimum 3 max 50 simvoldan ibaret",min = 3,max = 50)
    private String fullName;

    @NotNull(message = "Maash daxil edilmelidir, maashsiz ichschi ola bilmez")
    @Positive(message = "Musbet eded olaraq maash daxil olunmalidir")
    private Double salary;

    @NotNull(message = "Departmentin ID-si elave edilmelidir")
    private Long departmentId;
}
