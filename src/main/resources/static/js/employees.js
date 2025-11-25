const pageSize = 10;

const paginationState = {
  employees: { currentPage: 0, totalPages: 0 },
  salaries: { currentPage: 0, totalPages: 0 },
  applicants: { currentPage: 0, totalPages: 0 }
};

const sectionConfigs = {
  employees: {
    tableBodyId: "employeeTableBody",
    colSpan: 6,
    emptyText: "No employees found.",
    errorText: "Error loading employees.",
    endpoint: "/api/employees",
    paginationId: "pagination-controls",
    paginationParentSelector: "#employees-section",
    renderRow: emp => `
      <td>${emp.id}</td>
      <td>${emp.name || "-"}</td>
      <td>${emp.departmentName || "-"}</td>
      <td><strong>${emp.contact || "-"}</strong><br>${emp.email || ""}</td>
      <td>${emp.hireDate || "-"}</td>
      <td><button class="details-btn">View</button></td>
    `,
    afterRowRender: (row, emp) => {
      row.querySelector(".details-btn").addEventListener("click", () => showEmployeeDetails(emp));
    }
  },
  salaries: {
    tableBodyId: "salaryTableBody",
    colSpan: 8,
    emptyText: "No salary records found.",
    errorText: "Error loading salary data.",
    endpoint: "/api/payrolls",
    paginationId: "salary-pagination-controls",
    paginationParentSelector: "#payroll-section",
    renderRow: sal => `
      <td>${sal.id ?? "-"}</td>
      <td>${sal.employeeId ?? "-"}</td>
      <td>${sal.negotiatedAmount?.toLocaleString() ?? "-"}</td>
      <td>${sal.workDays ?? "-"}</td>
      <td>${sal.daysWorked ?? "-"}</td>
      <td>${sal.hoursWorked ?? "-"}</td>
      <td>${sal.hoursOverTime ?? "-"}</td>
      <td><button class="details-btn">View</button></td>
    `,
    afterRowRender: (row, sal) => {
      row.querySelector(".details-btn").addEventListener("click", () => showSalaryDetails(sal));
    }
  },
  applicants: {
    tableBodyId: "applicantTableBody",
    colSpan: 8,
    emptyText: "No applicants records found.",
    errorText: "Error loading applicant data.",
    endpoint: "/api/applicants",
    paginationId: "applicant-pagination-controls",
    paginationParentSelector: "#applicant-section",
    renderRow: app => `
      <td>${app.id}</td>
      <td>${app.name || "-"}</td>
      <td>${app.positionTitle || "-"}</td>
      <td><strong>${app.contact || "-"}</strong></td>
      <td>${app.dateApplied || "-"}</td>
      <td>${app.expectedSalary || "-"}</td>
      <td>${app.status || "-"}</td>
      <td><button class="details-btn">View</button></td>
    `,
    afterRowRender: (row, app) => {
      row.querySelector(".details-btn").addEventListener("click", () => showApplicantDetails(app));
    }
  }
};

document.addEventListener("DOMContentLoaded", () => {
  initSidebarNavigation();
  loadEmployees(0);
  loadSalaries(0);
  loadApplicants(0);
  setupBackButton();
});

/* ----------------------------
   Generic pagination loader
----------------------------- */
async function loadSection(sectionKey, page = 0) {
  const config = sectionConfigs[sectionKey];
  const state = paginationState[sectionKey];
  const tableBody = document.getElementById(config.tableBodyId);

  setTableMessage(tableBody, config.colSpan, "Loading...");

  try {
    const response = await fetch(`${config.endpoint}?page=${page}&size=${pageSize}`);
    if (!response.ok) throw new Error(`Failed to fetch ${sectionKey}`);

    const data = await response.json();
    const items = data.content || [];
    state.currentPage = data.number;
    state.totalPages = data.totalPages;

    if (!items.length) {
      setTableMessage(tableBody, config.colSpan, config.emptyText);
      return;
    }

    tableBody.innerHTML = "";
    items.forEach(item => {
      const row = document.createElement("tr");
      row.innerHTML = config.renderRow(item);
      config.afterRowRender?.(row, item);
      tableBody.appendChild(row);
    });

    renderPaginationControls(sectionKey);

  } catch (err) {
    console.error(`Error loading ${sectionKey}:`, err);
    setTableMessage(tableBody, config.colSpan, config.errorText);
  }
}

function loadEmployees(page = 0) {
  return loadSection("employees", page);
}

function loadSalaries(page = 0) {
  return loadSection("salaries", page);
}

function loadApplicants(page = 0) {
  return loadSection("applicants", page);
}

/* ----------------------------
   Render Pagination Buttons
----------------------------- */
function renderPaginationControls(sectionKey) {
  const config = sectionConfigs[sectionKey];
  const state = paginationState[sectionKey];

  let paginationDiv = document.getElementById(config.paginationId);
  if (!paginationDiv) {
    paginationDiv = document.createElement("div");
    paginationDiv.id = config.paginationId;
    paginationDiv.className = "pagination";
    document.querySelector(config.paginationParentSelector).appendChild(paginationDiv);
  }

  paginationDiv.innerHTML = `
    <button class="prev-btn" ${state.currentPage === 0 ? "disabled" : ""}>Previous</button>
    <span>Page ${state.currentPage + 1} of ${state.totalPages}</span>
    <button class="next-btn" ${state.currentPage >= state.totalPages - 1 ? "disabled" : ""}>Next</button>
  `;

  paginationDiv.querySelector(".prev-btn")?.addEventListener("click", () => loadSection(sectionKey, state.currentPage - 1));
  paginationDiv.querySelector(".next-btn")?.addEventListener("click", () => loadSection(sectionKey, state.currentPage + 1));
}

function setTableMessage(tableBody, colSpan, message) {
  tableBody.innerHTML = `<tr><td colspan="${colSpan}">${message}</td></tr>`;
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

function showApplicantDetails(applicant) {
  const overlay = document.getElementById("applicantDetails");
  const detailsBody = document.getElementById("applicant-details-body");
  if (overlay && detailsBody) {
    detailsBody.innerHTML = `
      <p><strong>ID:</strong> ${applicant.id}</p>
      <p><strong>Name:</strong> ${applicant.name || "-"}</p>
      <p><strong>Position:</strong> ${applicant.positionTitle || "-"}</p>
      <p><strong>Contact:</strong> ${applicant.contact || "-"}</p>
      <p><strong>Date Applied:</strong> ${applicant.dateApplied || "-"}</p>
      <p><strong>Expected Salary:</strong> ${applicant.expectedSalary || "-"}</p>
      <p><strong>Status:</strong> ${applicant.status || "-"}</p>
    `;
    overlay.classList.remove("hidden");
  }
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
