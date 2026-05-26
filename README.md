# Java-Amazon-Project-GitHub
IronHack Week 13 Final Project

### Ironhack Project :


#### Medicinist / INFO System

The Medicine Planning System is a Java-based application designed for hospitals, pharmacies, and veterinary clinics to manage patient medications and records efficiently.

###### Doctor ( Or Pharmacists/Vet/Nurse )

\- Login username

\- Password

\- Doctor ID    (Unique / Auto generated)

\- Lastname     (Dr. XXXX)

###### Patients ( User )

\- Login username

\- Password

\- Patient ID   (Unique / Auto generated)

\- Lastname     (Mr/Ms/Mrs XXXX)

###### Medicines ( Objects )

\- Medicine ID  (Unique / Auto generated)

\- Medicine Name

\- Medicine Quantity

###### Prescription ( Objects )

\- Prescription ID  (Unique / Auto generated)

\- Related -> doctorID, patientID, medicineID

\- Information

\- Date


### ABSTRACT

The Medicine Planning System is a simple Java-based application designed for hospitals, pharmacies, and veterinary clinics to manage patient medications and records efficiently. The system allows healthcare professionals such as pharmacists, doctors, nurses, and veterinarians to access and manage patient data through an administrative interface. Authorized users can create, edit, and remove medicines from the system, as well as assign or update medications for patients. Administrators can also create or delete patient profiles and add medical instructions or notes. Patients are able to view their assigned medicines and annotations through their own accounts. To maintain privacy and security, patients cannot edit their medications or access information belonging to other patients. The system includes error handling and security features that prevent unauthorized access and invalid actions. This project demonstrates the use of Java programming, object-oriented principles, and access control in a real-world healthcare management application.

### REQUIREMENTS

* A Doctor (Pharmacists/Vets/Nurses) has the option to access patient data (GET)
* A Doctor can add a new patient (POST)
* A Doctor can add a new medicine (POST)
* A Doctor can delete a patient (DELETE)
* A Doctor can delete medicines if it's not assigned to a patient (DELETE)
* A Doctor can add a medicine and prescription to the patients data (PUT)

* Doctor ----< Prescription >---- Patient
                  |
                  v
               Medicine

* Patients can see their assigned medicines (GET)
* Patients cannot access to other patients information
* Patients cannot edit their medicines

* Error messages will be shown for unavailable API URL accessing (WebSecurity SecurityConfig)

### API Documentation


\# 1. Unauthorized Access (Global)

| Field            | Value                                           |
| ---------------- | ----------------------------------------------- |
| Endpoint         | `ANY`                                           |
| Authorization    | Invalid or Missing Token                        |
| Description      | Handles unauthorized API access                 |
| Data In          | `{ "username": "", "password": "" }`            |
| Success Response | `200 OK - Token returned`                       |
| Error Response   | `401 Unauthorized - Wrong username or password` |

\# 2. Get Patient Data (Doctor Only)

| Field            | Value                                    |
| ---------------- | ---------------------------------------- |
| Endpoint         | `GET /patients`                          |
| Authorization    | Doctor Token                             |
| Description      | Returns patient information              |
| Data In          | None                                     |
| Success Response | `200 OK`                                 |
| Data Out         | `{ patientId, lastName, medicines\[] }`  |
| Error Response   | `403 Forbidden - Access denied`          |
| Error Response   | `404 Not Found - Patient does not exist` |

\# 3. Add Patient (Doctor Only)

| Field            | Value                                   |
| ---------------- | --------------------------------------- |
| Endpoint         | `POST /patient`                         |
| Authorization    | Doctor Token                            |
| Description      | Creates a new patient                   |
| Data In          | `{ userName, lastName, password }`      |
| Success Response | `201 Created - Patient added`           |
| Error Response   | `403 Forbidden - Access denied`         |
| Error Response   | `409 Conflict - Patient already exists` |

\# 4. Delete Patient (Doctor Only)

| Field            | Value                               |
| ---------------- | ----------------------------------- |
| Endpoint         | `DELETE /patient`                   |
| Authorization    | Doctor Token                        |
| Description      | Deletes a patient                   |
| Data In          | `{ userName }`                      |
| Success Response | `200 OK - Patient deleted`          |
| Error Response   | `404 Not Found - Patient not found` |
| Error Response   | `403 Forbidden - Access denied`     |

\# 5. Add Medicine (Doctor Only)

| Field            | Value                                     |
| ---------------- | ----------------------------------------- |
| Endpoint         | `POST /medicines`                         |
| Authorization    | Doctor Token                              |
| Description      | Adds a medicine to the system             |
| Data In          | `{ medicineName, medicineQuantity, type }`|
| Success Response | `200 OK - Medicine added`                 |
| Error Response   | `403 Forbidden - Access denied`           |
| Error Response   | `409 Conflict - Medicine already exists`  |

\# 6. Delete Medicine (Doctor Only)

| Field            | Value                                         |
| ---------------- | --------------------------------------------- |
| Endpoint         | `DELETE /medicines`                           |
| Authorization    | Doctor Token                                  |
| Description      | Deletes a medicine                            |
| Data In          | `{ medicineName}`                             |
| Success Response | `200 OK - Medicine deleted`                   |
| Error Response   | `403 Forbidden - Access denied`               |
| Error Response   | `409 Conflict - Medicine assigned to patient` |

\# 7. Assign Medicine To Patient (Doctor Only)

| Field            | Value                                           |
| ---------------- | ----------------------------------------------- |
| Endpoint         | `PUT /patients/{patientId}/medicines`           |
| Authorization    | Doctor Token                                    |
| Description      | Assigns medicine to patient                     |
| Data In          | `{ medicineId, patientId }`                     |
| Success Response | `200 OK - Medicine assigned`                    |
| Error Response   | `404 Not Found - Patient or medicine not found` |
| Error Response   | `403 Forbidden - Access denied`                 |

\# 8. Patient Views Own Medicines (Patient Only)

| Field            | Value                                        |
| ---------------- | -------------------------------------------- |
| Endpoint         | `GET /presctiptions/mymeds`                  |
| Authorization    | Patient Token                                |
| Description      | Returns logged-in patient medicines          |
| Data In          | None                                         |
| Success Response | `200 OK`                                     |
| Data Out         | `\[ { medicine, doctorName, instruction } ]` |
| Error Response   | `403 Forbidden - Access denied`              |

\# 9. Assign Presciption To Patient (Doctor Only)

| Field            | Value                                           |
| ---------------- | ----------------------------------------------- |
| Endpoint         | `PUT /prescription/{patientId}`                 |
| Authorization    | Doctor Token                                    |
| Description      | Assigns insctructions for patient's medicine    |
| Data In          | `{ medicineId, patientId, instruction[] }`      |
| Success Response | `200 OK - Presciption assigned`                 |
| Error Response   | `404 Not Found - Patient or medicine not found` |
| Error Response   | `403 Forbidden - Access denied`                 |

\# 10. Delete Presciption From Patient (Doctor Only)

| Field            | Value                                           |
| ---------------- | ----------------------------------------------- |
| Endpoint         | `DELETE /prescription/{patientId}`              |
| Authorization    | Doctor Token                                    |
| Description      | Deletes insctructions for patient's medicine    |
| Data In          | `{ prescriptionID }`                            |
| Success Response | `200 OK - Presciption removed`                  |
| Error Response   | `404 Not Found - Patient or medicine not found` |
| Error Response   | `403 Forbidden - Access denied`                 |
