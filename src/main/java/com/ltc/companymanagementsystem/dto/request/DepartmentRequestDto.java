package com.ltc.companymanagementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestDto {

    @NotBlank(message = "Department adi mutleq daxil olmalidir")
    @Size(min = 2, max = 250,message = "Verilen olchuye gore department adi daxil edilmelidir")
    private String name ;
}
