package com.example.EMS_v2.services;

import com.example.EMS_v2.dtos.ApplicantDto;
import com.example.EMS_v2.entities.Applicant;
import com.example.EMS_v2.entities.Employee;
import com.example.EMS_v2.entities.Salary;
import com.example.EMS_v2.mappers.ApplicantMapper;
import com.example.EMS_v2.repositories.ApplicantRepo;
import com.example.EMS_v2.repositories.EmployeeRepo;
import com.example.EMS_v2.repositories.PositionRepo;
import com.example.EMS_v2.repositories.SalaryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ApplicantService {

    @Autowired
    private EmployeeRepo employeeRepository;

    @Autowired
    private SalaryRepo salaryRepository;

    @Autowired
    private ApplicantRepo applicantRepository;

    @Autowired
    private PositionRepo positionRepository;

    private final ApplicantMapper applicantMapper;

    public ApplicantService(ApplicantMapper applicantMapper, ApplicantRepo applicantRepository) {
        this.applicantMapper = applicantMapper;
        this.applicantRepository = applicantRepository;
    }

    public Page<ApplicantDto> getAllApplicants(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Applicant> applicants = applicantRepository.findAll(pageable);
        return applicants.map(applicantMapper::toDto);
    }

    @Transactional
    public void convertApplicantToEmployee(Applicant applicant) {
        // ✅ Skip if already converted
        if (applicant.getConvertedToEmployee()) {
            System.out.println("Applicant " + applicant.getName() + " already converted. Skipping...");
            return;
        }

        // ✅ Extra safeguard: check if employee already exists (e.g., same contact or name)
        boolean employeeExists = employeeRepository.existsByContact(applicant.getContact());
        if (employeeExists) {
            System.out.println("Employee with contact " + applicant.getContact() + " already exists. Skipping...");
            applicant.setConvertedToEmployee(true);
            applicantRepository.save(applicant);
            return;
        }

        // ✅ 1️⃣ Create Employee
        Employee employee = Employee.create();
        employee.setName(applicant.getName());
        employee.setContact(applicant.getContact());
        employee.setHireDate(LocalDate.now().toString());
        employee.setPositionId(applicant.getPositionId());
        employee.setPositionTitle(applicant.getPositionTitle());
        // ✅ Retrieve department info from Position
        if (applicant.getPositionId() != null) {
            positionRepository.findById(applicant.getPositionId()).ifPresent(position -> {
                employee.setDepartmentId(position.getDepartmentId());
                employee.setDepartmentName(position.getDepartmentName());
            });
        }
        employee.setNegotiatedSalary(applicant.getExpectedSalary());

        Employee savedEmployee = employeeRepository.save(employee);

        // ✅ 2️⃣ Create Salary
        Salary salary = Salary.create();
        salary.setNegotiatedAmount(savedEmployee.getNegotiatedSalary());
        salary.setWorkDays(22);
        salary.setDaysWorked(0);
        salary.setHoursWorked(0);
        salary.setHoursOverTime(0);
        salary.setEmployeeId(savedEmployee.getId());
        salaryRepository.save(salary);

        // ✅ 3️⃣ Mark applicant as converted
        applicant.setConvertedToEmployee(true);
        applicantRepository.save(applicant);

        System.out.println("✅ Applicant " + applicant.getName() + " converted to Employee successfully.");
    }
}
