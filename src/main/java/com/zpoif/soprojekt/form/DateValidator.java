package com.zpoif.soprojekt.form;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

class DateValidator implements ConstraintValidator<DateConstraint, String> {

   @Override
   public void initialize(DateConstraint date) {
   }

   @Override
   public boolean isValid(String date, ConstraintValidatorContext ctx) {
      LocalDate today = LocalDate.now();
      LocalDate temp = LocalDate.parse(date);
      return temp.isBefore(today);
   }
}
