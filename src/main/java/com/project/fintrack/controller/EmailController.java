package com.project.fintrack.controller;

import com.project.fintrack.modal.ProfileModal;
import com.project.fintrack.service.*;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final ExcelService excelService;
    private final IncomeService incomeService;
    private final ExpenseService expenseService;
    private final EmailService emailService;
    private final ProfileService profileService;

    @GetMapping("/income-excel")
    public ResponseEntity<Void> emailIncomeExcel() throws IOException, MessagingException {
        ProfileModal currentProfile = profileService.getCurrentProfile();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        excelService.writeIncomesToExcel(baos, incomeService.getCurrentMonthIncomesForCurrentUser());
        emailService.sendEmailWithAttachment(currentProfile.getEmail(),
                "Your Income Excel Report",
                "Please find attachment your income report",
                baos.toByteArray(), "income.xlsx"
        );
        return ResponseEntity.ok(null);

    }

    @GetMapping("expense-excel")
    public ResponseEntity<Void> emailExpenseExcel() throws IOException, MessagingException {
        ProfileModal currentProfile = profileService.getCurrentProfile();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        excelService.writeExpensesToExcel(baos, expenseService.getCurrentMonthExpensesForCurrentUser());
        emailService.sendEmailWithAttachment(currentProfile.getEmail(),
                "Your Income Excel Report",
                "Please find attachment your income report",
                baos.toByteArray(), "expenses.xlsx"
        );
        return ResponseEntity.ok(null);
    }
}
