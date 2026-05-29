# Java-Amazon-Project-GitHub
IronHack Week 13 Final Project

### Ironhack Project :


#### Prescriptionist / INFO System

The Medicine Planning System is a Java-based application designed for hospitals, pharmacies, and veterinary clinics to manage patient medications and records efficiently.

###### Doctor ( Or Pharmacists/Vet/Nurse )

\- Login username

\- Password

\- Doctor ID    (Unique / Auto generated)

\- Lastname     (Dr. XXXX)

\- Doctor Title (DVM/MD/PHD/RN)

###### Patients ( User )

\- Login username

\- Password

\- Patient ID   (Unique / Auto generated)

\- Lastname     

\- PatientTitle (Mr/Ms/Mrs/Mx)

###### Medicines ( Objects )

\- Medicine ID  (Unique / Auto generated)

\- Medicine Name

\- Medicine Quantity

###### Prescription ( Objects )

\- Prescription ID  (Unique / Auto generated)

\- Related -> doctorID, patientID, medicineID

\- Description

\- Date


### ABSTRACT

The Medicine Planning System is a simple Java-based application designed for hospitals, pharmacies, and veterinary clinics to manage patient medications and records efficiently. The system allows healthcare professionals such as pharmacists, doctors, nurses, and veterinarians to access and manage patient data through an administrative interface. Authorized users can create, edit, and remove medicines from the system, as well as assign or update medications for patients. Administrators can also create or delete patient profiles and add medical instructions or notes. Patients are able to view their assigned medicines and annotations through their own accounts. To maintain privacy and security, patients cannot edit their medications or access information belonging to other patients. The system includes error handling and security features that prevent unauthorized access and invalid actions. This project demonstrates the use of Java programming, object-oriented principles, and access control in a real-world healthcare management application.

### REQUIREMENTS

* A Doctor (Pharmacists/Vets/Nurses) has the option to access patient data (GET)
  
* A Doctor can add a new doctor (POST)
* A Doctor can add a new patient (POST)
* A Doctor can add a new medicine (POST)
* A Doctor can add a new prescription (POST)
  
* A Doctor can delete a doctor (DELETE)
* A Doctor can delete a patient (DELETE)
* A Doctor can delete a medicine (DELETE)
* A Doctor can delete a prescription (DELETE)
* A Doctor can update a medicine quantity (PUT)
 
* Patients can see their assigned medicines (GET)
* Patients cannot access to other patients information
* Patients cannot edit their medicines

* Error messages will be shown for unavailable API URL accessing (WebSecurity SecurityConfig)

### UML Class Diagramm

<img width="555" height="680" alt="image(2)" src="https://github.com/user-attachments/assets/c640610f-35a5-4bf7-a207-57967af85730" />

### API Documentation

## 1. Unauthorized Access

| Field | Value |
|---|---|
| **Endpoint** | `ANY` |
| **Authorization** | Invalid/Missing Token |
| **Description** | Handles unauthorized API access |
| **Data In** | `{ "username": "", "password": "" }` |
| **Success Response** | `200 OK` |
| **Error Response** | `401 Unauthorized` |

---

## 2. Get Doctor Data

| Field | Value |
|---|---|
| **Endpoint** | `GET /doctors` |
| **Authorization** | Doctor |
| **Description** | Returns doctor information |
| **Data In** | `None` |
| **Data Out** | `{ doctorateTitle, userName, lastName, password }` |
| **Success Response** | `200 OK` |
| **Error Response** | `403 Forbidden` |
| **Error Response** | `404 Not Found` |

---

## 3. Add Doctor

| Field | Value |
|---|---|
| **Endpoint** | `POST /doctor` |
| **Authorization** | Doctor |
| **Description** | Creates a new doctor |
| **Data In** | `{ doctorateTitle, userName, lastName, password }` |
| **Success Response** | `201 Created` |
| **Error Response** | `403 Forbidden` |
| **Error Response** | `409 Conflict` |

---

## 4. Delete Doctor

| Field | Value |
|---|---|
| **Endpoint** | `DELETE /doctor` |
| **Authorization** | Doctor |
| **Description** | Deletes a doctor |
| **Data In** | `{ userName }` |
| **Success Response** | `200 OK` |
| **Error Response** | `404 Not Found` |
| **Error Response** | `403 Forbidden` |

---

## 5. Get Patient Data

| Field | Value |
|---|---|
| **Endpoint** | `GET /patients` |
| **Authorization** | Doctor |
| **Description** | Returns patient information |
| **Data In** | `None` |
| **Data Out** | `{ patientTitle, userName, lastName, password }` |
| **Success Response** | `200 OK` |
| **Error Response** | `403 Forbidden` |
| **Error Response** | `404 Not Found` |

---

## 6. Add Patient

| Field | Value |
|---|---|
| **Endpoint** | `POST /patient` |
| **Authorization** | Doctor |
| **Description** | Creates a new patient |
| **Data In** | `{ patientTitle, userName, lastName, password }` |
| **Success Response** | `201 Created` |
| **Error Response** | `403 Forbidden` |
| **Error Response** | `409 Conflict` |

---

## 7. Delete Patient

| Field | Value |
|---|---|
| **Endpoint** | `DELETE /patient` |
| **Authorization** | Doctor |
| **Description** | Deletes a patient |
| **Data In** | `{ userName }` |
| **Success Response** | `200 OK` |
| **Error Response** | `404 Not Found` |
| **Error Response** | `403 Forbidden` |

---

## 8. Get Medicine Data

| Field | Value |
|---|---|
| **Endpoint** | `GET /medicines` |
| **Authorization** | Doctor |
| **Description** | Returns medicine information |
| **Data In** | `None` |
| **Data Out** | `{ medicineName, medicineQuantity, type(HUMAN or VETERINARY) }` |
| **Success Response** | `200 OK` |
| **Error Response** | `404 Not Found` |
| **Error Response** | `403 Forbidden` |

---

## 9. Add Medicine

| Field | Value |
|---|---|
| **Endpoint** | `POST /medicine` |
| **Authorization** | Doctor |
| **Description** | Adds a new medicine |
| **Data In** | `{ medicineName, medicineQuantity, type(HUMAN or VETERINARY) }` |
| **Success Response** | `201 Created` |
| **Error Response** | `403 Forbidden` |
| **Error Response** | `409 Conflict` |

---

## 10. Update Medicine Quantity

| Field | Value |
|---|---|
| **Endpoint** | `POST /medicine/{medicineID}` |
| **Authorization** | Doctor |
| **Description** | updates a medicine quantity |
| **Data In** | `{ medicineQuantity }` |
| **Success Response** | `201 Updated` |
| **Error Response** | `403 Forbidden` |
| **Error Response** | `409 Conflict` |

---

## 11. Delete Medicine

| Field | Value |
|---|---|
| **Endpoint** | `DELETE /medicine` |
| **Authorization** | Doctor |
| **Description** | Deletes a medicine |
| **Data In** | `{ medicineName }` |
| **Success Response** | `200 OK` |
| **Error Response** | `403 Forbidden` |
| **Error Response** | `409 Conflict` |

---

## 12. Assign Prescription

| Field | Value |
|---|---|
| **Endpoint** | `POST /prescription/{patientID}` |
| **Authorization** | Doctor |
| **Description** | Assigns prescription to patient |
| **Data In** | `{ doctorId, medicineId, patientId, instruction[], date }` |
| **Success Response** | `201 Created` |
| **Error Response** | `404 Not Found` |
| **Error Response** | `403 Forbidden` |

---

## 13. Delete Prescription

| Field | Value |
|---|---|
| **Endpoint** | `DELETE /prescription` |
| **Authorization** | Doctor |
| **Description** | Deletes a prescription |
| **Data In** | `{ prescriptionID }` |
| **Success Response** | `200 OK` |
| **Error Response** | `404 Not Found` |
| **Error Response** | `403 Forbidden` |

---

## 14. View Own Medicines

| Field | Value |
|---|---|
| **Endpoint** | `GET /prescription/mymeds` |
| **Authorization** | Patient |
| **Description** | Returns logged-in patient medicines |
| **Data In** | `None` |
| **Data Out** | `{ doctorId, medicineId, patientId, instruction[], date }` |
| **Success Response** | `200 OK` |
| **Error Response** | `403 Forbidden` |


