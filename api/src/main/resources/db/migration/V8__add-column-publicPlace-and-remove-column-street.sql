ALTER TABLE patient
ADD COLUMN public_place VARCHAR(255);

ALTER TABLE patient
DROP COLUMN street;
