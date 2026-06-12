# Java-Prescription-Project-GitHub
IronHack Week 13 Final Project

## Abstract

The Prescription Planning System is an application designed for hospitals, pharmacies, and veterinary clinics to manage patient medications and records efficiently. The system provides a Backend-for-Frontend API that enables healthcare applications to access and manage patient data. Authorized Healthcare staff can create, edit, and remove medicines from the system, as well as create and manage prescriptions for patients. Healthcare staff can create, update, or delete patient and doctor profiles and add medical instructions or notes for patients. Patients then can view their assigned medications and prescriptions through client applications connected to the system. To ensure privacy and security, patients cannot modify their medications or access information belonging to other patients.

## Setup
The project development began with defining the core idea and identifying the system requirements based on real-world healthcare workflows. Before implementation, I created a UML Class Diagram to design the application's structure and relationships between entities such as Patients, Healthcare Staff, Medicines, and Prescriptions.
API endpoints and authorization requirements were planned and documented to establish a clear Backend-for-Frontend (BFF) architecture. Once the project structure was finalized, I designed the database schema and implemented it in MySQL.
Development was carried out incrementally by creating models, repositories, services, controllers, and security configurations, followed by endpoint testing and validation using Postman.

## Technologies Used

### Programming Language
* Java 21
### Frameworks
* Spring Boot 3
* Spring Data JPA
* Spring Security
### Database
* MySQL
### Development Tools
* IntelliJ IDEA
* Postman
* Git
* GitHub
### Architecture & Documentation
* Backend-for-Frontend (BFF) Architecture
* RESTful API Design
* UML Class Diagrams

## Requirements

* Healthcare staff (Doctors, Pharmacists, Nurses, and Veterinarians) can view patient information (GET).
* Healthcare staff can add a new staff member (POST).
* Healthcare staff can add a new patient (POST).
* Healthcare staff can add a new medicine (POST).
* Healthcare staff can create a new prescription (POST).
* Healthcare staff can delete a staff member (DELETE).
* Healthcare staff can delete a patient (DELETE).
* Healthcare staff can delete a medicine (DELETE).
* Healthcare staff can delete a prescription (DELETE).
* Healthcare staff can update medicine quantities (PUT).
* Patients can view their assigned medicines and prescriptions (GET).
* Patients cannot access information belonging to other patients.
* Patients cannot create, edit, or delete medicines or prescriptions.
* Error messages will be shown for unavailable API URL accessing (WebSecurity SecurityConfig)

### UML Class Diagramm

<img width="555" height="680" alt="image(2)" src="https://github.com/user-attachments/assets/c640610f-35a5-4bf7-a207-57967af85730" />

## API Documentation

### 1. Unauthorized Access

| Field | Value |
|---|---|
| **Endpoint** | `ANY` |
| **Authorization** | Invalid/Missing Token |
| **Description** | Handles unauthorized API access |
| **Data In** | `{ "username": "", "password": "" }` |
| **Success Response** | `200 OK` |
| **Error Response** | `401 Unauthorized` |

---

### 2. Get Doctor Data

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

### 3. Add Doctor

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

### 4. Delete Doctor

| Field | Value |
|---|---|
| **Endpoint** | `DELETE /doctor` |
| **Authorization** | Doctor |
| **Description** | Deletes a Doctor |
| **Data In** | `{ userName }` |
| **Success Response** | `200 OK` |
| **Error Response** | `404 Not Found` |
| **Error Response** | `403 Forbidden` |

---

### 5. Get Patient Data

| Field | Value |
|---|---|
| **Endpoint** | `GET /patients` |
| **Authorization** | Doctor |
| **Description** | Returns all patients information |
| **Data In** | `None` |
| **Data Out** | `{ patientTitle, userName, lastName, password }` |
| **Success Response** | `200 OK` |
| **Error Response** | `403 Forbidden` |
| **Error Response** | `404 Not Found` |

---

### 6. Add Patient

| Field | Value |
|---|---|
| **Endpoint** | `POST /patient` |
| **Authorization** | Doctor |
| **Description** | Creates a new Patient |
| **Data In** | `{ patientTitle, userName, lastName, password }` |
| **Success Response** | `201 Created` |
| **Error Response** | `403 Forbidden` |
| **Error Response** | `409 Conflict` |

---

### 7. Delete Patient

| Field | Value |
|---|---|
| **Endpoint** | `DELETE /patient` |
| **Authorization** | Doctor |
| **Description** | Deletes a Patient |
| **Data In** | `{ userName }` |
| **Success Response** | `200 OK` |
| **Error Response** | `404 Not Found` |
| **Error Response** | `403 Forbidden` |

---

### 8. Get Medicine Data

| Field | Value |
|---|---|
| **Endpoint** | `GET /medicines` |
| **Authorization** | Doctor |
| **Description** | Returns medicine informations |
| **Data In** | `None` |
| **Data Out** | `{ medicineName, medicineQuantity, type }` |
| **Success Response** | `200 OK` |
| **Error Response** | `404 Not Found` |
| **Error Response** | `403 Forbidden` |

---

### 9. Add Medicine

| Field | Value |
|---|---|
| **Endpoint** | `POST /medicine` |
| **Authorization** | Doctor |
| **Description** | Adds a new medicine |
| **Data In** | `{ medicineName, medicineQuantity, type) }` |
| **Success Response** | `201 Created` |
| **Error Response** | `403 Forbidden` |
| **Error Response** | `409 Conflict` |

---

### 10. Update Medicine Quantity

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

### 11. Delete Medicine

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

### 12. Assign Prescription

| Field | Value |
|---|---|
| **Endpoint** | `POST /prescription/{patientID}` |
| **Authorization** | Doctor |
| **Description** | Assigns prescription to patient |
| **Data In** | `{ doctorId, medicineId, patientId, instructions[], date }` |
| **Success Response** | `201 Created` |
| **Error Response** | `404 Not Found` |
| **Error Response** | `403 Forbidden` |

---

### 13. Delete Prescription

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

### 14. View Own Medicines

| Field | Value |
|---|---|
| **Endpoint** | `GET /prescription/mymeds` |
| **Authorization** | Patient |
| **Description** | Returns logged-in patient medicines |
| **Data In** | `None` |
| **Data Out** | `{ doctorId, medicineId, patientId, instructions[], date }` |
| **Success Response** | `200 OK` |
| **Error Response** | `403 Forbidden` |

## Future Improvements
The Prescription Planning System can be extended with additional functionality to improve usability, scalability, and healthcare workflow management.

### Planned future improvements include:

* Implementing update endpoints for Doctor and Patient profiles.
* Adding advanced prescription management features, including prescription renewal and expiration dates.
* Creating medicine stock alerts for low inventory levels.
* Implementing audit logs to track modifications made by healthcare staff.
* Supporting file attachments such as medical reports and treatment documents.
* Expanding API coverage to support integration with external healthcare management systems.

## Resources
### The development of this project was supported by a variety of learning resources and guidance:

* IronHack Bootcamp course materials and exercises.
* Guidance and feedback from IronHack Teacher.
* Google Search for technical research and troubleshooting.
* AI tools for learning, debugging, and documentation support.
* YouTube tutorials covering Spring Boot, Spring Security, REST APIs, and MySQL.
* Official Spring Framework and Java documentations.
* Community discussions and technical articles from developer forums.

### Team
* Solo created by @ilearnprogramms
