let currentPage = 0;
let totalPages = 0;
const pageSize = 10;

document.addEventListener("DOMContentLoaded", () => {
  initSidebarNavigation();
  loadEmployees(currentPage);
  loadSalaries(0);
  loadApplicants(0);
  setupBackButton();
});

/* ----------------------------
   Load Employees (with pagination)
----------------------------- */
async function loadEmployees(page = 0) {
  const tableBody = document.getElementById("employeeTableBody");
  tableBody.innerHTML = `<tr><td colspan="6">Loading...</td></tr>`;

  try {
    const response = await fetch(`/api/employees?page=${page}&size=${pageSize}`);
    if (!response.ok) throw new Error("Failed to fetch employees");

    const data = await response.json();
    const employees = data.content || [];
    currentPage = data.number;
    totalPages = data.totalPages;

    if (!employees.length) {
      tableBody.innerHTML = `<tr><td colspan="6">No employees found.</td></tr>`;
      return;
    }

    tableBody.innerHTML = "";
    employees.forEach(emp => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${emp.id}</td>
        <td>${emp.name || "-"}</td>
        <td>${emp.departmentName || "-"}</td>
        <td><strong>${emp.contact || "-"}</strong><br>${emp.email || ""}</td>
        <td>${emp.hireDate || "-"}</td>
        <td><button class="details-btn">View</button></td>
      `;
      row.querySelector(".details-btn").addEventListener("click", () => showEmployeeDetails(emp));
      tableBody.appendChild(row);
    });

    renderPaginationControls();

  } catch (err) {
    console.error("Error loading employees:", err);
    tableBody.innerHTML = `<tr><td colspan="6">Error loading employees.</td></tr>`;
  }
}

/* ----------------------------
   Render Pagination Buttons
----------------------------- */
function renderPaginationControls() {
  let paginationDiv = document.getElementById("pagination-controls");
  if (!paginationDiv) {
    paginationDiv = document.createElement("div");
    paginationDiv.id = "pagination-controls";
    paginationDiv.className = "pagination";
    document.querySelector("#employees-section").appendChild(paginationDiv);
  }

  paginationDiv.innerHTML = `
    <button ${currentPage === 0 ? "disabled" : ""} id="prevPage">Previous</button>
    <span>Page ${currentPage + 1} of ${totalPages}</span>
    <button ${currentPage >= totalPages - 1 ? "disabled" : ""} id="nextPage">Next</button>
  `;

  document.getElementById("prevPage")?.addEventListener("click", () => loadEmployees(currentPage - 1));
  document.getElementById("nextPage")?.addEventListener("click", () => loadEmployees(currentPage + 1));
}

/* ----------------------------
   Show Employee Details / Back Button
----------------------------- */
function showEmployeeDetails(emp) {
  document.getElementById("detail-id").textContent = emp.id || "-";
  document.getElementById("detail-name").textContent = emp.name || "-";
  document.getElementById("detail-department").textContent = emp.departmentName || "-";
  document.getElementById("detail-position").textContent = emp.positionTitle || "-";
  document.getElementById("detail-contact").textContent = emp.contact || "-";
  document.getElementById("detail-hiredate").textContent = emp.hireDate || "-";

  document.getElementById("employees-section").classList.remove("active");
  document.getElementById("employee-details-section").classList.add("active");
}

function setupBackButton() {
  document.getElementById("backToListBtn").addEventListener("click", () => {
    document.getElementById("employee-details-section").classList.remove("active");
    document.getElementById("employees-section").classList.add("active");
  });
}

/* ----------------------------
   Load Salaries (with pagination)
----------------------------- */
async function loadSalaries(page = 0) {
  const tableBody = document.getElementById("salaryTableBody");
  tableBody.innerHTML = `<tr><td colspan="8">Loading...</td></tr>`;

  try {
    const response = await fetch(`/api/payrolls?page=${page}&size=${pageSize}`);
    if (!response.ok) throw new Error("Failed to fetch salary data");

    const data = await response.json();
    const salaries = data.content || [];
    currentPage = data.number;
    totalPages = data.totalPages;

    if (!salaries.length) {
      tableBody.innerHTML = `<tr><td colspan="8">No salary records found.</td></tr>`;
      return;
    }

    tableBody.innerHTML = "";
    salaries.forEach(sal => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${sal.id ?? "-"}</td>
        <td>${sal.employeeId ?? "-"}</td>
        <td>${sal.negotiatedAmount?.toLocaleString() ?? "-"}</td>
        <td>${sal.workDays ?? "-"}</td>
        <td>${sal.daysWorked ?? "-"}</td>
        <td>${sal.hoursWorked ?? "-"}</td>
        <td>${sal.hoursOverTime ?? "-"}</td>
        <td><button class="details-btn">View</button></td>
      `;
      row.querySelector(".details-btn").addEventListener("click", () => showSalaryDetails(sal));
      tableBody.appendChild(row);
    });

    renderSalaryPaginationControls();

  } catch (err) {
    console.error("Error loading salaries:", err);
    tableBody.innerHTML = `<tr><td colspan="8">Error loading salary data.</td></tr>`;
  }
}

/* ----------------------------
   Render Salary Pagination Buttons
----------------------------- */
function renderSalaryPaginationControls() {
  let paginationDiv = document.getElementById("salary-pagination-controls");
  if (!paginationDiv) {
    paginationDiv = document.createElement("div");
    paginationDiv.id = "salary-pagination-controls";
    paginationDiv.className = "pagination";
    document.querySelector("#payroll-section").appendChild(paginationDiv);
  }

  paginationDiv.innerHTML = `
    <button ${currentPage === 0 ? "disabled" : ""} id="salaryPrevPage">Previous</button>
    <span>Page ${currentPage + 1} of ${totalPages}</span>
    <button ${currentPage >= totalPages - 1 ? "disabled" : ""} id="salaryNextPage">Next</button>
  `;

  document.getElementById("salaryPrevPage")?.addEventListener("click", () => loadSalaries(currentPage - 1));
  document.getElementById("salaryNextPage")?.addEventListener("click", () => loadSalaries(currentPage + 1));
}

/* ----------------------------
   Show Salary Details / Back Button
----------------------------- */
function showSalaryDetails(sal) {
  const overlay = document.getElementById("payrollDetails");
  const detailsBody = document.getElementById("payroll-details-body");

  detailsBody.innerHTML = `
    <p><strong>ID:</strong> ${sal.id}</p>
    <p><strong>Employee ID:</strong> ${sal.employee?.id || sal.employeeId || "-"}</p>
    <p><strong>Total Salary:</strong> ${sal.totalSalary?.toLocaleString() || sal.negotiated_amount?.toLocaleString() || "-"}</p>
    <p><strong>Work Days:</strong> ${sal.workDays || sal.work_days || "-"}</p>
    <p><strong>Days Worked:</strong> ${sal.daysWorked || sal.days_worked || "-"}</p>
    <p><strong>Hours Worked:</strong> ${sal.hoursWorked || sal.hours_worked || "-"}</p>
    <p><strong>Overtime (Hours):</strong> ${sal.overtimeHours || sal.hours_overtime || "-"}</p>
  `;

  overlay.classList.remove("hidden");
}

document.getElementById("closePayrollDetails").addEventListener("click", () => {
  document.getElementById("payrollDetails").classList.add("hidden");
});

// Optional: Close overlay when clicking outside the box
document.getElementById("payrollDetails").addEventListener("click", (e) => {
  if (e.target.id === "payrollDetails") {
    e.target.classList.add("hidden");
  }
});

function hidePayrollDetails() {
  document.getElementById("payrollDetails").classList.add("hidden");
  document.getElementById("payroll-section").classList.remove("hidden");
}


/* ----------------------------
   Back Button Logic
----------------------------- */
function setupSalaryBackButton() {
  const backBtn = document.getElementById("salaryBackToListBtn");
  if (backBtn) {
    backBtn.addEventListener("click", () => {
      document.getElementById("salary-details-section").classList.remove("active");
      document.getElementById("salary-section").classList.add("active");
    });
  }
}

/* ----------------------------
   Load Applicants (with pagination)
----------------------------- */
async function loadApplicants(page = 0) {
  const tableBody = document.getElementById("applicantTableBody");
  tableBody.innerHTML = `<tr><td colspan="8">Loading...</td></tr>`;

  try {
    const response = await fetch(`/api/applicants?page=${page}&size=${pageSize}`);
    if (!response.ok) throw new Error("Failed to fetch applicant data");

    const data = await response.json();
    const applicants = data.content || [];
    currentPage = data.number;
    totalPages = data.totalPages;

    if (!applicants.length) {
      tableBody.innerHTML = `<tr><td colspan="8">No applicants records found.</td></tr>`;
      return;
    }

    tableBody.innerHTML = "";
    applicants.forEach(app => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${app.id}</td>
        <td>${app.name || "-"}</td>
        <td>${app.positionTitle || "-"}</td>
        <td><strong>${app.contact || "-"}</strong></td>
        <td>${app.dateApplied || "-"}</td>
        <td>${app.expectedSalary || "-"}</td>
        <td>${app.status || "-"}</td>
        <td><button class="details-btn">View</button></td>
      `;
      row.querySelector(".details-btn").addEventListener("click", () => showApplicantDetails(app));
      tableBody.appendChild(row);
    });

    renderApplicantPaginationControls();

  } catch (err) {
    console.error("Error loading applicants:", err);
    tableBody.innerHTML = `<tr><td colspan="8">Error loading applicant data.</td></tr>`;
  }
}

/* ----------------------------
   Render Applicant Pagination Buttons
----------------------------- */
function renderApplicantPaginationControls() {
  let paginationDiv = document.getElementById("applicant-pagination-controls");
  if (!paginationDiv) {
    paginationDiv = document.createElement("div");
    paginationDiv.id = "applicant-pagination-controls";
    paginationDiv.className = "pagination";
    document.querySelector("#applicant-section").appendChild(paginationDiv);
  }

  paginationDiv.innerHTML = `
    <button ${currentPage === 0 ? "disabled" : ""} id="applicantPrevPage">Previous</button>
    <span>Page ${currentPage + 1} of ${totalPages}</span>
    <button ${currentPage >= totalPages - 1 ? "disabled" : ""} id="applicantNextPage">Next</button>
  `;

  document.getElementById("applicantPrevPage")?.addEventListener("click", () => loadApplicants(currentPage - 1));
  document.getElementById("applicantNextPage")?.addEventListener("click", () => loadApplicants(currentPage + 1));
}


/* ----------------------------
   Sidebar Navigation
----------------------------- */
function initSidebarNavigation() {
  const sidebarItems = document.querySelectorAll(".sidebar-section li");
  const contentSections = document.querySelectorAll(".content-section");

  sidebarItems.forEach(item => {
    item.addEventListener("click", () => {
      sidebarItems.forEach(li => li.classList.remove("active"));
      item.classList.add("active");

      const targetId = item.dataset.target;
      contentSections.forEach(section => {
        section.classList.remove("active");
        if (section.id === targetId) section.classList.add("active");
      });
    });
  });
}
