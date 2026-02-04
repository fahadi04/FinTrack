package com.project.fintrack.controller;

import com.project.fintrack.service.ExcelService;
import com.project.fintrack.service.ExpenseService;
import com.project.fintrack.service.IncomeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/excel")
@RequiredArgsConstructor
public class ExcelController {
    private final ExcelService excelService;
    private final IncomeService incomeService;
    private final ExpenseService expenseService;

    @GetMapping("/download/income")
    public void downloadIncomeExcel(HttpServletResponse response) throws IOException {
        response.setContentType("/application/vnd.openxmlformats-officedocuments.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=income.xlsx");
        excelService.writeIncomesToExcel(response.getOutputStream(), incomeService.getCurrentMonthIncomesForCurrentUser());

    }

    @GetMapping("/download/expense")
    public void downloadExpenseExcel(HttpServletResponse response) throws IOException {
        response.setContentType("/application/vnd.openxmlformats-officedocuments.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=income.xlsx");
        excelService.writeExpensesToExcel(response.getOutputStream(), expenseService.getCurrentMonthExpensesForCurrentUser());
    }
}
