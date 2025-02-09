ALTER TABLE patients
ADD COLUMN public_place VARCHAR(255);

ALTER TABLE patients
DROP COLUMN street;
