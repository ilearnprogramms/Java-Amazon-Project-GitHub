-- CLEAR OLD DATA
DELETE FROM medicines;
DELETE FROM doctors;
DELETE FROM patients;

-- RESET AUTO INCREMENT (optional)
ALTER TABLE medicines AUTO_INCREMENT = 1;
ALTER TABLE doctors AUTO_INCREMENT = 1;
ALTER TABLE patients AUTO_INCREMENT = 1;

-- MEDICINES
INSERT INTO medicines (medicine_name, medicine_category)
VALUES
    ('Paracetamol 500mg 10pcs', 'HUMAN'),
    ('Ibuprofen 400mg 50pcs', 'HUMAN'),
    ('Meloxicam 100mg 5pcs', 'VETERINARY');


-- DOCTORS
INSERT INTO doctors (dr_title, dr_last_name, username, password)
VALUES
    ('RN', 'Tom', 'tommy', '1234'),
    ('PHD', 'Kim', 'kimmy', '1234');


-- PATIENTS
INSERT INTO patients (username, password, patient_title, patient_last_name)
VALUES
    ('bauer', '1234', 'MR', 'Bauer'),
    ('shelby', '1234', 'MRS', 'Shelby');